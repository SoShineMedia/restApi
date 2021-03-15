package com.soshinemedia.rest.web;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.soshinemedia.rest.domain.Profile;
import com.soshinemedia.rest.domain.User;
import com.soshinemedia.rest.repository.UserRepository;
import com.soshinemedia.rest.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/rest")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository users;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody AuthenticationRequest data) {

        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username, this.users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody AuthenticationRequest data) {
        String username = data.getUsername();
        String password = data.getPassword();
        String name = data.getName();
        String uuid = data.getUuid();
        BigInteger privateKey;
        String publicKey;
        String filename = new Date().getTime() + "_" + "qr.png";
        //create env variable
        //file:///C:/Users/KenroyWhite/Downloads/2021-03-02-778%20(1).html
        String filepath = null;// = "/qr/"+filename;

        try {
           Optional usr = this.users.findByUsername(username);
            if(!usr.isPresent()){
                try {
                    ECKeyPair keyPair = Keys.createEcKeyPair();
                    privateKey = keyPair.getPrivateKey();
                    publicKey = keyPair.getPublicKey().toString(16);
                    Credentials cs = Credentials.create(privateKey.toString());

                    String address = cs.getAddress();

                    try {
                        BufferedImage bi = generateQRCodeImage(address);  // retrieve image
                        filepath = address+"_"+filename;
                        File outputfile = new File(filepath);
                        ImageIO.write(bi, "png", outputfile);

                    } catch (IOException e) {
                        // handle exception
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    this.users.save(User.builder()
                            .username(username)
                            .keyHash(privateKey.toString(16))
                            .password(this.passwordEncoder.encode(password))
                            .roles(Arrays.asList( "ROLE_USER"))
                            .profile(new Profile(address, String.format("/qr/%s", filepath), name, uuid))
                            .build()
                    );
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchProviderException e) {
                    e.printStackTrace();
                }

            }
            else{
                return ok("The username already exists");
            }

        } catch (AuthenticationException e) {
            return ok("Something went wrong please try again");
        }
        return ok("success");
    }
    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
                barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 300, 300);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    @PostMapping("/signout")
    public ResponseEntity signout(@RequestBody AuthenticationRequest data) {

        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username, this.users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());
            //create eth account
            Profile profile = new Profile();

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            model.put("balance", 12121212);
            //model.put("profile_id", profile.getId());
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
    @PostMapping("/deregister")
    public ResponseEntity deregister(@RequestBody AuthenticationRequest data) {

        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username, this.users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}

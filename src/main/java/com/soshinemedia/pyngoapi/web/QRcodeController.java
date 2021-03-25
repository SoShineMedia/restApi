package com.soshinemedia.pyngoapi.web;

import com.soshinemedia.pyngoapi.repository.FileSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/qr")
public class QRcodeController {
    @Autowired
    FileSystemRepository fileSystemRepository;

    //@GetMapping("/{path}")
    @GetMapping(value = "/{path}", produces = MediaType.IMAGE_PNG_VALUE)
    public FileSystemResource get(@PathVariable("path") String path) throws IOException {
        final FileSystemResource url = fileSystemRepository.findInFileSystem("/qr/" + path);
        return url;//OutputStream getOutputStream
        /*return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);*/
    }
    /*@RequestMapping(value = "/sid", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)

    public void getImage(HttpServletResponse response) throws IOException {

        var imgFile = new ClassPathResource("image/sid.jpg");

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }*/


}

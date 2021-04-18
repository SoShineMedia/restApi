package com.soshinemedia.pyngoapi.config;

import com.soshinemedia.pyngoapi.security.jwt.JwtSecurityConfigurer;
import com.soshinemedia.pyngoapi.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
                .cors().and()
                .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()

                .antMatchers(HttpMethod.POST,"/rest/signin").permitAll()

                .antMatchers(HttpMethod.POST,"/rest/register").permitAll()
                .antMatchers(HttpMethod.GET, "/qr/**").permitAll()
                .antMatchers(HttpMethod.GET, "/v1/rates").permitAll()
                .antMatchers(HttpMethod.GET, "/vehicles/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/vehicles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/v1/offers/**").permitAll()
                .antMatchers(HttpMethod.GET, "/v1/**").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**").permitAll()///swagger-ui.html
                .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                .anyRequest().authenticated()
            .and()
            .apply(new JwtSecurityConfigurer(jwtTokenProvider));
        //@formatter:on
    }


}


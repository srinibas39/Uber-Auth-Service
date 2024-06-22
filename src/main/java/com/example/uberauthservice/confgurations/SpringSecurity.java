package com.example.uberauthservice.confgurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {


    //to reduce 401 unathorized
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeRequests(
                        auth -> auth
                                .requestMatchers("api/v1/auth/signup/*").permitAll()
                                .requestMatchers("api/v1/auth/signin/*").permitAll()

                ).build();
    }


    @Bean
   public PasswordEncoder encoder(){
       return new BCryptPasswordEncoder();
   }
}

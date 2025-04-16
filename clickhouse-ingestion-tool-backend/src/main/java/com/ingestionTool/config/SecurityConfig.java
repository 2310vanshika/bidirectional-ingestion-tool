//package com.ingestionTool.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .cors()
//            .and()
//            .csrf().disable()
//            .authorizeRequests()
//                .anyRequest().permitAll()
//            .and()
//            .httpBasic().disable()       // Disable HTTP Basic Auth
//            .formLogin().disable();      // Disable default login form
//
//        return http.build();
//    }
//}

package com.ingestionTool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class SecurityConfig {

    // ✅ Security filter chain for authorizing requests and enabling CORS
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors() // Enable CORS support
            .and()
            .csrf().disable() // Disable CSRF for APIs (especially when using JWT)
            .authorizeRequests()
            .anyRequest().permitAll(); // Allow all requests (you can secure endpoints later)

        return http.build();
    }

    // ✅ CORS filter to allow frontend (localhost:3000) to access backend
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowCredentials(true); // Allow sending credentials (cookies, headers, etc.)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}

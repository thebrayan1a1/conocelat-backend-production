package com.example.conocemelat.jwt.security;

import com.example.conocemelat.enums.Rol;
import com.example.conocemelat.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled=false,
        securedEnabled = false,
        jsr250Enabled = true
)
//esto va
//@RequiredArgsConstructor

public class SecurityConfiguration {
    //no iba
    @Autowired
    //no iba
    private UserDetailsServiceImpl myUserDetailsService;
    //no iba
    @Autowired
    //no iba
    private JwtResquestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        //agregado, no iba
        //BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //return bCryptPasswordEncoder;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()*/
        http.csrf().disable().authorizeRequests()
                .and().authorizeRequests().antMatchers("/authenticate/**").permitAll()
                .and().authorizeRequests().antMatchers("/login/**").permitAll()
                .and().authorizeRequests().antMatchers("/categories/**").permitAll()
                .and().authorizeRequests().antMatchers("/bookings/**").permitAll()
                .and().authorizeRequests().antMatchers("/characteristics/**").permitAll()
                .and().authorizeRequests().antMatchers("/cities/**").permitAll()
                .and().authorizeRequests().antMatchers("/countries/**").permitAll()
                .and().authorizeRequests().antMatchers("/images/**").permitAll()
                .and().authorizeRequests().antMatchers("/products/**").permitAll()
                .and().authorizeRequests().antMatchers("/roles/**").permitAll()
                .and().authorizeRequests().antMatchers("/users/**").permitAll()
                //.and().authorizeRequests().antMatchers("/users/**").hasAuthority((Rol.ROLE_USER.name()))
                .and().authorizeRequests().antMatchers("/product-characteristics/**").permitAll()
                .and().authorizeRequests().antMatchers("/swagger-ui/index/**").permitAll()
                .and().authorizeRequests().antMatchers("/swagger-ui/**").permitAll()
                .and().authorizeRequests().antMatchers("/v3/api-docs/**").permitAll()
                .and().authorizeRequests().antMatchers("/v2/api-docs/**").permitAll()
                //.antMatchers(UrlMapping.AUTH + UrlMapping.LOGIN).permitAll()
                //.antMatchers(UrlMapping.VALIDATE_JWT).permitAll()
                //.antMatchers("/api/test/**").permitAll()
                //.anyRequest().authenticated()
                .anyRequest().permitAll()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    /*@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*");
                //registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST","PUT", "DELETE");
            }
        };
    }*/

    //no iba
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        //config.setAllowedOrigins(Arrays.asList("http://localhost:4200", "*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        UrlBasedCorsConfigurationSource cors = new UrlBasedCorsConfigurationSource();
        cors.registerCorsConfiguration("/**", config);
        return cors;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
    //no iba

    }


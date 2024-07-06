package com.humber.Week8Security.config;

import com.humber.Week8Security.services.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailService;

    public SecurityConfig(UserDetailsService userDetailService){
        this.userDetailService = userDetailService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/restaurant/home", "login/**", "/", "/register/**").permitAll()
                .requestMatchers("/restaurant/menu/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/restaurant/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        ).formLogin(customLogin -> {
            customLogin.loginPage("/login")
                    .defaultSuccessUrl("/restaurant/home", true)
                    .permitAll();
        })

                .logout(l -> l
                        .logoutUrl("/logout")
                        .permitAll());

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user1 = User.withUsername("user")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin1 = User.withUsername("admin")
//                .password(passwordEncoder().encode("password"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, admin1);
//    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        //1)user detail service
        provider.setUserDetailsService(userDetailService);

        //2) password encoder
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer ignoreResources(){
        return (webSecurity) -> webSecurity
                .ignoring()
                .requestMatchers("/css/**", "/h2-console/**");
    }

}

package com.accenture.hotel.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AuthenticationSuccessHandler successHandler;	

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails user1 = User
                .withUsername("User")
                .password("$2y$12$MuXKFZRv6WSK9XB1YvU/3.nxXayJ6Rb3731vGlScnEKb7t9cKJCwi")
                .roles("USER")
                .build();
        UserDetails user2 = User
                .withUsername("Admin")
                .password("$2y$12$4EscvwlHjYnTJ19y7UKWLOppbiLqEBBYoAuneajlb4QQ5/uqgyoY2")
                .roles("ADMIN")
                .build();
        UserDetails user3 = User
                .withUsername("Staff")
                .password("$2y$12$xe6jlpTzz29tKfGn5UTC7u8S7B5iTK4E./YFragCk3L8dWHluswmO")
                .roles("STAFF")
                .build(); 
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/", "/home", "/**").permitAll()
            .antMatchers("/admin/**").hasAnyRole("ADMIN")
            .antMatchers("/user/**").hasAnyRole("USER")
            .antMatchers("/staff/**").hasAnyRole("STAFF")
            .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login")       
                .usernameParameter("username").passwordParameter("password")
				.successHandler(successHandler)
                .permitAll()
                .failureUrl("/login")              
                .and()
            .logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))            
            	.logoutSuccessUrl("/login")
            	.permitAll()
            .logoutSuccessUrl("/login");     
    }

}

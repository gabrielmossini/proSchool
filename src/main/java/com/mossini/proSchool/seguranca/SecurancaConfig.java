package com.mossini.proSchool.seguranca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurancaConfig {
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityChain(HttpSecurity http) throws Exception{
        http.csrf().disable().
        authorizeRequests().anyRequest().authenticated()
        .and().formLogin().loginPage("/login")
        .and().formLogin().loginProcessingUrl("/login")
        .and().formLogin().defaultSuccessUrl("/").permitAll()
        .and().logout().logoutSuccessUrl("/login");
    return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);		
	}
}

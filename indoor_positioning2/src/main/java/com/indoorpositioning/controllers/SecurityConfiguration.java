package com.indoorpositioning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.indoorpositioning.persistence.UserRepositoryAuthenticationProvider;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
    public UserRepositoryAuthenticationProvider authenticationProvider;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
    	// Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/loginerror").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();

        // Private pages (all other pages)
        // Relacionada con beacons
        http.authorizeRequests().antMatchers("/pairComponents").hasAnyRole("USER,ADMIN");
        http.authorizeRequests().antMatchers("/nameBeacons").hasAnyRole("USER,ADMIN");
        http.authorizeRequests().antMatchers("/unpairComponents").hasAnyRole("USER,ADMIN");
        // Relacionada con receivers y componentes
        http.authorizeRequests().antMatchers("/addComponents").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/manageReceivers").hasAnyRole("ADMIN");
        
        
        // Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/home");
        http.formLogin().failureUrl("/loginerror");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");
        
        // Disable CSRF at the moment
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
    	// Enable default password encoder (mandatory since Spring Security 5 to avoid storing passwords in plain text)
    	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        
    	// Database authentication provider
        auth.authenticationProvider(authenticationProvider);
    }

}

package com.cmc.training.security.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cmc.training.security.CustomUserDetailsService;
import com.cmc.training.security.UserAuthenticationProvider;

/**
 * 
 * @author 
 *
 */
//@Configuration
//@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
    private CustomUserDetailsService userDetailsService;
//
    @Autowired
    private UserAuthenticationProvider accountAuthenticationProvider;
//
//	@Autowired
//	private AuthenticationManager authenticationManager;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/scss/**", "/js/**", "/images/**",
				"/assets/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
			.disable()
			.antMatcher("/api/**")
			.authorizeRequests()
			.antMatchers("oauth/token", "/webjars/**")
			.permitAll()
			.antMatchers(HttpMethod.OPTIONS).permitAll()
			.anyRequest()
			.authenticated();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(accountAuthenticationProvider);
//        auth.parentAuthenticationManager(authenticationManager);
    }

}

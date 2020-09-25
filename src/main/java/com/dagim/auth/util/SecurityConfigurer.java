package com.dagim.auth.util;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	private UserPrincipalDetailService userPrincipalDetailService;
	
	public SecurityConfigurer(UserPrincipalDetailService userPrincipalDetailService) {
			super();
			this.userPrincipalDetailService = userPrincipalDetailService;
	}

//    @Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) 
//	  throws Exception {
//    	
//		// Authentication
//		auth.inMemoryAuthentication()
//			.withUser("user1").password(passwordEncoder().encode("password")).roles("ADMIN").and()
//			.withUser("user2").password(passwordEncoder().encode("password")).roles("USER");
//	}
    
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Authentication
		auth.authenticationProvider(daoAuthenticationProvider());
//		.withUser("Admin").password(passwordEncoder().encode("admin123")).roles("ADMIN").and()
//		.withUser("User").password(passwordEncoder().encode("user123")).roles("USER").and()
//		.withUser("Manager").password(passwordEncoder().encode("man123")).roles("MANAGER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// HTTP Basic AUthorization
		http.authorizeRequests()
		.antMatchers("/h2/**").permitAll()
		.antMatchers("/home").authenticated()
		.antMatchers("/auth-service/users").hasRole("ADMIN")
		.antMatchers("/auth-service/users/role/**").hasRole("MANAGER")
		.antMatchers("/auth-service/users/userid/**").hasAnyRole("USER","MANAGER","ADMIN")
		.antMatchers("/auth-service/user").permitAll()
		.antMatchers("/auth-service").permitAll()
		.and().formLogin()
//		.loginProcessingUrl("/auth-service/signin")
		.loginPage("/login").permitAll()
//		.usernameParameter("nameParam")
//		.passwordParameter("passwordParam")
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login")
		.and()
		.rememberMe()
		.tokenValiditySeconds(2592000).key("MyLoginDetails");
//		.rememberMeParameter("rememberMeCheckBox");
		
		
        http.csrf().disable();
        http.headers().frameOptions().disable();

	}

	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailService);
		
		return daoAuthenticationProvider;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
		

}

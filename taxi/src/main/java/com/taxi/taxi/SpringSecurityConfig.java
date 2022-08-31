package com.taxi.taxi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.taxi.taxi.auth.JWTAuthenticationFilter;
import com.taxi.taxi.auth.JWTAuthorizationFilter;
import com.taxi.taxi.services.JWTService;
import com.taxi.taxi.services.impl.UserServiceImpl;

@EnableGlobalMethodSecurity(securedEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
	private UserServiceImpl userService;
		
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

    @Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{
		build.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	@Autowired
	private JWTService jwtService;

	@Bean
	protected CorsConfigurationSource corsConfigurationSource(){
		UrlBasedCorsConfigurationSource source =
		new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**",
		new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
			.and()
			.authorizeRequests()
            .antMatchers("/users/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))
			.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))		
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
    
}

package com.dong.jwtService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import com.dong.jwtService.Filter.MyFilter1;
import com.dong.jwtService.Filter.MyFilter3;
import com.dong.jwtService.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	
	private final CorsFilter corsFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http.addFilterBefore(new MyFilter3(),BasicAuthenticationFilter.class);
			http.csrf().disable();
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Session을 사용하지 않겠다.
			.and()
			.addFilter(corsFilter) // @CrossOrigin (인증X), 시큐리티필터에 등록이 필요함
			.formLogin().disable() // 기본 form을 사용하겠다.
			.httpBasic().disable() // 
			.addFilter(new JwtAuthenticationFilter(authenticationManager()))
			.authorizeRequests()
			.antMatchers("/api/v1/user/**")
			.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
			.antMatchers("/api/v1/manager/**")
			.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
			.antMatchers("/api/v1/admin/**")
			.access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll();
			
	}
	
	
}

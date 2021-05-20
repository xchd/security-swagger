/*
 * @Project Name: security
 * @File Name: SecurityConfiguration.java
 * @Package Name: com.atom.demo.security.conf
 * @Date: 2021年5月18日上午11:07:52
 * @Creator: xuchuandi
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package com.atom.demo.security.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 */
@Configuration
@EnableWebSecurity
@EnableOpenApi
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//OAuth2 中的 password 模式进行登录，需要明确的提供一个 AuthenticationManager 的 Bean
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.inMemoryAuthentication()
         .withUser("admin")
         .password(passwordEncoder().encode("admin"))
         .roles("admin")
         .and()
         .withUser("java")
         .password(passwordEncoder().encode("123"))
         .roles("admin");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .requestMatchers().antMatchers(HttpMethod.OPTIONS, "/oauth/**")
        .and()
        .csrf().disable().formLogin()
        .and()
        .cors();
	}

	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }
	
	private static final String[] AUTH_WHITELIST = {
			"/swagger-ui.html", "/swagger-ui/index.html", "/swagger-ui/*", "/swagger-resources/**", "/swagger-ui/**",
			"/v2/api-docs", "/v3/api-docs", "/webjars/**" };
}

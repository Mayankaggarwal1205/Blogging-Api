package com.example.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.blog.security.CustomUserDetailService;
import com.example.blog.security.JwtAuthenticationEntryPoint;
import com.example.blog.security.JwtAuthenticationFilter;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;




@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig  {
	
	 public static final RequestMatcher PUBLIC_URLS = request -> {
	        String path = request.getServletPath();
	        return path.startsWith("/api/v1/auth/") ||
	                path.equals("/v3/api-docs") ||
	                path.equals("/v2/api-docs") ||
	                path.startsWith("/swagger-resources/") ||
	                path.startsWith("/swagger-ui/") ||
	                path.startsWith("/webjars/");
	    };
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;


//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer() {
//	    return (web) -> web.debug(securityDebug)
//	      .ignoring()
//	      .antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
//	}
	
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     
    	http
    	.csrf()
    	.disable()
    	.authorizeHttpRequests()
    	.requestMatchers(PUBLIC_URLS).permitAll()
//    	.requestMatchers(HttpMethod.GET).permitAll()
//    	requestMatchers()
//        .requestMatchers(RequestMatcherFactory.matchers(HttpMethod.GET)).permitAll();
//        .requestMatchers(new AntPathRequestMatcher("/v3/api-docs")).permitAll()

    	.anyRequest()
    	.authenticated()
    	.and()
    	.httpBasic();
//    	.exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
//    	.and()
//    	.sessionManagement()
//    	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	
//    	http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
// 
    	http.authenticationProvider(daoAuthenticationProvider());
//    	
    	DefaultSecurityFilterChain build = http.build();
    	
        return build;
    }
    
    public DaoAuthenticationProvider daoAuthenticationProvider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setUserDetailsService(this.customUserDetailService);
    	provider.setPasswordEncoder(passwordEncoder());
    	return provider;
    }
 
    @Bean
	PasswordEncoder passwordEncoder() {
		// TODO Auto-generated method stub
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	} 
	
	
	
}

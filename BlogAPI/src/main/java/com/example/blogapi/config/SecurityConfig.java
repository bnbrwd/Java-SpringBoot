package com.example.blogapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.example.blogapi.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity  //provide method level security
@Component("com.example.blogapi.**")
@EnableWebSecurity
//@EnableWebSecurity
public class SecurityConfig {
	
	//add authentication entry point
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint; //it will execute whenever
	//an unauthorize user tries to accesses the protected resource
	
	@Autowired
	private UserDetailsService userDetailsService; 
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	
	//Basic Authentication
	/* #customize user and password
	spring.security.user.name=Narayan
	spring.security.user.password=12345 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//Basic Authentication
	/*	http.csrf(csrf -> csrf.disable())
		.cors(cors -> cors.disable())
			.authorizeHttpRequests((auth) -> auth.anyRequest()
					.authenticated()
					).httpBasic(Customizer.withDefaults());  */
		
		http.csrf(csrf -> csrf.disable())
		.cors(cors -> cors.disable())
			.authorizeHttpRequests((auth) -> auth.requestMatchers(HttpMethod.GET, "/api/**")
					.permitAll()
					.requestMatchers(HttpMethod.GET, "/api/categories/**")
					.permitAll()
					.requestMatchers("api/auth/**")
					.permitAll()
					.requestMatchers("/swagger-ui/**")
					.permitAll()
					.requestMatchers("/v3/api-docs/**")
					.permitAll()
					.requestMatchers("/webjars/**")
					.permitAll()
					.requestMatchers("/swagger-ui.html")
					.permitAll()
					.requestMatchers("/swagger-resources/**")
					.permitAll()
					.anyRequest().authenticated()
					).httpBasic(Customizer.withDefaults())
					.exceptionHandling(exception -> exception
													.authenticationEntryPoint(authenticationEntryPoint))
					.sessionManagement(session -> session
													.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	//for in memory use to store user credentials
/*	@Bean
	UserDetailsService userDetailsService() {
		//create several users and store in inmemory objects
		
		UserDetails narayan = User.builder()
								.username("narayan")
								.password(passwordEncoder().encode("12345"))
								.roles("USER")
								.build();
		
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("12345"))
				.roles("ADMIN")
				.build();
		
	return new InMemoryUserDetailsManager(narayan,admin);
	} */
}

package com.example.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfigs {
	@Bean
	protected  UserDetailsService userDetailsService(PasswordEncoder encoder ) {
	
		UserDetails admin=User.withUsername("Nani")
		.password(encoder.encode("Nani"))
		.roles("ADMIN")
		.build();
		
		UserDetails user=User.withUsername("Binnu")
				.password(encoder.encode("Binnu"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(admin,user);
	}
	
	protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .requestMatchers("/test") // Specify the URL pattern of the API you want to exclude
                .permitAll() // Allow access without authentication
            .anyRequest()
                .authenticated()
            .and()
            .httpBasic();
    }
//		@Bean
//		public SecurityFilterChain securityFilterChain(HttpSecurity http)  {
//			http
//            .authorizeRequests(authorizeRequests ->
//                authorizeRequests
//                    .antMatchers("/test/**").permitAll()
//                    .anyRequest().authenticated()
//            )
//            .formLogin(formLogin ->
//                formLogin
//                    .loginPage("/login")
//                    .permitAll()
//            )
//            .rememberMe(withDefaults());
//			 
//					
////			return http.csrf().disable()
////					.authorizedHttpRequest()
////					.requestMatchers("/test ").permiAll()
////					.and()
////		
////		
////                   
////                    .authorizedHttpRequest().requestMatcherss("/**").authenticated()
////                    .and().formLogin()
////                    .and().build();
//			
//		}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
		
	

}

package com.raja.guestbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebSecurity
public class GuestbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestbookApplication.class, args);
	}
	
	
	@Configuration
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	static class SecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

		/*
		 * (non-Javadoc)
		 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry)
		 */
		//@Override
		//public void addViewControllers(ViewControllerRegistry registry) {

			// Route requests to /login to the login view (a default one provided by Spring Security)
			//registry.addViewController("/login").setViewName("login");
		//}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication()
			.withUser("admin").password("admin").roles("ADMIN");
			auth.inMemoryAuthentication()
			.withUser("guest").password("guest").roles("USER");
		}

		/*
		 * (non-Javadoc)
		 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
		 */
		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.csrf().disable();

			// Allow all requests on the URI level, configure form login.
			http.authorizeRequests().anyRequest().permitAll() //
					.and().formLogin() //
					.and().logout().logoutSuccessUrl("/").clearAuthentication(true);
		}
	}
	
	
	
}

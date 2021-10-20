package elena.krunic.twiggy.gymSoftware.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import elena.krunic.twiggy.gymSoftware.security.auth.RestAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled= true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService; 
	
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint; 
	
	@Autowired
	private void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
	    return super.userDetailsService();
	}
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
	    AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
	    authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
	    return authenticationTokenFilter;
	 }
	   
	  
	  protected void configure(HttpSecurity http) throws Exception {
	      http.csrf().disable().cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	      		.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
	      		.and().authorizeRequests().antMatchers("/api/users/login").permitAll()
				.antMatchers("api/trainings/all").permitAll()
	      		.antMatchers("api/users/register-coach").permitAll()
	      		.antMatchers("api/users/register-client").permitAll()
	      		.antMatchers("api/users/register-admin").permitAll();

	      http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	  }
}

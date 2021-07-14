package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	DataSource dataSource;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomLoginSuccess();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//Sét đặt dịch vụ để tìm kiếm User trong Database.
		// Và sét đặt PasswordEncoder.
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());

	}
	// nhớ lại mật khẩu
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		
		
		// phân quyền các trang không cần đăng nhập
		http.authorizeRequests().antMatchers("/pc/home","/login","/logout").permitAll();
		// phân quyền các trang cần user
		http.authorizeRequests().antMatchers().hasAnyAuthority("USER","ADMIN");
		// Phan quyen cac trang can admin
		http.authorizeRequests().antMatchers("/pc/pagination","/pc","/pc/home").hasAuthority("ADMIN");
		// Trang thong bao loi 
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/404");
		
		
		
		// xu ly login
		http.authorizeRequests().and().formLogin()
		.loginProcessingUrl("/j_spring_security_check") // đường dẫn submit của form
		.loginPage("/login") // duong dan cua tràn login
		.successHandler(authenticationSuccessHandler())// xử lý khi login thành công thành công
		.failureUrl("/login?Error=true")// đường dẫn lúc login thất bại 
		.usernameParameter("name")// tên input trên form
		.passwordParameter("password") //  tên password
		.and().logout().logoutUrl("/logout")// đường dẫn cảu trang logout
		.logoutSuccessUrl("/logoutSeccess");
		http.authorizeRequests().and().rememberMe().tokenRepository(this.persistentTokenRepository()).tokenValiditySeconds(36000);
		
	}
	
	
}

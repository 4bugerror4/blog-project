package com.bug.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bug.blog.auth.PrincipalDetailsService;

@Configuration // 빈등록
@EnableWebSecurity // 시큐리티 필터 설정
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PrincipalDetailsService principal;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean // IoC 등록 == Spring이 관리
	public BCryptPasswordEncoder encodePWD() {
		
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인
	// 패스워드 가로채기 하는데 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉴로 암호화해서 DB에 있는 해쉬랑 비교
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principal).passwordEncoder(encodePWD());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // csrf 토큰 비활성화
			.authorizeRequests()
			.antMatchers("/", "/auth/**", "/css/**", "/js/**", "/image/**").permitAll() // 설정한 주소로 요청오는것은 인증없이 가능
			.anyRequest() // 그 외 모든 요청은
			.authenticated() // 인증이 필요함
		.and()
			.formLogin()
			.loginPage("/auth/loginForm") // 인증이 필요한 곳은 해당 주소로 옮겨 짐
			.loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로챈다.
			.defaultSuccessUrl("/"); // 로그인이 되었다면 해당 주소로 옮겨짐
	}

}

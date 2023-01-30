package com.bug.blog.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bug.blog.domain.RoleType;
import com.bug.blog.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
@Getter
@AllArgsConstructor
public class PrincipalDetails implements UserDetails {

	private User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> auth = new ArrayList<>();
		auth.add(new GrantedAuthority() {

			@Override
			public String getAuthority() {
				return "" + RoleType.ROLE_USER; // enum 타입이여서 String 타입으로 "" 변경함
			}
		});

		return auth;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// 계정이 만료되지 않았는지 리턴한다. (true: 만료 안됨)
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정이 잠겨있지 않았는지 리턴한다. (true: 잠기지 않음)
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호가 만료되지 않았는지 리턴한다. (true: 만료 안됨)
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 계정이 활성화(사용가능)인지 리턴한다. (true: 활성화)
		return true;
	}

}

package me.chanwoong.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
// UserDetails를 상속해야 인증 객체로 사용이 가능
// UserDetails는 사용자 인증 정보를 담아오는 인터페이스
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    // 로그인에 사용되는 id
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Builder
    public User(String email, String password, String auth) {
        this.email = email;
        this.password = password;
    }

    // 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    // 사용자 id 반환(고유 값)
    @Override
    public String getUsername() {
        return email;
    }

    // 사용자 패스워드 반환
    @Override
    public String getPassword() {
        return password;
    }

    // 계정 만료 여부 반환
    // true인 경우, 만료가 아님을 의미
    @Override
    public boolean isAccountNonExpired() {
        // 만료 확인 로직
        return true;
    }

    // 계정 잠금 여부 반환
    // true인 경우, 안 잠겼음을 의미
    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금 확인 로직
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 패스워드 만료 확인 로직
        return true;
    }

    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        // 계정이 사용 가능 확인 로직
        return true;
    }
}

package me.chanwoong.springbootdeveloper.config.jwt;

import io.jsonwebtoken.Jwts;
import me.chanwoong.springbootdeveloper.domain.User;
import me.chanwoong.springbootdeveloper.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProperties jwtProperties;

    @DisplayName("generateToken(): 유저 정보와 만료 기간을 전달해 토큰 생성")
    @Test
    void generateToken() {
        // given
        // 임의 유저 저장
        User testUser = userRepository.save(User.builder()
                .email("testUser@email.com")
                .password("test")
                .build());

        // when
        // 임의 유저 대상으로 JWT 토큰 생성
        String token = tokenProvider.generateToken(testUser, Duration.ofDays(14));

        // then
        // 임의 유저의 ID와 JWT 파싱한 ID 일치 확인
        Long userId = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);

        assertThat(userId).isEqualTo(testUser.getId());
    }

    @DisplayName("validToken(): 만료된 토큰인 경우 요효성 검증")
    @Test
    void validToken_invaildToken() {
        // given
        // 만료된 토큰 생성
        String token = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
                .build()
                .createToken(jwtProperties);

        // when
        // 토큰 유효성 검사
        boolean result = tokenProvider.validToken(token);

        // then
        // 결과가 false인지 확인
        assertThat(result).isFalse();
    }

    @DisplayName("validToken(): 유효한 토큰인 경우 유효성 검증")
    @Test
    void validToken_vaildToken() {
        // given
        String token = JwtFactory.withDefaultValues()
                .createToken(jwtProperties);

        // when
        boolean result = tokenProvider.validToken(token);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("getAuthentication(): 토큰 기반으로 인증정보를 가져올 수 있다.")
    @Test
    void getAuthentication() {
        // given
        String userEmail = "user@email.com";
        String token = JwtFactory.builder()
                .subject(userEmail)
                .build()
                .createToken(jwtProperties);

        // when
        Authentication authentication = tokenProvider.getAuthentication(token);

        // then
        assertThat(((UserDetails) authentication.getPrincipal()).getUsername()).isEqualTo(userEmail);
        System.out.println("인증 정보 >>> " + ((UserDetails) authentication.getPrincipal()).getUsername());
    }

    @DisplayName("getUserId(): 토큰으로 유저 ID를 가져올 수 있다.")
    @Test
    void getUserId() {
        // given
        Long userId = 1L;
        String token = JwtFactory.builder()
                .claims(Map.of("id", userId))
                .build()
                .createToken(jwtProperties);

        // when
        Long userIdByToken = tokenProvider.getUserId(token);

        // then
        assertThat(userIdByToken).isEqualTo(userId);
    }
}
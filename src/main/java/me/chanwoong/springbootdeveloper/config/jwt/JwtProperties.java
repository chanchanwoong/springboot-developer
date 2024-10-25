package me.chanwoong.springbootdeveloper.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
// ConfigureationProperties를 통해 application.yml에 접근 후,
// jwt에 일치하는 하위 데이터를 객체로 매핑해준다.
@ConfigurationProperties("jwt")
public class JwtProperties {

    private String issuer;
    private String secretKey;
}

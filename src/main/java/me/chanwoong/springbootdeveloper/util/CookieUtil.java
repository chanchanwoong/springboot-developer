package me.chanwoong.springbootdeveloper.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.SerializationUtils;
import org.springframework.web.util.WebUtils;

import java.util.Base64;

public class CookieUtil {

    // 이름, 값, 만료 기간을 입력받아 HTTP Response에 쿠키를 추가
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);

        // "/" 이하는 전부 쿠키가 유효하도록 설정
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);

        response.addCookie(cookie);
    }

    // 쿠키 이름을 입력받아 쿠키 삭제
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        // 쿠키 배열 가져오기
//        Cookie[] cookies = request.getCookies();
//
//        if (cookies == null) return;

        // 만료 시간을 0으로 초기화하여 삭제
//        for (Cookie cookie : cookies) {
//            if (name.equals(cookie.getName())) {
//                cookie.setValue("");
//                cookie.setPath("/");
//                cookie.setMaxAge(0);
//                response.addCookie(cookie);
//            }
//        }

        // WebUtils을 이용하여 보다 가독성있게 쿠키 삭제
        Cookie cookie = WebUtils.getCookie(request, name);

        if (cookie != null) {
            cookie.setValue("");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    // 객체를 직렬화하여 쿠키 값으로 변환
    public static String serialize(Object obj) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(obj));
    }

    // 쿠키 역직렬화하여 객체로 변환
    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(
                SerializationUtils.deserialize(
                        Base64.getUrlDecoder().decode(cookie.getValue())
                )
        );
    }
}

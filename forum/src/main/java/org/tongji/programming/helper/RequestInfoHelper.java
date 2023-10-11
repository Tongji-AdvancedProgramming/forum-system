package org.tongji.programming.helper;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 提供和Http请求有关的工具函数
 *
 * @author cinea
 */
public class RequestInfoHelper {
    private static final List<String> IP_HEADERS = Arrays.asList("X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR");

    /**
     * 提取真实IP，去除CDN干扰
     */
    public static String getClientIpAddr(HttpServletRequest request) {
        return IP_HEADERS.stream()
                .map(request::getHeader)
                .filter(Objects::nonNull)
                .filter(ip -> !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip))
                .findFirst()
                .orElseGet(request::getRemoteAddr);
    }

    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }


}

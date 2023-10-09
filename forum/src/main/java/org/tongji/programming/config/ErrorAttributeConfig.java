package org.tongji.programming.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 为错误添加一些用于前端显示的信息。
 * 如果数据库的结构可能会泄露，也会在这里打上小补丁。
 *
 * @author cinea
 */
@Slf4j
@Configuration
public class ErrorAttributeConfig extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        log.info("后端出现异常，正在处理异常消息……");

        Map<String, Object> attributes = super.getErrorAttributes(webRequest, options);
        var status = (Integer) attributes.get("status");
        if (status.equals(500)) {
            attributes.put("msg", "内部错误");
        } else if (status.equals(401)) {
            attributes.put("msg", "未登录");
        } else if (status.equals(403)) {
            attributes.put("msg", "拒绝访问");
        }

        log.info("请看当前的异常消息：{}", attributes);
        return attributes;
    }
}

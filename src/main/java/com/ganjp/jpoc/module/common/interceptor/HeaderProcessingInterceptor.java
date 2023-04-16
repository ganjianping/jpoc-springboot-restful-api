package com.ganjp.jpoc.module.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class HeaderProcessingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestId = request.getHeader("J-Request-ID");
        if (requestId == null) {
            requestId = generateDefaultRequestId();
        }
        request.setAttribute("requestId", requestId);

        String deviceInfo = request.getHeader("J-Device-Info");
        if (deviceInfo == null) {
            deviceInfo = "Model=Unknown; OS=Unknown; Version=Unknown";
        }
        request.setAttribute("deviceInfo", deviceInfo);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        // You can perform additional processing after the controller has executed, if necessary
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // You can perform cleanup tasks after the entire request has completed, if necessary
    }

    private String generateDefaultRequestId() {
        // Replace this with your own logic for generating a default request ID
        return "default-request-id";
    }
}

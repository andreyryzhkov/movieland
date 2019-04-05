package com.aryzhkov.movieland.web.interceptor;

import com.aryzhkov.movieland.entity.User;
import com.aryzhkov.movieland.security.SecurityService;
import com.aryzhkov.movieland.security.util.Session;
import com.aryzhkov.movieland.web.auth.UserHolder;
import com.aryzhkov.movieland.web.auth.annotation.ProtectedBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Optional;

@Service
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private final SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Method method = ((HandlerMethod) handler).getMethod();

        if (method.isAnnotationPresent(ProtectedBy.class)) {
            String token = request.getHeader("token");
            if (token != null) {
                Optional<Session> session = securityService.getSession(token);
                if (session.isPresent()) {
                    User user = session.get().getUser();
                    if (method.getAnnotation(ProtectedBy.class).value() == user.getUserRole()) {
                        UserHolder.setCurrentUser(user);
                        return true;
                    }
                }
            }
        } else {
            return true;
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserHolder.remove();
    }

    @Autowired
    public AuthInterceptor(SecurityService securityService) {
        this.securityService = securityService;
    }
}

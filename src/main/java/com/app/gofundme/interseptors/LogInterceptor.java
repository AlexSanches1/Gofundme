package com.app.gofundme.interseptors;

import com.app.gofundme.models.User;
import com.app.gofundme.repositories.UserRepository;
import com.app.gofundme.services.ConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ConvertService convertService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        String url = request.getRequestURI();
        if (url.startsWith("/api/admin/")) {
            Long userId = convertService.convertFromBase64(token);
            User user = userRepository.getOne(userId);
            if (user.getIsAdmin()){
                return true;
            }else {
                return false;
            }
        }
        return true;
    }
}

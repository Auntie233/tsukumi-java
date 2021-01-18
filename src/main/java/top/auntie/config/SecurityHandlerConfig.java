package top.auntie.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.*;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import top.auntie.util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Configuration
public class SecurityHandlerConfig {

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return ((httpServletRequest, httpServletResponse, e) -> {
            if (e instanceof BadCredentialsException) {
                log.error("BadCredentials异常 {}", e, e);
                ResponseUtil.responseWriter(httpServletResponse, "坏的凭证", HttpStatus.UNAUTHORIZED.value());
            } else {
                log.error("401异常 {}", e, e);
                ResponseUtil.responseWriter(httpServletResponse, e.getMessage(), HttpStatus.UNAUTHORIZED.value());
            }
        });
    }

    @Bean
    public LogoutHandler logoutHandler() {
        // todo 登出
        return null;
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        // 认证成功
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
            }
        };
    }

    @Bean
    public WebResponseExceptionTranslator webResponseExceptionTranslator() {
        // 认证异常翻译器
        return new DefaultWebResponseExceptionTranslator() {

            @Override
            public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
                OAuth2Exception oAuth2Exception;
                String BAD_MSG = "坏的凭证";
                if (e.getMessage()!=null && e.getMessage().equalsIgnoreCase(BAD_MSG)) {
                    oAuth2Exception = new InvalidGrantException("用户名密码错误", e);
                    ResponseEntity<OAuth2Exception> response = super.translate(oAuth2Exception);
                    ResponseEntity.status(HttpStatus.UNAUTHORIZED.value());
                    response.getBody().addAdditionalInformation("code", "401");
                    response.getBody().addAdditionalInformation("msg", "用户名密码错误");
                    return response;
                } else if (e instanceof InternalAuthenticationServiceException) {
                    oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
                } else if (e instanceof RedirectMismatchException) {
                    oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
                } else if (e instanceof InvalidScopeException) {
                    oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
                } else {
                    oAuth2Exception = new UnsupportedResponseTypeException("服务内部错误", e);
                }
                ResponseEntity<OAuth2Exception> response = super.translate(oAuth2Exception);
                ResponseEntity.status(HttpStatus.UNAUTHORIZED.value());
                response.getBody().addAdditionalInformation("code", "401");
                response.getBody().addAdditionalInformation("msg", "用户名密码错误");
                return response;
            }
        };
    }

}

package top.auntie.controller.oauth2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.auntie.auth.UserPassAuthenticationToken;
import top.auntie.model.common.LoginUserDetails;
import top.auntie.model.common.Result;
import top.auntie.model.dto.UserPassDto;
import top.auntie.service.TsukumiUserService;
import top.auntie.util.AuthUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@Slf4j
@RestController
public class Oauth2Controller {

    @Resource
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private TsukumiUserService userService;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @PostMapping("/oauth/user/token")
    public void getUserTokenInfo(@RequestBody UserPassDto userPassDto,
                                 HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserPassAuthenticationToken token = new UserPassAuthenticationToken(userPassDto.getUsername()
                , userPassDto.getPassword());
        writerToken(request, response, token);
    }

    private void writerToken(HttpServletRequest request, HttpServletResponse response, AbstractAuthenticationToken token) throws IOException {
        try {
            final String[] clientInfos = AuthUtils.extractClient(request);
            String clientId = clientInfos[0];
            String clientSecret = clientInfos[1];

            ClientDetails clientDetails = getClient(clientId, clientSecret, clientDetailsService);
            TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "tsukumi");
            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
            OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
            oAuth2Authentication.setAuthenticated(true);
            response.setHeader("Authorization", "Bearer " + oAuth2AccessToken.getValue());
            // 获取用户名返回前端
            JSONObject tokenMap = (JSONObject) JSON.toJSON(oAuth2AccessToken);
            LoginUserDetails userDetails = (LoginUserDetails) authentication.getPrincipal();
            tokenMap.put("userName", userDetails.getUsername());
            tokenMap.put("userId", userDetails.getUserId());
            writerObj(response, Result.success(tokenMap));
        } catch (BadCredentialsException e) {
            log.error("401异常 Oauth2token BadCredentialsException : request {} {}", request.getRequestURL().toString(), e);
            exceptionHandlerBadCredentials(response, e.getMessage());
        } catch (InternalAuthenticationServiceException e) {
            log.error("401异常 Oauth2token InternalAuthenticationServiceException : request {} {}", request.getRequestURL().toString(), e);
            exceptionHandlerBadCredentials(response, e.getMessage());
        }catch (Exception e) {
            log.error("401异常 Oauth2token Exception : request {} {}", request.getRequestURL().toString(), JSON.toJSONString(request.getParameterMap()), e);
            exceptionHandlerBadCredentials(response, e.getMessage());
        }
    }

    private void exceptionHandlerBadCredentials(HttpServletResponse response, String msg) throws IOException {
        writerObj(response, Result.failed(401, msg));
    }

    private void exceptionHandler(HttpServletResponse response, Exception e) throws IOException {
        log.error("exceptionHandler-error:", e);
        exceptionHandler(response, e.getMessage());
    }

    private void exceptionHandler(HttpServletResponse response, String msg) throws IOException {
        writerObj(response, Result.failed(HttpStatus.UNAUTHORIZED.value(), msg));
    }

    private void writerObj(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try (
                Writer writer = response.getWriter()
        ) {
            writer.write(objectMapper.writeValueAsString(obj));
            writer.flush();
        }
    }

    private ClientDetails getClient(String clientId, String clientSecret, ClientDetailsService clientDetailService) {
        ClientDetails clientDetails = clientDetailService.loadClientByClientId(clientId);

        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的信息不存在");
        } else if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配");
        }
        return clientDetails;
    }

}

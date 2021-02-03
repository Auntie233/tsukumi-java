package top.auntie.auth;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Collection;

import static top.auntie.constant.Constant.CLIENT_ID;

@Slf4j
@Setter
public class UserPassAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;
    private TokenStore tokenStore;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserPassAuthenticationToken userPassAuthenticationToken = (UserPassAuthenticationToken) authentication;
        String username = (String) userPassAuthenticationToken.getPrincipal();
        String password = (String) userPassAuthenticationToken.getCredentials();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("用户不存在");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("用户名/密码错误");
        }
        Collection<OAuth2AccessToken> accessTokens = this.tokenStore.findTokensByClientIdAndUserName(CLIENT_ID, username);
        for (OAuth2AccessToken token: accessTokens) {
            revoke(token.getValue());
        }
        UserPassAuthenticationToken authenticationToken = new UserPassAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        authenticationToken.setDetails(authentication.getDetails());
        return authenticationToken;
    }

    private void revoke(String value) {
        OAuth2AccessToken accessToken = this.tokenStore.readAccessToken(value);
        if (accessToken == null) {
            return;
        }
        if (accessToken.getRefreshToken() != null) {
            this.tokenStore.removeRefreshToken(accessToken.getRefreshToken());
        }
        this.tokenStore.removeAccessToken(accessToken);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UserPassAuthenticationToken.class.isAssignableFrom(authentication);
    }

}

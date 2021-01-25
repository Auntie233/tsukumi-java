package top.auntie.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;
import top.auntie.service.TsukumiUserService;

@Component
public class UserPassAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private TsukumiUserService tsukumiUserService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void configure(HttpSecurity http) {
        UserPassAuthenticationProvider provider = new UserPassAuthenticationProvider();
        provider.setUserDetailsService(tsukumiUserService);
        provider.setTokenStore(tokenStore);
        provider.setPasswordEncoder(passwordEncoder);
        http.authenticationProvider(provider);
    }
}

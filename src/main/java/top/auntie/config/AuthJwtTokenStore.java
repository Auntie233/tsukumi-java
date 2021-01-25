package top.auntie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Import(AuthJwtTokenConverter.class)
public class AuthJwtTokenStore {

    @Autowired
    private DataSource dataSource;


    @Bean("jdbcClientService")
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(this.dataSource);
    }

    @Bean
    public TokenEnhancer tokenEnhancer(){
        return (accessToken, authentication) -> accessToken;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

}

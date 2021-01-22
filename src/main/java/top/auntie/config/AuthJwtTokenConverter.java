package top.auntie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import top.auntie.properites.KeyStoreProperties;

@Import(KeyStoreProperties.class)
public class AuthJwtTokenConverter {

    @Autowired
    private KeyStoreProperties properties;

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(new KeyStoreKeyFactory(
                this.properties.getKeyStore().getLocation(),
                this.properties.getKeyStore().getSecret().toCharArray()
        ).getKeyPair(this.properties.getKeyStore().getAlias()));
        return converter;
    }

}

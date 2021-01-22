package top.auntie.properites;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "encrypt")
public class KeyStoreProperties {

    private KeyStore keyStore;

    public KeyStore getKeyStore() {
        return keyStore;
    }

    @Data
    class KeyStore {
        private String location;
        private String secret;
        private String alias;
    }

}

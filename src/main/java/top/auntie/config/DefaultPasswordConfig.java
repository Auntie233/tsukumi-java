package top.auntie.config;

import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;

@Configuration
public class DefaultPasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return Md5Crypt.md5Crypt(charSequence.toString().getBytes(StandardCharsets.UTF_8), "Auntie@tai");
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return encode(charSequence).equalsIgnoreCase(s);
            }
        };
    }

}

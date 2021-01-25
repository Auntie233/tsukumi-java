package top.auntie.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import top.auntie.exception.UserException;
import top.auntie.model.dto.RegisterDto;

public interface TsukumiUserService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void register(RegisterDto registerDto) throws UserException;

}

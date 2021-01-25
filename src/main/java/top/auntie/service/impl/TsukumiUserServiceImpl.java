package top.auntie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.auntie.exception.UserException;
import top.auntie.mapper.UserInfoMapper;
import top.auntie.model.common.LoginUserDetails;
import top.auntie.model.dto.RegisterDto;
import top.auntie.pojo.UserInfo;
import top.auntie.service.TsukumiUserService;

import java.util.Date;

@Service
public class TsukumiUserServiceImpl implements TsukumiUserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = new UserInfo();
        user.setUsername(username);
        user = userInfoMapper.selectOne(user);
        if (user == null) {
//            throw new UsernameNotFoundException("用户不存在");
            return null;
        }
        LoginUserDetails userDetails = new LoginUserDetails();
        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(user.getPassword());
        userDetails.setId(user.getId());
        return userDetails;
    }

    @Override
    public void register(RegisterDto registerDto) throws UserException {
        UserInfo user = new UserInfo();
        user.setUsername(registerDto.getUsername());
        user = userInfoMapper.selectOne(user);
        if (user != null) {
            throw new UserException("用户名已存在");
        }
        user = new UserInfo();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setCreateTime(new Date());
        userInfoMapper.insertSelective(user);
    }

}

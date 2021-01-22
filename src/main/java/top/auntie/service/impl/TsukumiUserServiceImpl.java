package top.auntie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.auntie.mapper.UserInfoMapper;
import top.auntie.model.common.LoginUserDetails;
import top.auntie.pojo.UserInfo;
import top.auntie.service.TsukumiUserService;

@Service
public class TsukumiUserServiceImpl implements TsukumiUserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = new UserInfo();
        user.setUsername(username);
        user = userInfoMapper.selectOne(user);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        LoginUserDetails userDetails = new LoginUserDetails();
        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(user.getPassword());
        userDetails.setId(user.getId());
        return userDetails;
    }

}

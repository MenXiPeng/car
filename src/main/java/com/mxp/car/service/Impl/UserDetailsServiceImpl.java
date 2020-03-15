package com.mxp.car.service.Impl;

import com.mxp.car.mapper.BaseMapper;
import com.mxp.car.mapper.UserMapper;
import com.mxp.car.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl extends BaseServiceImpl<User, Long> implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Value("${uuid}")
    private String configUUID;

    @Override
    public BaseMapper<User, Long> getMapper() {
        return userMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.selectUserByUsername(s);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在");
        }
        if (!StringUtils.isEmpty(user.getVerification())) {
            if (!user.getVerification().equals(configUUID)) {
                throw new RuntimeException("机器码验证失败");
            }
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }


}

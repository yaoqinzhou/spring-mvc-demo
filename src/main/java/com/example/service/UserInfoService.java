package com.example.service;

import com.example.dao.UserInfoDao;
import com.example.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userInfoService")
public class UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    public UserInfo getUserInfoById(int userId){
        return userInfoDao.getUserInfoById(userId);
    }

    public boolean updateUserInfoById(UserInfo userInfo){
        return userInfoDao.updateUserInfoById(userInfo);
    }
}

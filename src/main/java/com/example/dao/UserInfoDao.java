package com.example.dao;

import com.example.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("userInfoDao")
public class UserInfoDao {

    @Autowired
    private JdbcTemplate demoJdbcTemplate;

    /**
     * 根据userid获取用户信息
     * @param userId
     * @return
     */
    public UserInfo getUserInfoById(int userId){
        final UserInfo userInfo = new UserInfo();

        StringBuilder sb = new StringBuilder();
        sb.append("select * from userinfo where id = ? ");

        demoJdbcTemplate.query(sb.toString(),new Object[]{userId},new RowCallbackHandler(){
            public void processRow(ResultSet rs) throws SQLException {
                userInfo.setId(rs.getInt("id"));
                userInfo.setName(rs.getString("username"));
                userInfo.setAge(rs.getInt("age"));
            }
        });

        return userInfo;
    }

    /**
     * 更新用户信息
     * @param userInfo
     * @return
     */
    public boolean updateUserInfoById(final UserInfo userInfo){
        boolean updateFlag = false;

        StringBuilder sb = new StringBuilder();
        sb.append(" update userinfo set username = ?,age = ? ");
        sb.append(" where id = ? ");

        updateFlag = demoJdbcTemplate.update(sb.toString(), new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,userInfo.getName());
                ps.setInt(2,userInfo.getAge());
                ps.setInt(3,userInfo.getId());
            }
        }) > 0;

        return  updateFlag;
    }
}

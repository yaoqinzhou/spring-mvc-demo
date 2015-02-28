package com.example.web;

import com.alibaba.fastjson.JSON;
import com.example.domain.UserInfo;
import com.example.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(method = RequestMethod.GET)
    public String welcome(ModelMap model){
        System.out.println("执行到welcome方法中");

        model.put("msg","hello spring mvc");

        return "welcome";
    }

    /**
     * 根据用户id 修改用户姓名和年龄
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/updateUserInfoById.html",method = RequestMethod.POST)
    public @ResponseBody
    String updateUserInfoById(HttpServletRequest request, HttpServletResponse response){
        String returnMsg = "";

        try{
            String userId = request.getParameter("userId");
            String name = request.getParameter("name");
            String age = request.getParameter("age");

            UserInfo userInfo = new UserInfo();
            userInfo.setId(Integer.valueOf(userId));
            userInfo.setName(name);
            userInfo.setAge(Integer.valueOf(age));

            boolean updateFlag = userInfoService.updateUserInfoById(userInfo);

            Map<String,String> msg = new HashMap<String,String>();

            String msgFlag = "success";
            if(!updateFlag){
                msgFlag = "fault";
            }

            msg.put("msgFlag",msgFlag);

            return JSON.toJSONString(msg);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return returnMsg;
    }

    /**
     * 根据用户Id查询用户姓名和年龄
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/getUserInfoById.html",method = RequestMethod.POST)
    public @ResponseBody
    String getUserInfoById(HttpServletRequest request, HttpServletResponse response){
        String returnMsg = "";

        try{
            String userId = request.getParameter("userId");

            if(!"".equals(userId) && userId != null){
                UserInfo userInfo = userInfoService.getUserInfoById(Integer.valueOf(userId));

                returnMsg = JSON.toJSONString(userInfo);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }

        return returnMsg;
    }
}

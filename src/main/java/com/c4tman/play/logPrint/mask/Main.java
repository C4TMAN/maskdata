package com.c4tman.play.logPrint.mask;

import com.c4tman.play.model.User;
import com.google.gson.Gson;

/**
 * Created by zhangxiaoman on 2018/6/5.
 */
public class Main {

    public static void main(String[] args) {
        User user1 = new User();
        user1.setName("王菊不红天理难容");
        user1.setPassword("你一票我一票王菊迟早能出道");
        user1.setMobile("15012345678");
        user1.setIdCard("370101191801012758");
        user1.setVerifyCode("13142wj");
        user1.setRemark("投票地址：http://game.h5gf.cn/create101/Wap/html/call.html");


        MaskLogUtil.print(LogType.INFO, false, " 原数据：{}",  new Gson().toJson(user1));
        MaskLogUtil.print(LogType.INFO, true, " 脱敏后：{}",  new Gson().toJson(user1));

    }
}

package com.c4tman.play.logPrint.custom;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangxiaoman on 2018/6/1.
 */
@Slf4j
public class Print {

    public static void main(String[] args) {

        Customer customer = new Customer();
        customer.setAddress("斯里兰卡");
        customer.setBankNo("6217002710000684874");
        customer.setIdCard("370101191801012758");
        customer.setMobile("15087654321");

        String json = new Gson().toJson(customer);
        log.info(json);

    }
}

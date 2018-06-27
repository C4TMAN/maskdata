package com.c4tman.play.model;

import com.c4tman.play.logPrint.desense.DesenseType;
import com.c4tman.play.logPrint.desense.Desensitization;
import com.c4tman.play.logPrint.desense.DesensitizationType;
import com.c4tman.play.logPrint.desense.ExtendMsg;
import com.c4tman.play.logPrint.desense.GenderEnum;
import com.c4tman.play.model.Book;
import com.c4tman.play.model.Person;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by zhangxiaoman on 2018/5/29.
 */
@Data
@DesensitizationType
public class User {

    @Desensitization(type = DesenseType.PASSWORD)
    String password;

    @Desensitization(method = "trueName", className = "com.c4tman.play.logPrint.desense.MyDesensitization")
    String name;

    @Desensitization(type = DesenseType.MOBILE)
    String mobile;

    @Desensitization(type = DesenseType.ID_CARD)
    String idCard;

    @Desensitization(type = DesenseType.VERIFY_CODE)
    String verifyCode;

    String remark;

    ExtendMsg extendMsg;

    List<String> area = new ArrayList<>();

    Boolean success = true;

    boolean fail = false;

    int age = 20;

    GenderEnum gender;

    int[] array = new int[]{1, 2, 3};

    List<List<Integer>> manyList = new ArrayList<>();


    //无法实现打印
    Map<String, Integer> mapp = new HashMap<>();

    //无法实现打印
    Map<Book, Person> objectMap = new HashMap<>();
}

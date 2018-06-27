package com.c4tman.play.logPrint.desense;

import com.c4tman.play.model.Book;
import com.c4tman.play.model.Person;
import lombok.Data;

import java.util.List;

/**
 * Created by zhangxiaoman on 2018/5/31.
 */
@Data
@DesensitizationType
public class ExtendMsg {

    @Desensitization(method = "area", className = "com.c4tman.play.logPrint.desense.MyDesensitization")
    private String area;

    private List<String> hobbies;

    List<Book> books;

    Person person;
}

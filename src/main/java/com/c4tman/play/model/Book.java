package com.c4tman.play.model;

import com.c4tman.play.logPrint.desense.Desensitization;
import com.c4tman.play.logPrint.desense.DesensitizationType;
import lombok.Data;

/**
 * Created by zhangxiaoman on 2018/4/11.
 */
@Data
@DesensitizationType
public class Book implements  Comparable {
    @Desensitization(method = "book", className = "com.c4tman.play.logPrint.desense.MyDesensitization")
    private String name;

    private Integer num;

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

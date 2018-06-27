package com.c4tman.play.model;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by zhangxiaoman on 2017/10/24.
 */
@Data
@ToString
public class Person {
    @NotBlank(message = "姓名不能为空")
    String name;

    @NotBlank(message = "用户身份证号不能为空")
    @Pattern(regexp = "(\\d{15}$)|(\\d{17}([0-9]|X|x)$)", message = "请输入正确的身份证号")
    private String idNo;

    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1(3|4|5|7|8)[0-9]\\d{8}$", message = "请输入正确的手机号")
    String phone;

    @NotNull(message = "年龄不能为空")
    @Max(value = 120, message = "年龄最大为120")
    @Min(value = 1, message = "最小年龄为18")
    Integer age;

    @NotNull(message = "index不能为空")
    @Range(min = 0, max = 150, message = "index.not.in.range")
    Integer index;

    String address;
    String remark;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "email.not.correct")
    String email;
//
//    @NotEmpty(message = "list不能为空")
//    List<String> list;

}

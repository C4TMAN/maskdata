package com.c4tman.play.logPrint.desense;

import com.c4tman.play.model.Book;
import com.c4tman.play.model.Person;
import com.c4tman.play.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxiaoman on 2018/5/29.
 */

@Slf4j
public class PrintLog {

    private static Gson GSON2 = new GsonBuilder().setFieldNamingStrategy(new MyFieldNamingStrategy()).create();

    private static Gson GSON = new GsonBuilder().registerTypeAdapterFactory(new MyTypeAdapterFactory()).create();

    public static void main(String[] args) {


        User user1 = new User();
        user1.setName("王菊不红天理难容");
        user1.setPassword("你一票我一票王菊迟早能出道");
        user1.setMobile("15012345678");
        user1.setIdCard("370101191801012758");
        user1.setVerifyCode("12345678");
        user1.setRemark("投票地址：http://game.h5gf.cn/create101/Wap/html/call.html");

        user1.setGender(GenderEnum.FEMALE);

        List<String> hobbies1 = new ArrayList<>();
        hobbies1.add("陶渊明");
        hobbies1.add("爱菊说");

        ExtendMsg extendMsg1 = new ExtendMsg();
        extendMsg1.setArea("北极星以北");
        Person person = new Person();
        person.setIndex(1);
        person.setRemark("林克米法大法好");
        extendMsg1.setPerson(person);

        List<Book> books = new ArrayList<>();
        Book book = new Book();
        book.setNum(1);
        book.setName("白夜行");
        books.add(book);

        Book book2 = new Book();
        book2.setNum(2);
        book2.setName("幻夜");
        books.add(book2);

        extendMsg1.setBooks(books);

        user1.setExtendMsg(extendMsg1);

        user1.getArea().add("地址1");
        user1.getArea().add("地址2");


        List<Integer> integerList = new ArrayList<>();
        integerList.add(new Integer(1));
        integerList.add(new Integer(2));

        List<Integer> integerList1 = new ArrayList<>();
        integerList1.add(new Integer(1));
        integerList1.add(new Integer(2));

        user1.getManyList().add(integerList);
        user1.getManyList().add(integerList1);
        user1.getMapp().put("aaa", 11);
        user1.getMapp().put("aaa", 11);


        Book b1 = new Book();
        b1.setName("book1");
        b1.setNum(1);
        Person p1 = new Person();
        p1.setIndex(1);
        p1.setName("塞尔达等我来救你");
        user1.getObjectMap().put(b1, p1);

        System.out.println(GSON.toJson(user1));
        System.out.println();
        System.out.println();
        System.out.println(GSON2.toJson(user1));


    }


}

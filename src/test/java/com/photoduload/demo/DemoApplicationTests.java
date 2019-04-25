package com.photoduload.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test(){
        String a = "课程目录.txt";

    try {
            System.out.println(new String(a.getBytes("gbk"), "iso8859-1"));
            System.out.println(new String(a.getBytes("gbk"), "UTF-8"));
            System.out.println(new String(a.getBytes(), "iso8859-1"));
            System.out.println(new String(a.getBytes("UTF-8"), "iso8859-1"));
            System.out.println(new String(a.getBytes(),"gbk"));
            System.out.println(new String(a.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}

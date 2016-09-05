package com.car.test.user;

import org.testng.annotations.Test;

/**
 * Created by YJH on 2016/3/7.
 */
public class UserTest {

    @Test
    public void test(){
        String chars = "0123456789";
        String rands = "";
        for (int i = 0; i < 6; i++) {
            int rand = (int) (Math.random() * 10);
            rands += chars.charAt(rand);
        }
        System.out.println(rands.trim().toString());
    }
}

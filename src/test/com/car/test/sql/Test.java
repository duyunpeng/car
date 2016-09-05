package com.car.test.sql;

import pengyi.application.permission.command.CreatePermissionCommand;
import pengyi.core.api.BaseResponse;
import pengyi.core.util.CoreDateUtils;
import pengyi.core.util.CoreStringUtils;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by YJH on 2016/3/25.
 */
public class Test {

    @org.testng.annotations.Test
    public void test() throws IOException {
        File file = new File("C:\\Users\\YJH\\Desktop\\123123.txt");
        File file1 = new File("C:\\Users\\YJH\\Desktop\\123123123.txt");
        InputStreamReader read = new InputStreamReader(new FileInputStream(file), "GBK");
        InputStreamReader read1 = new InputStreamReader(new FileInputStream(file1), "GBK");
        BufferedReader bufferedReader1 = new BufferedReader(read);
        BufferedReader bufferedReader2 = new BufferedReader(read1);
        String lineTxt = null;
        String lineTxt1 = null;
//        ArrayList map = new ArrayList();
        while ((lineTxt = bufferedReader1.readLine()) != null) {
            lineTxt1 = bufferedReader2.readLine();
//            lineTxt = lineTxt.replaceAll("/", ":");
//            lineTxt = lineTxt.substring(1, lineTxt.length());
            lineTxt1 = lineTxt1.substring(0, lineTxt1.length() - 2);
//            map.add(lineTxt);
            System.out.println(lineTxt + lineTxt1);
        }
    }

    @org.testng.annotations.Test
    public void test_1() throws IOException {
        File file = new File("C:\\Users\\YJH\\Desktop\\123123.txt");
        FileInputStream in = null;
        FileOutputStream out = new FileOutputStream("C:\\Users\\YJH\\Desktop\\123123123.txt");
        in = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int len = in.read(buffer);
        while (len != -1) {
            out.write(buffer);
            in.read(buffer);
        }


        in.close();
    }

}

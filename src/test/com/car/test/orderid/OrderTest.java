package com.car.test.orderid;

import org.testng.annotations.Test;
import pengyi.core.util.CharsetConstant;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by YJH on 2016/3/22.
 */
public class OrderTest {

    @Test
    public void testId() {
        for (int i = 1; i <= 100; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    while (true) {
                        String result = null;
                        try {
                            String url = "http://127.0.0.1:8080/get_id";
                            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                            conn.setDoOutput(true);
                            conn.setRequestMethod("POST");

                            // Get the api!
                            int httpResponseCode = conn.getResponseCode();
                            if (httpResponseCode != HttpURLConnection.HTTP_OK) {
                                throw new Exception("HTTP api code: " + httpResponseCode +
                                        "\nurl:" + url);
                            }

                            InputStream inputStream = conn.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, CharsetConstant.UTF8_STRING));
                            StringBuilder builder = new StringBuilder();
                            String readLine;
                            while (null != (readLine = br.readLine())) {
                                builder.append(readLine);
                            }
                            inputStream.close();
                            result = builder.toString();
                            System.out.println("线程[" + Thread.currentThread().getName() + "],结果=" + result);
                            conn.disconnect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            thread.start();
        }
    }
}
package com.back;

import com.back.standard.util.TestUtil;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class AppTestRunner {

    public static String run(String input) {
        Scanner sc = TestUtil.genScanner(input + "\n종료"); //이렇게하면 테스트케이스에 매번 종료 안넣어도 됨
        ByteArrayOutputStream outputStream = TestUtil.setOutToByteArray();

        AppContext.init(sc);
        new App().run();
        return outputStream.toString();
    }

}
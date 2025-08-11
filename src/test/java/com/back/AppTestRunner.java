package com.back;

import com.back.standard.util.TestUtil;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class AppTestRunner {
    //반복작업 하는 곳
    public static String run(String input) {
        Scanner sc = TestUtil.genScanner(input+"\n종료"); //이렇게 넣으면 테스트케이스에서 매번 종료 안넣어도 됨
        ByteArrayOutputStream outputStream = TestUtil.setOutToByteArray();
        new App(sc).run(); //테스트용 sc를 붙여야한다는 말이 무슨 말인지 이해가 잘 안갔음
        return outputStream.toString();
    }
}

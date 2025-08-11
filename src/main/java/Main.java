import com.back.App;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new App(new Scanner(System.in)).run(); //테스트가 아니라 실제 사용자가 입력한 값 확인하는 용도
//        experiment1();
        //experiment2();
    }

    public static void experiment2() {
        System.out.println("안녕하세요");

        PrintStream originalOut = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        System.setOut(printStream);

        System.out.println("하하하");

        String outStr = outputStream.toString();
        System.setOut(originalOut);
        printStream.close();

        if(outStr.equals("하하하")) {
            System.out.println("출력 결과가 일치합니다.");
        } else {
            System.out.println("출력 결과가 일치하지 않습니다.");
        }

        System.out.println(outStr);


    }

    public static void experiment1() {

        String input = """
                등록
                너 자신을 알라
                """;
        Scanner sc = new Scanner(input);
        String cmd = sc.nextLine();
        String saying = sc.nextLine();
        System.out.println("입력한 명령어: " + cmd);
        System.out.println("입력한 명언: " + saying);

    }
}

package com.back;

import com.back.domain.wiseSaying.controller.WiseSayingController;
import com.back.system.SystemController;

import java.util.Scanner;

public class App {

    private Scanner sc;
    private WiseSayingController wiseSayingController;
    private SystemController systemController;
    public App(){
        this.sc = AppContext.sc;
        wiseSayingController = AppContext.wiseSayingController;
        systemController = AppContext.systemController;
    }

    public void run(){
        System.out.println("==명언 앱==");

        while(true){
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            Rq rq = new Rq(cmd);
            String action = rq.getActionName();

            switch (action) {
                case "등록" -> wiseSayingController.actionAdd(); // WiseSayingController로 하면 오류가 난다. 대브악
                case "목록" -> wiseSayingController.actionList(rq); // wiseSayingController로 해야 오류가 안나네...
                case "삭제" -> wiseSayingController.actionDelete(rq);
                case "수정" -> wiseSayingController.actionModify(rq);
                case "종료" -> {
                    systemController.actionExit();
                    return;
                }
            }
        }
    }


}

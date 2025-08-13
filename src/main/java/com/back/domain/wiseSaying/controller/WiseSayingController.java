package com.back.domain.wiseSaying.controller;

import com.back.AppContext;
import com.back.PageDto;
import com.back.Rq;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WiseSayingController {
    private Scanner sc;
    private WiseSayingService wiseSayingService;

    public WiseSayingController() {
        this.sc = AppContext.sc;
        this.wiseSayingService = AppContext.wiseSayingService;
    }


    public void actionAdd() {
        System.out.print("명언 : ");
        String saying = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        WiseSaying wiseSaying = wiseSayingService.write(saying, author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));
    }

    public void actionList(Rq rq) {
        String kw = rq.getParam("keyword", "");
        String kwType = rq.getParam("keywordType", "");

        int pageSize = rq.getParamAsInt("pageSize", 5);
        int page = rq.getParamAsInt("page", 1);

        System.out.println("번호 / 작가 / 명언");
        System.out.println("-----------------------");

        PageDto pageDto = wiseSayingService.findListDesc(kw, kwType, pageSize, page);

        pageDto.getContent()
//              .reversed() -> service 단계에서 이미 reversed 되므로 할 필요 없음
                .stream()
                .forEach(wiseSaying -> System.out.printf("%d / %s / %s%n",
                        wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getSaying()));

        System.out.println("------------------------");
        //페이지 개수 => 전체 개수 / 페이지 사이즈, ex) 명언 10개, 페이지 사이즈 한 번에 5개 => 페이지 개수는 2개

        int totalPageCnt = pageDto.getTotalPageCnt();
        int currentPageNo = pageDto.getPage();

        String pageMenu = IntStream.rangeClosed(1, totalPageCnt)
                .mapToObj(i -> i == currentPageNo ? "[%d]".formatted(i) : String.valueOf(i)) //현재 페이지에 대괄호 붙임
                .collect(Collectors.joining(" / ")); //joining을 하면 요소를 붙일 때 그 사이에 / 를 붙인다는 뜻

        System.out.println("페이지 : "+ pageMenu);
    }

    public void actionDelete(Rq rq) {
        int id = rq.getParamAsInt("id", -1);
        //삭제는 컨트롤러에서 구현하는 게 아니라 서비스에서 삭제 하는 것
        boolean deleted = wiseSayingService.delete(id);

        if (!deleted) { //삭제처리가 되지않았다면
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }
        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    public void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", -1);
        WiseSaying wiseSaying = wiseSayingService.findByIdOrNull(id);
        if (wiseSaying == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("명언(기존) : %s".formatted(wiseSaying.getSaying()));
        String newSaying = sc.nextLine();
        System.out.println("작가(기존) : %s".formatted(wiseSaying.getAuthor()));
        String newAuthor = sc.nextLine();

        wiseSayingService.modify(wiseSaying, newSaying, newAuthor);

    }
}

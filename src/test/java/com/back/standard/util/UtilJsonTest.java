package com.back.standard.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilJsonTest {
    @Test
    @DisplayName("Map을 Json으로 바꿀 수 있다")
    void t1(){
        //given
        Map<String, Object> map = new HashMap<>();

        map.put("id", "1");
        map.put("name", "홍길동");
        map.put("age", "20");

        //when - map이 주어졌을 때 json 변환 한다
        String jsonStr = Util.json.toString(map);

        //then - 검증
        assertThat(jsonStr).isEqualTo(
                """
                        {
                            "id": 1,
                            "name": "홍길동",
                            "age": 20
                        }
                        """
        );

    }
}

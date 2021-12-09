package com.koreait.basic.board.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Entity 는 하나의 개체
// DB 테이블과 1:1 맵핑되는 개체
public class BoardEntity {
    private int ibaord;
    private String title;
    private String ctnt;
    private int writer;
    private int hit;
    private String rdt;
    private String mdt;
}

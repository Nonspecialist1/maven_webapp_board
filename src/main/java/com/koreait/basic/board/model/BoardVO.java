package com.koreait.basic.board.model;

import lombok.Builder;
import lombok.Getter;
/*
  <INSERT, UPDATE, DELETE 할 때 사용>
    - Entity - DB의 테이블과 1:1 맵핑되는 개체
  <SELECT 할 때 사용>
  1. 파라미터로 사용
    - DTO (Data Transfer Object) 는 SELECT 때 쓰는 것
  2. 리턴 타입으로 사용
    - VO는 setter 가 없어 생성자로 값을 넣어줌, 이후 값이 불변하는 객체, 뷰 용도로 사용
    - Builder 로 값을 딱 한번 넣어줌
    - SELECT 결과물에 따라 추가되는 열이 있다면 추가
  <Builder 패턴을 사용하는 이유>
    - set 하고 싶은 값만 생성자를 만들기 때문에 효율적
    - Data 의 크기가 클수록 모든 생성자를 만들면 데이터 소모가 많음
*/
@Getter
@Builder
public class BoardVO {
    private int iboard;
    private String title;
    private String ctnt;
    private int writer;
    private int hit;
    private String rdt;
    private String mdt;

    private String writerNm;
}

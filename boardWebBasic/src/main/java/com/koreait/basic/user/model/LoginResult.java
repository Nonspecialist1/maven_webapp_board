package com.koreait.basic.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // 생성자로만 값을 set할 수 있는 기능
public class LoginResult {
    private final int result;
    private final UserEntity loginUser; // final 은 주소값만 고정된 것이지 그 주소값에 접근했을 때 UserEntity 의 값은 변화 가능(uid, upw 등은 final이 아니기 때문에)
}

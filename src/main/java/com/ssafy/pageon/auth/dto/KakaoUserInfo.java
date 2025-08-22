package com.ssafy.pageon.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record KakaoUserInfo(
        Long id,
        KakaoAccount kakaoAccount,
        Properties properties,
        String email,
        String profileImageUrl
) {

    public record KakaoAccount(String email) {

    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Properties(
            String nickname,
            String profileImage
    ) {

    }
}

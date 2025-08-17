package com.ssafy.pageon.challenge.dto;

public record AuthorResponse(
        Long id,
        String nickname,
        String imageKey,
        String presignedUrl
) {

}

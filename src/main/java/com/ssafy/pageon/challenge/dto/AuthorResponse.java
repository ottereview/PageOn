package com.ssafy.pageon.challenge.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public record AuthorResponse(
        Long id,
        String nickname,
        String imageKey,
        String presignedUrl
) {

}

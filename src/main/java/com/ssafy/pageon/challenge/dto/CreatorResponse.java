package com.ssafy.pageon.challenge.dto;

public record CreatorResponse(
        Long id,
        String nickname,
        String imageKey,
        String prsignedUrl
) {

}

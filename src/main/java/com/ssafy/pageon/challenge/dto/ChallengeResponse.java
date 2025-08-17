package com.ssafy.pageon.challenge.dto;

import kakao.rebit.challenge.entity.ChallengeType;

import java.time.LocalDateTime;

public record ChallengeResponse(
        Long id,
        CreatorResponse creator,
        String title,
        String content,
        String imageKey,
        String presignedUrl,
        ChallengeType type,
        Integer minimumEntryFee,
        LocalDateTime recruitmentStartDate,
        LocalDateTime recruitmentEndDate,
        LocalDateTime challengeStartDate,
        LocalDateTime challengeEndDate,
        Integer minHeadcount,
        Integer maxHeadcount,
        LocalDateTime createdAt,
        Integer currentHeadcount,
        Integer totalEntryFee
) {

}

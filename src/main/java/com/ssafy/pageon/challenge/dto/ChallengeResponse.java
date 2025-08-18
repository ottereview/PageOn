package com.ssafy.pageon.challenge.dto;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@Builder
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

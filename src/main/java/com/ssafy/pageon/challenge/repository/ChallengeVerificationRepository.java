package com.ssafy.pageon.challenge.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import com.ssafy.pageon.challenge.entity.Challenge;
import com.ssafy.pageon.challenge.entity.ChallengeParticipation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChallengeVerificationRepository extends JpaRepository<ChallengeVerification, Long> {

    @EntityGraph(attributePaths = {"challengeParticipation", "challengeParticipation.member", "challengeParticipation.challenge"})
    Page<ChallengeVerification> findAllByChallengeParticipation_Challenge(Challenge challenge, Pageable pageable);

    @EntityGraph(attributePaths = {"challengeParticipation", "challengeParticipation.member"})
    Optional<ChallengeVerification> findByIdAndChallengeParticipation_Challenge(Long verificationId, Challenge challenge);

    @Query("SELECT EXISTS (SELECT 1 FROM ChallengeVerification cv " +
            "WHERE cv.challengeParticipation.id = :participationId " +
            "AND FUNCTION('DATE', cv.createdAt) = FUNCTION('DATE', :now))")
    boolean existsDailyVerification(@Param("participationId") Long participationId, @Param("now") LocalDateTime now);

    long countByChallengeParticipation(ChallengeParticipation participation);

    boolean existsById(Long id);
}

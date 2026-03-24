package com.patchly.repository;

import com.patchly.entity.SolutionVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolutionVerificationRepository extends JpaRepository<SolutionVerification, Long> {

    // 🔍 Find verification records for a solution
    List<SolutionVerification> findBySolution_SolutionId(Long solutionId);
}
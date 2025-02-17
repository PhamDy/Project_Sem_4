package com.projectsem4.StadiumService.repository;

import com.projectsem4.StadiumService.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}

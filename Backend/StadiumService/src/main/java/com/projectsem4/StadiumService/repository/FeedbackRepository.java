package com.projectsem4.StadiumService.repository;

import com.projectsem4.StadiumService.model.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, String> {
}

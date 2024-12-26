package com.projectsem4.TournamentService.repository;

import com.projectsem4.TournamentService.entity.TournamentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentServiceRepository extends JpaRepository<TournamentService, Long> {
}

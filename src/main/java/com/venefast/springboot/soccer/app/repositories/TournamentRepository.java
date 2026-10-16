package com.venefast.springboot.soccer.app.repositories;

import com.venefast.springboot.soccer.app.models.entities.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    List<Tournament> findByStatus(String status);
}

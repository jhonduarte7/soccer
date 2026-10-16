package com.venefast.springboot.soccer.app.repositories;

import com.venefast.springboot.soccer.app.models.entities.TournamentMatchday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentMatchdayRepository extends JpaRepository<TournamentMatchday, Long> {
    List<TournamentMatchday> findByTournamentId(Long tournamentId);
}

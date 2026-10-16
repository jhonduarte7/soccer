package com.venefast.springboot.soccer.app.repositories;

import com.venefast.springboot.soccer.app.models.entities.PlayerGuardian;
import com.venefast.springboot.soccer.app.models.entities.PlayerGuardianId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerGuardianRepository extends JpaRepository<PlayerGuardian, PlayerGuardianId> {
    List<PlayerGuardian> findByIdPlayerId(Long playerId);
    List<PlayerGuardian> findByIdGuardianId(Long guardianId);
}

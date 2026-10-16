package com.venefast.springboot.soccer.app.repositories;

import com.venefast.springboot.soccer.app.models.entities.PlayerPosition;
import com.venefast.springboot.soccer.app.models.entities.PlayerPositionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerPositionRepository extends JpaRepository<PlayerPosition, PlayerPositionId> {
    List<PlayerPosition> findByIdPlayerId(Long playerId);
}

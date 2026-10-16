package com.venefast.springboot.soccer.app.repositories;

import com.venefast.springboot.soccer.app.models.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByCategoryId(Long categoryId);
}

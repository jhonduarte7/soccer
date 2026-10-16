package com.venefast.springboot.soccer.app.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PlayerPositionId implements Serializable {

    @Column(name = "id_jugador")
    private Long playerId;

    @Column(name = "posicion", length = 50)
    private String position;

    public PlayerPositionId() {
    }

    public PlayerPositionId(Long playerId, String position) {
        this.playerId = playerId;
        this.position = position;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerPositionId that = (PlayerPositionId) o;
        return Objects.equals(playerId, that.playerId) && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, position);
    }
}

package com.venefast.springboot.soccer.app.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PlayerGuardianId implements Serializable {

    @Column(name = "id_jugador")
    private Long playerId;

    @Column(name = "id_tutor")
    private Long guardianId;

    public PlayerGuardianId() {
    }

    public PlayerGuardianId(Long playerId, Long guardianId) {
        this.playerId = playerId;
        this.guardianId = guardianId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(Long guardianId) {
        this.guardianId = guardianId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerGuardianId that = (PlayerGuardianId) o;
        return Objects.equals(playerId, that.playerId) && Objects.equals(guardianId, that.guardianId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, guardianId);
    }
}

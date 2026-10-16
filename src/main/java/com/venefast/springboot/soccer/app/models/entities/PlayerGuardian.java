package com.venefast.springboot.soccer.app.models.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "jugadores_tutores")
public class PlayerGuardian {

    @EmbeddedId
    private PlayerGuardianId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("playerId")
    @JoinColumn(name = "id_jugador", nullable = false)
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("guardianId")
    @JoinColumn(name = "id_tutor", nullable = false)
    private Guardian guardian;

    @Column(name = "relacion", nullable = false, length = 50)
    private String relationship;

    public PlayerGuardian() {
    }

    public PlayerGuardian(Player player, Guardian guardian, String relationship) {
        this.player = player;
        this.guardian = guardian;
        this.relationship = relationship;
        this.id = new PlayerGuardianId(player != null ? player.getId() : null, guardian != null ? guardian.getId() : null);
    }

    public PlayerGuardian(PlayerGuardianId id, Player player, Guardian guardian, String relationship) {
        this.id = id;
        this.player = player;
        this.guardian = guardian;
        this.relationship = relationship;
    }

    public PlayerGuardianId getId() {
        return id;
    }

    public void setId(PlayerGuardianId id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        if (player != null) {
            if (this.id == null) {
                this.id = new PlayerGuardianId();
            }
            this.id.setPlayerId(player.getId());
        }
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
        if (guardian != null) {
            if (this.id == null) {
                this.id = new PlayerGuardianId();
            }
            this.id.setGuardianId(guardian.getId());
        }
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerGuardian that = (PlayerGuardian) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

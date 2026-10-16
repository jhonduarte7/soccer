package com.venefast.springboot.soccer.app.models.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "jugador_posiciones")
public class PlayerPosition {

    @EmbeddedId
    private PlayerPositionId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("playerId")
    @JoinColumn(name = "id_jugador", nullable = false)
    private Player player;

    public PlayerPosition() {
    }

    public PlayerPosition(Player player, String position) {
        this.player = player;
        this.id = new PlayerPositionId(player != null ? player.getId() : null, position);
    }

    public PlayerPosition(PlayerPositionId id, Player player) {
        this.id = id;
        this.player = player;
    }

    public PlayerPositionId getId() {
        return id;
    }

    public void setId(PlayerPositionId id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        if (player != null) {
            if (this.id == null) {
                this.id = new PlayerPositionId();
            }
            this.id.setPlayerId(player.getId());
        }
    }

    public String getPosition() {
        return id != null ? id.getPosition() : null;
    }

    public void setPosition(String position) {
        if (this.id == null) {
            this.id = new PlayerPositionId();
        }
        this.id.setPosition(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerPosition that = (PlayerPosition) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

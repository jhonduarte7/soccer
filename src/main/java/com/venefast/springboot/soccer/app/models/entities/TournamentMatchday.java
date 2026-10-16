package com.venefast.springboot.soccer.app.models.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "torneo_jornadas")
public class TournamentMatchday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jornada")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_torneo", nullable = false)
    private Tournament tournament;

    @Column(name = "equipo_local", nullable = false, length = 100)
    private String homeTeam;

    @Column(name = "equipo_visitante", nullable = false, length = 100)
    private String awayTeam;

    @Column(name = "fecha_hora_partido", nullable = false)
    private LocalDateTime matchDateTime;

    @Column(name = "ubicacion", nullable = false, length = 150)
    private String location;

    public TournamentMatchday() {
    }

    public TournamentMatchday(Long id, Tournament tournament, String homeTeam, String awayTeam, LocalDateTime matchDateTime, String location) {
        this.id = id;
        this.tournament = tournament;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchDateTime = matchDateTime;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public LocalDateTime getMatchDateTime() {
        return matchDateTime;
    }

    public void setMatchDateTime(LocalDateTime matchDateTime) {
        this.matchDateTime = matchDateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TournamentMatchday that = (TournamentMatchday) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

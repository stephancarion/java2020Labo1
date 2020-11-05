package be.technifutur.java2020.gestionStage.stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class ActiviteModel {
    private String nom;
    private LocalDateTime debut;
    private int duree; // en minutes

    public ActiviteModel(String nom, LocalDateTime debut, int duree){
        this.nom = nom;
        this.debut = debut;
        this.duree = duree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActiviteModel that = (ActiviteModel) o;
        return Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    public String getNom() {
        return nom;
    }

    public LocalDateTime getDebut() {
        return debut;
    }

    public int getDuree() {
        return duree;
    }

    public LocalDateTime fin(){
        LocalDateTime fin = this.debut.plusMinutes(this.duree);
        return fin;
    }
}

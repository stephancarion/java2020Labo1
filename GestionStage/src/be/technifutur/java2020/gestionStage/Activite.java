package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.exception.ChaineDeCaractereVideException;
import be.technifutur.java2020.gestionStage.exception.DureeNegativeOuEgaleAZeroException;

import java.time.LocalDateTime;
import java.util.Objects;

public class Activite implements Comparable{
    private String nom;
    private LocalDateTime debut;
    private int duree; // en minutes

    public Activite(String nom, LocalDateTime debut, int duree) throws ChaineDeCaractereVideException, DureeNegativeOuEgaleAZeroException {
        if (nom.length() >0){
            this.nom = nom;
        }else{
            throw new ChaineDeCaractereVideException();
        }

        this.debut = debut;

        if (duree > 0) {
            this.duree = duree;
        }else{
            throw new DureeNegativeOuEgaleAZeroException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activite that = (Activite) o;
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

    public LocalDateTime getFin(){
        return debut.plusMinutes(duree);
    }

    public int getDuree() {
        return duree;
    }

    @Override
    public int compareTo(Object o) {
        Activite otherActivite = (Activite) o;
        return this.debut.compareTo(otherActivite.getDebut());
    }

    @Override
    public String toString() {
        return "Activite{" +
                "nom='" + nom + '\'' +
                ", debut=" + debut +
                ", duree=" + duree +
                '}';
    }
}

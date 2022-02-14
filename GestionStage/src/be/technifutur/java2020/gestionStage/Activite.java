package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.exception.ChaineDeCaractereVideException;
import be.technifutur.java2020.gestionStage.exception.DureeNegativeOuEgaleAZeroException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;

public class Activite implements Comparable<Activite>, Serializable {
    private String nom;
    private LocalDateTime debut;
    private int duree; // en minutes
    private HashSet<Participant> inscriptions;
    private double prix = 0;


    public Activite(String nom, LocalDateTime debut, int duree) throws ChaineDeCaractereVideException, DureeNegativeOuEgaleAZeroException {
        if (nom.length() >0){
            this.nom = nom;
        }else{
            throw new ChaineDeCaractereVideException("Le nom de l'activité n'a pas été fourni pour créer celle-ci.");
        }

        this.debut = debut;

        if (duree > 0) {
            this.duree = duree;
        }else{
            throw new DureeNegativeOuEgaleAZeroException();
        }
        inscriptions = new HashSet<>();
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

    public void addParticipant(Participant p){
        inscriptions.add(p);
    }

    public int compareTo(Activite a) {
        return this.debut.compareTo(a.getDebut());
    }

    @Override
    public String toString() {
        return "Activite{" +
                "nom='" + nom + '\'' +
                ", debut=" + debut +
                ", duree=" + duree +
                '}';
    }

    public double getPrix() {
        return this.prix;
    }

    public void setPrix(double prix){
        this.prix=prix;
    }



}

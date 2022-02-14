package be.technifutur.java2020.gestionStage.stage;

import be.technifutur.java2020.gestionStage.Activite;
import be.technifutur.java2020.gestionStage.exception.ActiviteDejaExistanteDansCeStageException;
import be.technifutur.java2020.gestionStage.exception.ChaineDeCaractereVideException;
import be.technifutur.java2020.gestionStage.exception.DateDeFinNonValideException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.TreeSet;

public class StageModel implements Serializable {
    private String nom;
    private LocalDateTime debut;
    private LocalDateTime fin;
    private HashMap<String, Activite> activiteMap = new HashMap<>();

    public StageModel(String nom, LocalDateTime debut, LocalDateTime fin) throws ChaineDeCaractereVideException, DateDeFinNonValideException {
        if (nom.length() > 0 && fin.compareTo(debut) > 0){
            this.nom =nom;
            this.debut =debut;
            this.fin =fin;
        }

        if(nom.length() == 0){
            throw new ChaineDeCaractereVideException("Impossible de définir un stage sans nom");
        }

        if (fin.compareTo(debut) <= 0){
            throw new DateDeFinNonValideException("Impossible de définir un stage avec une fin antérieure ou égale au début");
        }
    }

    public String getNom() {
        return nom;
    }

    public LocalDateTime getDebut() {
        return debut;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setNom(String nom) throws ChaineDeCaractereVideException {
        if (nom.length() > 0){
            this.nom = nom;
        }else{
            throw new ChaineDeCaractereVideException("Impossible de modifier un stage sans nom");
        }

    }

    public void setDebut(LocalDateTime debut) throws DateDeFinNonValideException {
        if (this.fin.compareTo(debut) > 0){
            this.debut = debut;
        }else{
            throw new DateDeFinNonValideException("Impossible de modifier un stage avec un début postérieur ou égale à la fin");
        }

    }

    public void setFin(LocalDateTime fin) throws DateDeFinNonValideException {
        if (fin.compareTo(this.debut) > 0){
            this.fin = fin;
        }else{
            throw new DateDeFinNonValideException("Impossible de modifier un stage avec une fin antérieure ou égale au début");
        }

    }


    public boolean containActivite (String nomActivite){
        return activiteMap.containsKey(nomActivite);
    }

    public TreeSet<Activite> getActiviteSetOrderedByDateHeureDebut(){
        TreeSet<Activite> activiteTreeSet = new TreeSet<Activite>();
        for (Activite activite: activiteMap.values()) {
            activiteTreeSet.add(activite);
        }
        return activiteTreeSet;
    }

    public void addActivite (Activite activite) throws ActiviteDejaExistanteDansCeStageException {
        String nom = activite.getNom();
        if (activiteMap.containsKey(nom)){
            throw new ActiviteDejaExistanteDansCeStageException();
        }else{
            activiteMap.put(nom, activite);
        }
    }

}

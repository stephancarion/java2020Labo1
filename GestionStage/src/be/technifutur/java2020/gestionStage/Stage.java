package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.exception.ActiviteDejaExistanteDansCeStageException;
import be.technifutur.java2020.gestionStage.exception.ChaineDeCaractereVideException;
import be.technifutur.java2020.gestionStage.exception.DateDeFinNonValideException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.TreeSet;

public class Stage implements Serializable {
    private String name;
    private LocalDateTime debut;
    private LocalDateTime fin;
    private HashMap<String, Activite> activiteMap = new HashMap<>();

    public Stage(String name, LocalDateTime debut, LocalDateTime fin) throws ChaineDeCaractereVideException, DateDeFinNonValideException {
        if (name.length() > 0){
            this.name=name;
        }
        else{
            throw new ChaineDeCaractereVideException();
        }

        if (fin.compareTo(debut) > 0){
            this.debut =debut;
            this.fin =fin;
        }else{
            throw new DateDeFinNonValideException();
        }
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateDebut() {
        return debut.toLocalDate();
    }

    public LocalTime getHeureDebut() {
        return debut.toLocalTime();
    }

    public LocalDate getDateFin() {
        return fin.toLocalDate();
    }

    public LocalTime getHeureFin() {
        return fin.toLocalTime();
    }

    public LocalDateTime getDebut() {
        return debut;
    }

    public LocalDateTime getFin() {
        return fin;
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

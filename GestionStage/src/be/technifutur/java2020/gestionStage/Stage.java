package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.Activite;
import be.technifutur.java2020.gestionStage.exception.ActiviteDejaExistanteDansCeStageException;
import be.technifutur.java2020.gestionStage.exception.ChaineDeCaractereVideException;
import be.technifutur.java2020.gestionStage.exception.DateDeFinNonValideException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Stage {
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

    public Set<String> getCleActiviteSet(){
        return activiteMap.keySet();
    }

    public Activite getActivite(String cle) {
        return activiteMap.get(cle);
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

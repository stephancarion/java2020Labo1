package be.technifutur.java2020.gestionStage.stage;

import be.technifutur.java2020.gestionStage.exception.InvalidEndDateTimeStageException;
import be.technifutur.java2020.gestionStage.exception.notNewActiviteInStageException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;

public class StageModel {
    private String name=null;
    private LocalDateTime debut =null;
    private LocalDateTime fin =null;
    private HashSet<ActiviteModel> activiteSet = new HashSet<>();

    public StageModel(String name, LocalDateTime dateHeureDebut, LocalDateTime dateHeureFin) throws InvalidEndDateTimeStageException {
        this.name=name;
        if (dateHeureFin.compareTo(dateHeureDebut) > 0){
            this.debut =dateHeureDebut;
            this.fin =dateHeureFin;
        }else{
            throw new InvalidEndDateTimeStageException();
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

    public HashSet<ActiviteModel> getActiviteSet() {
        return activiteSet;
    }

    public void addActivite (ActiviteModel activite) throws notNewActiviteInStageException {
        if (activiteSet.contains(activite)){
            throw new notNewActiviteInStageException();
        }else{
            activiteSet.add(activite);
        }
    }

}

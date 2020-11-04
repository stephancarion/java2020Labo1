package be.technifutur.java2020.gestionStage.stage;

import be.technifutur.java2020.gestionStage.exception.emptyNameStageException;
import be.technifutur.java2020.gestionStage.exception.invalidEndDateTimeStageException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class StageModel {
    private String name;
    private LocalDateTime dateHeureDebut;
    private LocalDateTime dateHeureFin;

    public StageModel(String name, LocalDateTime dateHeureDebut, LocalDateTime dateHeureFin){
        if(name.length() > 0){
            this.name=name;
            if (dateHeureFin.compareTo(dateHeureDebut) > 0){
                this.dateHeureDebut=dateHeureDebut;
                this.dateHeureFin=dateHeureFin;
            }else{
                //TODO
                //throw new invalidEndDateTimeStageException();
            }
        }else{
            // TODO
            //throw new emptyNameStageException();
        }
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateDebut() {
        return dateHeureDebut.toLocalDate();
    }

    public LocalTime getHeureDebut() {
        return dateHeureDebut.toLocalTime();
    }

    public LocalDate getDateFin() {
        return dateHeureFin.toLocalDate();
    }

    public LocalTime getHeureFin() {
        return dateHeureFin.toLocalTime();
    }


}

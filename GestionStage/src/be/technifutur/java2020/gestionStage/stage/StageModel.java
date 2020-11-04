package be.technifutur.java2020.gestionStage.stage;

import be.technifutur.java2020.gestionStage.exception.emptyNameStageException;
import be.technifutur.java2020.gestionStage.exception.invalidEndDateTimeStageException;

import java.time.LocalDateTime;

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
                throw new invalidEndDateTimeStageException();
            }
        }else{
            throw new emptyNameStageException();
        }
    }
}

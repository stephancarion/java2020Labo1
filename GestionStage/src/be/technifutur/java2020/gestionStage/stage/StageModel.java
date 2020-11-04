package be.technifutur.java2020.gestionStage.stage;

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
                throw invalidEndDateTimeStageException;
            }
        }else{
            throw emptyNameStageException;
        }
    }
}

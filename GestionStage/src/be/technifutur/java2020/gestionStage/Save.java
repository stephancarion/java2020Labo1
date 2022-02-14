package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.stage.StageModel;

import java.io.*;
import java.util.HashSet;

public class Save {

    private Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    public void saveStages(){
        File stageFile = new File("stages.ser");

        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(stageFile))){

            HashSet<StageModel> stageModels = model.getStageSet();

            stream.writeObject(stageModels);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveParticipants(){
        File participantFile = new File ("participants.ser");

        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(participantFile))){
            HashSet<Participant> participants = new HashSet<>();

            for (Participant participant: model.getParticipantCollection()) {
                participants.add(participant);

            }

            stream.writeObject(participants);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

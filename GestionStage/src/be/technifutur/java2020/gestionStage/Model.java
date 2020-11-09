package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.exception.ParticipantDejaExistantException;

import java.util.HashMap;
import java.util.Set;

public class Model {
    private HashMap<Integer, Stage> stageMap = new HashMap<>();
    private static int cptStage=0;
    private HashMap<String, Participant> participantMap = new HashMap<>();

    public Set<Integer> getCleStageSet(){
        return stageMap.keySet();
    }

    public static int getCptStage() {
        return cptStage;
    }

    public Stage getStage(Integer cle) {
        return stageMap.get(cle);
    }

    public Integer addStage(Stage stage){
        cptStage++;
        stageMap.put(cptStage,stage);
        return cptStage;
    }

    public Participant getParticipant(String nomParticipant, String prenomParticipant){
        String cle = nomParticipant + " " + prenomParticipant;
        return participantMap.get(cle);
    }

    public void addParticipant(Participant participant) throws ParticipantDejaExistantException{
        if (! containParticipant(participant)){
            String cle = participant.getNom() + " " + participant.getPrenom();
            participantMap.put(cle, participant);
        }else{
            throw new ParticipantDejaExistantException();
        }
    }

    public boolean containParticipant(Participant participant){
        String cle = participant.getNom() + " " + participant.getPrenom();
        return participantMap.containsKey(cle);
    }
}

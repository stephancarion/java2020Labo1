package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.exception.ParticipantDejaExistantException;

import java.util.*;

public class Model {
    private HashMap<String, Stage> stageMap = new HashMap<>();
    private LinkedHashSet<Stage> stageSet;
    private static int cptStage=0;
    private HashMap<String, Participant> participantMap = new HashMap<>();

     public static int getNbStage() {
        return cptStage;
    }

    // Renvoie la clé du stage ajouté
    public String addStage(Stage stage){
        String cleNom = stage.getName();
        stageMap.put(cleNom,stage);
        cptStage++;
        return cleNom;
    }

    public Stage getStage(String nomStage) {
        return stageMap.get(nomStage);
    }



    public HashSet<Stage> getStageSet(){
        if (stageSet == null){
            stageSet = new LinkedHashSet<>();

            for (Stage stage: stageMap.values()) {
                stageSet.add(stage);
            }
        }
        return stageSet;
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

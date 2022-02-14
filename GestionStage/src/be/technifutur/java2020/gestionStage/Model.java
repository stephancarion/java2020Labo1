package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.exception.ParticipantDejaExistantException;
import be.technifutur.java2020.gestionStage.stage.StageModel;

import java.util.*;

public class Model {
    private HashMap<String, StageModel> stageMap = new HashMap<>();
    private static int cptStage=0;
    private HashMap<String, Participant> participantMap = new HashMap<>();

    public static int getNbStage() {
        return cptStage;
    }

    // Renvoie la clé du stage ajouté
    public String addStage(StageModel stageModel){
        String cleNom = stageModel.getNom();
        stageMap.put(cleNom, stageModel);
        cptStage++;
        return cleNom;
    }

    public StageModel getStage(String nomStage) {
        return stageMap.get(nomStage);
    }

    public HashSet<StageModel> getStageSet(){
        LinkedHashSet<StageModel> stageModelSet = new LinkedHashSet<>();
        for (StageModel stageModel : stageMap.values()) {
            stageModelSet.add(stageModel);
        }
        return stageModelSet;
    }

    public Participant getParticipant(String nomParticipant, String prenomParticipant){
        String cle = nomParticipant.toLowerCase() + " " + prenomParticipant.toLowerCase();
        return participantMap.get(cle);
    }

    public Collection<Participant> getParticipantCollection(){
        return participantMap.values();
    }

    public void addParticipant(Participant participant) throws ParticipantDejaExistantException{
        if (! containParticipant(participant)){
            String cle = participant.getNom().toLowerCase() + " " + participant.getPrenom().toLowerCase();
            participantMap.put(cle, participant);
        }else{
            throw new ParticipantDejaExistantException();
        }
    }

    public boolean containParticipant(Participant participant){
        String cle = participant.getNom().toLowerCase() + " " + participant.getPrenom().toLowerCase();
        return participantMap.containsKey(cle);
    }

    public boolean containParticipant(String nomParticipant, String prenomParticipant){
        String cle = nomParticipant.toLowerCase() + " " + prenomParticipant.toLowerCase();
        return participantMap.containsKey(cle);
    }

}

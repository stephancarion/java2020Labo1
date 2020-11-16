package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.exception.ParticipantDejaExistantException;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;

public class Read {
    Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    public void stagesRead(){
        File stageFile = new File("stages.ser");

        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(stageFile));){

            HashSet<Stage> stages ;
            Object o = stream.readObject();
            if (o instanceof HashSet){
                stages = (HashSet<Stage>) o;
                for (Stage stage: stages) {
                    model.addStage(stage);
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("La sauvegarde des stages n'a pas été chargée");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void participantsRead(){
        File participantFile = new File("participants.ser");

        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(participantFile));){

            Collection<Participant> participants;
            Object o = stream.readObject();
            if (o instanceof Collection){
                participants = (Collection<Participant>) o;
                for (Participant participant: participants) {
                    model.addParticipant(participant);
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("La sauvegarde des participants n'a pas été chargée");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ParticipantDejaExistantException e) {
            e.printStackTrace();
        }
    }
}

package be.technifutur.java2020.gestionStage;

import java.io.*;
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
}

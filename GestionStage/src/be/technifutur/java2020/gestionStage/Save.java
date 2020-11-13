package be.technifutur.java2020.gestionStage;

import java.io.*;
import java.util.HashSet;

public class Save {


    private Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    public void stagesSave(){
        File stageFile = new File("stages.ser");

        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(stageFile))){
            HashSet<Stage> stages = model.getStageSet();
            stream.writeObject(stages);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

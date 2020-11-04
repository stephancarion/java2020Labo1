package be.technifutur.java2020.gestionStage.stage;

import be.technifutur.java2020.gestionStage.exception.StageException;

import java.time.format.DateTimeFormatter;

public class StageVue {
    private StageModel model;

    public void setModel(StageModel model) {
        this.model = model;
    }

    public void afficheInfoStageAjoute(){
        String texteAffichage;
        try{
            texteAffichage = "\n"+
                    "*************Stage ajouté ************\n"+
                    "* Nom du stage : " + model.getName() + "\n" +
                    "* Debut : " + model.getDateDebut().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " à "+ model.getHeureDebut()+"\n" +
                    "* Fin : " + model.getDateFin().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " à "+ model.getHeureFin()+"\n" +
                    "**************************************\n";
        } catch (NullPointerException e){
            texteAffichage = "\n"+
                    "***********************\n"+
                    "* Pas de stage trouvé *\n" +
                    "***********************\n";
        }

        System.out.println(texteAffichage);
    }

}

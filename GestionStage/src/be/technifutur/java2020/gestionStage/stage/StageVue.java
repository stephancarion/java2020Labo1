package be.technifutur.java2020.gestionStage.stage;

import be.technifutur.java2020.gestionStage.exception.StageException;

public class StageVue {
    private StageModel model;

    public void setModel(StageModel model) {
        this.model = model;
    }

    public void afficheInfoStageAjoute(){
        String texteAffichage;
        try{
            texteAffichage = ""+
                    "*************Stage ajouté ************\n"+
                    "* Nom du stage : " + model.getName() + "*\n" +
                    "* Debut : " + model.getDateDebut().format() + " à "+ model.getHeureDebut()+"*\n" +
                    "* Fin : " + model.getDateFin() + " à "+ model.getHeureFin()+"*\n" +
                    "**************************************";
        } catch (NullPointerException e){
            texteAffichage = ""+
                    "***********************\n"+
                    "* Pas de stage trouvé *\n" +
                    "***********************\n";
        }

        System.out.println(texteAffichage);
    }

}

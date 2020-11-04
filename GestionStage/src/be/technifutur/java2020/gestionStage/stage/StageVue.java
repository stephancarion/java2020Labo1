package be.technifutur.java2020.gestionStage.stage;

import be.technifutur.java2020.gestionStage.exception.StageException;

public class StageVue {
    private StageModel model;

    public void setModel(StageModel model) {
        this.model = model;
    }

    public void afficheInfoStage(){
        String texteAffichage = ""+
                "**************************************"+
                " Nom du stage : " + model.getName() + "\n" +
                " Debut : " + model.getDateDebut() + " à "+ model.getHeureDebut()+"\n" +
                " Fin : " + model.getDateFin() + " à "+ model.getHeureFin()+"\n" +
                "**************************************";

        System.out.println(texteAffichage);
    }

    public void afficheConsigneAjoutNom() {
    }

    public void afficheConsigneAjoutDateHeureDebut() {
    }

    public void afficheConsigneAjoutDateHeureFin() {
    }
}

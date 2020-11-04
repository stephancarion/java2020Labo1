package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.stage.StageCtrl;
import be.technifutur.java2020.gestionStage.stage.StageModel;
import be.technifutur.java2020.gestionStage.stage.StageVue;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        StageCtrl ctrl = new StageCtrl();
        StageVue vue = new StageVue();
        HashMap<Integer, StageModel> map = new HashMap<>();
        Integer cpt=0;

        vue.afficheInfoStageAjoute();

        for(cpt=1; cpt < 5; cpt++){
            StageModel stageModel = ctrl.ajoutStage();
            map.put(cpt,stageModel);
            vue.setModel(map.get(cpt));
            vue.afficheInfoStageAjoute();
        }

    }
}

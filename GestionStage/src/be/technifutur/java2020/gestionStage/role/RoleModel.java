package be.technifutur.java2020.gestionStage.role;

import be.technifutur.java2020.gestionStage.stage.StageModel;

import java.util.HashMap;

public class RoleModel {
    private HashMap<Integer, StageModel> mapStage = new HashMap<>();
    private static Integer cleStage=0;
    private static int cptStage=0;

    public HashMap<Integer, StageModel> getMapStage() {
        return mapStage;
    }

    public static Integer getCleStage() {
        return cleStage;
    }

    public static int getCptStage() {
        return cptStage;
    }

    public void addStage(StageModel stage){
        cleStage=cleStage+1;
        mapStage.put(cleStage,stage);
        cptStage++;
    }
}

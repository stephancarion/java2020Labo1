package be.technifutur.java2020.gestionStage;

import java.util.HashMap;
import java.util.Set;

public class Model {
    private HashMap<Integer, Stage> stageMap = new HashMap<>();
    private static int cptStage=0;

    public Set<Integer> getCleStageSet(){
        return stageMap.keySet();
    }

    public static int getCptStage() {
        return cptStage;
    }

    public Stage getStage(Integer cle) {
        return stageMap.get(cle);
    }

    public Integer addStage(Stage stage){
        cptStage++;
        stageMap.put(cptStage,stage);
        return cptStage;
    }
}

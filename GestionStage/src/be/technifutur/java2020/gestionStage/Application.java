package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.exception.PositionInvalideException;

public class Application implements Runnable{
    private MenuVue vue;
    private MenuCtrl ctrl;
    private MenuModel model;

    public void setVue(MenuVue vue) {
        this.vue = vue;
    }

    public void setCtrl(MenuCtrl ctrl) {
        this.ctrl = ctrl;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    @Override
    public void run() {
        vue.affiche("Menu général");
        if (model.nbItem() > 0){
            ctrl.run();
            int choix = ctrl.getDernierChoixValide();
            if (choix != 0){
                Item item = null;
                try {
                    item = model.getItem(choix);
                } catch (PositionInvalideException e) {
                    System.out.println("Item non trouvé");
                }
                item.run();
            }
        }
    }

    public static void stop(){
        System.out.println("Au revoir !");
    }

    public Item choixToItem
}

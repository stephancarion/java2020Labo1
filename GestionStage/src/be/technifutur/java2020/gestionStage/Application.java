package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.exception.PositionInvalideException;

import java.util.Optional;

public class Application implements Runnable{
    private Optional<MenuModel> modelPrecedent;
    private MenuModel modelEnCours;
    private MenuVue vue;
    private MenuCtrl ctrl;
    private String titre;
    private Read read;

    public void setModelPrecedent(Optional<MenuModel> modelPrecedent) {
        this.modelPrecedent = modelPrecedent;
    }

    public void setModelEnCours(MenuModel modelEnCours) {
        this.modelEnCours = modelEnCours;
    }

    public void setVue(MenuVue vue) {
        this.vue = vue;
    }

    public void setCtrl(MenuCtrl ctrl) {
        this.ctrl = ctrl;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setRead(Read read) {
        this.read = read;
    }

    @Override
    public void run() {
        read.stagesRead();

        boolean sortie=false;
        int choix = 0;
        Item item;
        do{
            vue.affiche(this.titre);
            if (modelEnCours.nbItem() > 0){
                ctrl.run();
                choix = ctrl.getDernierChoix();
                if (choix > 0 && choix <= modelEnCours.nbItem()){
                    try {
                        item = modelEnCours.getItem(choix);
                        Object o = item.run();
                        if (o instanceof MenuModel){
                            modelPrecedent = Optional.of(modelEnCours);
                            modelEnCours = (MenuModel) o;
                            vue.setModel(modelEnCours);
                            ctrl.setModel(modelEnCours);
                            this.titre = "Menu " + modelEnCours.getNomMenu();
                        }
                    } catch (PositionInvalideException e) {
                        System.out.println("Item non trouvé. Choix non valide");
                    }
                } else if (choix == 0 && modelPrecedent.isPresent()){
                    modelEnCours = modelPrecedent.get();
                    modelPrecedent = modelEnCours.getMenuModelPrecedent();
                    vue.setModel(modelEnCours);
                    ctrl.setModel(modelEnCours);
                    this.titre = "Menu " + modelEnCours.getNomMenu();
                } else if (choix == 0 && modelPrecedent.isEmpty()){
                    stop();
                    sortie=true;
                }
            }else if (modelEnCours.nbItem() == 0){
                modelEnCours = modelPrecedent.get();
                modelPrecedent = modelEnCours.getMenuModelPrecedent();
                vue.setModel(modelEnCours);
                ctrl.setModel(modelEnCours);
                this.titre = "Menu " + modelEnCours.getNomMenu();
            }
        }while(! sortie);
    }

    public static void stop(){
        String affiche = "\n"+
                        "***************\n" +
                        "* Au revoir ! *\n" +
                        "***************\n" ;

        System.out.println(affiche);
    }

}

package be.technifutur.java2020.gestionStage;

public class Factory {
    Ctrl ctrl;
    Model model;
    Vue vue;

    public Ctrl getCtrl() {
        if (ctrl == null){
            ctrl= new Ctrl();
            ctrl.setModel(getModel());
            ctrl.setVue(getVue());
        }
        return ctrl;
    }

    public Model getModel() {
        if (model == null){
            model= new Model();
        }
        return model;
    }

    public Vue getVue() {
        if (vue == null) {
            vue = new Vue();
            vue.setModel(model);
        }
        return vue;
    }
}

package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.vue.ConsignesVue;

import java.util.Scanner;

public class Factory {
    Scanner scanner;
    Ctrl ctrl;
    Model model;
    Vue vue;
    ConsignesVue consignesVue;

    public Scanner getScanner() {
        if (scanner == null){
            scanner =new Scanner(System.in);
        }
        return scanner;
    }

    public Ctrl getCtrl() {
        if (ctrl == null){
            ctrl= new Ctrl();
            ctrl.setScanner(getScanner());
            ctrl.setModel(getModel());
            ctrl.setVue(getVue());
            ctrl.setConsignesVue(getConsignesVue());
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

    public ConsignesVue getConsignesVue() {
        if (consignesVue == null){
            consignesVue = new ConsignesVue();
        }
        return consignesVue;
    }
}

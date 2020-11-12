package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.vue.ConsignesVue;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.Scanner;

public class Factory {
    private Application app;
    private Scanner scanner;
    private Ctrl ctrl;
    private Model model;
    private Vue vue;
    private ConsignesVue consignesVue;
    private MenuVue menuVue;
    private MenuCtrl menuCtrl;

    private MenuModel menuGeneralModel;
    private MenuModel menuVisiteurModel;
    private MenuModel menuParticipantModel;
    private MenuModel menuInscritModel;
    private MenuModel menuOrganisateurModel;
    private MenuModel menuTresorierModel;
    private MenuModel menuSecretariatModel;

    public Application getApp() {
        if (app == null){
            app = new Application();
            app.setVue(getMenuVue());
            app.setCtrl(getMenuCtrl());
            app.setModelPrecedent(Optional.empty());
            app.setTitre("Menu Général");
            app.setModelEnCours(getMenuGeneralModel());
            getMenuVue().setModel(getMenuGeneralModel());
            getMenuCtrl().setModel(getMenuGeneralModel());
        }
        return app;
    }

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

    public MenuVue getMenuVue() {
        if (menuVue == null) {
            menuVue = new MenuVue();
        }
        return menuVue;
    }

    public MenuCtrl getMenuCtrl() {
        if (menuCtrl == null){
            menuCtrl = new MenuCtrl();
            menuCtrl.setScanner(getScanner());
        }
        return menuCtrl;
    }

    public MenuModel getMenuGeneralModel() {
        if (menuGeneralModel == null){
            menuGeneralModel = new MenuModel("Général");
            menuGeneralModel.setMenuModelPrecedent(Optional.empty());
            Item item1 = new Item("Visiteur",getMenuVisiteurModel(),"getModel");
            Item item2 = new Item("Participant",getMenuParticipantModel(),"getModel");
            Item item3 = new Item("Inscrit",getMenuInscritModel(),"getModel");
            Item item4 = new Item("Organisateur",getMenuOrganisateurModel(),"getModel");
            Item item5 = new Item("Trésorier",getMenuTresorierModel(),"getModel");
            Item item6 = new Item("Secrétariat",getMenuSecretariatModel(),"getModel");
            Item[] itemTab = {item1, item2, item3, item4, item5, item6};
            for (Item item:itemTab) {
                menuGeneralModel.addItem(item);
            }
        }
        return menuGeneralModel;
    }

    /*public MenuModel getMenuGeneralModel() {
        if (menuGeneralModel == null){
            Item item1 = new Item("Visiteur",getMenuVisiteurModel(),"getModel");
            Item item2 = new Item("Participant",getMenuParticipantModel(),"getModel");
            Item item3 = new Item("Inscrit",getMenuInscritModel(),"getModel");
            Item item4 = new Item("Organisateur",getMenuOrganisateurModel(),"getModel");
            Item item5 = new Item("Trésorier",getMenuTresorierModel(),"getModel");
            Item item6 = new Item("Secrétariat",getMenuSecretariatModel(),"getModel");
            Item[] itemTab = {item1, item2, item3, item4, item5, item6};
            menuGeneralModel = new MenuModel("Général", itemTab);
            menuGeneralModel.setMenuModelPrecedent(Optional.empty());
        }
        return menuGeneralModel;
    }*/

    public MenuModel getMenuVisiteurModel() {
        if (menuVisiteurModel == null){
            Item item1 = new Item("Affiche horaire stage",getCtrl(),"afficheHoraireStage");
            Item[] itemTab ={item1};
            menuVisiteurModel = new MenuModel("Visiteur", itemTab);
            menuVisiteurModel.setMenuModelPrecedent(Optional.of(getMenuGeneralModel()));
        }
        return menuVisiteurModel;
    }

    public MenuModel getMenuParticipantModel() {
        if (menuParticipantModel == null){
            menuParticipantModel = new MenuModel("Participant");
            menuParticipantModel.setMenuModelPrecedent(Optional.of(getMenuGeneralModel()));
            Item[] itemTab= {};
        }
        return menuParticipantModel;
    }

    public MenuModel getMenuInscritModel() {
        if (menuInscritModel == null){
            Item[] itemTab ={};
            menuInscritModel = new MenuModel("Inscrit", itemTab);
            menuInscritModel.setMenuModelPrecedent(Optional.of(getMenuGeneralModel()));
        }
        return menuInscritModel;
    }

    public MenuModel getMenuOrganisateurModel() {
        if (menuOrganisateurModel == null){
            Item item1 = new Item("Ajouter un stage",getCtrl(),"ajoutStage");
            Item item2 = new Item("Ajouter une activité à un stage",getCtrl(),"ajoutActivite");
            Item item3 = new Item("Ajouter une participant",getCtrl(),"ajoutParticipant");
            Item[] itemTab ={item1, item2, item3};
            menuOrganisateurModel = new MenuModel("Organisateur", itemTab);
            menuOrganisateurModel.setMenuModelPrecedent(Optional.of(getMenuGeneralModel()));
        }
        return menuOrganisateurModel;
    }

    public MenuModel getMenuTresorierModel() {
        if (menuTresorierModel == null){
            Item[] itemTab ={};
            menuTresorierModel = new MenuModel("Trésorier", itemTab);
            menuTresorierModel.setMenuModelPrecedent(Optional.of(getMenuGeneralModel()));
        }
        return menuTresorierModel;
    }

    public MenuModel getMenuSecretariatModel() {
        if (menuSecretariatModel == null){
            Item[] itemTab ={};
            menuSecretariatModel = new MenuModel("Secrétariat", itemTab);
            menuSecretariatModel.setMenuModelPrecedent(Optional.of(getMenuGeneralModel()));
        }
        return menuSecretariatModel;
    }
}

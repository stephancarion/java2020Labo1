package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.role.organisateur.OrganisateurCtrl;

public class Main {
    public static void main(String[] args) {
        OrganisateurCtrl organisateurCtrl = new OrganisateurCtrl();

        organisateurCtrl.start();
    }
}

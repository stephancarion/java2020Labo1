package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.Item;
import be.technifutur.java2020.gestionStage.MenuCtrl;

import java.util.LinkedHashSet;
import java.util.Optional;

public class MenuVue {
    private MenuModel model;

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public void affiche(String titre) {
        LinkedHashSet<String> itemNameSet = this.model.itemNameSet();
        String affiche ="";
        int cptItem = 0;

        // Titre du menu
        affiche += "**********" + titre +"**********\n";

        // Liste des items si au moins 1 item dans le set
        if (model.nbItem() > 0) {
            for (String nom : itemNameSet) {
                cptItem++;
                affiche += " " + cptItem + ". " + nom + "\n";
            }
            // item de sortie
            affiche += " 0. Sortie de ce menu\n";

            // demande un choix
            affiche += "\n";
            affiche += " Votre choix ?";
            System.out.print(affiche);
        // Sinon, message pour signaler qu'il n'y a pas d'items
        }else{
            affiche += " Pas d'item trouvé pour ce menu\n";
            System.out.println(affiche);
        }
    }
}

package be.technifutur.java2020.gestionStage.vue;

import be.technifutur.java2020.gestionStage.Item;
import java.util.LinkedHashSet;

public class MenuVue {
    LinkedHashSet<Item> itemSet;
    //MenuCtrl menuCtrl;

    public void affiche(String titre, LinkedHashSet<Item> itemSet) {
        String affiche ="";
        int cptItem = 0;

        // Titre du menu
        affiche += "**********" + titre +"**********\n";

        // Liste des items si au moins 1 item dans le set
        if (itemSet.size() > 0) {
            for (Item item : itemSet) {
                cptItem++;
                affiche += " " + cptItem + ". " + item + "\n";
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

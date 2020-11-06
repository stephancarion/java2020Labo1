package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.exception.ChaineDeCaractereVideException;

public class Item {
    String nom;

    public Item(String nom) throws ChaineDeCaractereVideException {
        nom = nom.trim();

        if (nom.length()>0){
            this.nom = nom;
        }else{
            throw new ChaineDeCaractereVideException();
        }
    }
}

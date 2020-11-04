package be.technifutur.java2020.gestionStage.common;

public class ConsignesVue {

    public void ajoutNomStage() {
        String consigne = "Entrez le nom du stage : ";
        System.out.print(consigne);
    }

    public void ajoutDateHeureDebutStage() {
        String consigne = "Entrez la date et heure de début de stage au format \"dd/mm/aaaa hh:mm\" : ";
        System.out.print(consigne);
    }

    public void ajoutDateHeureFinStage() {
        String consigne = "Entrez la date et heure de fin de stage au format \"dd/mm/aaaa hh:mm\" : ";
        System.out.print(consigne);
    }
}

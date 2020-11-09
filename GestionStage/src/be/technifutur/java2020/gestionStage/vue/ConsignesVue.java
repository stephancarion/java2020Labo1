package be.technifutur.java2020.gestionStage.vue;

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

    public void accesNumeroStagePourAjoutActivite () {
        String consigne = " Entrez le N° de stage auquel vous souhaitez ajouter une activité : ";
        System.out.print(consigne);
    }

    public void accesNumeroStagePourVoirActivite () {
        String consigne = " Entrez le N° de stage pour lequel vous souhaitez voir les activités : ";
        System.out.print(consigne);
    }

    public void ajoutNomActivite() {
        String consigne = "Entrez le nom de l'activité : ";
        System.out.print(consigne);
    }

    public void ajoutDateHeureDebutActivite() {
        String consigne = "Entrez la date et heure de début de l'activité au format \"dd/mm/aaaa hh:mm\" : ";
        System.out.print(consigne);
    }

    public void ajoutDureeActivite() {
        String consigne = "Entrez la durée de l'activité en minutes : ";
        System.out.print(consigne);
    }
}

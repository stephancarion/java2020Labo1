package be.technifutur.java2020.gestionStage;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class Vue {
    private Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    public void afficheInfosStageAjoute(Integer cle){
        String affiche;
        try{
            Stage stage = model.getStage(cle);
            String nomStage = stage.getName();
            String dateDebut = stage.getDateDebut().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String heureDebut = stage.getHeureDebut().toString();
            String dateFin = stage.getDateFin().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String heureFin = stage.getHeureFin().toString();

            affiche = "\n"+
                    "************ Stage ajouté ************\n"+
                    "* Nom du stage : " + nomStage + "\n" +
                    "* Debut : " + dateDebut + " à "+ heureDebut+"\n" +
                    "* Fin : " + dateFin + " à "+ heureFin+"\n" +
                    "**************************************\n";
        } catch (NullPointerException e){
            affiche = "\n"+
                    "***********************\n"+
                    "* Pas de stage ajouté *\n" +
                    "***********************\n";
        }

        System.out.println(affiche);
    }

    public void afficheStageSet(){
        String affiche="";

        // Séparation avec le bloc précédent
        affiche += "\n";

        // En-tête d'affiche
        affiche += "***********************************************************\n";
        affiche += "******************* Ensemble des stages *******************\n";
        affiche += "***********************************************************\n";

        // En-tête de colonne.
        affiche += " N°\tNom, date et heure de début\n";

        // Lignes des stages disponibles
        Set<Integer> cleStageSet = model.getCleStageSet();
        for (Integer cle : cleStageSet) {
            Stage stage = model.getStage(cle);
            affiche += " "+ cle + "\t" +
                    stage.getName() +", "+
                        stage.getDateDebut().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+
                        " à " + stage.getHeureDebut() + "\n";
        }

        // pied de tableau
        affiche += "***********************************************************\n";

        System.out.println(affiche);
    }

    public void afficheInfoActiviteStage(Integer cleStage){
        String texteAffichage;
        try{
            Stage stage = model.getStage(cleStage);

            String nomStage = stage.getName();
            String dateDebutStage = stage.getDateDebut().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String heureDebutStage = stage.getHeureDebut().toString();
            String dateFinStage=stage.getDateFin().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String heureFinStage = stage.getHeureFin().toString();

            texteAffichage = "\n"+
                    "**************** Stage  ***************\n"+
                    "* Nom du stage : " + nomStage + "\n" +
                    "* Debut : " + dateDebutStage + " à "+ heureDebutStage+"\n" +
                    "* Fin : " + dateFinStage + " à "+ heureFinStage+"\n" +
                    "*********** Liste des activités du stage ***********\n";
            // En-tête de colonne.
            texteAffichage += " Nom, date et heure de début, durée\n";

            Set<String> cleActiviteSet = stage.getCleActiviteSet();
            for (String cleActivite : cleActiviteSet) {
                Activite activite = stage.getActivite(cleActivite);
                String nomActivite = activite.getNom();
                String dateDebutActivite = activite.getDebut().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String heureDebutActivite = activite.getDebut().toLocalTime().toString();
                Integer dureeActivite = activite.getDuree();

                texteAffichage += " "+ nomActivite + ", " +
                        dateDebutActivite+ " à " + heureDebutActivite + ", "+
                        dureeActivite + " minute(s)\n";
            }

            // pied de tableau
            texteAffichage += "***************************************************\n";
        } catch (NullPointerException e){
            texteAffichage = "\n"+
                    "***********************\n"+
                    "* Pas de stage ajouté *\n" +
                    "***********************\n";
        }
        System.out.println(texteAffichage);
    }
}

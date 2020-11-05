package be.technifutur.java2020.gestionStage.role;

import be.technifutur.java2020.gestionStage.stage.ActiviteModel;
import be.technifutur.java2020.gestionStage.stage.StageModel;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;

public class RoleVue {
    private RoleModel roleModel;

    public void setRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
    }

    public void afficheInfoStageAjoute(){
        String texteAffichage;
        try{
            Integer lastCle = RoleModel.getCleStage();
            String nomStage = roleModel.getMapStage().get(lastCle).getName();
            String dateDebut = roleModel.getMapStage().get(lastCle).getDateDebut().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String heureDebut = roleModel.getMapStage().get(lastCle).getHeureDebut().toString();
            String dateFin = roleModel.getMapStage().get(lastCle).getDateFin().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String heureFin = roleModel.getMapStage().get(lastCle).getHeureFin().toString();

            texteAffichage = "\n"+
                    "************ Stage ajouté ************\n"+
                    "* Nom du stage : " + nomStage + "\n" +
                    "* Debut : " + dateDebut + " à "+ heureDebut+"\n" +
                    "* Fin : " + dateFin + " à "+ heureFin+"\n" +
                    "**************************************\n";
        } catch (NullPointerException e){
            texteAffichage = "\n"+
                    "***********************\n"+
                    "* Pas de stage ajouté *\n" +
                    "***********************\n";
        }

        System.out.println(texteAffichage);
    }

    public void afficheStageSet(){
        String affichage="";

        // Séparation avec le bloc précédent
        affichage += "\n";

        // En-tête d'affichage
        affichage += "***********************************************************\n";
        affichage += "******************* Ensemble des stages *******************\n";
        affichage += "***********************************************************\n";

        // En-tête de colonne.
        affichage += " N°\tNom, date et heure de début\n";

        // Lignes des stages disponibles
        for (Integer cle: roleModel.getMapStage().keySet()) {
            StageModel stage = roleModel.getMapStage().get(cle);
            affichage += " "+ cle + "\t" +
                    stage.getName() +", "+
                        stage.getDateDebut().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+
                        " à " + stage.getHeureDebut() + "\n";
        }

        // pied de tableau
        affichage += "***********************************************************\n";

        System.out.println(affichage);
    }

    public void afficheInfoActiviteStage(Integer cleStage){
        String texteAffichage;
        try{
            StageModel stage = roleModel.getMapStage().get(cleStage);
            HashSet<ActiviteModel> activiteSet = stage.getActiviteSet();

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

            for (ActiviteModel activite : activiteSet) {

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

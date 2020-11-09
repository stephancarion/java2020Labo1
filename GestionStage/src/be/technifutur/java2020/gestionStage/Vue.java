package be.technifutur.java2020.gestionStage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

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

    public void afficheHoraireStage(Integer cleStage){
        String affiche = "";
        Stage stage = model.getStage(cleStage);
        TreeSet<Activite> activiteSet = stage.getActiviteSetOrderedByDateHeureDebut();
        Iterator<Activite> iterator = activiteSet.iterator();

        String nomStage = stage.getName();
        String debutStage = stage.getDebut().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String finStage = stage.getFin().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // En-tête d'affichage
        affiche += "Stage : " + nomStage + " (du " + debutStage + " au " + finStage +")\n";
        affiche += "Activités du stage : \n";


        if (activiteSet.size() == 0){ // si pas d'activité pour ce stage
            affiche += "\tPas d'activité pour ce stage\n";
        }else{ // si au moins une activité

            /* Initilisation virtuelle de la date d'une première activité un jour avant le début du stage
            pour que la première activité du stage aie une date différente dans la boucle while
             */
            LocalDate dateDebutActivite = stage.getDateDebut().minusDays(1);


            while (iterator.hasNext()){
                Activite activite = iterator.next();

                if (dateDebutActivite.compareTo(activite.getDebut().toLocalDate()) != 0){
                    dateDebutActivite = activite.getDebut().toLocalDate();
                    String debutActiviteAfficheDate = dateDebutActivite.format(DateTimeFormatter.ofPattern("EE dd L yyyy"));
                    affiche += "\t" + debutActiviteAfficheDate + "\n";
                }

                LocalTime heureDebutActivite = activite.getDebut().toLocalTime();
                String heureDebutActiviteAffiche = heureDebutActivite.format(DateTimeFormatter.ofPattern("HH"))+
                        "h"+ heureDebutActivite.format(DateTimeFormatter.ofPattern("mm"));

                LocalTime heureFinActivite = activite.getFin().toLocalTime();
                String heureFinActiviteAffiche = heureFinActivite.format(DateTimeFormatter.ofPattern("HH"))+
                        "h"+ heureFinActivite.format(DateTimeFormatter.ofPattern("mm"));

                String nomActivite = activite.getNom();

                int dureeActivite = activite.getDuree();

                affiche += "\t\t" +  heureDebutActiviteAffiche + " - " + heureFinActiviteAffiche;
                affiche += " " + nomActivite + " (" + dureeActivite + " minutes)\n";

            }
        }
        System.out.println(affiche);
    }


    public void afficheParticipantAjoute(Participant participant) {
        String affiche;
        try{
            String nom = participant.getNom();
            String prenom = participant.getPrenom();
            String nomClub = participant.getNomClub();
            String mail = participant.getMail();

            affiche = "\n"+
                    "********************** Participant ajouté **********************\n"+
                    "* Nom et prénom : " + nom + " " + prenom + "\n" +
                    "* Nom du club (optionnel) : " + nomClub +"\n" +
                    "* Mail (optionnel) : " + mail +"\n" +
                    "*****************************************************************\n";
        } catch (NullPointerException e){
            affiche = "\n"+
                    "*****************************\n"+
                    "* Pas de participant ajouté *\n" +
                    "*****************************\n";
        }
        System.out.println(affiche);
    }
}

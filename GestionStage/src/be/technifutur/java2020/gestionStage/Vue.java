package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.stage.StageModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Vue {
    private Model model;
    private HashMap<Integer, String> clesStages  = new HashMap<>();
    private TarifStatut tarifStatut;


    public void setModel(Model model) {
        this.model = model;
    }

    public boolean choixStagePossible(int choix){
        return choix >= 1 && choix <= Model.getNbStage();
    }

    public StageModel stageChoisi(int choix){
        return model.getStage(clesStages.get(choix));
    }

    public void afficheStageAjoute(String cle){
        String affiche;
        try{
            StageModel stageModel = model.getStage(cle);

            String nomStage = stageModel.getNom();
            String dateDebut = stageModel.getDebut().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String heureDebut = stageModel.getDebut().toLocalTime().format(DateTimeFormatter.ofPattern("HH'h'mm"));
            String dateFin = stageModel.getFin().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String heureFin = stageModel.getFin().toLocalTime().format(DateTimeFormatter.ofPattern("HH'h'mm"));

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
        int cpt=0;
        String affiche="";

        // Séparation avec le bloc précédent
        affiche += "\n";

        if (Model.getNbStage() > 0) {
            clesStages.clear();
            // En-tête d'affiche
            affiche += "*******************************************************************************\n";
            affiche += "***************************** Ensemble des stages *****************************\n";
            affiche += "*******************************************************************************\n";

            // En-tête de colonne.
            affiche += " N°\t\tNom, date et heure de début\n";

            // Lignes des stages disponibles
            for (StageModel stageModel : model.getStageSet()) {
                cpt++;
                String nom = stageModel.getNom();
                String dateDebut = stageModel.getDebut().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String heureDebut = stageModel.getDebut().toLocalTime().format(DateTimeFormatter.ofPattern("HH'h'mm"));
                String dateFin = stageModel.getFin().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String heureFin = stageModel.getFin().toLocalTime().format(DateTimeFormatter.ofPattern("HH'h'mm"));
                clesStages.put(cpt,nom);

                affiche += " " + cpt + ".\t\t" +
                        nom +
                        " commence le " + dateDebut + " à " + heureDebut +
                        " et se termine le " + dateFin + " à " + heureFin + "\n";
            }

            // pied de tableau
            affiche += "*******************************************************************************\n";
        }else{
            affiche += "*******************************************************************************\n";
            affiche += "*************************** Pas de stage disponible ***************************\n";
            affiche += "*******************************************************************************\n";
        }

        System.out.println(affiche);
    }

    public void afficheHoraireStage(StageModel stageModel){
        String affiche = "";
        TreeSet<Activite> activiteSet = stageModel.getActiviteSetOrderedByDateHeureDebut();
        Iterator<Activite> iterator = activiteSet.iterator();

        String nomStage = stageModel.getNom();
        String debutStage = stageModel.getDebut().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String finStage = stageModel.getFin().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // En-tête d'affichage
        affiche += "Stage : " + nomStage + " (du " + debutStage + " au " + finStage +")\n";
        affiche += "Activités du stage : \n";


        if (activiteSet.size() == 0){ // si pas d'activité pour ce stage
            affiche += "\tPas d'activité pour ce stage\n";
        }else{ // si au moins une activité

            /* Initilisation virtuelle de la date d'une première activité un jour avant le début du stage
            pour que la première activité du stage aie une date différente dans la boucle while
             */
            LocalDate dateDebutActivite = stageModel.getDebut().toLocalDate().minusDays(1);

            while (iterator.hasNext()){
                Activite activite = iterator.next();

                if (dateDebutActivite.compareTo(activite.getDebut().toLocalDate()) != 0){
                    dateDebutActivite = activite.getDebut().toLocalDate();
                    String debutActiviteAfficheDate = dateDebutActivite.format(DateTimeFormatter.ofPattern("EEEE dd LLLL yyyy"));
                    affiche += "\t" + debutActiviteAfficheDate + "\n";
                }

                LocalTime heureDebutActivite = activite.getDebut().toLocalTime();
                String heureDebutActiviteAffiche = heureDebutActivite.format(DateTimeFormatter.ofPattern("HH'h'mm"));

                LocalTime heureFinActivite = activite.getFin().toLocalTime();
                String heureFinActiviteAffiche = heureFinActivite.format(DateTimeFormatter.ofPattern("HH'h'mm"));

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

    public void afficheParticipant(Participant participant) {
        String affiche;
        try{
            String nom = participant.getNom();
            String prenom = participant.getPrenom();
            String nomClub = participant.getNomClub();
            String mail = participant.getMail();
            String statutTarif = participant.getTarifStatut().name().toUpperCase().charAt(0) +
                    participant.getTarifStatut().name().toLowerCase().substring(1);

            affiche = "\n"+
                    "********************* Participant *********************\n"+
                    "* Nom et prénom : " + nom + " " + prenom + "\n" +
                    "* Nom du club (optionnel) : " + nomClub +"\n" +
                    "* Mail (optionnel) : " + mail +"\n" +
                    "* Statut tarifaire : " + statutTarif + "\n" +
                    "********************************************************\n";
        } catch (NullPointerException e){
            affiche = "\n"+
                    "*******************************\n"+
                    "* Ce participant n'existe pas *\n" +
                    "*******************************\n";
        }
        System.out.println(affiche);
    }

    public void afficheActiviteStage(StageModel s, Activite a){
        String affiche = "";
        String nomS = s.getNom();
        String nomA = a.getNom();
        String debutA = a.getDebut().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        String finA = a.getFin().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));


        // En-tête
        affiche += "************************* " + "Résumé activité" + " *************************\n";

        // Stage
        affiche += "* Stage : " + nomS + "\n";

        // Activité
        affiche += "* Activité : " + nomA + " du " + debutA + " au " + finA + "\n";

        // Pied
        affiche += "*******************************************************************\n";

        System.out.println(affiche);
    }

    public void afficheTarifStatut(){
        int numero;
        String nom;
        String affiche = "\n";

        // En-tête
        affiche += "****************** Statuts tarifaires ******************\n";

        // Liste des statuts tarifaires
        for (TarifStatut tarif:TarifStatut.values()) {
            numero = tarif.ordinal() + 1;
            nom = tarif.name().toUpperCase().charAt(0) + tarif.name().toLowerCase().substring(1);
            affiche += "* " + numero + ". " + nom + "\n";
        }

        // Pied
        affiche += "********************************************************";
        System.out.println(affiche);
    }

    public boolean containChoixTarifStatut(int choix){
        return choix >= 1 && choix <= TarifStatut.values().length;
    }

}

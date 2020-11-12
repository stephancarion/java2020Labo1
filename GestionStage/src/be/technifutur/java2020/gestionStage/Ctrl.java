package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.exception.*;
import be.technifutur.java2020.gestionStage.vue.ConsignesVue;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ctrl {
    private Model model;
    private Vue vue;
    private ConsignesVue consignesVue;
    private Scanner scanner;

    private String input;
    private Optional<String> optionalInput;
    private String consigne;
    private Pattern pattern;
    private Matcher matcher;
    private boolean stop;

    public void setModel(Model model) {
        this.model = model;
    }

    public void setVue(Vue vue) {
        this.vue = vue;
    }

    public void setConsignesVue(ConsignesVue consignesVue) {
        this.consignesVue = consignesVue;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    // ajout un stage au modèle
    public void ajoutStage(){
        String nom;
        LocalDateTime debut;
        LocalDateTime fin;
        Optional<LocalDateTime> optionalDate;

        input = getNomStage();

        stop = "0".equalsIgnoreCase(input);
        if (! stop){
            nom = input;

            consigne = "Entrez les date et heure du début du stage (obligatoire)(format : \"dd/mm/aaaa hh:mm\")(sortie : 0 + enter) : ";
            pattern = PatternPerso.pasVideEtDateHeureCourtOuZero;
            optionalDate = inputToDateTimeCourt();

            stop = optionalDate.isEmpty();
            if (! stop){
                debut = optionalDate.get();

                consigne = "Entrez les date et heure de la fin du stage (obligatoire)(format : \"dd/mm/aaaa hh:mm\")(sortie : 0 + enter) : ";
                pattern = PatternPerso.pasVideEtDateHeureCourtOuZero;
                optionalDate = inputToDateTimeCourt();

                stop = optionalDate.isEmpty();
                if (! stop){
                    fin = optionalDate.get();

                    while (fin.compareTo(debut) < 0 && !stop){
                        System.out.println("Date et heure de fin antérieure à date et heure de début");
                        optionalDate = inputToDateTimeCourt();

                        if(optionalDate.isPresent()){
                            fin = optionalDate.get();
                        }else{
                            stop = true;
                        }
                    }

                    if(! stop){
                        try{
                            // création d'un stage et ajout du stage au model + récupération de la cle pour affichage
                            String cle =  model.addStage(new Stage(nom,debut,fin));
                            vue.afficheStageAjoute(cle);
                        }catch ( DateDeFinNonValideException e) {
                            System.out.println("Stage non ajouté : les date et heure de fin sont postérieures au début");
                        }catch (ChaineDeCaractereVideException e) {
                            System.out.println("Stage non ajouté : le nom est vide");
                        }
                    }
                }
            }
        }
    }
    
    // ajout une activité à un stage
    public void ajoutActivite() {
        String nom;
        LocalDateTime debut;
        int duree;

        int choixStage;

        vue.afficheStageSet();
        if (vue.nbStage() > 0){
            consigne = " A quel stage souhaitez-vous ajouter une activité? (sortir : 0 + enter)";
            pattern = PatternPerso.pasVideEtChiffresUniquement;

            input = inputToString();
            choixStage = Integer.parseInt(input);
            while (! (vue.choixStagePossible(choixStage) || choixStage == 0)){
                System.out.println("Ce choix n'est pas dans la liste");
                input = inputToString();
                choixStage = Integer.parseInt(input);
            }

            stop = choixStage == 0;

            if (!stop) {
                Stage stage = vue.stageChoisi(choixStage);

                // Récupération d'un nom d'activité d'au moins 1 caractère
                consigne = "Entrez le nom de l'activité (sortir : 0 + enter) : ";
                pattern = PatternPerso.pasVideEtLettresUniquementOuZero;

                input = inputToString();
                while (stage.containActivite(input)) {
                    input = inputToString();
                }

                stop = "0".equalsIgnoreCase(input);
                if (!stop) {
                    nom = input;

                    consigne = "Entrez la date et heure de début de l'activité (format: \"dd/mm/aaaa hh:mm\") (sortir : 0 + enter) : ";
                    pattern = PatternPerso.pasVideEtDateHeureCourtOuZero;

                    Optional<LocalDateTime> optionalDate = inputToDateTimeCourt();

                    stop = optionalDate.isEmpty();

                    if (!stop) {
                        debut = optionalDate.get();

                        consigne = "Entrez la durée de l'activité en minutes (sortir : 0 + enter) : ";
                        pattern = PatternPerso.pasVideEtChiffresUniquement;

                        input = inputToString();

                        stop = "0".equalsIgnoreCase(input);

                        if (!stop) {
                            duree = Integer.parseUnsignedInt(input);

                            try {
                                stage.addActivite(new Activite(nom, debut, duree));
                            } catch (ChaineDeCaractereVideException e) {
                                System.out.println("Le nom de l'activité est vide");
                            } catch (DureeNegativeOuEgaleAZeroException e) {
                                System.out.println("La durée de l'activité ne peut être négative ou nulle");
                            } catch (ActiviteDejaExistanteDansCeStageException e) {
                                System.out.println("L'activité est déjà existante dans ce stage");
                            }
                        }
                    }
                }
            }
        }
    }

    // afficher l'horaire d'un stage
    public void afficheHoraireStage() {
        int choixStage;

        vue.afficheStageSet();
        if (vue.nbStage() > 0) {
            consigne = " De quel stage souhaitez-vous voir l'horaire? (sortir : 0 + enter)";
            pattern = PatternPerso.pasVideEtChiffresUniquement;

            input = inputToString();
            choixStage = Integer.parseInt(input);
            while (!(vue.choixStagePossible(choixStage) || choixStage == 0)) {
                System.out.println("Ce choix n'est pas dans la liste");
                input = inputToString();
                choixStage = Integer.parseInt(input);
            }

            stop = choixStage == 0;

            if (!stop) {
                vue.afficheHoraireStage(vue.stageChoisi(choixStage));
            }
        }
    }

    // ajout un participant
    public void ajoutParticipant(){
        String nom;
        String prenom;
        Optional<String> nomClub;
        Optional<String> mail;

        input = getNomParticipant();

        stop = "0".equalsIgnoreCase(input);
        if (! stop){
            nom = input;

            input = getPrenomParticipant();

            stop = "0".equalsIgnoreCase(input);
            if (! stop){
                prenom = input;

                optionalInput = getOptionalNomClubParticipant();

                if (optionalInput.isPresent()){
                    input = optionalInput.get();
                    stop = "0".equalsIgnoreCase(input);
                    nomClub = Optional.of(input);
                }else{
                    nomClub = Optional.empty();
                    stop = false;
                }

                if (! stop){
                    optionalInput = getOptionalMailParticipant();

                    if (optionalInput.isPresent()) {
                        input = optionalInput.get();
                        stop = "0".equalsIgnoreCase(input);
                        mail = Optional.of(input);
                    } else {
                        mail = Optional.empty();
                    }

                    if (! stop){
                        try {
                            Participant participant = new Participant(nom, prenom, nomClub, mail);
                            model.addParticipant(participant);
                            vue.afficheParticipantAjoute(participant);
                        } catch (ChaineDeCaractereVideException e) {
                            System.out.println("Impossible de créer le participant car une chaîne de caractère est vide");
                        } catch (ParticipantDejaExistantException e){
                            System.out.println("Un participant avec les mêmes nom et prénom existe déjà");
                        }
                    }
                }
            }
        }
    }


    /*
    Gets Stage
     */
    private String getNomStage(){
        consigne = "Entrez le nom du stage (obligatoire)(sortie : 0 + enter) : ";
        pattern = PatternPerso.pasVideEtLettresUniquementOuZero;
        input = inputToString();

        return input;
    }


    /*
    Gets Participant
     */
    private String getNomParticipant(){
        consigne = "Entrez le nom du participant (obligatoire)(sortie : 0 + enter) : ";
        pattern = PatternPerso.pasVideEtLettresUniquementOuZero;
        input = inputToString();

        return input;
    }

    private String getPrenomParticipant(){
        consigne = "Entrez le prénom du participant (obligatoire)(sortie : 0 + enter) : ";
        pattern = PatternPerso.pasVideEtLettresUniquementOuZero;
        input = inputToString();

        return input;
    }

    private Optional<String> getOptionalNomClubParticipant(){
        consigne = "Entrez le nom du club du participant (optionnel: enter)(sortie : 0 + enter) : ";
        pattern = PatternPerso.videOuLettresUniquementOuZero;
        optionalInput = inputToOptionalString();

        return optionalInput;
    }

    private Optional<String> getOptionalMailParticipant(){
        consigne = "Entrez le mail du participant (optionnel: enter)(sortie : 0 + enter) : ";
        pattern = PatternPerso.videOuMailOuZero;
        optionalInput = inputToOptionalString();

        return optionalInput;
    }


    /*
    Transformation
     */
    private String inputToString(){
        Optional<String> string = Optional.empty();

        string = inputToOptionalString();
        while (string.isEmpty()) {
            System.out.println("Ce paramètre n'est pas optionnel");
            string = inputToOptionalString();
        }

        return string.get();
    }

    private Optional<String> inputToOptionalString(){
        Optional<String> string = Optional.empty();

        System.out.print(consigne);
        input =  scanner.nextLine();
        input = input.trim();
        matcher = pattern.matcher(input);
        while(! matcher.matches()) {
            System.out.println("Entrée non valide");
            System.out.print(consigne);
            input =  scanner.nextLine();
            input = input.trim();
            matcher = pattern.matcher(input);
        }

        if (input.length() > 0) {
            string = Optional.of(input);
        }

        return string;
    }

    private Optional<LocalDateTime> inputToDateTimeCourt(){
        Optional<LocalDateTime> localDateTime;
        Optional<String> string;

        string = inputToOptionalString();
        while (string.isEmpty()) {
            System.out.println("Ce paramètre n'est pas optionnel");
            string = inputToOptionalString();
        }

        if ("0".equalsIgnoreCase(string.get())){
            localDateTime = Optional.empty();
        }else{
            int date = Integer.parseUnsignedInt(matcher.group(2));
            int mois = Integer.parseUnsignedInt(matcher.group(6));
            int annee = Integer.parseUnsignedInt(matcher.group(9));
            int heure = Integer.parseUnsignedInt(matcher.group(10));
            int minute = Integer.parseUnsignedInt(matcher.group(13));
            localDateTime = Optional.of(LocalDateTime.of(annee, mois, date, heure, minute));
        }
        return localDateTime;
    }

}

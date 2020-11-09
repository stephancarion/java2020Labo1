package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.exception.*;
import be.technifutur.java2020.gestionStage.vue.ConsignesVue;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ctrl {
    private Scanner scanner;
    private Model model;
    private Vue vue;
    private ConsignesVue consignesVue;

    private String input;
    private Optional<String> optionalInput;

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setVue(Vue vue) {
        this.vue = vue;
    }

    public void setConsignesVue(ConsignesVue consignesVue) {
        this.consignesVue = consignesVue;
    }

    public void start(){
        input ="";
        while(! "0".equalsIgnoreCase(input)){
            System.out.println("********** Menu Organisateur ***************");
            System.out.println("* Que souhaitez-vous ?                     *");
            System.out.println("* 1 - Ajouter un stage                     *");
            System.out.println("* 2 - Voir tous les stages                 *");
            System.out.println("* 3 - Ajouter une activité à un stage      *");
            System.out.println("* 4 - Voir toutes les activités d'un stage *");
            System.out.println("* 0 - Quitter rôle organisateur            *");
            System.out.print("* Votre Choix ? ");
            input=scanner.nextLine();

            switch (input){
                case "1" :
                    Integer cle = ajoutStage();
                    vue.afficheInfosStageAjoute(cle);
                    break;
                case "2" :
                    vue.afficheStageSet();
                    break;
                case "3" :
                    vue.afficheStageSet();
                    try {
                        Integer cleStage = accesStagePourAjoutActivite();
                        ajoutActivite(cleStage);
                        vue.afficheInfoActiviteStage(cleStage);
                    } catch (NumeroDeStageNonValideException e) {
                        System.out.println("Numéro de stage non valide");
                    }
                    break;
                case "4" :
                    vue.afficheStageSet();
                    try {
                        Integer cleStage = accesStagePourVoirActivite();
                        vue.afficheHoraireStage(cleStage);
                    } catch (NumeroDeStageNonValideException e) {
                        System.out.println("Numéro de stage non valide");
                    }
                    break;
            }
        }
    }

    // retourne la clé du stage ajouté
    private Integer ajoutStage(){
        Integer cleStageAjoute = null;

        Stage stageModel;
        String input;
        String nom;
        LocalDateTime debut;
        LocalDateTime fin;

        // pattern "dd/mm/yyyy hh:mm" //TODO à mettre dans une factory
        String pattern = "((0[1-9])|([12][0-9])|(3[0-1]))/"+ // dd/  --> entre 01 et 31
                "((0[1-9])|(1[0-2]))/"+ // mm/  --> entre 01 et 12
                "([0-9][0-9][0-9][0-9]) "+ // yyyy  --> entre 0000 et 9999
                "(([01][0-9])|(2[0-3])):"+ // hh: --> entre 00 et 23
                "([0-5][0-9])"; // mm --> entre 00 et 59
        Pattern patternDateHeure = Pattern.compile(pattern);
        // matcher
        Matcher matcher;

        // Récupération d'un nom de stage d'au moins 1 caractère
        consignesVue.ajoutNomStage();
        nom = scanner.nextLine();
        while (nom.length()==0){ //TODO vérifier pour oter les espaces --> TREAM
            System.out.println("Nom invalide");
            consignesVue.ajoutNomStage();
            nom = scanner.nextLine();
        }

        // Récupération des date et heure de début de stage
        consignesVue.ajoutDateHeureDebutStage();
        input = scanner.nextLine();
        matcher = patternDateHeure.matcher(input);

        while (! matcher.matches()){ //TODO mettre dans une fonction (bonus avec optionnal)
            System.out.println("Format Invalide");
            consignesVue.ajoutDateHeureDebutStage();
            input = scanner.nextLine();
            matcher = patternDateHeure.matcher(input);
        }
        int dateDebut = Integer.parseUnsignedInt(matcher.group(1));
        int moisDebut = Integer.parseUnsignedInt(matcher.group(5));
        int anneeDebut = Integer.parseUnsignedInt(matcher.group(8));
        int heureDebut = Integer.parseUnsignedInt(matcher.group(9));
        int minuteDebut = Integer.parseUnsignedInt(matcher.group(12));
        debut = LocalDateTime.of(anneeDebut, moisDebut, dateDebut, heureDebut, minuteDebut);

        // Récupération des date et heure de fin de stage
        consignesVue.ajoutDateHeureFinStage();
        input = scanner.nextLine();
        matcher = patternDateHeure.matcher(input);
        while (! matcher.matches()){
            System.out.println("Format Invalide");
            consignesVue.ajoutDateHeureFinStage();
            input = scanner.nextLine();
            matcher = patternDateHeure.matcher(input);
        }
        int dateFin = Integer.parseUnsignedInt(matcher.group(1));
        int moisFin = Integer.parseUnsignedInt(matcher.group(5));
        int anneeFin = Integer.parseUnsignedInt(matcher.group(8));
        int heureFin = Integer.parseUnsignedInt(matcher.group(9));
        int minuteFin = Integer.parseUnsignedInt(matcher.group(12));
        fin = LocalDateTime.of(anneeFin,moisFin,dateFin, heureFin, minuteFin);

        try{
            // création d'un stage
            stageModel = new Stage(nom,debut,fin);

            // ajout du stage à la map
            cleStageAjoute =  model.addStage(stageModel);



        }catch ( DateDeFinNonValideException e) {
            System.out.println("Veuillez entrer une date et heure de fin postérieure au début");
        }catch (ChaineDeCaractereVideException e) {
            System.out.println("Veuillez entrer un nom avec au moins 1 caractère");
        }

        return cleStageAjoute;
    }
    
    private Integer accesStagePourAjoutActivite() throws NumeroDeStageNonValideException {
        Integer cleStage = null;
        String input;
        Set<Integer> cleStageSet = model.getCleStageSet();

        consignesVue.accesNumeroStagePourAjoutActivite();
        input=scanner.nextLine();

        input=input.trim();

        try {
            cleStage = Integer.parseUnsignedInt(input);
            if (! cleStageSet.contains(cleStage)){
                throw new NumeroDeStageNonValideException();
            }
        }catch (NumberFormatException e){
            System.out.println("Vous n'avez pas rentré un nombre");
        }
        
        return  cleStage;
    }

    private Integer accesStagePourVoirActivite() throws NumeroDeStageNonValideException {
        Integer cleStage = null;
        String input;
        Set<Integer> cleStageSet = model.getCleStageSet();

        consignesVue.accesNumeroStagePourVoirActivite();
        input=scanner.nextLine();

        input=input.trim();

        try {
            cleStage = Integer.parseUnsignedInt(input);
            if (! cleStageSet.contains(cleStage)){
                throw new NumeroDeStageNonValideException();
            }
        }catch (NumberFormatException e){
            System.out.println("Vous n'avez pas rentré un nombre");
        }

        return  cleStage;
    }
    
    private void ajoutActivite(Integer cleStage){
        String input;
        String nom;
        LocalDateTime debutActivite;
        int dureeActivite;

        // pattern "dd/mm/yyyy hh:mm"
        String pattern = "((0[1-9])|([12][0-9])|(3[0-1]))/"+ // dd/  --> entre 01 et 31
                "((0[1-9])|(1[0-2]))/"+ // mm/  --> entre 01 et 12
                "([0-9][0-9][0-9][0-9]) "+ // yyyy  --> entre 0000 et 9999
                "(([01][0-9])|(2[0-3])):"+ // hh: --> entre 00 et 23
                "([0-5][0-9])"; // mm --> entre 00 et 59
        Pattern patternDateHeure = Pattern.compile(pattern);
        // matcher
        Matcher matcher;

        if (cleStage!= null) {
            // Récupération d'un nom d'activité d'au moins 1 caractère
            consignesVue.ajoutNomActivite();
            nom = scanner.nextLine();
            while (nom.length() == 0) {
                consignesVue.ajoutNomActivite();
                nom = scanner.nextLine();
            }


            // Récupération d'une date de début d'activité
            consignesVue.ajoutDateHeureDebutActivite();
            input = scanner.nextLine();
            matcher = patternDateHeure.matcher(input);
            while (!matcher.matches()) {
                consignesVue.ajoutDateHeureDebutActivite();
                input = scanner.nextLine();
                matcher = patternDateHeure.matcher(input);
            }
            int dateDebut = Integer.parseUnsignedInt(matcher.group(1));
            int moisDebut = Integer.parseUnsignedInt(matcher.group(5));
            int anneeDebut = Integer.parseUnsignedInt(matcher.group(8));
            int heureDebut = Integer.parseUnsignedInt(matcher.group(9));
            int minuteDebut = Integer.parseUnsignedInt(matcher.group(12));
            debutActivite = LocalDateTime.of(anneeDebut, moisDebut, dateDebut, heureDebut, minuteDebut);

            // Récupération de la durée en minutes
            consignesVue.ajoutDureeActivite();
            input = scanner.nextLine();
            dureeActivite = 0;
            try {
                dureeActivite = Integer.parseUnsignedInt(input);
            } catch (NumberFormatException e) {
                System.out.println("La durée entrée n'est pas un entier");
            }
            while (dureeActivite <= 0) {
                System.out.println("La durée en minute doit être un entier strictement positif");
                consignesVue.ajoutNomActivite();
                nom = scanner.nextLine();
                try {
                    dureeActivite = Integer.parseUnsignedInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("La durée entrée n'est pas un entier");
                }
            }

            Stage stage = model.getStage(cleStage);

                if (debutActivite.compareTo(stage.getDebut()) >= 0 &&
                        debutActivite.plusMinutes(dureeActivite).compareTo(stage.getFin()) <= 0) {
                    try{
                        stage.addActivite(new Activite(nom, debutActivite, dureeActivite));
                    }catch (ChaineDeCaractereVideException e){
                        System.out.println("Le nom de l'activité est vide");
                    }catch (DureeNegativeOuEgaleAZeroException e){
                        System.out.println("La durée de l'activité ne peut être négative ou nulle");
                    }catch (ActiviteDejaExistanteDansCeStageException e){
                        System.out.println("L'activité est déjà existante dans ce stage");
                    }
                } else {
                    System.out.println("Le créneau de l'activité n'est pas inclus dans le créneau du stage");
                }
            }
    }

    private void ajoutParticipant(){
        String nom;
        String prenom;
        Optional<String> nomClub;
        Optional<String> mail;

        boolean sortieAjout = false;

        input = inputToString("Entrez le nom du participant (obligatoire) ou 0 pour sortir: ", MatcherPattern.lettresUniquementOuZero);
        sortieAjout = "0".equalsIgnoreCase(input);

        if (! sortieAjout){
            nom = input;
            input = inputToString();
        }

    }

    private String inputToString(String consigne, Pattern pattern){
        Optional<String> string = Optional.empty();
        Matcher matcher;

        do {
            System.out.print(consigne);
            input = scanner.nextLine();
            input = input.trim();

            matcher = pattern.matcher(input);

            if (matcher.matches()) {
                string = Optional.of(input);
            }
        }while (string.isEmpty());

        return string.get();
    }

    private Optional<String> inputToOptionalString(String consigne){
        Optional<String> string = Optional.empty();

        System.out.print(consigne);
        input =  scanner.nextLine();
        input = input.trim();

        if (input.length() > 0) {
            string = Optional.of(input);
        }

        return string;
    }


}

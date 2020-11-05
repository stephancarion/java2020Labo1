package be.technifutur.java2020.gestionStage.stage;

import be.technifutur.java2020.gestionStage.common.ConsignesVue;
import be.technifutur.java2020.gestionStage.exception.InvalidEndDateTimeStageException;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StageCtrl {
    private StageModel model;
    private StageVue vue;

    public void setModel(StageModel model) {
        this.model = model;
    }

    public void setVue(StageVue vue) {
        this.vue = vue;
    }

    public StageModel ajoutStage(){
        ConsignesVue consigneVue = new ConsignesVue();
        Scanner scanner=new Scanner(System.in);
        String input;
        String nom;
        LocalDateTime debut;
        LocalDateTime fin;

        // pattern "dd/mm/yyyy hh:mm"
        String pattern = "((0[1-9])|([12][0-9])|(3[0-1]))/"+ // dd/  --> entre 01 et 31
                "((0[1-9])|(1[0-2]))/"+ // mm/  --> entre 01 et 12
                "([0-9][0-9][0-9][0-9]) "+ // yyyy  --> entre 0000 et 9999
                "(([01][0-9])|(2[0-3])):"+ // hh: --> entre 00 et 23
                "([0-5][0-9])"; // mm --> entre 00 et 59
        Pattern patternDateHeure = Pattern.compile(pattern);
        // matcher
        Matcher matcher;

        consigneVue.ajoutNomStage();
        nom = scanner.nextLine();
        while (nom.length()==0){
            System.out.println("Nom invalide");
            consigneVue.ajoutNomStage();
            nom = scanner.nextLine();
        }

        consigneVue.ajoutDateHeureDebutStage();
        input = scanner.nextLine();
        matcher = patternDateHeure.matcher(input);

        while (! matcher.matches()){
            System.out.println("Format Invalide");
            consigneVue.ajoutDateHeureDebutStage();
            input = scanner.nextLine();
            matcher = patternDateHeure.matcher(input);
        }
        int dateDebut = Integer.parseUnsignedInt(matcher.group(1));
        int moisDebut = Integer.parseUnsignedInt(matcher.group(5));
        int anneeDebut = Integer.parseUnsignedInt(matcher.group(8));
        int heureDebut = Integer.parseUnsignedInt(matcher.group(9));
        int minuteDebut = Integer.parseUnsignedInt(matcher.group(12));
        debut = LocalDateTime.of(anneeDebut, moisDebut, dateDebut, heureDebut, minuteDebut);


        consigneVue.ajoutDateHeureFinStage();
        input = scanner.nextLine();
        matcher = patternDateHeure.matcher(input);
        while (! matcher.matches()){
            System.out.println("Format Invalide");
            consigneVue.ajoutDateHeureFinStage();
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
            model = new StageModel(nom,debut,fin);
            setModel(model);
            setVue(vue);
        }catch (InvalidEndDateTimeStageException e) {
            System.out.println("Veuillez entrer une date et heure de fin postérieure au début");
        }
    return model;
    }
}

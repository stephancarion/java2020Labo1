package be.technifutur.java2020.gestionStage.stage;

import java.nio.file.LinkOption;
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

    public void ajoutStage(){
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

        vue.afficheConsigneAjoutNom();
        nom = scanner.nextLine();

        vue.afficheConsigneAjoutDateHeureDebut();

        input = scanner.nextLine();
        matcher = patternDateHeure.matcher(input);
        if (matcher.matches()){
            int dateDebut = Integer.parseUnsignedInt(matcher.group(1));
            int moisDebut = Integer.parseUnsignedInt(matcher.group(5));
            int anneeDebut = Integer.parseUnsignedInt(matcher.group(8));
            int heureDebut = Integer.parseUnsignedInt(matcher.group(9));
            int minuteDebut = Integer.parseUnsignedInt(matcher.group(12));

            debut = LocalDateTime.of(anneeDebut,moisDebut,dateDebut, heureDebut, minuteDebut);
        }else{
            debut=null;
            new Exception("Format invalide");
        }

        vue.afficheConsigneAjoutDateHeureFin();

        input = scanner.nextLine();
        matcher = patternDateHeure.matcher(input);
        if (matcher.matches()){
            int dateFin = Integer.parseUnsignedInt(matcher.group(1));
            int moisFin = Integer.parseUnsignedInt(matcher.group(5));
            int anneeFin = Integer.parseUnsignedInt(matcher.group(8));
            int heureFin = Integer.parseUnsignedInt(matcher.group(9));
            int minuteFin = Integer.parseUnsignedInt(matcher.group(12));

            fin = LocalDateTime.of(anneeFin,moisFin,dateFin, heureFin, minuteFin);
        }else{
            fin = null;
            new Exception("Format invalide");
        }

        model = new StageModel(nom,debut,fin);

    }
}

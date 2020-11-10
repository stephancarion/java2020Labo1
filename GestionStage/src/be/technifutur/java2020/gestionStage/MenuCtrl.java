package be.technifutur.java2020.gestionStage;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuCtrl implements Runnable{
    private MenuModel model;

    private Scanner scanner;
    private String input;
    private Pattern pattern;

    private int dernierChoixValide = 0;

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void run() {
        int choixMenu;
        //Item itemChoisi;
        pattern = PatternPerso.pasVideEtChiffresUniquement;

        choixMenu = inputToIntChoixMenu(pattern);
        while (choixMenu < 0 || choixMenu > model.nbItem()){
            System.out.println("Choix non valide");
            choixMenu = inputToIntChoixMenu(pattern);
        }

        dernierChoixValide = choixMenu;

    }

    private int inputToIntChoixMenu(Pattern pattern){
        int choixMenu;
        Matcher matcher;

        input =  scanner.nextLine();
        input = input.trim();
        matcher = pattern.matcher(input);
        while(! matcher.matches()) {
            System.out.println("Entrée non valide");
            input =  scanner.nextLine();
            input = input.trim();
            matcher = pattern.matcher(input);
        }

        choixMenu = Integer.parseUnsignedInt(input);

        return choixMenu;
    }

    public int getDernierChoixValide() {
        return dernierChoixValide;
    }
}

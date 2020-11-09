package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.exception.ChaineDeCaractereVideException;
import be.technifutur.java2020.gestionStage.exception.DureeNegativeOuEgaleAZeroException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Factory factory = new Factory();

        Ctrl ctrl = factory.getCtrl();
        ctrl.start();
        factory=null;
    }
}

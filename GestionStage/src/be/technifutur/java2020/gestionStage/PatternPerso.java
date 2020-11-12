package be.technifutur.java2020.gestionStage;

import com.sun.source.tree.ParameterizedTypeTree;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternPerso {
    public static final Pattern pasVideEtLettresUniquementOuZero = Pattern.compile("([a-zA-ZàâäçéèêëîïôöùûüÿÀÂÄÉÈÊËÎÏÔÖÙÛÜ-]+?)|0");

    public static final Pattern pasVideEtLettresUniquementSansZero = Pattern.compile("[a-zA-ZàâäçéèêëîïôöùûüÿÀÂÄÉÈÊËÎÏÔÖÙÛÜ-]+?");

    public static final Pattern videOuLettresUniquementOuZero = Pattern.compile("([a-zàâäçéèêëîïôöùûüÿA-ZÀÂÄÉÈÊËÎÏÔÖÙÛÜ]*+)|0");

    public static final Pattern videOuMailOuZero = Pattern.compile("(([^@]+@[^@]+)|0)?");

    public static final Pattern pasVideEtChiffresUniquement = Pattern.compile("[1-9]*[0-9]+");

    public static final Pattern pasVideEtDateHeureCourtOuZero = Pattern.compile("(((0[1-9])|([12][0-9])|(3[0-1]))/"+ // dd/  --> entre 01 et 31
                                                                            "((0[1-9])|(1[0-2]))/"+ // mm/  --> entre 01 et 12
                                                                            "([0-9][0-9][0-9][0-9]) "+ // yyyy  --> entre 0000 et 9999
                                                                            "(([01][0-9])|(2[0-3])):"+ // hh: --> entre 00 et 23
                                                                            "([0-5][0-9]))|0"); // mm --> entre 00 et 59);
    public static final Pattern pasVideEtOOuNOuZero = Pattern.compile("[oOnN0]{1}+");
}

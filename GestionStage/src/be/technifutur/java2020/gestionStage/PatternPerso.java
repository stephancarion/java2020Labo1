package be.technifutur.java2020.gestionStage;

import com.sun.source.tree.ParameterizedTypeTree;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternPerso {
    public static final Pattern pasVideEtLettresUniquementOuZero = Pattern.compile("([a-zàâäçéèêëîïôöùûüÿA-ZÀÂÄÉÈÊËÎÏÔÖÙÛÜ]+)|0");

    public static final Pattern videOuLettresUniquementOuZero = Pattern.compile("([a-zàâäçéèêëîïôöùûüÿA-ZÀÂÄÉÈÊËÎÏÔÖÙÛÜ]*)|0");

    public static final Pattern videOuMailOuZero = Pattern.compile("(([^@]+@[^@]+)|0)?");

    public static final Pattern pasVideEtChiffresUniquement = Pattern.compile("[1-9]*[0-9]+");
}

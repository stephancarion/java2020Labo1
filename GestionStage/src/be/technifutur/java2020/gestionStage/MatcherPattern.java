package be.technifutur.java2020.gestionStage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherPattern {
    public static final Pattern lettresUniquementOuZero = Pattern.compile("([a-zàâäçéèêëîïôöùûüÿA-ZÀÂÄÉÈÊËÎÏÔÖÙÛÜ]+)|0");

    public static boolean matchAvecLettresUniquementOuZero (String input) {
        Matcher matcher = lettresUniquementOuZero.matcher(input);
        return matcher.matches();
    }
}

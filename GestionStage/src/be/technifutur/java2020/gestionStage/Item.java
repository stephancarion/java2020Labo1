package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.exception.ChaineDeCaractereVideException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Item {
    private String nom;
    private Object objetToRun;
    private String nomMethodeToRun;

    public Item(String nom, Object objetToRun, String nomMethodeToRun){
        nom = nom.trim();

        this.nom = nom;
        this.objetToRun = objetToRun;
        this.nomMethodeToRun = nomMethodeToRun;
    }

    public String getNom() {
        return nom;
    }

    public Object getObjetToRun() {
        return objetToRun;
    }

    public Object run() {
        Method m = null;
        Object o = null;
        try {
            m = objetToRun.getClass().getMethod(nomMethodeToRun);
            o = m.invoke(objetToRun);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return o;
    }
}

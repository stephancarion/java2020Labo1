package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.exception.ChaineDeCaractereVideException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Item implements Runnable{
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

    @Override
    public void run() {
        Method m = null;
        try {
            m = objetToRun.getClass().getMethod(nomMethodeToRun);
            m.invoke(objetToRun);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

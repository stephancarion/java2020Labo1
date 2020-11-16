package be.technifutur.java2020.gestionStage;

import java.io.Serializable;

public enum TarifStatut implements Serializable {
    NORMAL(new TarifNormal()), ETUDIANT(new TarifEtudiant()), COUPLE(new TarifCouple()), CHOMEUR(new TarifChomeur());

    private Tarif tarif;

    private TarifStatut(Tarif tarif){
        this.tarif = tarif;
    }

    public double getPrixActiviteSansReduction(Activite activite){
        return tarif.getPrixActiviteSansReduction(activite);
    }

    public double getPrixActiviteAvecReduction(Activite activite){
        return tarif.getPrixActiviteAvecReduction(activite);
    }
}

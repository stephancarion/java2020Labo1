package be.technifutur.java2020.gestionStage;

public interface Tarif {
    int getReductionEnPourCent();

    int getReductionEnDecimal();

    int getPrixActiviteSansReduction(Activite activite);

    int getPrixActiviteAvecReduction(Activite activite);

}

package be.technifutur.java2020.gestionStage;

public interface Tarif {
    void setReductionEnPourCent(double reductionEnPourCent);

    double getReductionEnPourCent();

    double getReductionEnDecimal();

    double getPrixActiviteSansReduction(Activite activite);

    double getPrixActiviteAvecReduction(Activite activite);

}

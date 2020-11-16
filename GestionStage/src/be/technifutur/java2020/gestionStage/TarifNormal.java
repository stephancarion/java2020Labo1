package be.technifutur.java2020.gestionStage;

public class TarifNormal implements Tarif{
    private double reductionEnDecimal=0;


    @Override
    public void setReductionEnPourCent(double reductionEnPourCent) {
        this.reductionEnDecimal = reductionEnPourCent/100;
    }

    @Override
    public double getReductionEnPourCent() {
        return reductionEnDecimal * 100;
    }

    @Override
    public double getReductionEnDecimal() {
        return reductionEnDecimal;
    }

    @Override
    public double getPrixActiviteSansReduction(Activite activite) {
        return activite.getPrix();
    }

    @Override
    public double getPrixActiviteAvecReduction(Activite activite) {
        return activite.getPrix()*(1-reductionEnDecimal);
    }
}

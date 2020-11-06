package be.technifutur.java2020.gestionStage;

public class Main {
    public static void main(String[] args) {
        Factory factory = new Factory();

        Ctrl ctrl = factory.getCtrl();
        ctrl.start();
        factory=null;
    }
}

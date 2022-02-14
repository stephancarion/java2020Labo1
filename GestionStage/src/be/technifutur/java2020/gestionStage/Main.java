package be.technifutur.java2020.gestionStage;

public class Main {
    public static void main(String[] args) {
        Factory factory = new Factory();
        Application app = factory.getApp();
        app.run();
        factory=null;
    }
}

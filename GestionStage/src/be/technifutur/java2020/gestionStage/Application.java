package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.menu.MenuGeneral;

public class Application implements Runnable{
    MenuGeneral menu;

    public void setMenu(MenuGeneral menu) {
        this.menu = menu;
    }

    @Override
    public void run() {
        menu.run();
    }
}

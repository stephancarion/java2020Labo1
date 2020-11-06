package be.technifutur.java2020.gestionStage.menu;

import be.technifutur.java2020.gestionStage.Item;
import be.technifutur.java2020.gestionStage.vue.MenuVue;

import java.util.LinkedHashSet;

public class MenuGeneral implements Runnable{
    LinkedHashSet<Item> itemSet;
    MenuVue menuVue;

    public void setMenuVue(MenuVue menuVue) {
        this.menuVue = menuVue;
    }

    public void addItem(Item item){
        itemSet.add(item);
    }

    public void affiche(){
        menuVue.affiche("Menu général", itemSet);
    }

    @Override
    public void run() {
        affiche();
    }
}

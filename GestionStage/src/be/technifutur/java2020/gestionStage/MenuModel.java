package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.exception.PositionInvalideException;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;

public class MenuModel {
    private LinkedHashSet<Item> itemSet;
    private String nomMenu;
    private Optional<MenuModel> menuModelPrecedent;
    private MenuModel menuModelEnCours = this;

    public void setMenuModelPrecedent(Optional<MenuModel> menuModelPrecedent) {
        this.menuModelPrecedent = menuModelPrecedent;
    }

    public String getNomMenu (){
        return this.nomMenu;
    }
    public Optional<MenuModel> getMenuModelPrecedent() {
        return menuModelPrecedent;
    }

    public MenuModel(String nomMenu, Item[] itemSet) {
        this.nomMenu = nomMenu;
        this.itemSet = new LinkedHashSet<>();
        for (Item item: itemSet) {
            this.itemSet.add(item);
        }
    }

    public MenuModel(String nomMenu) {
        this.nomMenu = nomMenu;
        this.itemSet = new LinkedHashSet<>();
    }

    public void addItem(Item item){
        this.itemSet.add(item);
    }



    public void setNewItemSet(Item[] itemSet) {
        this.itemSet.clear();
        for (Item item:itemSet) {
            this.itemSet.add(item);
        }
    }

    public LinkedHashSet<String> itemNameSet(){
        LinkedHashSet<String> set = new LinkedHashSet<>();

        for (Item item: itemSet) {
            set.add(item.getNom());
        }

        return set;
    }

    public int nbItem(){
        return itemSet.size();
    }

    public Item getItem(int position) throws PositionInvalideException{
        Item item=null;
        Iterator<Item> iterator = itemSet.iterator();

        if (position >= 1){
            for(int i = 1; i<=position ; i++){
                item=iterator.next();
            }
        }else{
            throw new PositionInvalideException();
        }

        return item;
    }

    public MenuModel getModel(){
        return this;
    }
}
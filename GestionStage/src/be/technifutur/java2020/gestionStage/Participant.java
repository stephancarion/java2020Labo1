package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.exception.ChaineDeCaractereVideException;

import java.util.Iterator;
import java.util.Optional;
import java.util.TreeMap;

public class Participant {
    private String nom;
    private String prenom;
    private Optional<String> nomClub;
    private Optional<String> mail;
    private TreeMap<Activite,Stage> planning;
    private TarifStatut tarifStatut;

    public Participant(String nom, String prenom, Optional<String> nomClub, Optional<String> mail) throws ChaineDeCaractereVideException {
        if (nom.length() > 0 && prenom.length() > 0){
            this.nom = nom;
            this.prenom = prenom;
            if(nomClub.isPresent()){
                String nomClubString = nomClub.get();
                if(nomClubString.length() > 0){
                    this.nomClub = Optional.of(nomClubString);
                }
                else{
                    throw new ChaineDeCaractereVideException();
                }
            }else{
                this.nomClub = Optional.empty();
            }
            if(mail.isPresent()){
                String mailString = mail.get();
                if(mailString.length() > 0){
                    this.mail = Optional.of(mailString);
                }
                else{
                    throw new ChaineDeCaractereVideException();
                }
            }else{
                this.mail = Optional.empty();
            }
        }else{
            throw new ChaineDeCaractereVideException();
        }
        planning = new TreeMap<>();
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getNomClub() {
        String nomClub="";
        if(this.nomClub.isPresent()){
            nomClub += this.nomClub.get();
        }
        return nomClub;
    }

    public String getMail() {
        String mail="";
        if(this.mail.isPresent()){
            mail += this.mail.get();
        }
        return mail;
    }

    public TarifStatut getTarifStatut() {
        return tarifStatut;
    }

    public void setNomClub(Optional<String> nomClub){
        this.nomClub=nomClub;
    }

    public void setMail(Optional<String> mail){
        this.mail = mail;
    }

    public TreeMap<Activite,Stage> getPlanning() {
        return planning;
    }

    // Renvoie les activités qui pourraient être en conflit avec une activité passée en paramètre
    public TreeMap<Activite, Stage> getActivitiesInConflict (Activite aTest){
        TreeMap<Activite, Stage> activitesStages = new TreeMap<>();

        for (Activite aPlanning: planning.keySet()) {
            if (aTest.getFin().compareTo(aPlanning.getDebut()) > 0 || aTest.getDebut().compareTo(aPlanning.getFin()) < 0){
                activitesStages.put(aPlanning,planning.get(aPlanning));
            }
        }

        return activitesStages;
    }

    public void addActivite (Activite a, Stage s){
        planning.put(a,s);
    }

    public boolean containActivite (Activite a){
        boolean contains = false;
        Iterator<Activite> iterator = planning.keySet().iterator();
        while (iterator.hasNext() && ! contains){
            contains = iterator.next() == a;
        }
        return contains;
    }

    public void defineTarifStatut (TarifStatut tarifStatut){
        this.tarifStatut = tarifStatut;
    }
}

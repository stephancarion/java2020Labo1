package be.technifutur.java2020.gestionStage;

import be.technifutur.java2020.gestionStage.exception.ChaineDeCaractereVideException;

import java.util.Optional;

public class Participant {
    private String nom;
    private String prenom;
    private Optional<String> nomClub;
    private Optional<String> mail;

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

    public void addNomClub(String nomClub){
        if(nomClub.length() > 0){
            this.nomClub=Optional.of(nomClub);
        }
    }

    public void removeNomClub(){
        this.nomClub=Optional.empty();
    }

    public void addMail(String mail){
        if(mail.length() > 0){
            this.mail=Optional.of(mail);
        }
    }

    public void removeMail(){
        this.mail=Optional.empty();
    }
}

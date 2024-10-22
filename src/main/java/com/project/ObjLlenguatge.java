package com.project;

public class ObjLlenguatge {

    private int id;
    private String nom;
    private int any;
    private String dificultat;
    private int popularitat;

    public ObjLlenguatge(int id, String nom, int any, String dificultat, int popularitat) {
        this.id = id;
        this.nom = nom;
        this.any = any;
        this.dificultat = dificultat;
        this.popularitat = popularitat;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getAny() {
        return any;
    }

    public String getDificultat() {
        return dificultat;
    }

    public int getPopularitat() {
        return popularitat;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAny(int any) {
        this.any = any;
    }

    public void setDificultat(String dificultat) {
        this.dificultat = dificultat;
    }

    public void setPopularitat(int popularitat) {
        this.popularitat = popularitat;
    }

    @Override
    public String toString() {
        return "Llenguatge: " + this.id + " " + this.nom + ", " + this.any + " - " + this.dificultat + "/"+ this.popularitat;
    }

}

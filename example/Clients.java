package org.example;

public class Clients {
    private int id;
    private String name;
    private boolean isDeleted;
    private Photorgafs idPhotorgafs;

    public Clients(int id, String name, boolean isDeleted, Photorgafs idPhotorgafs) {
        this.id = id;
        this.name = name;
        this.isDeleted = isDeleted;
        this.idPhotorgafs = idPhotorgafs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Photorgafs getIdPhotorgafs() {
        return idPhotorgafs;
    }

    public void setIdPhotorgafs(Photorgafs idSPhotorgafs) {
        this.idPhotorgafs = idPhotorgafs;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
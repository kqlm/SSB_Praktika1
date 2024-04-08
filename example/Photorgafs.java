package org.example;

public class Photorgafs {
    private int id;
    private String F_name;
    private boolean F_isDeleted;

    public Photorgafs(int id, String name, boolean isDeleted) {
        this.id = id;
        this.F_name = name;
        this.F_isDeleted = isDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return F_name;
    }

    public void setName(String name) {
        this.F_name = name;
    }

    public boolean getIsDeleted() {
        return F_isDeleted;
    }

    public void setisDeleted(boolean f_isDeleted) {
        F_isDeleted = f_isDeleted;
    }
}
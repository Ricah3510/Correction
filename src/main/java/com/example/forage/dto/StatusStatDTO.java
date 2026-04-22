package com.example.forage.dto;

public class StatusStatDTO {
    private Integer id;
    private String libelle;
    private Integer count;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public StatusStatDTO(Integer id, String libelle, Integer count) {
        this.id = id;
        this.libelle = libelle;
        this.count = count;
    }
    public StatusStatDTO() {
    }

    
}

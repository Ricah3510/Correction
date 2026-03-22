package com.example.forage.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_demande")
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String lieu;
    private String district;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL)
    private List<Devis> deviss;

    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL)
    private List<DemandeStatus> demandeStatuses;

    public Demande() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Devis> getDeviss() {
        return deviss;
    }

    public void setDeviss(List<Devis> deviss) {
        this.deviss = deviss;
    }

    public List<DemandeStatus> getDemandeStatuses() {
        return demandeStatuses;
    }

    public void setDemandeStatuses(List<DemandeStatus> demandeStatuses) {
        this.demandeStatuses = demandeStatuses;
    }
    
}
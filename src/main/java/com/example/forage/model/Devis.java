package com.example.forage.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "t_devis")
public class Devis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_demande", nullable = false)
    private Demande demande;

    @ManyToOne
    @JoinColumn(name = "id_type", nullable = false)
    @JsonIgnoreProperties("deviss")
    private Type type;

    // @Column(name = "montant_total")
    // private BigDecimal montantTotal;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(mappedBy = "devis", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetailsDevis> detailsDeviss;

    public Devis() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    // public BigDecimal getMontantTotal() {
    //     return montantTotal;
    // }

    // public void setMontantTotal(BigDecimal montantTotal) {
    //     this.montantTotal = montantTotal;
    // }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<DetailsDevis> getDetailsDeviss() {
        return detailsDeviss;
    }

    public void setDetailsDeviss(List<DetailsDevis> detailsDeviss) {
        this.detailsDeviss = detailsDeviss;
    }

    public BigDecimal getMontantTotal() {
        if (detailsDeviss == null) return BigDecimal.ZERO;
    
        BigDecimal total = BigDecimal.ZERO;
    
        for (DetailsDevis d : detailsDeviss) {
            total = total.add(d.getQte().multiply(d.getPu()));
        }
    
        return total;
    }
}
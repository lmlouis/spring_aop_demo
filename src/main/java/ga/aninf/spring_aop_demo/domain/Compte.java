package ga.aninf.spring_aop_demo.domain;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Louis Martin WORA SOUAMY
 * @description Modèle de données des comptes bancaires
 */
public class Compte {
    private UUID uuid;
    private String titulaire;
    private BigDecimal montant;
    private String devise;

    /**
     * Création d'un compte bancaire à l'aide de l'identité du titulaire, le montant initial et de la dévise
     * @param titulaire
     * @param montant
     * @param devise
     */
    public Compte(String titulaire, BigDecimal montant, String devise) {
        this.titulaire = titulaire;
        this.montant = montant;
        this.devise = devise;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public void setTitulaire(String titulaire) {
        this.titulaire = titulaire;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }
}

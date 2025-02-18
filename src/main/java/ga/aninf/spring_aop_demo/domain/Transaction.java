package ga.aninf.spring_aop_demo.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * @author Louis Martin WORA SOUAMY
 * @description Modèle de données pour enrégistrer les transactions bancaires
 */
public class Transaction {
    private UUID uuid;
    private String type;
    private BigDecimal montant;
    private String description;
    private UUID compteReference;

    public Transaction(String type, BigDecimal montant, String description, UUID compteReference, Instant date) {
        this.type = type;
        this.montant = montant;
        this.description = description;
        this.compteReference = compteReference;
        this.date = date;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    private Instant date;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getCompteReference() {
        return compteReference;
    }

    public void setCompteReference(UUID compteReference) {
        this.compteReference = compteReference;
    }
}

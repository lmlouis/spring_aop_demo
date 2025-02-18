package ga.aninf.spring_aop_demo.service;

import ga.aninf.spring_aop_demo.domain.Compte;
import ga.aninf.spring_aop_demo.repository.CompteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Service de gestion des comptes.
 */
@Service
public class CompteService {
    private final CompteRepository compteRepository;

    public CompteService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    /**
     * Créer un compte à partir de son titulaire, son montant initial et sa devise.
     * @param titulaire
     * @param montantInitial
     * @param devise
     * @return
     */
    public Compte creerCompte(String titulaire, BigDecimal montantInitial, String devise){
        Compte compte = new Compte(titulaire, montantInitial, devise);
        return compteRepository.save(compte);
    }

    /**
     * Consulter le solde d'un compte.
     * @param uuid
     * @return
     */
    public Compte consulterSolde(UUID uuid){
        return compteRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Compte non trouvé"));
    }

    /**
     * Créditer un compte.
     * @param uuid
     * @param montant
     * @return le solde mise à jour
     */
    public BigDecimal crediter(UUID uuid, BigDecimal montant) {
        Compte compte = compteRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
        compte.setMontant(compte.getMontant().add(montant));
        return compte.getMontant();
    }

    /**
     * Débiter un compte.
     * @param uuid
     * @param montant
     * @return le solde mise à jour
     */
    public BigDecimal debiter(UUID uuid, BigDecimal montant) {
        Compte compte = compteRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
        if (compte.getMontant().compareTo(montant) < 0) {
            throw new IllegalArgumentException("Solde insuffisant");
        }
        compte.setMontant(compte.getMontant().subtract(montant));
        return compte.getMontant();
    }
}

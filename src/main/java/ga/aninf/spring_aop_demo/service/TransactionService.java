package ga.aninf.spring_aop_demo.service;

import ga.aninf.spring_aop_demo.domain.Transaction;
import ga.aninf.spring_aop_demo.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Service de gestion des transactions.
 */
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Création d'une transaction.
     * @param type
     * @param montant
     * @param description
     * @param compteReference
     * @param date
     * @return
     */
    public Transaction creerTransaction(String type, BigDecimal montant, String description, UUID compteReference){
        Transaction transaction = new Transaction(type, montant, description, compteReference, Instant.now());
        return transactionRepository.save(transaction);
    }


    /**
     * Historique des transactions d'un titulaire.
     * @param uuid
     * @return
     */
    public List<Transaction> consulterTransactionsTitulaire(UUID uuid){
        return transactionRepository.findAll().stream().filter(t -> t.getCompteReference().equals(uuid)).toList();
    }

}

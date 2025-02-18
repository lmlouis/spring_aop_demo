package ga.aninf.spring_aop_demo.repository;

import ga.aninf.spring_aop_demo.domain.Transaction;
import ga.aninf.spring_aop_demo.utils.UuidGenerator;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TransactionRepository {
    /**
     * Liste des transactions
     */
    private final Map<UUID, Transaction> transactions = new HashMap<>();

    /**
     * Ajout d'une transaction
     * @param transaction
     * @return
     */
    public Transaction save(Transaction transaction) {
        transaction.setUuid(UuidGenerator.generateUuid());
        transactions.put(transaction.getUuid(), transaction);
        return transactions.get(transaction.getUuid());
    }

    /**
     * Trouver une transaction
     * @param id
     * @return
     */
    public Optional<Transaction> findById(UUID id){
        return Optional.ofNullable(transactions.get(id));
    }

    /**
     * Lister toutes les transactions
     * @return
     */
    public List<Transaction> findAll() {
        return new ArrayList<>(transactions.values());
    }
}

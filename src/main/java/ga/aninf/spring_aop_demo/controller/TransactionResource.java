package ga.aninf.spring_aop_demo.controller;

import ga.aninf.spring_aop_demo.domain.Transaction;
import ga.aninf.spring_aop_demo.repository.TransactionRepository;
import ga.aninf.spring_aop_demo.service.TransactionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

/**
 * @author Louis Martin WORA SOUAMY
 *
 * @description Rest API de gestion des transactions des titulaires domiciliés
 */
@RestController
@RequestMapping("api/transactions")
public class TransactionResource {

    private final TransactionService transactionService;
    private final TransactionRepository transactionRepository;

    public TransactionResource(TransactionService transactionService, TransactionRepository transactionRepository) {
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
    }

    /**
     * {@code GET  :uuid/historique-titulaire} endpoint pour consulter toutes les transactions d'un titulaire
     * @return
     */
    @GetMapping("/{uuid}/historique-titulaire")
    public List<Transaction> getAllTransactionByCompte(@PathVariable UUID uuid) {
        return transactionService.consulterTransactionsTitulaire(uuid);
    }

    /**
     * {@code GET /all} endpoint pour consulter toutes les transactions
     * @return
     */
    @GetMapping("/all")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}

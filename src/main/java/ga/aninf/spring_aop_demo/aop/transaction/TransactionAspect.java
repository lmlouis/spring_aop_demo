package ga.aninf.spring_aop_demo.aop.transaction;

import ga.aninf.spring_aop_demo.domain.Compte;
import ga.aninf.spring_aop_demo.repository.CompteRepository;
import ga.aninf.spring_aop_demo.service.TransactionService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Louis Martin WORA SOUAMY
 * @description Aspect pour historiser les transactions
 */
@Aspect
@Component
public class TransactionAspect {

    private final TransactionService transactionService;

    private final CompteRepository compteRepository;

    public TransactionAspect(TransactionService transactionService, CompteRepository compteRepository) {
        this.transactionService = transactionService;
        this.compteRepository = compteRepository;
    }

    /**
     * Créer une transaction avec les infos de la méthode et les paramètres passés en paramètre
     * @param joinPoint
     * @return
     */
    public void creerTransaction(JoinPoint joinPoint, Boolean isSuccess) {
        if (Boolean.FALSE.equals(isSuccess)) return;
        // Récupération des arguments passés à la méthode interceptée
        Object[] args = joinPoint.getArgs();
        UUID compteReference = (UUID) args[0];  // Le premier argument est l'UUID du compte
        BigDecimal montant = args.length > 1 ? (BigDecimal) args[1] : BigDecimal.ZERO;

        //  Déduction du type de transaction
        String methodName = joinPoint.getSignature().getName();
        String typeTransaction = switch (methodName) {
            case "crediter" -> "CREDIT";
            case "debiter" -> "DEBIT";
            case "consulterSolde" -> "CONSULTATION";
            default -> "INCONNU";
        };
        Compte compte = compteRepository.findById(compteReference).orElse(null);
        String author = compte != null? compte.getTitulaire() : "INCONNU";
        String devise = compte != null? compte.getDevise() : "Devise inconnue";
        String description = "";
        if(typeTransaction.equals("CONSULTATION")){
            description =  Boolean.TRUE.equals(isSuccess) ?
                    String.format("%s  a effectuer avec succès une %s de solde",author, typeTransaction)
                    : String.format("%s  a echouer une %s de solde",author, typeTransaction);
        }
        else{
            description =  Boolean.TRUE.equals(isSuccess) ?
                String.format("%s  a effectuer avec succès une transaction de %s de %s %s",author, typeTransaction, montant, devise)
                : String.format("%s  a echouer une transaction de %s de %s",author, typeTransaction, montant);
        }

        // Création de la transaction
        transactionService.creerTransaction(
                typeTransaction,
                montant,
                description,
                compteReference
        );
    }

    /**
     * Pointcut pour intercepter les méthodes de création de transaction
     */
    @Pointcut("execution(* ga.aninf.spring_aop_demo.service.CompteService.crediter(..)) || " +
                    "execution(* ga.aninf.spring_aop_demo.service.CompteService.debiter(..)) || " +
                    "execution(* ga.aninf.spring_aop_demo.service.CompteService.consulterSolde(..))"
    )
    public void transactionPointcut() {
        // Pas de traitement car elle impléments des conseils d'aspects
    }

    /**
     * Conseil d'aspect pour les méthodes succès de transaction
     * @param joinPoint
     * @param result
     */
    @AfterReturning(pointcut = "transactionPointcut()",
            returning = "result")
    public void apresTransactionReussie(JoinPoint joinPoint, Object result) {
        System.out.println("Transaction réussie : " + result);
        // Appeler la méthode creerTransaction avec succès
        creerTransaction(joinPoint, Boolean.TRUE);
    }

    /**
     * Conseil d'aspect pour les échecs de transaction
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(pointcut = "transactionPointcut()",
            throwing = "ex")
    public void apresTransactionEchouee(JoinPoint joinPoint, Exception ex) {
        System.out.println("Transaction échouée : " + ex.getMessage());
        // Appeler la méthode creerTransaction avec échec
        creerTransaction(joinPoint, Boolean.FALSE);
    }


}

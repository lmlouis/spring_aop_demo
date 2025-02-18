package ga.aninf.spring_aop_demo.controller;


import ga.aninf.spring_aop_demo.domain.Compte;
import ga.aninf.spring_aop_demo.repository.CompteRepository;
import ga.aninf.spring_aop_demo.service.CompteService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @author Louis Martin WORA SOUAMY
 *
 * @description Rest API de gestion des comptes domiciliés
 */
@RestController
@RequestMapping("api/comptes")
public class CompteResource {
    
    private final CompteService compteService;

    private final CompteRepository compteRepository;

    public CompteResource(CompteService compteService, CompteRepository compteRepository) {
        this.compteService = compteService;
        this.compteRepository = compteRepository;
    }

    /**
     * {@code POST  :uuid/creer} endpoint pour créer un compte
     * @param titulaire
     * @param montant
     * @param devise
     * @return
     */
    @PostMapping("/creer")
    public Compte creerCompte(@RequestParam String titulaire, @RequestParam BigDecimal montant, @RequestParam String devise) {
        return compteService.creerCompte(titulaire, montant, devise);
    }

    /**
     * {@code PUT  :uuid/crediter} endpoint pour créditer un montant depuis un compte
     * @param uuid
     * @param montant
     * @return
     */
    @PutMapping("/{uuid}/crediter")
    public BigDecimal crediter(@PathVariable UUID uuid, @RequestParam BigDecimal montant) {
        return compteService.crediter(uuid, montant);
    }

    /**
     * {@code PUT  :uuid/debiter} endpoint pour débiter un montant depuis un compte
     * @param uuid
     * @param montant
     * @return
     */
    @PutMapping("/{uuid}/debiter")
    public BigDecimal debiter(@PathVariable UUID uuid, @RequestParam BigDecimal montant) {
        return compteService.debiter(uuid, montant);
    }

    /**
     * {@code GET  :uuid/solde} : endpoint pour consulter le solde d'un compte
     * @param uuid
     * @return
     */
    @GetMapping("/{uuid}/solde")
    public Compte consulterSolde(@PathVariable UUID uuid) {
        return compteService.consulterSolde(uuid);
    }

    /**
     * {@code GET  /all} : endpoint pour consulter la liste des comptes
     * @return
     */
    @GetMapping("/all")
    public List<Compte> consulterListeComptes() {
        return compteRepository.findAll();
    }
    
}

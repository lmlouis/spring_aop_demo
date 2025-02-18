package ga.aninf.spring_aop_demo.repository;

import ga.aninf.spring_aop_demo.domain.Compte;
import ga.aninf.spring_aop_demo.utils.UuidGenerator;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CompteRepository {
    /**
     * Liste des comptes domiciliés
     */
    private final Map<UUID, Compte> comptesDomiciliers = new HashMap<>();

    /**
     * Ajouter un compte domicilié
     * @param compte
     * @return compte crée
     */
    public Compte save(Compte compte){
        compte.setUuid(UuidGenerator.generateUuid());
        comptesDomiciliers.put(compte.getUuid(), compte);
       return comptesDomiciliers.get(compte.getUuid());
    }

    /**
     * Trouver un compte domicilié par son id
     * @param id
     * @return
     */
    public Optional<Compte> findById(UUID id){
        return Optional.ofNullable(comptesDomiciliers.get(id));
    }

    /**
     * Lister tous les comptes domiciliés
     * @return
     */
    public List<Compte> findAll() {
        return new ArrayList<>(comptesDomiciliers.values());
    }
}

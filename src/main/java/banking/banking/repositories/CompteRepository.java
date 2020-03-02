package banking.banking.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import banking.banking.models.Compte;

/**
 compte repository
 * 
 */
@RepositoryRestResource(collectionResourceRel = "compte", path = "compte")
public interface CompteRepository extends CrudRepository<Compte, Long> {
    @Override
    /**
     * lister tout les comptes.
     */
    List<Compte> findAll();
}

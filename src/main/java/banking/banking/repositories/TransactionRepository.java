package banking.banking.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import banking.banking.models.Compte;
import banking.banking.models.TransactionDB;

/**
 transaction repository
 * 
 */
@RepositoryRestResource
public interface TransactionRepository extends CrudRepository<TransactionDB, Long> {

}

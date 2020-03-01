package banking.banking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import banking.banking.models.Compte;
import banking.banking.models.TransactionDB;

/**
 * @author macanina
 *
 */
/**
 * @author macanina
 *
 */
@Repository
public interface CompteService {

	/**/
	/**
	 * @param c
	 * @return
	 */
	Compte ajouterCompte(Compte c);
	/**
	 * @return
	 */
	List<Compte> listerComptes();
	/**
	 * @param c
	 * @return
	 */
	Compte modifierCompte(Compte c);
	/**
	 * @param idCompte
	 */
	void supprimerCompte(Long idCompte);
	
	Optional<Compte> getCompte(Long id);
	List<TransactionDB>getTransaction(Long idCompte);
	
}



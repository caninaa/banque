package banking.banking.models.facade.metier;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import banking.banking.models.Compte;
import banking.banking.models.TransactionDB;
import banking.banking.repositories.CompteRepository;
/**
 * operation de dépot.
 * @author macanina
 *
 */
@Component
public class Deposit implements Operation {
	@Autowired
	private CompteRepository repo;
/**
 * @param montant
 * @param compte concérne
 * 
 */
	@Override
	public void transaction(Double d, Compte cpt) {
		TransactionDB t = new TransactionDB();
		t.setType('d');
		t.setDateTransaction(new GregorianCalendar().getTime());
		t.setMontantTransaction(d);
		if(cpt.getTransactions()==null) {
			cpt.setTransactions(new ArrayList<TransactionDB>());
		}
		cpt.getTransactions().add(t);

		cpt.setBalance(cpt.getBalance()+d);
		repo.save(cpt);
	}

}

package banking.banking.models.facade.metier;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import banking.banking.models.Compte;
import banking.banking.models.TransactionDB;
import banking.banking.repositories.CompteRepository;
import banking.banking.utils.SoldeNegatiofExceptionException;
/**
 * operation de retrait.
 * @author macanina
 *
 */
@Component
public class Withdraw implements Operation {
	@Autowired
	private CompteRepository repo;
	/**
	 * @param montant
	 * @param compte concérne
	 * 
	 */
	@Override
	public void transaction(Double d,Compte cpt) throws SoldeNegatiofExceptionException {
		TransactionDB t = new TransactionDB();
		t.setType('w');
		t.setDateTransaction(new GregorianCalendar().getTime());
		t.setMontantTransaction(d);
		if(cpt.getTransactions()==null) {
			cpt.setTransactions(new ArrayList<TransactionDB>());
		}
		cpt.getTransactions().add(t);
		if((cpt.getBalance()-d)<0)
			throw new SoldeNegatiofExceptionException("Sole Negatif");
		cpt.setBalance(cpt.getBalance()-d);
		repo.save(cpt);

	}

}

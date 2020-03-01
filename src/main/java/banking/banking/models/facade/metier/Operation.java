package banking.banking.models.facade.metier;

import banking.banking.models.Compte;
import banking.banking.utils.SoldeNegatiofExceptionException;
/**
 * 
 * @author macanina
 * interface operation qui peut être depot ou retrait.
 *
 */
public interface Operation {

	void transaction(Double d,Compte cpt) throws SoldeNegatiofExceptionException;
}

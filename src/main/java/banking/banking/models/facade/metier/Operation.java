package banking.banking.models.facade.metier;

import banking.banking.models.Compte;

public interface Operation {

	void transaction(Double d,Compte cpt);
}

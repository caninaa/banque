package banking.banking.models.facade.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import banking.banking.models.Compte;
@Service
public class TransactionHandler {
	@Autowired
	private Deposit d;
	@Autowired
	private Withdraw w ;
	private Compte c;
	
	public Deposit getD() {
		return d;
	}
	public void setD(Deposit d) {
		this.d = d;
	}
	public Withdraw getW() {
		return w;
	}
	public void setW(Withdraw w) {
		this.w = w;
	}
	public Compte getC() {
		return c;
	}
	public void setC(Compte c) {
		this.c = c;
	}
	
	public TransactionHandler(Deposit d, Withdraw w, Compte c) {
		super();
		this.d = d;
		this.w = w;
		this.c = c;
	}
	
	public TransactionHandler() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void retrait(double montant)
	{
		w.transaction(montant, c);
	}
	public void deposit(double montant)
	{
		d.transaction(montant, c);
	}
}

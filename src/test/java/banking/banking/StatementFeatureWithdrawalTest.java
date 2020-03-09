package banking.banking;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import banking.banking.api.TrasactionControler;
import banking.banking.models.Compte;
import banking.banking.models.TransactionDB;
import banking.banking.repositories.CompteRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.textui.TestRunner;


public class StatementFeatureWithdrawalTest extends TestRunner {
	
	@Autowired
	private CompteRepository repo; 
	@Autowired
	private TrasactionControler ctrl;
	Compte c ;
	Double solde;
	@Given("^client x fait une  demande un relevé$")
	public void client_x_fait_une_demande_un_relevé() throws Exception {
		Optional<Compte>opt=repo.findById(1L);
		assertTrue(opt.isPresent());
		c=opt.get();
		solde=c.getBalance();
}

	@Then("^les valeurs sont correctes$")
	public void les_valeurs_sont_correctes() throws Exception {
     List<TransactionDB>	listDesTransaction=( List<TransactionDB>) ctrl.historique(1L).getBody();
     assertTrue(!listDesTransaction.isEmpty());
	}}

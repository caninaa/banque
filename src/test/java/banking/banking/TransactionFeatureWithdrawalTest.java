package banking.banking;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import banking.banking.api.TrasactionControler;
import banking.banking.models.Compte;
import banking.banking.repositories.CompteRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.textui.TestRunner;


public class TransactionFeatureWithdrawalTest extends CucumberStepDeffinition {
	
	@Autowired
	private CompteRepository repo; 
	@Autowired
	private TrasactionControler ctrl;
	Compte c ;
	Double solde;
	@Given("^client x fait un retrait$")
	public void client_x_fait_un_depot() throws Exception {
	Optional<Compte>opt=repo.findById(1L);
	assertTrue(opt.isPresent());
	c=opt.get();
	solde=c.getBalance();
	}

	@When("^la transaction depot s'execute $")
	public void la_transaction_s_execute() throws Exception {
		ObjectNode json = (ObjectNode) new ObjectMapper().readTree("  {\r\n" + 
				"                \"idCompte\": 1,\r\n" + 
				"           \"montant\":300,\r\n" + 
				"           \"nom\":\"nom\"\r\n" + 
				"            }");
		ctrl.retrait(json);
		solde=solde-300d;
		
	}

	@Then("^le compte est debité$")
	public void le_compte_est_alimenté() throws Exception {
		Optional<Compte>opt=repo.findById(1L);
		assertTrue(opt.isPresent());
		c=opt.get();
		assertEquals(solde,c.getBalance());
	}
}

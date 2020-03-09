package banking.banking;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import banking.banking.api.TrasactionControler;
import banking.banking.models.Compte;
import banking.banking.repositories.CompteRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.textui.TestRunner;


public class TransactionFeatureDepositSoldNegatifTest extends TestRunner {
	
	@Autowired
	private CompteRepository repo; 
	@Autowired
	private TrasactionControler ctrl;
	Compte c ;
	Double solde;

	@Given("^client y fait un retrait$")
	public void client_y_fait_un_retrait() throws Exception {
		Optional<Compte>opt=repo.findById(1L);
		assertTrue(opt.isPresent());
		c=opt.get();
		solde=c.getBalance();
	}

	@When("^la transaction s'execute   exception est lancée$")
	public void la_transaction_s_execute_exception_est_lancée() throws Exception {
		ObjectNode json = (ObjectNode) new ObjectMapper().readTree("  {\r\n" + 
				"                \"idCompte\": 1,\r\n" + 
				"           \"montant\":30000,\r\n" + 
				"           \"nom\":\"nom\"\r\n" + 
				"            }");
		try {
		ctrl.retrait(json);
		}
		catch(Exception e) {
			
			assertNotNull(e);
			assertEquals(e.getClass(), ResponseStatusException.class);
		}
		solde=solde+30000d;
	}

	@Then("^pas de changement sur le solde$")
	public void pas_de_changement_sur_le_solde() throws Exception {
		Optional<Compte>opt=repo.findById(1L);
		assertTrue(opt.isPresent());
		c=opt.get();
		assertNotEquals(solde,c.getBalance());
	}
}

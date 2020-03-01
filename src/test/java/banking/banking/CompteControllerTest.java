package banking.banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import banking.banking.api.CompteControler;
import banking.banking.models.Compte;
import banking.banking.repositories.CompteRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CompteControllerTest {

	@Autowired
	private CompteRepository repo;
	@Autowired
	private CompteControler ctrl;

	@Test
	void testCreationCompte() {
		Compte c = new Compte("cpt1", 9999d);
		ResponseEntity response = ctrl.create(c);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(Compte.class, response.getBody().getClass());

	}
	@Test
	void testModificationCompte() {
		Compte c = new Compte("cpt1", 9999d);
		ResponseEntity response = ctrl.create(c);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(Compte.class, response.getBody().getClass());

	}
	@Test
	void testGetAllCompte() {

		CollectionModel<EntityModel<Compte>> response = ctrl.all();
		assertTrue(!response.getContent().isEmpty());

	}
	
}

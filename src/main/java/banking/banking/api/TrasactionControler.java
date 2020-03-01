package banking.banking.api;

import java.security.Principal;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.node.ObjectNode;

import banking.banking.models.Compte;
import banking.banking.models.facade.metier.TransactionHandler;
import banking.banking.service.CompteService;
import banking.banking.utils.CompteNotFoundException;

@RestController
@RequestMapping(value = "/api/transaction/", produces = "application/hal+json")
public class TrasactionControler {

	@Autowired
	private CompteService dao;

	@Autowired
	private TransactionHandler tr;

	/**
	 * Operation de retrait
	 */

	@PostMapping(path = "retrait")
	public ResponseEntity retrait(@RequestBody ObjectNode json) {
		return executeTransaction(json, 'w');
	}

	/**
	 * Operation de retrait
	 */

	@PostMapping(path = "depot")
	public ResponseEntity depot(@RequestBody ObjectNode json) {
		return executeTransaction(json, 'd');

	}

	@PostMapping("/historique/{id}")
	public ResponseEntity historique(@PathVariable Long id) {
	
		return new ResponseEntity<>(dao.getTransaction(id), HttpStatus.OK);

	}
	private ResponseEntity executeTransaction(ObjectNode objet, char type) {
		Long idCompte = objet.get("idCompte").asLong();
		Double montant = objet.get("montant").asDouble();
		String nom = objet.get("nom").textValue();
		Optional<Compte> compteOpt = dao.getCompte(idCompte);
		if (compteOpt.isPresent()) {
			Compte c = compteOpt.get();
			if (!c.getClient().getNom().equals(nom)) {

				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compte non trouvé");
			}
			tr.setC(c);
			if (type == 'd')
				tr.deposit(montant);
			else
				tr.retrait(montant);
			return new ResponseEntity<>("depot reussi", HttpStatus.OK);
		}
		return new ResponseEntity<>("un probléme!", HttpStatus.NOT_MODIFIED);
	}
}

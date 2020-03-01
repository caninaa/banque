package banking.banking.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import banking.banking.models.Compte;
import banking.banking.repositories.CompteRepository;
import banking.banking.service.CompteService;
import banking.banking.utils.CompteNotFoundException;
import banking.banking.utils.ModelAssembler;

@RestController
@RequestMapping(value = "/api/compte", produces = "application/hal+json")
public class CompteControler {
	@Autowired
	private CompteService dao;

	private final ModelAssembler assembler = new ModelAssembler();

	/**
	 * rechercher l'ensemble des comptes.
	 * 
	 * @return tout les comptes.
	 */
	@GetMapping
	public CollectionModel<EntityModel<Compte>> all() {
		SecurityContextHolder.getContext().getAuthentication();
		List<EntityModel<Compte>> comptes = dao.listerComptes().stream().map(assembler::toModel)
				.collect(Collectors.toList());

		return new CollectionModel<>(comptes, linkTo(methodOn(CompteControler.class).all()).withSelfRel());
	}

	/**
	 * rechercher compte
	 * 
	 * @param id
	 * @return le compte concérné
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Compte> one(@PathVariable Long id) {

		try {
			return dao.getCompte(id).map(p -> ResponseEntity.ok(p))
					.orElseThrow(() -> new CompteNotFoundException(id.toString()));
		} catch (CompteNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compte non trouvé");
		}

	}
/**
 * creer un compte.
 * @param compte compte à créer.
 * @return
 */
	// default JSR 380
	@PostMapping
	public ResponseEntity<Compte> create(@Valid @RequestBody Compte compte) {
		dao.ajouterCompte(compte);

		return ResponseEntity.ok(compte);
	}


/**
* creer un compte.
* @param compte compte à Supprimer.
* @return
*/
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") final long id) {
		try {
			return dao.getCompte(id).map(p -> {
				dao.supprimerCompte(id);
				return ResponseEntity.noContent().build();

			}).orElseThrow(() -> new CompteNotFoundException("z"));
		} catch (CompteNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compte non trouvé");

		}

	}

}

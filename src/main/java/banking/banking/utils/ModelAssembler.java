package banking.banking.utils;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import banking.banking.api.CompteControler;
import banking.banking.models.Compte;

@Component

public class ModelAssembler implements RepresentationModelAssembler<Compte, EntityModel<Compte>> {
	  @Override
	  public EntityModel<Compte> toModel(Compte compte) {

	    return new EntityModel<>(compte,
	      linkTo(methodOn(CompteControler.class).one(compte.getId())).withSelfRel(),
	      linkTo(methodOn(CompteControler.class).all()).withRel("comptes"));
	  }
}


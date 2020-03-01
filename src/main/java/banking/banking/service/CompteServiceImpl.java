package banking.banking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import banking.banking.models.Compte;
import banking.banking.models.TransactionDB;
import banking.banking.repositories.CompteRepository;
import net.bytebuddy.asm.Advice.Return;
@Repository
@Transactional(readOnly = true)
public class CompteServiceImpl implements CompteService{


	@Autowired
	private CompteRepository repo;
	@Override
	public Compte ajouterCompte(Compte c) {
		return repo.save(c);
	}

	@Override
	public List<Compte> listerComptes() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Compte modifierCompte(Compte c) {
	return repo.save(c);
	}

	@Override
	public void supprimerCompte(Long idCompte) {
		repo.deleteById(idCompte);
		
	}

	@Override
	public Optional<Compte> getCompte(Long id) {
		return repo.findById(id);
	}

	@Override
	public List<TransactionDB> getTransaction(Long idCompte) {
		Optional<Compte> compte= repo.findById(idCompte);
		if(compte.isPresent())
		return  compte.get().getTransactions();
		return null;
	}

}

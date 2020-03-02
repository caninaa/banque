package banking.banking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banking.banking.models.Compte;
import banking.banking.models.TransactionDB;
import banking.banking.repositories.CompteRepository;
@Service
public class CompteServiceImpl implements CompteService{


	@Autowired
	private CompteRepository repo;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Compte ajouterCompte(Compte c) {
		return repo.save(c);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Compte> listerComptes() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Compte modifierCompte(Compte c) {
	return repo.save(c);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void supprimerCompte(Long idCompte) {
		repo.deleteById(idCompte);
		
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Compte> getCompte(Long id) {
		return repo.findById(id);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TransactionDB> getTransaction(Long idCompte) {
		Optional<Compte> compte= repo.findById(idCompte);
		if(compte.isPresent())
		return  compte.get().getTransactions();
		return null;
	}

}

package banking.banking.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "Compte")
public class Compte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// optimiste lock
	@Version
	private Integer version;
	private String name;
	private Double balance;
	@ManyToOne(cascade=CascadeType.ALL )
	private Client client;

    @OneToMany(fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
	private List<TransactionDB> transactions;
	
	

	public List<TransactionDB> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionDB> transactions) {
		this.transactions = transactions;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Compte() {
		super();
	}

	public Compte(String name, Double balance) {
		super();
		this.setName(name);
		this.setBalance(balance);
	}

	public Compte(Long id, String name, Double balance) {
		super();
		this.setId(id);
		this.setName(name);
		this.setBalance(balance);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Account{" + "id=" + id + ", name='" + name + '\'' + ", balance='" + balance + '\'' + '}';
	}

	public void credit(Double montant) {
		balance += montant;
	}

	public void debit(Double montant) {
		balance -= montant;
	}

}

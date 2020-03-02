package banking.banking.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author macanina
 *
 */
@Entity
@Table(name = "TransactionDB")
public class TransactionDB {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private char type;
	private Double montantTransaction ;
	private Date dateTransaction;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public Date getDateTransaction() {
		return dateTransaction;
	}
	public void setDateTransaction(Date dateTransaction) {
		this.dateTransaction = dateTransaction;
	}
	public Double getMontantTransaction() {
		return montantTransaction;
	}
	public void setMontantTransaction(Double montantTransaction) {
		this.montantTransaction = montantTransaction;
	}
	
	
	
}

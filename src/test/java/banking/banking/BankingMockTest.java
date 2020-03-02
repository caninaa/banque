package banking.banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import banking.banking.models.Compte;
import banking.banking.repositories.CompteRepository;
import banking.banking.service.CompteService;
import banking.banking.service.CompteServiceImpl;
/**
 * Tester les fonctionalité de base de compte.
 * @author macanina
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BankingMockTest {

	@InjectMocks
	private CompteService service = new CompteServiceImpl();

	@Mock
	private CompteRepository repo;
	@BeforeEach
	void setMock() {
		Compte c = new Compte("cpt ", 99d);
		List<Compte> lc = new ArrayList<Compte>();
		lc.add(c);
		Mockito.when(repo.findAll()).thenReturn(lc);

	}

	@DisplayName("Test get all")
	@Test
	void testGet() {
		assertEquals(1, service.listerComptes().size());
	}

	@Test
	void adherentNonTrouve() {
		assertEquals(Optional.empty(), service.getCompte(99L));
	}
}

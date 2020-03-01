package banking.banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.mock.*;
import org.springframework.boot.test.context.SpringBootTest;

import banking.banking.models.Compte;
import banking.banking.service.CompteService;
import banking.banking.service.CompteServiceImpl;

@SpringBootTest
public class BankingMockTest {

	@Mock
	private CompteService service = new CompteServiceImpl();

	@BeforeEach
	void setMock() {
		Compte c = new Compte("cpt ", 99d);
		List<Compte> lc = new ArrayList<Compte>();
		lc.add(c);
		Mockito.when(service.listerComptes()).thenReturn(lc);

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

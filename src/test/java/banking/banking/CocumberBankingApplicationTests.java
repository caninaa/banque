package banking.banking;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * pout generer les classes de tests à partir des fichiers .features.
 * @author macanina
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty"},features = "src/test/resources")
public class CocumberBankingApplicationTests {



}


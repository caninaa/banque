package banking.banking.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import banking.banking.models.Client;
import banking.banking.models.Compte;
import banking.banking.repositories.CompteRepository;
/**
 * init Runner pour initialiser un jdd.
 *  * @author macanina
 *
 */
@Component
public class InitRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(InitRunner.class);
    @Autowired
    private CompteRepository dao;
    @Override
    public void run(ApplicationArguments args) throws Exception {
    	
        logger.info("Your application started with option names : {}", args.getOptionNames());
        Compte c = new Compte(999L,"999 0010 01201", 99d);
        List<Compte> l = dao.findAll();
        if(l==null || l.isEmpty()) {
            Client cl = new Client();
            cl.setNom("nom");
            c.setClient(cl);
            dao.save(c);
      
        }
   
    }
}



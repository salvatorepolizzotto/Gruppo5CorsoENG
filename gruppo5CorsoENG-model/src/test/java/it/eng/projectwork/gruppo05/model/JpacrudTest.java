package it.eng.projectwork.gruppo05.model;

import java.util.List;

import javax.persistence.Query;

import org.junit.Test;

import it.eng.projectwork.gruppo05.model.User;

public class JpacrudTest extends JPAHibernateTest {

	
	@Test // usiamo test per testare la corretta popolazione del database 
	public void test1() {
		em.getTransaction().begin();
		
		
		User u = new User();
		u.setNome("Salvo");
		u.setCognome("Cutaia");
		u.setUsername("Salvocutaia");
		u.setEmail("salvo.cutaia@eng.it");
		
		em.persist(u);
		
		
		//em.getTransaction().commit();
		
		//em.getTransaction().begin();
		
		User t=new User();
		t.setNome("pippo");
		t.setCognome("pluto");
		t.setUsername("plutocretino");
		t.setEmail("pluto.pippo@eng.it");
		
		em.persist(t);
		
		em.getTransaction().commit();
		em.getTransaction().begin();
		
		t.setNome("Poppo");			//cosi abbiamo aggiornato un'istanza della tabella user
		em.merge(t);				// merge 
		em.getTransaction().commit();
		
	}
	
	@Test
	public void test2() {
		
		Query query = em.createQuery("SELECT u FROM User u WHERE u.email=:email OR u.email=:emails");
		query.setParameter("email", "salvo.cutaia@eng.it");
		query.setParameter("emails","pluto.pippo@eng.it");
		// query.setFirstResult(1);   indica da quale riga della tabella user iniziare (in questo caso dalla riga 1)  
		List<User> users = query.getResultList();
		for(User user : users) {
			System.out.println(user);
			
		}
		 
	}
	
	
}

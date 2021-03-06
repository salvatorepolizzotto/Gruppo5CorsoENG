package it.eng.projectwork.gruppo05.model;

import java.util.List;

import javax.persistence.Query;

import org.junit.Test;

import it.eng.projectwork.gruppo05.model.User;

public class JpacrudTest extends JPAHibernateTest {

	
	@Test // usiamo test per testare la corretta popolazione del database 
	public void test1() {
		em.getTransaction().begin();
		
		
		User u = new Supplier("Salvo","Cutaia", "Salvocutaia","emailx");
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

		Query query = em.createQuery("SELECT u FROM User u WHERE u.email=:email");
		Query query2 = em.createQuery("select u from User u");
		query.setParameter("email", "salvo.cutaia@eng.it");
		//query.setParameter("emails","pluto.pippo@eng.it");
		// query.setFirstResult(1);   indica da quale riga della tabella user iniziare (in questo caso dalla riga 1)  
		List<User> users = query.getResultList();
		for(User user : users) {
			System.out.println(user);
			
		}
		List<User> users1 = query2.getResultList();
		for(User user1 : users1) {
			System.out.println(user1);
			
		}	 
	}	
}

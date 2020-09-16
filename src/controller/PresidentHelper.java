package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.President;//import the president
public class PresidentHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PresidentsListWeek3");

	public void insertPresident(President li) {
		EntityManager em = emfactory.createEntityManager();//creating an entity manager 
		em.getTransaction().begin();//adding an object, commiting it, then closing it
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}

	public List<President> showAllPresidents() {
		EntityManager em = emfactory.createEntityManager();
		List<President> allPresidents = em.createQuery("SELECT i FROM President i").getResultList();
		return allPresidents;
	}
	
	public void deletePresident(President toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<President> typedQuery = em.createQuery(
				"select pr from President pr where pr.presidencyNumber = :selectedNum and pr.name = :selectedName",
				President.class);
		// Substitute parameter with actual data from the toDelete President
		typedQuery.setParameter("selectedNum", toDelete.getPresidencyNumber());
		typedQuery.setParameter("selectedName", toDelete.getName());

		// we only want one result
		typedQuery.setMaxResults(1);

		// get the result and save it into a new list President
		President result = typedQuery.getSingleResult();

		// remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();

	}

	public List<President> searchForPresidentByNum(int presNum) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<President> typedQuery = em.createQuery("select pr from President pr where pr.presidencyNumber = :selectedNum", President.class);
		typedQuery.setParameter("selectedNum", presNum);

		List<President> foundPresidents = typedQuery.getResultList();
		em.close();
		return foundPresidents;
	}
	
	public President searchForPresidentById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		President found = em.find(President.class, idToEdit);
		em.close();
		return found;
	}

	public void updatePresident(President toEdit) {//update a president with the same id
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();		
		em.merge(toEdit);//merge the inputed president with the one with the same ID
		em.getTransaction().commit();
		em.close();
	}

	public List<President> searchForPresidentByName(String PresidentName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<President> typedQuery = em.createQuery("select pr from President pr where pr.name = :selectedName", President.class);
		typedQuery.setParameter("selectedPresident", PresidentName);

		List<President> foundPresidents = typedQuery.getResultList();
		em.close();
		return foundPresidents;
	}
	
	public void cleanUp(){
		emfactory.close();
	}	
}

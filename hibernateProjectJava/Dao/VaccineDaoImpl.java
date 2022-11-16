package com.solera.appdevelopment.hibernateProjectJava.Dao;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.solera.appdevelopment.hibernateProjectJava.entity.IndividualDetails;

@Repository
public class VaccineDaoImpl implements VaccineDao {

	private EntityManager em; // Entity manager is used to read, delete and write an entity

	@Autowired
	public VaccineDaoImpl(EntityManager emC) { // constructor of the class
		em = emC;
	}

	// Various functions with their implementations

	@Override
	public void save(IndividualDetails theIndividual) {
		// TODO Auto-generated method stub

		Session currentSession = em.unwrap(Session.class);

		currentSession.saveOrUpdate(theIndividual);
	}


@Override
public List<IndividualDetails> findAll() {
	// TODO Auto-generated method stub
	Session currentSession = em.unwrap(Session.class);
	Query<IndividualDetails> theQuery = currentSession.createQuery("from IndividualDetails",
			IndividualDetails.class);

	List<IndividualDetails> crowd = theQuery.getResultList();
	return crowd;
}

/*
@Override
public boolean ifIDPresent(int individualID) {
	Session currentSession = em.unwrap(Session.class);
	Query<IndividualDetails> theQ = currentSession.createQuery("from IndividualDetails where individualID =: d1",IndividualDetails.class) ;
	System.out.println("ind ID = "+ individualID);
	 theQ.setParameter("d1",individualID) ;
	System.out.println("theQ size  = "+theQ.getFetchSize());
	if(theQ.getFetchSize() == null) {
		return false ;
	}
	else {
		return true ;
	}
}
*/

@Override
public IndividualDetails addingIndividual(IndividualDetails theIndividual) {

	Session currentSession = em.unwrap(Session.class);

	Date d1 = theIndividual.getfDoseDate();
	Date d2 = theIndividual.getsDoseDate();
	Date d3 = theIndividual.gettDoseDate();
	String vaccName = theIndividual.getVaccineName();
	boolean check = false;

	if (d1 == null && d2 == null && d3 == null) {
		System.out.println(true);
		theIndividual.setVaccineNumber(0);
		theIndividual.setStatus("NOT VACCINATED");
		((Session) em).save(theIndividual);
		check = true;
	}

	else if (d1 != null && d2 == null & d3 == null) {
		theIndividual.setVaccineNumber(1);
		theIndividual.setStatus("SINGLEDOSE VACCINATED");
		((Session) em).save(theIndividual);
		check = true;
	}

	else if (d1 != null && d2 != null & d3 == null) {

		long diff2to1 = d2.getTime() - d1.getTime(); // gives the duration in seconds
		diff2to1 = TimeUnit.MILLISECONDS.toDays(diff2to1); // converts it into days

		if (diff2to1 > 120) {
			theIndividual.setVaccineNumber(2);
			theIndividual.setStatus("DOUBLEDOSE VACCINATED");
			((Session) em).save(theIndividual);
			check = true;
		}
	}

	else if (d1 != null && d2 != null & d3 != null) {

		long diff3to2 = d3.getTime() - d2.getTime();
		diff3to2 = TimeUnit.MILLISECONDS.toDays(diff3to2);

		if (diff3to2 > 270) {
			theIndividual.setVaccineNumber(3);
			theIndividual.setStatus("FULLY VACCINATED");
			((Session) em).save(theIndividual);
			check = true;
		}
	}
	if (check == true) {
		return theIndividual;
	} else {
		return null;
	}
	
}

@Override
public boolean ifIDPresent1(int individualID) {
	Session currentSession = em.unwrap(Session.class);
	System.out.println("1 ind ID = "+ individualID);
	Query<IndividualDetails> theQ = currentSession.createQuery("from IndividualDetails where individualID =: d1",IndividualDetails.class) ;
	
	 theQ.setParameter("d1",individualID) ;
	 List<IndividualDetails> personList = theQ.getResultList() ;
	System.out.println("theQ size  = "+ personList.size());
	if(personList.size() > 0) {
		return true ;
	}
	else {
		return false ;
	}
}
/*
@Override
public IndividualDetails findPerson(int individualID) {   // incomplete
	Session currentSession = em.unwrap(Session.class);
	Query<IndividualDetails> theQ = currentSession.createQuery("from IndividualDetails where individualID =: d1",IndividualDetails.class) ;
	List<IndividualDetails> personList = theQ.getResultList() ;
	
	return personList;
}
*/

@Override
public boolean deleteIndividual(int individualID) { // incomplete
	Session currentSession = em.unwrap(Session.class);
	boolean check = true ;
	Query<IndividualDetails> theQ = currentSession.createQuery("from IndividualDetails where vaccine_number = 3 ",IndividualDetails.class);

	List<IndividualDetails> personList = theQ.getResultList();

	for (IndividualDetails i : personList) {
		int person_Id = i.getIndividualID();

		if (person_Id == individualID) {
			Query theQuery = currentSession.createQuery("delete from IndividualDetails where id=: ps1");
			theQuery.setParameter("ps1", person_Id);
			theQuery.executeUpdate();
			System.out.println("1 =="+check);
			check = true ;
		} else {
			check = false ;
			System.out.println("2 =="+ check);
			
		}
	}
	return check;

}


@Override
public List<IndividualDetails> findByVacc(){
	Session currentSession = em.unwrap(Session.class);
	Query<IndividualDetails> theQ = currentSession.createQuery("from IndividualDetails where vaccineNumber >= 1",IndividualDetails.class) ;
	List<IndividualDetails> individualList = theQ.getResultList() ;
	return individualList ;
}

@Override
public List<IndividualDetails> singleVaccinatedPerson(int individualID){
	Session currentSession = em.unwrap(Session.class);
	Query<IndividualDetails> theQ = currentSession.createQuery("from IndividualDetails where individualID =: d1",IndividualDetails.class) ;
	theQ.setParameter("d1", individualID);
	List<IndividualDetails> individualList = theQ.getResultList() ;
	return individualList ;
}


@Override
public List<IndividualDetails> CovaxinBenificiaryPeople() {

	Session currentSession = em.unwrap(Session.class);
	Query<IndividualDetails> theQ = currentSession
			.createQuery("from IndividualDetails where vaccine_name = 'COVAXIN' ", IndividualDetails.class);
	// theQ.setParameter("d1", "1") ;
	List<IndividualDetails> CovaxinVaccineList = theQ.getResultList();
	return CovaxinVaccineList;
}

@Override
public List<IndividualDetails> CovishieldBenificiaryPeople() {

	Session currentSession = em.unwrap(Session.class);
	Query<IndividualDetails> theQ = currentSession
			.createQuery("from IndividualDetails where vaccine_name = 'COVISHIELD' ", IndividualDetails.class);
	// theQ.setParameter("d1", "1") ;
	List<IndividualDetails> CovaxinVaccineList = theQ.getResultList();
	return CovaxinVaccineList;
}

@Override
public List<IndividualDetails> SputnikBenificiaryPeople() {

	Session currentSession = em.unwrap(Session.class);
	Query<IndividualDetails> theQ = currentSession
			.createQuery("from IndividualDetails where vaccine_name = 'SPUTNIK' ", IndividualDetails.class);
	// theQ.setParameter("d1", "1") ;
	List<IndividualDetails> CovaxinVaccineList = theQ.getResultList();
	return CovaxinVaccineList;
}

@Override
public int covaxinCount() {
	Session currentSession = em.unwrap(Session.class);
	Query<IndividualDetails> theQ = currentSession
			.createQuery("from IndividualDetails where vaccine_name = 'COVAXIN' ", IndividualDetails.class);
	List<IndividualDetails> vList = theQ.getResultList();
	return vList.size();

}

@Override
public int covishieldCount() {
	Session currentSession = em.unwrap(Session.class);
	Query<IndividualDetails> theQ = currentSession
			.createQuery("from IndividualDetails where vaccine_name = 'COVISHIELD' ", IndividualDetails.class);
	List<IndividualDetails> vList = theQ.getResultList();
	return vList.size();

}

@Override
public int sputnikCount() {
	Session currentSession = em.unwrap(Session.class);
	Query<IndividualDetails> theQ = currentSession
			.createQuery("from IndividualDetails where vaccine_name = 'SPUTNIK' ", IndividualDetails.class);
	List<IndividualDetails> vList = theQ.getResultList();
	return vList.size();

}

@Override
public IndividualDetails findById(int individualID) {
    // TODO Auto-generated method stub
    Session currentSession = em.unwrap(Session.class);        
    return currentSession.get(IndividualDetails.class, individualID);
    
}



}

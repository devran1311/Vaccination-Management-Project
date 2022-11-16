package com.solera.appdevelopment.hibernateProjectJava.Dao;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.solera.appdevelopment.hibernateProjectJava.entity.IndividualDetails;

public interface VaccineDao {

	public void save(IndividualDetails theIndividual);

	public List<IndividualDetails> findAll();

	public  IndividualDetails addingIndividual(IndividualDetails theIndividual);

	// public boolean ifIDPresent(int individualID);

	// public IndividualDetails findByID(int IndividualID);

	public boolean ifIDPresent1(int individualID);

	// public IndividualDetails findPerson(int individualID);

	public List<IndividualDetails> findByVacc();

	public List<IndividualDetails> singleVaccinatedPerson(int individualID);

	public boolean deleteIndividual(int individualID);
	
	public List<IndividualDetails> CovaxinBenificiaryPeople();

	public List<IndividualDetails> CovishieldBenificiaryPeople();

	public List<IndividualDetails> SputnikBenificiaryPeople();

	public int covaxinCount();

	public int covishieldCount();

	public int sputnikCount();

	public IndividualDetails findById(int individualID);

	
	
	
	

	
}

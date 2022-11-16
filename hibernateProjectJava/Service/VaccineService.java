package com.solera.appdevelopment.hibernateProjectJava.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.solera.appdevelopment.hibernateProjectJava.entity.IndividualDetails;

public interface VaccineService {
	
	public void save(IndividualDetails theIndividual);
	
	public List<IndividualDetails> findAll() ;

	public  IndividualDetails addingIndvidual(IndividualDetails theIndividual);
	
	// public boolean ifIDPresent(int individualID);
	
	public boolean ifIDPresent1(int individualID);

	// public IndividualDetails findPerson(int individualID);

	public List<IndividualDetails> findByVacc();

	public List<IndividualDetails> singleVaccinatedPerson(int individualID);
	
	public boolean deleteIndividual(int individualID);

	public int covaxinCount();

	public int covishieldCount();

	public int sputnikCount();

	public List<IndividualDetails> SputnikVaccineBenificiary();

	public List<IndividualDetails> CovishieldVaccineBenificiary();

	public List<IndividualDetails> CovaxinVaccineBenificiary();
	
	public IndividualDetails findById(int individualID) ;
}


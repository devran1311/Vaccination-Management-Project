package com.solera.appdevelopment.hibernateProjectJava.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.solera.appdevelopment.hibernateProjectJava.Dao.VaccineDao;
import com.solera.appdevelopment.hibernateProjectJava.entity.IndividualDetails;

@Service
public class VaccineServiceImpl implements VaccineService {
	
	private VaccineDao vDao ;

	public VaccineServiceImpl(VaccineDao vD) {
		this.vDao = vD ;
	}
	
	@Override
	@Transactional
	public void save(IndividualDetails theIndividual) {
		vDao.save(theIndividual);
	
	}
	
	@Override
	@Transactional
	public List<IndividualDetails> findAll(){
		return vDao.findAll() ;
	}
	
	@Override
	@Transactional
	public IndividualDetails addingIndvidual(IndividualDetails theIndividual) {
		return vDao.addingIndividual(theIndividual) ;
	}
	
	/*
	@Override
	@Transactional
	public boolean ifIDPresent(int individualID) {
		return vDao.ifIDPresent(individualID) ;
	}
	*/
	
	@Override
	@Transactional
	public boolean ifIDPresent1(int individualID) {
		return vDao.ifIDPresent1(individualID);
	}
	/*
	@Override
	@Transactional
	public IndividualDetails findPerson(int individualID) {
		return vDao.findPerson(individualID) ;
	}
	*/
	
	@Override
	@Transactional
	public List<IndividualDetails> findByVacc(){
		return vDao.findByVacc() ;
	}
	
	@Override
	@Transactional
	public List<IndividualDetails> singleVaccinatedPerson(int individualID){
		return vDao.singleVaccinatedPerson( individualID) ;
		
	}
	
	@Override
	@Transactional
	public boolean deleteIndividual(int individualID) {
		return vDao.deleteIndividual(individualID);
	}
	
	@Override
	@Transactional
	public List<IndividualDetails> CovaxinVaccineBenificiary() {
		// TODO Auto-generated method stub
		return vDao.CovaxinBenificiaryPeople();
	}


	@Override
	@Transactional
	public List<IndividualDetails> CovishieldVaccineBenificiary() {
		// TODO Auto-generated method stub
		return vDao.CovishieldBenificiaryPeople();
	}


	@Override
	@Transactional
	public List<IndividualDetails> SputnikVaccineBenificiary() {
		// TODO Auto-generated method stub
		return vDao.SputnikBenificiaryPeople();
	}

	@Override
	@Transactional
	public int covaxinCount() {
		return vDao.covaxinCount();
	}

	@Override
	@Transactional
	public int covishieldCount() {
		return vDao.covishieldCount();
	}

	@Override
	@Transactional
	public int sputnikCount() {
		return vDao.sputnikCount();
	}
	
	@Override
	@Transactional
	public IndividualDetails findById(int individualID) {
		return vDao.findById(individualID);
	}

}

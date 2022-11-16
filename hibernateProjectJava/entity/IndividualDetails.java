package com.solera.appdevelopment.hibernateProjectJava.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name="PERSON_DETAILS_TABLE")
@SecondaryTable(name="VACCINE_TABLE_DETAILS")
public class IndividualDetails {
	
	@Id
    @Column(name = "Person_ID")
    private int individualID;
	
    @Column(name = "Name")
    private String name;
    
    @Column(name = "DOB")
    private Date dateOfBirth;
	
    @Column(table="VACCINE_TABLE_DETAILS" , name="FIRST_DOSE_DATE")
    private Date fDoseDate;
    
    @Column(table="VACCINE_TABLE_DETAILS" , name="SECOND_DOSE_DATE")
    private Date sDoseDate;
    
    @Column(table="VACCINE_TABLE_DETAILS" , name="THIRD_DOSE_DATE")
    private Date tDoseDate;
    
    @Column(table="VACCINE_TABLE_DETAILS", name="VACCINE_NAME")
    private String vaccineName;

    @Column(table="VACCINE_TABLE_DETAILS", name="Vaccination_status")
    private String status;
    
    @Column(table="VACCINE_TABLE_DETAILS", name="Vaccine_Number")
    private int vaccineNumber;
    
	public IndividualDetails() {

	}

	public IndividualDetails(int individualID, String name, Date dateOfBirth, Date fDoseDate, Date sDoseDate,
			Date tDoseDate, String vaccineName, String status, int vaccineNumber) {
		super();
		this.individualID = individualID;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.fDoseDate = fDoseDate;
		this.sDoseDate = sDoseDate;
		this.tDoseDate = tDoseDate;
		this.vaccineName = vaccineName;
		this.status = status;
		this.vaccineNumber = vaccineNumber;
	}

	public int getIndividualID() {
		return individualID;
	}

	public void setIndividualID(int individualID) {
		this.individualID = individualID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.toUpperCase();
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getfDoseDate() {
		return fDoseDate;
	}

	public void setfDoseDate(Date fDoseDate) {
		this.fDoseDate = fDoseDate;
	}

	public Date getsDoseDate() {
		return sDoseDate;
	}

	public void setsDoseDate(Date sDoseDate) {
		this.sDoseDate = sDoseDate;
	}

	public Date gettDoseDate() {
		return tDoseDate;
	}

	public void settDoseDate(Date tDoseDate) {
		this.tDoseDate = tDoseDate;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName.toUpperCase();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status.toUpperCase();
	}

	public int getVaccineNumber() {
		return vaccineNumber;
	}

	public void setVaccineNumber(int vaccineNumber) {
		this.vaccineNumber = vaccineNumber;
	}
	
	


	
	

}

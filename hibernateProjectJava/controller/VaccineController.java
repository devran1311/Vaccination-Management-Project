package com.solera.appdevelopment.hibernateProjectJava.controller;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.solera.appdevelopment.hibernateProjectJava.Service.VaccineService;
import com.solera.appdevelopment.hibernateProjectJava.entity.IndividualDetails;

@RestController
@RequestMapping("/api")
public class VaccineController {

	private VaccineService vS; // creating an object of the Service interface

	@Autowired
	public VaccineController(VaccineService vSCon) {
		vS = vSCon;
	}

	@GetMapping("/individuals") // getting details of all the individuals in the database
	public List<IndividualDetails> findAll() {
		return vS.findAll();
	}

	@PostMapping("/individuals") // posting a new entry into the database
	public ResponseEntity<Object> addIndividual(@RequestBody IndividualDetails theIndividual) throws Exception {
	//	try {
			// boolean idPresent = vS.ifIDPresent(theIndividual.getIndividualID());
			boolean idPresent = vS.ifIDPresent1(theIndividual.getIndividualID());
			System.out.println("VALUE TILL " + idPresent);

			if (idPresent == false) {
				theIndividual = vS.addingIndvidual(theIndividual);

				System.out.println("new check = " + theIndividual);

				if (theIndividual == null) {
					return new ResponseEntity<>("Dates are mismatched, please input correct date", HttpStatus.OK);
				} else {
					return new ResponseEntity<>(theIndividual, HttpStatus.OK);
				}

			} else {
				return new ResponseEntity<>("something went wrong", HttpStatus.OK);
			}
		} 
	//	catch (Exception e) {
	//		return new ResponseEntity<>(" Something went wrong, please check all details entered  ", HttpStatus.OK);
	//	}
	
 /*
	@PutMapping("/individuals/{m}") // Editing the existing the records
	public ResponseEntity<Object> editIndividual(@PathVariable int m, @RequestBody IndividualDetails theIndividual) {
	//	try {
			boolean check = vS.ifIDPresent1(m);

			System.out.println("1 check  = " + check);

			if (check == true) {
				theIndividual = vS.addingIndvidual(theIndividual);

				// System.out.println("new check = " + theIndividual);

				if (theIndividual == null) {
					return new ResponseEntity<>("Dates are mismatched, please input correct date", HttpStatus.OK);
				} else {
					return new ResponseEntity<>(theIndividual, HttpStatus.OK);
				}

			} else {
				return new ResponseEntity<>("something went wrong", HttpStatus.FORBIDDEN);
			}

	//	} catch (Exception e) {
	//		return new ResponseEntity<>(" Something went wrong, please check all details entered  ",
	//				HttpStatus.BAD_REQUEST);
		}
	//}
	 * 
	 */

	@GetMapping("/AllVaccinatedIndividuals") // 2 == Display Details of Vaccinated (Partly / Fully) Citizens.
	public List<IndividualDetails> findByVacc() {
		return vS.findByVacc();
	}

	@GetMapping("/singleVaccinatedIndividuals/{individualID}") // displaying the details of single citizen who is
																// vaccinated
	public ResponseEntity<Object> singleVaccinatedPerson(@PathVariable int individualID) {
		boolean check = vS.ifIDPresent1(individualID);
		if (check == true) {
			if (vS.singleVaccinatedPerson(individualID) != null) {
				return new ResponseEntity<>(vS.singleVaccinatedPerson(individualID), HttpStatus.OK);
			} else {
				return new ResponseEntity<>("The person with ID : " + individualID + " is not vaccinated ",
						HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<>("The person with ID : " + individualID + " is not present ",
					HttpStatus.FORBIDDEN);
		}

	}

	@DeleteMapping("/deleteindividuals/{individualID}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable int individualID) throws Exception {

		boolean check = vS.ifIDPresent1(individualID);
		System.out.println("check =" + check);

		if (check == true) {
			boolean check1 = vS.deleteIndividual(individualID);
			System.out.println("con ==" + check1);
			if (check1 == true) {
				return new ResponseEntity<>("The person with ID :" + individualID + " is deleted", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("The person with ID :" + individualID + " is not fully vaccinated",
						HttpStatus.FORBIDDEN);
			}

		} else {
			return new ResponseEntity<>("The person with ID : " + individualID + " is not present",
					HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping("/covaxin")
	public List<IndividualDetails> CovaxinBenificiary() {
		return vS.CovaxinVaccineBenificiary();
	}

	@GetMapping("/covishield")
	public List<IndividualDetails> covishieldBenificiary() {
		return vS.CovishieldVaccineBenificiary();
	}

	@GetMapping("/sputnik")
	public List<IndividualDetails> sputnikBenificiary() {
		return vS.SputnikVaccineBenificiary();
	}

	@GetMapping("/covaxinCount")
	public int covaxinCount() {
		return vS.covaxinCount();
	}

	@GetMapping("/covishieldCount")
	public int covishieldCount() {
		return vS.covishieldCount();
	}

	@GetMapping("/sputnikCount")
	public int sputnikCount() {
		return vS.sputnikCount();
	}
	

	@PutMapping("/individuals/{m}") // 4 == Edit the status of Vaccinated Citizen // ERROR HANDLED

	public ResponseEntity<Object> updatePerson(@RequestBody IndividualDetails theIndividual, @PathVariable int m) throws Exception {

		IndividualDetails theIndividual1 = vS.findById(m) ;
		boolean check2 = vS.ifIDPresent1(m);
		
		
		if(check2 == true) {
			Date firstDoseDate = theIndividual.getfDoseDate();
			Date secondDoseDate = theIndividual.getsDoseDate();
			Date thirdDoseDate = theIndividual.gettDoseDate();
			String vaccName = theIndividual.getVaccineName() ;
			

			long returnTime = 0;
			boolean check = false;

			if (firstDoseDate == null && secondDoseDate == null && thirdDoseDate == null) {
			//	theIndividual.setIndividualID(m);
				theIndividual1.setVaccineNumber(0);
				theIndividual1.setStatus("NOT VACCINATED");
				// theIndividual.setfDoseDate(firstDoseDate);
				vS.save(theIndividual1);
				check = true;
			}

			else if (firstDoseDate != null && secondDoseDate == null && thirdDoseDate == null && vaccName != null) {
			//	theIndividual.setIndividualID(m);
				theIndividual1.setVaccineNumber(1);
				theIndividual1.setVaccineName(vaccName);
				theIndividual1.setStatus("SINGLEDOSE VACCINATED");
				theIndividual1.setfDoseDate(firstDoseDate);
				vS.save(theIndividual1);
				check = true;
			}

			else if (firstDoseDate != null && secondDoseDate != null && thirdDoseDate == null) {
				long diff2to1 = secondDoseDate.getTime() - firstDoseDate.getTime(); // gives the duration in seconds
				diff2to1 = TimeUnit.MILLISECONDS.toDays(diff2to1); // converts it into days

				if (diff2to1 > 120 && diff2to1 > 0) {
					//theIndividual.setIndividualID(m);
					theIndividual1.setVaccineNumber(2);
					theIndividual1.setVaccineName(vaccName);
					theIndividual1.setfDoseDate(firstDoseDate);
					theIndividual1.setsDoseDate(secondDoseDate);
					theIndividual1.setStatus("DOUBLEDOSE VACCINATED");
					vS.save(theIndividual1);
					check = true;
				} else if (diff2to1 > 0 && diff2to1 < 120) {
					returnTime = 120 - diff2to1;
					return new ResponseEntity(
							"too early for Second dose Vaccination, Please come after " + returnTime + " days",
							HttpStatus.BAD_REQUEST);
				}
			}

			else if (firstDoseDate != null && secondDoseDate != null && thirdDoseDate != null) {
				long diff3to2 = thirdDoseDate.getTime() - secondDoseDate.getTime(); // gives the duration in seconds
				diff3to2 = TimeUnit.MILLISECONDS.toDays(diff3to2); // converts it into days

				if (diff3to2 > 270) {
				//	theIndividual.setIndividualID(m);
					theIndividual1.setVaccineName(vaccName);
					theIndividual1.setfDoseDate(firstDoseDate);
					theIndividual1.setsDoseDate(secondDoseDate);
					theIndividual1.settDoseDate(thirdDoseDate);
					theIndividual1.setVaccineNumber(3);
					theIndividual1.setStatus("FULLY VACCINATED");
					vS.save(theIndividual1);
					check = true;
				} else if (diff3to2 > 0 && diff3to2 < 270) {
					returnTime = 270 - diff3to2;
					return new ResponseEntity(
							"too early for Booster dose Vaccination, Please come after " + returnTime + " days",
							HttpStatus.BAD_REQUEST);
				}
			}

			if (check == true) {
				return new ResponseEntity<>(theIndividual1, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(" Dose dates are mismatched, please enter correct dates or some error occured ",
						HttpStatus.NOT_ACCEPTABLE);
			}

	}
		else {
			return new ResponseEntity<>(" ID is not present ",HttpStatus.OK);
		}

}

	
	
	
	
}

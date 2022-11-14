package com.abc.insurance.controller;


import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abc.insurance.dto.DefaultResponseDTO;
import com.abc.insurance.dto.ErrorDTO;
import com.abc.insurance.dto.MyDTO;
import com.abc.insurance.entity.MedicalInsurance;
import com.abc.insurance.entity.MedicalPolicies;
import com.abc.insurance.service.MedicalInsuranceService;
import com.abc.insurance.service.MedicalPoliciesService;
import com.abc.insurance.util.MedicalInsuranceDTOConvertor;


@RestController
@RequestMapping("/insurance")
public class MedicalInsuranceWebController {
	
	@Autowired
  MedicalInsuranceService medicalInsuranceService;
	
	@Autowired
	MedicalPoliciesService medicalPoliciesService;
	
	@Autowired
	MedicalInsuranceDTOConvertor dtoConvertor;
	
	public  MedicalInsuranceWebController() {
		System.out.println("\n\n\n====>> Inside Constructor "+this);
	}
	
	@GetMapping("/welcome")
	public String welcome()
	{
		
		return "Welcome to Medical Insurance";
		
	}
	
	private final Logger mylogs = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping("/add")//5000/insurance/add?clientName=sai
	public ResponseEntity<MyDTO> addMedicalInsurance(@RequestBody @Valid MedicalInsurance medicalInsurance,@RequestParam String userName)
	{
		MedicalPolicies alreadySavedPolicies= null;
		try
		{
			System.out.println(" --- > "+mylogs);
			mylogs.info("---->>>Inside try of addTravelInsurance ");

			MedicalInsurance savedMedicalInsurance = medicalInsuranceService.insertMedicalInsurance(medicalInsurance);
			if(savedMedicalInsurance.getmId() != 0)
			{
				alreadySavedPolicies = medicalPoliciesService.getMedicalPoliciesByUserName(userName);

				if(alreadySavedPolicies != null)
				{
					MedicalPolicies addMedicalInsurance = medicalPoliciesService.linkPolicies(alreadySavedPolicies, savedMedicalInsurance);

					DefaultResponseDTO dtoResponse = dtoConvertor.getMedicalInsuranceDefaultResponseDTO(addMedicalInsurance);

					return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
				}
				else
				{
					mylogs.error("Insurance not found in post mapping uri : add");
					throw new Exception("Insurance Not Found,  "+alreadySavedPolicies+" for "+userName);
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);

			ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return null;
	}
	@GetMapping("/viewMedicalInsurance")
	public List<MedicalInsurance> viewAllInsurance()
	{

		try {
			List<MedicalInsurance>  allExtractedInsurance =medicalInsuranceService.getAllMedicalInsurance();
			
			return allExtractedInsurance;
			
		} catch (Exception e) {
		
			System.out.println(e);
			
		}
		
		return null;
	}
	@GetMapping("/sumInsured/{sumInsured}")
	public MedicalInsurance getMedicalInsuranceBySumInsured(@PathVariable int sumInsured)throws Exception
	{
		
		return medicalInsuranceService.getMedicalInsuranceBySumInsured(sumInsured);
		
	}
	
	@GetMapping("/premium/{premium}")
	public List<MedicalInsurance> getMedicalInsuranceByPremiumamount(@PathVariable  int premium) throws Exception
	{
		
		return medicalInsuranceService.getMedicalInsuranceByPremium(premium);
		
	}
	
	
    @GetMapping("/insuranceName/{name}")
    public MedicalInsurance getMedicalInsuranceByInsuranceName(@PathVariable String insuranceName) throws Exception
    {
    	return medicalInsuranceService.getMedicalInsuranceByInsuranceName(insuranceName);
    }
    @PutMapping("/updateMedicalInsurance")
	public MedicalInsurance updateMedicalInsurance(@RequestBody MedicalInsurance medicalInsurance)throws Exception
	{
		
		return medicalInsuranceService.updateMedicalInsurance(medicalInsurance);
		
		
	}
    @DeleteMapping("/deleteMedicalInsurance")
    public String deleteInsurance(@RequestParam int mId) throws Exception
    {
    	medicalInsuranceService.deleteInsuranceByMId(mId);
    	return "Deleted id =" +mId+ "Data";
    }
    
}//end of class}//end of class}
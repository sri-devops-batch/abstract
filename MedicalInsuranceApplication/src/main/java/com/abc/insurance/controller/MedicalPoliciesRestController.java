package com.abc.insurance.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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


import lombok.Value;

@RestController
@RequestMapping("/policy/client/medicalPolicies")
@Validated
public class MedicalPoliciesRestController {
	@Autowired
	MedicalPoliciesService medicalPoliciesService;
	
	@Autowired
	MedicalInsuranceService medicalInsuranceService;
	
	@Autowired
	MedicalInsuranceDTOConvertor dtoConvertor;
	
   private final Logger mylogs = LoggerFactory.getLogger(this.getClass());
	
	

	@PostMapping("/add")  //8005/client/policy/add
	public ResponseEntity<String> doMedicalPoliciesThings(@RequestBody  MedicalPolicies medicalPolicies)
	{

		try
		{
			System.out.println(" --- > "+mylogs);
			mylogs.info("---->>>Inside try of doTravelPolicyThings");

			MedicalPolicies savedMedicalPolicies = medicalPoliciesService.addMedicalPolicies(medicalPolicies);

			String responseMsg = savedMedicalPolicies.getUserName()+"save with Id "+savedMedicalPolicies.getMedicalPolicyId();



			mylogs.info(savedMedicalPolicies.getUserName()+"save with Id "+savedMedicalPolicies.getMedicalPolicyId());

			return new ResponseEntity<String>(responseMsg, HttpStatus.OK);
		}

		catch(Exception e) {

			String errorMsg =  "Contact to Travel Agency 1800-250-960 or mail us :- travelagency@insurance.com";
			return new ResponseEntity<String>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	@GetMapping("/policiesByPolicyNo")
	public List<MedicalPolicies> policiesBetweenPolicyNumber(@RequestParam int r1 , @RequestParam int r2)throws Exception
	{
		
		return medicalPoliciesService.getMedicalPoliciesBetweenPolicyNumber(r1, r2);
	}	
	
	@GetMapping("/policies/{searchuserName}")
	public MedicalPolicies getByClientName(@PathVariable String userName)throws Exception
	{
		return medicalPoliciesService.getMedicalPoliciesByUserName(userName);
	}
	@GetMapping("/isClaimed/{claimedDate}")
	public List<MedicalPolicies> getPoliciesOnClaimedDate(@PathVariable String isClaimed,@RequestParam String claimedDate)throws Exception
	{
		if(claimedDate != null)
		{
			return medicalPoliciesService.getMedicalPoliciesBasedOnisClaimedDate(isClaimed, claimedDate);
		}
		else return null;
	}
	
	
}
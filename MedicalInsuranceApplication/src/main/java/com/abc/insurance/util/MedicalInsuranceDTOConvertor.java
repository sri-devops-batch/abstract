package com.abc.insurance.util;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.abc.insurance.dto.DefaultResponseDTO;
import com.abc.insurance.entity.MedicalInsurance;
import com.abc.insurance.entity.MedicalPolicies;

@Component
@Scope("singleton")
public class MedicalInsuranceDTOConvertor {

	public static DefaultResponseDTO getMedicalInsuranceDefaultResponseDTO(MedicalPolicies addMedicalInsurance) {
		
		DefaultResponseDTO dto = new DefaultResponseDTO(
				addMedicalInsurance.getUserName(),
				addMedicalInsurance.getMedicalPolicyId(),
				addMedicalInsurance.getMedicalInsurances().getInsuranceName(),

				"Client Policy Added, Travel Insurance Id :"+addMedicalInsurance.getMedicalInsurances().getmId());

		return dto;

		
	}

	
}
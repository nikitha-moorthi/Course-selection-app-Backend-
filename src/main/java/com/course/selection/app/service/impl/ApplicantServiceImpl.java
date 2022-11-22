package com.course.selection.app.service.impl;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.course.selection.app.constants.AdmissionStatus;
import com.course.selection.app.entity.Admission;
import com.course.selection.app.entity.Applicant;
import com.course.selection.app.exception.ApplicantNotFoundException;
import com.course.selection.app.repository.ApplicantRepository;
import com.course.selection.app.service.ApplicantService;

@Service
public class ApplicantServiceImpl implements ApplicantService  {
	
	@Autowired
	private ApplicantRepository applicantRepository;

	@Override
	public Applicant saveApplicantDetails(Applicant applicant) {
		Applicant newApplicant = applicantRepository.save(applicant);
		sendWhatsAppMessage(newApplicant.getMobileNumber().toString(), "Congratulations Dear "+newApplicant.getApplicantName()+". Your Applicant Id: "+newApplicant.getApplicantId().toString()+". Your password: "+newApplicant.getPassword());
		return newApplicant;
		
	}

	@Override
	public Applicant updateApplicantDetails(Applicant applicant, Long applicantId) {
		Applicant applicantUpdate = applicantRepository.findById(applicantId).orElse(null);
		//if(!ObjectUtils.isEmpty(applicantUpdated)) 
		if(applicantUpdate!=null){
			applicantUpdate.setApplicantName(applicant.getApplicantName());
			applicantUpdate.setMobileNumber(applicant.getMobileNumber());
			applicantUpdate.setApplicantDegree(applicant.getApplicantDegree());
			applicantUpdate.setApplicantGraduationPercent(applicant.getApplicantGraduationPercent());
			applicantRepository.save(applicantUpdate);
			return applicantUpdate;
		}
		else
		{
			throw new ApplicantNotFoundException("ApplicantId ("+applicantId+") Not Found");
		}
	}

	@Override
	public List<Applicant> getAllApplicantDetails() {
		return applicantRepository.findAll();
	}

	@Override
	public Applicant getApplicantDetails(Long applicantId) {
		Applicant applicantOpt = applicantRepository.findById(applicantId).orElse(null);
		if(applicantOpt==null) {
			throw new ApplicantNotFoundException("ApplicantId ("+applicantId+") Not Found");
		}else {
		return applicantOpt;
		}
	}

	@Override
	public void deleteApplicantDetails(Long applicantId)  {
		//applicantRepository.deleteById(applicantId);
		Optional<Applicant> applicantOpt = applicantRepository.findById(applicantId);
		if(!applicantOpt.isPresent()) {
			throw new ApplicantNotFoundException("ApplicantId ("+applicantId+") Not Found");
		}else {
			applicantRepository.deleteById(applicantId);
		}
	}
	
	private void sendWhatsAppMessage(String phoneNumber, String content) {
        
        String ACCOUNT_SID = "AC6513d8e1db0973db2f6676b8d7f10fd4";
        String AUTH_TOKEN = "566efddbd11663a5b3586fa733c80cf0";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            
            //Twilio.setPassword("babugtech@gmail.com");
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("whatsapp:+91"+phoneNumber), 
                    new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                    content)
                .create();
         System.out.println("==============Sent Message=================");    }

   }

	


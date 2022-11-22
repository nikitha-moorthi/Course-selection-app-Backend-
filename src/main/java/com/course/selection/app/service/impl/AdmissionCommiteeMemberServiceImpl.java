package com.course.selection.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.selection.app.constants.AdmissionStatus;
import com.course.selection.app.entity.Admission;
import com.course.selection.app.entity.AdmissionCommiteeMember;
import com.course.selection.app.entity.Applicant;
import com.course.selection.app.exception.AdminNotFoundException;
import com.course.selection.app.exception.AdmissionSelectionException;
import com.course.selection.app.exception.ApplicantNotFoundException;
import com.course.selection.app.repository.AdmissionCommiteeMemberRepository;
import com.course.selection.app.repository.AdmissionRepository;
import com.course.selection.app.repository.ApplicantRepository;
import com.course.selection.app.service.AdmissionCommiteeMemberService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class AdmissionCommiteeMemberServiceImpl implements AdmissionCommiteeMemberService{

	@Autowired
	private AdmissionCommiteeMemberRepository admissionCommiteeMemberRepository;
	
	@Autowired
	private AdmissionRepository admissionRepository;
	
	@Autowired
	private ApplicantRepository applicantRepository;
	
	
	@Override
	public AdmissionCommiteeMember addCommiteeMember(AdmissionCommiteeMember admissionCommiteeMember) {
		AdmissionCommiteeMember newAdmin= admissionCommiteeMemberRepository.save(admissionCommiteeMember);
		sendWhatsAppMessage(newAdmin.getAdminContact().toString(), "Congratulations Dear "+newAdmin.getAdminName()+". You have successfully registered as admin in the University. Your Admin Id: "+newAdmin.getAdminId().toString()+". Your password: "+newAdmin.getPassword());
		return newAdmin;
	}

	@Override
	public AdmissionCommiteeMember updateCommiteeMember(AdmissionCommiteeMember admissionCommiteeMember, Integer adminId) {
		AdmissionCommiteeMember updatedCommiteeMember = admissionCommiteeMemberRepository.findById(adminId).orElse(null);
		
		if(!(updatedCommiteeMember == null)) {
				updatedCommiteeMember.setAdminName(admissionCommiteeMember.getAdminName());
				updatedCommiteeMember.setAdminContact(admissionCommiteeMember.getAdminContact());
				admissionCommiteeMemberRepository.save(updatedCommiteeMember);
				return updatedCommiteeMember;
		}
		else 
			throw new AdminNotFoundException("adminId ("+adminId+") Not Found");
		
	}

	@Override
	public AdmissionCommiteeMember viewCommiteeMember(Integer adminId) {
		AdmissionCommiteeMember admOpt = admissionCommiteeMemberRepository.findById(adminId).orElse(null);
			if(admOpt==null) {
				throw new AdminNotFoundException("AdminId ("+adminId+") Not Found");
			}else {
			return admOpt;
		}
	}

	@Override
	public void removeCommiteeMember(Integer adminId) {
		AdmissionCommiteeMember admin = admissionCommiteeMemberRepository.findById(adminId).orElse(null);
		
		if(!(admin == null)) {
			admissionCommiteeMemberRepository.deleteById(adminId);
		}
		else 
			throw new AdminNotFoundException("adminId ("+adminId+") Not Found");
		
		
	}

	@Override
	public List<AdmissionCommiteeMember> viewAllCommiteeMembers() {
		return admissionCommiteeMemberRepository.findAll();
	}

	@Override
	public void provideAdmissionResult(Long applicantId, Admission admission) {
		
		Optional<Applicant> applicantOpt = applicantRepository.findById(applicantId);
		List<Admission> admissions =  admissionRepository.findByApplicant(applicantOpt.get());
		//Optional<Admission> applicant = admissionRepository.findById(applicantId);
		List<Admission> applicants = admissions.stream().filter(adm -> adm.getApplicant().getApplicantId() == applicantId).collect(Collectors.toList());//.stream().flatMap(applicant->applicant.stream()).collect(Collectors.toList());
		if(applicants.isEmpty()) {
			throw new ApplicantNotFoundException("ApplicantId ("+applicantId+") Not Found");
		}
		else {
			
			admissions.forEach(adm -> {
				if(adm.getStatus()== AdmissionStatus.APPLIED && admission.getStatus() == AdmissionStatus.PENDING ) {

					adm.setStatus(admission.getStatus());
					admissionRepository.saveAll(admissions);
					
				
				}
				else if(adm.getStatus()== AdmissionStatus.PENDING && 
						(admission.getStatus() == AdmissionStatus.CONFIRMED || 
						admission.getStatus() == AdmissionStatus.REJECTED) ) {
					
					adm.setStatus(admission.getStatus());
					admissionRepository.saveAll(admissions);
					
				}
				else {
					throw new AdmissionSelectionException("Status Cannot be Updated");
				}
			});
			
		}
		
		
		
		
	}
	
private void sendWhatsAppMessage(String phoneNumber, String content) {
        
        String ACCOUNT_SID = "AC6513d8e1db0973db2f6676b8d7f10fd4";
        String AUTH_TOKEN = "566efddbd11663a5b3586fa733c80cf0";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("whatsapp:+91"+phoneNumber), 
                    new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                    content)
                .create();
         System.out.println("==============Sent Message=================");    }


	

}

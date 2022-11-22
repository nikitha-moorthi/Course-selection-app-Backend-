package com.course.selection.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import com.course.selection.app.entity.UniversityStaffMember;
import com.course.selection.app.exception.StaffNotFoundException;
import com.course.selection.app.repository.UniversityStaffMemberRepository;
import com.course.selection.app.service.UniversityStaffMemberService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;


@Service
public class UniversityStaffMemberServiceImpl implements UniversityStaffMemberService {
	@Autowired
	private UniversityStaffMemberRepository universityStaffMemberRepository;

	@Override
	public UniversityStaffMember addStaff(UniversityStaffMember staff) {
		
		UniversityStaffMember newStaff = universityStaffMemberRepository.save(staff);
		sendWhatsAppMessage(newStaff.getStaffContact().toString(), "Congratulations!! You have registered as staff "+". Your Staff Id: "+newStaff.getStaffId().toString()+". Your password: "+newStaff.getPassword());
		return newStaff;
	}

	@Override
	public UniversityStaffMember updateStaff(UniversityStaffMember staff, Integer staffId) {
		UniversityStaffMember staffUpdated = universityStaffMemberRepository.findById(staffId).orElse(null);
		if(!ObjectUtils.isEmpty(staffUpdated)) {
			staffUpdated.setStaffId(staff.getStaffId());
			staffUpdated.setRole(staff.getRole());
			staffUpdated.setPassword(staff.getPassword());
			universityStaffMemberRepository.save(staffUpdated);
				}		
		return staffUpdated;
	}


	@Override
	public void removeStaff(Integer staffId) {
		
		universityStaffMemberRepository.deleteById(staffId);
	}

	@Override
	public List<UniversityStaffMember> viewAllStaffs() {
		return universityStaffMemberRepository.findAll();
	}
	@Override
	public UniversityStaffMember viewStaff(Integer staffId) {
		UniversityStaffMember staffOpt = universityStaffMemberRepository.findById(staffId).orElse(null);
		if (staffOpt == null) {
			throw new StaffNotFoundException("StaffId (" + staffId + ") Not Found");
		} 
			else {
				return staffOpt;
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


	
	

	



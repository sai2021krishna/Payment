package com.barclays.service;

import com.barclays.entity.EmailDetails;

public interface EmailService {
	String sendSimpleMail(EmailDetails details);
	String sendMailWithAttachment(EmailDetails details);
}

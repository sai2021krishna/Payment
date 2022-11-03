package com.barclays.service;

import com.barclays.entity.EmailDetail;

public interface EmailService {
	String sendSimpleMail(EmailDetail details);
	String sendMailWithAttachment(EmailDetail details);
}

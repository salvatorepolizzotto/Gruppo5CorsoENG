package it.eng.projectwork.gruppo05.eisint;

import javax.ejb.Local;

@Local
public interface SendMail {
	
	public void sendMailAllUser(Message message) throws MailNotSendException;
	public void sendMail(Message message,String destination) throws MailNotSendException;

}

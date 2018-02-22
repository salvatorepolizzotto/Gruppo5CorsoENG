package it.eng.projectwork.gruppo05.eis;

import javax.ejb.Stateless;

import it.eng.projectwork.gruppo05.eisint.MailNotSendException;
import it.eng.projectwork.gruppo05.eisint.Message;
import it.eng.projectwork.gruppo05.eisint.SendMail;

@Stateless
public class SendMailImpl implements SendMail{

	@Override
	public void sendMailAllUser(Message message) throws MailNotSendException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMail(Message message, String destination) throws MailNotSendException {
		// TODO Auto-generated method stub
		
	}
	
	public void outputMail() {
		System.out.println("Email inviata.");
	}
}

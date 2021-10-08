package elena.krunic.twiggy.gymSoftware.service.impl;


import java.io.StringWriter;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import elena.krunic.twiggy.gymSoftware.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	JavaMailSender mailSender; 
	
	//@Autowired 
	//VelocityEngine ve;
	
	//implementacija velocity-ja 
	VelocityEngine ve = new VelocityEngine();
	
    public static final String EMAIL_ELENA = "krunicele@gmail.com";
    public static final String PASS_ELENA = "wetrovi691!";
	
	@Override
	public void scheduleTraining(String date, String coach, String trainingName, String emailTo) {
		VelocityContext context = new VelocityContext(); 
		
		context.put("name", trainingName);
		context.put("coach", coach); 
		context.put("duration", date);
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setSubject("Training info");
			helper.setFrom(EMAIL_ELENA);
			helper.setTo(emailTo);
			helper.setText(trainingInformation(context), true);
			new Thread(() -> {
				mailSender.send(helper.getMimeMessage());
			}).start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public String trainingInformation(VelocityContext context) {
		StringWriter writer = new StringWriter();
		try {
			//Template template = Velocity.getTemplate("templates/my.vm");
			//ve.mergeTemplate("/gymSoftware/src/main/resources/templates/scheduledTraining-template.vm", context, writer);
			Template t = ve.getTemplate("./src/main/resources/templates/scheduledTraining-template.vm");
			//System.out.println(t.getData());
			t.merge(context, writer);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return writer.toString();
	}

}

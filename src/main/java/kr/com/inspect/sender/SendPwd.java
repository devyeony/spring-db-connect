package kr.com.inspect.sender;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 임시 비밀번호를 메일로 발송함
 * @author Yeonhee Kim
 *
 */
@Service
@PropertySource(value = "classpath:properties/sender.properties")
public class SendPwd {
	/**
	 * 메일전송 기능을 위한 필드 선언
	 */
    @Autowired
    private JavaMailSender mailSender;
    
    /**
     * 발신 이메일
     */
    @Value("${mail.username}") 
    private String mailUsername;
    
    /**
     *  비밀번호 변경을 위한 인증번호 발송 메일을 보냄
    * @param email 회원 이메일
    * @param key 인증번호
    * @throws Exception 예외
     */
    public void sendMail(String email, String pwd) throws Exception{
    	try{
    		MimeMessage message = mailSender.createMimeMessage();
    		
    		message.addRecipient(RecipientType.TO, new InternetAddress(email)); // 받는 사람
    		message.setFrom(new InternetAddress(mailUsername)); // 보내는 사람
    		message.setSubject("SDTM 임시 비밀번호 발송(비밀번호 변경 바랍니다.)"); // 메일 제목 (생략 가능)
    		
    		String msg = "";
    		msg += "<div align='center' style='border:1px solid black; font-family:verdana;'>";
    		msg += "<h2><b>임시 비밀번호로 로그인하세요.</b></h2>";
    		msg += "<div style='font-size: 130%;'>";
    		msg += "<span style='color: red;'>로그인 후 비밀번호를 변경하세요.</span><br/>";
    		msg += "임시 비밀번호는 <strong>" + pwd + "</strong> 입니다.<br/><br/></div>";
    		msg += "<img src='http://45.32.55.180:8080/resource/img/NAMU_Logo_PNG.png' width='300'><br/><br/><br/>";
    		message.setContent(msg,"text/html;charset=euc-kr"); // 메일 본문
    		    		
    		/* 메일 발송 */
    		mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
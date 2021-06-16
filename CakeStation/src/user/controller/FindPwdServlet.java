package user.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FindPwdServlet
 */
@WebServlet("/member/findPwd")
public class FindPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    /*
    public static void sendEmail(String email, String subject, String text) {
    	String host = "smtp.gmail.com";
    	String user = "xxxxx@gmail.com";
    	String sender = "ccccc@gmail.com";
    	String password = "zzzzzzzzzz";
    	
    	// SMTP 서버 정보 설정(ssl 적용x)
    	Properties props = new Properties();
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.host", host);
    	props.put("mail.smtp.prot", 587);
    	props.put("mail.smtp.auth", "true");
    	
    	// 인증
    	Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
    		protected PasswordAuthentication getPasswordAuthentication() {
    			return new PasswordAuthentication(user, password);
    		}
    	});
    	
    	try {
    		MimeMessage message = new MimeMessage(session);
    		// 받는 사람 메일
			message.setFrom(new InternetAddress(sender));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			// 메일 제목
			message.setSubject(subject);
			// 메일 내용
			message.setText(text);
			// send the message
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    */
    
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Properties props = System.getProperties();
		props.put("mail.smtp.user", "main0kunn@gmail.com"); // 서버 아이디만 쓰기(발신인 이메일)
		props.put("mail.smtp.host", "smtp.gmail.com"); // 구글 SMTP
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.debug", "true"); //////////////
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		
		// 메일 인증
		Authenticator auth = new MyAuthentication();
		// session 생성 및 MimeMessage 생성
		Session session = Session.getDefaultInstance(props, auth); // getInst...
		session.setDebug(true); // 메일 전송 시 상세상황 콘솔에 출력 ///////////
		MimeMessage msg = new MimeMessage(session);
		int randomCode = 0;
		try { 
			// 편지 보낸 시간
			msg.setSentDate(new Date());
			
			InternetAddress from = new InternetAddress("main0kunn@gmail.com"); //보내는 사람(메일주소?)
			
			// 이메일 발신자
			msg.setFrom(from);
			
			// 이메일 수신자
			String email = request.getParameter("email-id") + "@" + request.getParameter("email-domain"); // 사용자가 입력한 이메일 받아오기
			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO, to);
			
			// 이메일 제목
			msg.setSubject("[Cake Station] 비밀번호 재설정 인증메일입니다.", "UTF-8");
			
			// 이메일 내용
			randomCode = (int)Math.floor((Math.random()*(99999-10000+1)))+10000;
						// 5자리 숫자로 이루어진 인증번호 랜덤 생성
			//String code = request.getParameter("code-check");
			String content = "임시 비밀번호는 " + randomCode + " 입니다."; // 인증번호 값 받기
			msg.setText(content, "UTF-8");
			
			// 이메일 헤더
			msg.setHeader("content-Type", "text/html");
			
			// 메일 보내기
			Transport.send(msg);
			System.out.println("보냄!!");
		} catch (AddressException addr_e) {
			addr_e.printStackTrace();
		}catch (MessagingException msg_e) {
			msg_e.printStackTrace();
		}
		request.setAttribute("checkCode", randomCode);
		RequestDispatcher view = request.getRequestDispatcher("/member/findPwd.jsp");
		view.forward(request, response);
	}

	// 아이디 패스워드 인증받기 함수
	class MyAuthentication extends Authenticator{
		
		PasswordAuthentication pa;
		
		public MyAuthentication() {
			String id = "main0kunn"; // 구글 ID(발신인 이메일-도메인 제외)
			String pw = "gywn12love!!"; // 구글 비밀번호(발신인 이메일 비번)
			// ID와 비밀번호 입력
			pa = new PasswordAuthentication(id, pw);
		}
		
		// 시스템에서 사용하는 인증정보
		public PasswordAuthentication getPasswordAuthentication() {
			return pa;
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String userId = request.getParameter("userId");
//		String email = request.getParameter("email-id") + "@" + request.getParameter("email-domain");
//		
//		String subject = "[Cake Station] 비밀번호 재설정 인증메일입니다.";
//		String text = "임시 비밀번호는 0123456789 입니다."; // 임시 비밀번호 생성 다시 알아보고 마저 구현
//		//doGet(request, response);
//		sendEmail(email, subject, text);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		service(request, response);
	}

}

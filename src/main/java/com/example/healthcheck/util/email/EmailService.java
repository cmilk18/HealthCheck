package com.example.healthcheck.util.email;


import com.example.healthcheck.user.requestdto.UserCreateRequestDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    public void sendEmail(UserCreateRequestDTO userCreateRequestDTO, String title, String text) throws MessagingException {
        String toEmail = userCreateRequestDTO.getName();
        /*SimpleMailMessage emailForm = createEmailForm(toEmail, title, text);
        try {
            emailSender.send(emailForm);
        } catch (RuntimeException e) {
            throw new SendEmailErrorException("이메일 전송에 실패했습니다.");
        }*/
        MimeMessage emailForm = createHtmlEmail(toEmail, title, text);

        // 실제 메일 전송
        emailSender.send(emailForm);

    }

    // 발신할 이메일 데이터 세팅
    /*private SimpleMailMessage createEmailForm(String toEmail,
                                              String title,
                                              String text) {

        String htmlEmailContent =
                "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "<style>" +
                        "body { font-family: Arial, sans-serif; }" +
                        ".container { border: 5px solid #49B7CF; padding: 20px; }" +
                        "h1 { color: #49B7CF; text-align: center; font-size: 50px; }" +
                        "hr { color: #49B7CF; }" +
                        "p { color: #666666; text-align: center; }" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container\">" +
                        "<h1>SOM</h1>" +
                        "<hr>" +
                        "<p> 인증번호 : "+ text + "</p>" +
                        "</div>" +
                        "</body>" +
                        "</html>";


        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("alertmail119@gmail.com");
        message.setTo(toEmail);
        message.setSubject(title);
        message.setContent(htmlEmailContent,"text/html");

        return message;
    }*/
    public MimeMessage createHtmlEmail(String toEmail, String title, String text) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("${spring.mail.host}");
        helper.setTo(toEmail);
        helper.setSubject(title);
        helper.setText(createHtmlContent(text), true);

        return message;
    }

    private String createHtmlContent(String text) {
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "</head>" +
                "<body style=\"font-family: Arial, sans-serif;\">" +
                "<div style=\"border: 5px solid #49B7CF; padding: 20px;\">" +
                "<h1 style=\"color: #49B7CF; text-align: center; font-size: 50px;\">SOM</h1>" +
                "<hr style=\"color: #49B7CF;\">" +
                "<p style=\"color: #666666; text-align: center;\">인증번호 : " + text + "</p>" +
                "</div>" +
                "</body>" +
                "</html>";
    }

}
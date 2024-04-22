package com.flashwizserver.controller;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flashwizserver.model.User;
import com.flashwizserver.service.UserDAO;
import com.flashwizserver.utility.Utility;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

import java.security.SecureRandom;

@RestController
public class ForgotPasswordController {
	@Autowired
    private JavaMailSender mailSender;
     
    @Autowired
    private UserDAO userDAO;
     
    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
        return "forgot_password_form";
    }
 
//    @PostMapping("/forgot_password")
//    public String processForgotPassword(HttpServletRequest request, Model model) {
//        String email = request.getParameter("email");
//        String token = RandomString.make(30);
//         
//        try {
//            userDAO.updateResetPasswordToken(token, email);
//            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
//            sendEmail(email, resetPasswordLink);
//            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
//             
//        } catch (AccountNotFoundException ex) {
//            model.addAttribute("error", ex.getMessage());
//        } catch (UnsupportedEncodingException | MessagingException e) {
//            model.addAttribute("error", "Error while sending email");
//        }
//             
//        return "forgot_password_form";
//    }
    
    private String generateOTP() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder otp = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            otp.append(secureRandom.nextInt(10));
        }
        return otp.toString(); // Tạo một chuỗi OTP 6 ký tự
    }
    @PostMapping("/forgot_password")
    @ResponseBody
    public ResponseEntity<Map<String, String>> processForgotPassword(HttpServletRequest request) {
        String email = request.getParameter("email");
        String otp = generateOTP();
        Map<String, String> response = new HashMap<>();
         
        try {
            userDAO.updateResetPasswordOTP(otp, email);
         
            sendEmail(email, otp);
            
            response.put("status", "success");
            response.put("email", email);
            response.put("otp", otp); // Thêm OTP vào phản hồi
            
             
            return ResponseEntity.ok(response);
             
        } catch (AccountNotFoundException ex) {
            response.put("status", "error");
            response.put("message", ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (UnsupportedEncodingException | MessagingException e) {
            response.put("status", "error");
            response.put("message", "Error while sending email");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    public void sendEmail(String recipientEmail, String otp)
        throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();              
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setFrom("contact@flashwizapp.com", "FlashWizApp Support");
        helper.setTo(recipientEmail);
         
        String subject = "Your One-Time Password (OTP) for password reset";
         
        String content = "<html>"
                + "<head>"
                + "<style>"
                + "body {font-family: Arial, sans-serif; font-size: 16px; margin: 0; padding: 0;}"
                + "a {color: #007bff; text-decoration: none;}"
                + "a:hover {text-decoration: underline;}"
                + ".container { margin: 0 auto; padding: 20px 20px 20px 0;}"
                + ".note {color: red; font-size: 14px;}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class=\"container\">"
                + "<h2>Hello,</h2>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Your One-Time Password (OTP) is:</p>"
                + "<h3>" + otp + "</h3>" 
                + "<p>Please use this OTP to reset your password:</p>"
                + "<p class=\"note\">"
                + "Note: This OTP will expire in 10 minutes for security reasons. "
                + "If you do not reset your password within this time frame, "
                + "you will need to request another OTP."
                + "</p>"
                + "<br>"
                + "<p>Ignore this email if you remember your password or "
                + "you have not made the request.</p>"
                + "<p>Thank you,</p>"
                + "<p>Your FlashWizApp Support Team</p>"
                + "</div>"
                + "</body>"
                + "</html>";


         
        helper.setSubject(subject);
         
        helper.setText(content, true);
         
        mailSender.send(message);
    }
     
     
//    @GetMapping("/reset_password")
//    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
//        Optional<User> user = userDAO.getByResetPasswordToken(token);
//        model.addAttribute("token", token);
//         
//        if (user == null) {
//            model.addAttribute("message", "Invalid Token");
//            return "message";
//        }
//         
//        return "reset_password_form";
//    }
    
//    @PostMapping("/reset_password")
//    public String processResetPassword(HttpServletRequest request, Model model) {
//        String token = request.getParameter("token");
//        String password = request.getParameter("password");
//         
//        Optional<User> user = userDAO.getByResetPasswordToken(token);
//        model.addAttribute("title", "Reset your password");
//         
//        if (user == null) {
//            model.addAttribute("message", "Invalid Token");
//            return "message";
//        } else {           
//            userDAO.updatePassword(user, password);
//             
//            model.addAttribute("message", "You have successfully changed your password.");
//        }
//         
//        return "message";
//    }
    
    

        @PostMapping("/reset_password")
        public ResponseEntity<Map<String, String>> processResetPassword(@RequestParam("otp") String otp, 
                                                                        @RequestParam("password") String password) {
            Map<String, String> response = new HashMap<>();
             
            Optional<User> userOptional = userDAO.getByResetPasswordOTP(otp);
             
            if (!userOptional.isPresent()) {
                response.put("status", "error");
                response.put("message", "Invalid Token");
                return ResponseEntity.badRequest().body(response);
            } else {           
                userDAO.updatePassword(userOptional, password);
                response.put("status", "success");
                response.put("message", "You have successfully changed your password.");
                return ResponseEntity.ok(response);
            }
        }
    


    
}

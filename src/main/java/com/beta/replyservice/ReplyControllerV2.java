package com.beta.replyservice;

import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/v2")
public class ReplyControllerV2 {
//
//	@GetMapping("/reply")
//	public ReplyMessage replying() {
//		return new ReplyMessage("Message is empty");
//	}

	@GetMapping("/reply/{message}")
	public ResponseEntity<ReplyMessage> replying(@PathVariable String message) throws NoSuchAlgorithmException {
		String pattern = "[1-2][1-2]-[a-z0-9]*";
		boolean matches = Pattern.matches(pattern, message);
		if(!matches)
			return ResponseEntity.badRequest().body(new ReplyMessage("invalid input"));

		String[] split = message.split("-");
		String s0 = split[0];
		String s1 = split[1];
		for (int i = 0; i < s0.length(); i++) {
			if(s0.charAt(i) == '1'){
				s1 = new StringBuilder(s1).reverse().toString();
			} else if (s0.charAt(i) == '2') {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(s1.getBytes());
				byte[] digest = md.digest();
				s1 = DatatypeConverter
						.printHexBinary(digest).toLowerCase();
			}
		}

		return ResponseEntity.ok(new ReplyMessage(s1));
	}
}
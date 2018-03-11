package br.com.lepsistemas.patrimonium.service;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class PasswordService {

	private static final String ALPHANUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public String generateRandom() {
		SecureRandom rnd = new SecureRandom();
		int len = 6;
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			sb.append(ALPHANUMERIC.charAt(rnd.nextInt(ALPHANUMERIC.length())));
		}
		return sb.toString();
	}

}

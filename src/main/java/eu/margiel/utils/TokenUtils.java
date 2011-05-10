package eu.margiel.utils;

import org.jasypt.util.password.StrongPasswordEncryptor;


public class TokenUtils {
	private static StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();

	public static String generateToken() {
		String token = encryptor.encryptPassword("" + System.currentTimeMillis());
		return token.replaceAll("/", "");
	}
}

package com.hdsam.springclouddemo.springsecuritydemo.intergation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.Arrays;

/**
 * SpringSecurityIntegrationTest
 *
 * @author Yeo
 * @date 2023/7/5
 */

public class SpringSecurityEncryptorTest {

	/**
	 *  byte加密器
	 * @throws Exception
	 */
	@Test
	public void bytesEncryptorTest() throws Exception {
		String salt = "60f859fcf84ef36b";
		System.out.println("salt = " + salt);

		BytesEncryptor bytesEncryptor = Encryptors.stronger("password", salt);

		byte[] encrypt = bytesEncryptor.encrypt("1234".getBytes());

		System.out.println(Arrays.toString(encrypt));
	}

	/**
	 * 文本加密器
	 * @throws Exception
	 */
	@Test
	public void textEncryptorTest() throws Exception {
		String salt = "60f859fcf84ef36b";
		TextEncryptor textEncryptor = Encryptors.text("password", salt);
		String encrypt = textEncryptor.encrypt("1234");
		System.out.println(encrypt);
	}


	/**
	 *
	 * 秘钥生成器
	 * @throws Exception
	 */
	@Test
	public void keyGeneratorsTest() throws Exception {
		// KeyGenerators is a thread-safe class

		// default keyLength(8) * 2 =  16 chars
		StringKeyGenerator stringKeyGenerator = KeyGenerators.string();
		System.out.println(stringKeyGenerator.generateKey());

		// specified keyLength bit same key
		BytesKeyGenerator shared1 = KeyGenerators.shared(16);
		System.out.println(Arrays.toString(shared1.generateKey()));
		System.out.println(Arrays.toString(shared1.generateKey()));

		// default keyLength(8) random key
		BytesKeyGenerator bytesKeyGenerator1 = KeyGenerators.secureRandom();
		System.out.println(Arrays.toString(bytesKeyGenerator1.generateKey()));
		System.out.println(Arrays.toString(bytesKeyGenerator1.generateKey()));

		// specified length bits  random key
		BytesKeyGenerator bytesKeyGenerator = KeyGenerators.secureRandom(16);
		System.out.println(Arrays.toString(bytesKeyGenerator.generateKey()));
		System.out.println(Arrays.toString(bytesKeyGenerator.generateKey()));
	}

	@Test
	public void passwordEncodingTest() throws Exception {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encode = bCryptPasswordEncoder.encode("1234");
		Assertions.assertTrue(bCryptPasswordEncoder.matches("1234", encode));

		Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
		String encode1 = pbkdf2PasswordEncoder.encode("mypassword");
		Assertions.assertTrue(pbkdf2PasswordEncoder.matches("mypassword", encode1));
	}
}

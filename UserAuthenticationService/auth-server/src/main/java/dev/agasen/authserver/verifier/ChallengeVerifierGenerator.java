package dev.agasen.authserver.verifier;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class ChallengeVerifierGenerator {
  
  public String generateCodeVerifier() {
    /**
     * Code Verifier is a number
     */
    SecureRandom secureRandom = new SecureRandom();
    byte[] code = new byte[32];
    secureRandom.nextBytes(code);
    return Base64.getUrlEncoder()
        .withoutPadding()
        .encodeToString(code);
  }

  public String generateCodeChallenge(String verifier) throws NoSuchAlgorithmException {
    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
    byte[] digested = messageDigest.digest(verifier.getBytes());
    return Base64.getUrlEncoder() 
        .withoutPadding()
        .encodeToString(digested);
  }
}

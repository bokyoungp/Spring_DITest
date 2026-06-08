package org.example.ditest.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {
  public static String encode(String password) {
    // 원본 문자열 "ABC"를 byte[] --> getByte() : 배열 [65,66,67] --> encode : Base64 문자열 "QUJD" 로 변환
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      byte[] hashed = md.digest(password.getBytes());
      return Base64.getEncoder().encodeToString(hashed);

    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  public static boolean matches(String rawPassword, String encodedPassword) {
    return encode(rawPassword).equals(encodedPassword);
  }
}
//
//// 비밀번호 암호화 (SHA-256 + Salt 생성 포함)
//public static String encode(String password) {
//  byte[] salt = generateSalt();
//  String saltStr = Base64.getEncoder().encodeToString(salt);
//  String hashed = hash(password, saltStr);
//  return saltStr + ":" + hashed;  // salt:hash 형태로 저장
//}
//
//// 비밀번호 검증
//public static boolean matches(String rawPassword, String encodedPassword) {
//  String[] parts = encodedPassword.split(":");
//  String saltStr = parts[0];
//  String hashed = parts[1];
//  return hash(rawPassword, saltStr).equals(hashed);
//}
//
//private static byte[] generateSalt() {
//  SecureRandom random = new SecureRandom();
//  byte[] salt = new byte[16];
//  random.nextBytes(salt);
//  return salt;
//}
//
//private static String hash(String password, String saltStr) {
//  try {
//    MessageDigest md = MessageDigest.getInstance("SHA-256");
//    md.update(saltStr.getBytes());
//    byte[] hashed = md.digest(password.getBytes());
//
//    StringBuilder sb = new StringBuilder();
//    for (byte b : hashed) {
//      sb.append(String.format("%02x", b));
//    }
//    return sb.toString();
//
//  } catch (NoSuchAlgorithmException e) {
//    throw new RuntimeException("SHA-256 알고리즘을 찾을 수 없습니다.", e);
//  }
//}
//

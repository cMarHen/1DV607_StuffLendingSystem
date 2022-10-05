package model.domain;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RandomString-class.
 *
 */
public class RandomString {
  private String lowerChars = "abcdefghijklmnopqrstuvwxyz";
  private String upperChars = this.lowerChars.toUpperCase();
  private String numbers = "0123456789";
  private Random random = new Random();

  /**
   * Generate random alphanumeric string of specific length.
   *
   * @param length - Length of the string.
   * @return - Alphanumeric string.
   */
  public String getAlphanumeric(int length) {
    String alphanumerics = this.lowerChars + this.upperChars + this.numbers;
    StringBuilder stringBuilder = new StringBuilder(length);
    String regex = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher;

    do {
      stringBuilder.setLength(0);

      for (int i = 0; i < length; i++) {
        int randomCharAt = this.random.nextInt(alphanumerics.length());
        char randomChar = alphanumerics.charAt(randomCharAt);
  
        stringBuilder.append(randomChar);
      }
      
      matcher = pattern.matcher(stringBuilder.toString());
    } while (!matcher.matches());

    return stringBuilder.toString();
  }
}
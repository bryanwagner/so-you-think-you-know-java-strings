import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class CodePointDemo {

  public static int[] toCodePoints(char[] chars) {
    int codePointsLength = 0;
    int[] codePoints = new int[chars.length];
    for (int i = 0; i < chars.length; i++, codePointsLength++) {
      char c1 = chars[i];
      int codePoint;
      if (Character.isHighSurrogate(c1) && ++i < chars.length) {  // decode for the high surrogate index and skip the low surrogate index
        char c2 = chars[i];
        codePoint = Character.isLowSurrogate(c2) ? Character.toCodePoint(c1, c2) : c1;
      }
      else {
        codePoint = c1;
      }
      codePoints[codePointsLength] = codePoint;
    }
    if (codePointsLength != chars.length) {
      codePoints = Arrays.copyOf(codePoints, codePointsLength);  // truncate if supplementary characters were found
    }
    return codePoints;
  }

  public static int[] toCodePoints(String string) {
    return toCodePoints(string.toCharArray());
  }

  public static String formatCodePoints(char[] chars) throws UnsupportedEncodingException {
    String string = new String(chars);
    int[] codePoints = toCodePoints(chars);
    return String.format("%s: codePoints=%s, codePointsLength=%d, charsLength=%d, utf8BytesLength=%d",
        string, Arrays.toString(codePoints), codePoints.length, chars.length, string.getBytes("UTF-8").length);
  }

  public static String formatCodePoints(String string) throws UnsupportedEncodingException {
    return formatCodePoints(string.toCharArray());
  }

  public static void main(String[] args) throws UnsupportedEncodingException {
    System.out.println(formatCodePoints("0"));  // 0: codePoints=[48], codePointsLength=1, charsLength=1, utf8BytesLength=1
    System.out.println(formatCodePoints("\u263A"));  // ☺: codePoints=[9786], codePointsLength=1, charsLength=1, utf8BytesLength=3
    System.out.println(formatCodePoints("\u8F38"));  // 輸: codePoints=[36664], codePointsLength=1, charsLength=1, utf8BytesLength=3
    // supplementary characters (2 chars, 1 code point which should be displayed by one glyph)
    System.out.println(formatCodePoints("\uD801\uDC00"));  // 𐐀: codePoints=[66560], codePointsLength=1, charsLength=2, utf8BytesLength=4
  }
}

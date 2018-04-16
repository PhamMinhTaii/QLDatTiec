
package CommonConstance;

public class Encryption {
    
   public static String getHash(String txt, String hashType) {
        try {
                    java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
                    byte[] array = md.digest(txt.getBytes());
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < array.length; ++i) {
                        sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
                 }
                    return sb.toString();
            } catch (java.security.NoSuchAlgorithmException e) {
                  return null;
            }          
    }
   
   public static String sha1(String passWord) {
        return Encryption.getHash(passWord, "SHA1");
    }
    
}

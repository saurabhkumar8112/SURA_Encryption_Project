import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * Basic IO example with CTR using AES
 */
public class AESCTR {
	
  public static void main(String[] args) throws Exception {
    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    
    byte[] input = "www.java2s.".getBytes();
    byte[] keyBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09,
        0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17 };
    byte[] ivBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x00, 0x01, 0x02, 0x03, 0x00, 0x00, 0x00,
        0x00, 0x00, 0x00, 0x00, 0x01 };
    SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
    Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding", "BC");
    System.out.println("input : " + new String(input));

    // encryption pass
    ByteArrayInputStream bIn = new ByteArrayInputStream(input);
    long start = System.nanoTime();
    IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
    cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
    CipherInputStream cIn = new CipherInputStream(bIn, cipher);
    long end = System.nanoTime() - start;
    
    System.out.println("Total Time : " + end);
    
    ByteArrayOutputStream bOut = new ByteArrayOutputStream();

    int ch;
    while ((ch = cIn.read()) >= 0) {
     //System.out.println(ch);
     bOut.write(ch);
    }

    byte[] cipherText = bOut.toByteArray();

    System.out.println("cipher: " + new String(cipherText));

    // decryption pass
    cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
    
    bOut = new ByteArrayOutputStream();
    CipherOutputStream cOut = new CipherOutputStream(bOut, cipher);
    
    cOut.write(cipherText);
    cOut.close();
    System.out.println("plain : " + new String(bOut.toByteArray()));
    
    
  }
  
  public AESCTR(byte[] keyBytes, byte[] ivBytes)	{
	  this.keyBytes = keyBytes;
	  this.ivBytes = ivBytes;
  }
  
  byte[] keyBytes;
  byte[] ivBytes;
  
  public void setKey(byte[] keyBytes)	{
	  this.keyBytes = keyBytes;
  }
  
  public void ivKey(byte[] ivBytes)	{
	  this.ivBytes = ivBytes;
  }
  
  
  
  public byte[] encrypt(byte[] input)	{
	  byte[] cipherText = null;
	  try {
	  	Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	    
	  //input is adjusted in the input of the function	byte[] input = "www.java2s.com".getBytes();

	    SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
	    IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
	    Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding", "BC");

	    // encryption pass
	    cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
	    ByteArrayInputStream bIn = new ByteArrayInputStream(input);
	    CipherInputStream cIn = new CipherInputStream(bIn, cipher);
	    ByteArrayOutputStream bOut = new ByteArrayOutputStream();

	    int ch;
	    while ((ch = cIn.read()) >= 0) {
	    	System.out.println(ch);
	    	bOut.write(ch);
	    }

	    cipherText = bOut.toByteArray();
	    System.out.println("cipher: " + new String(cipherText));

	    // decryption pass
	    cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
	    bOut = new ByteArrayOutputStream();
	    CipherOutputStream cOut = new CipherOutputStream(bOut, cipher);
	    cOut.write(cipherText);
	    cOut.close();
	    System.out.println("plain : " + new String(bOut.toByteArray()));

	  }
	  catch	(Exception e){
		  System.out.println("Exception Found");
	  }
	return cipherText;
	  
  }
  
}

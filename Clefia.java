
public class Clefia {
	//the following is the constant numbers matrix 
	
	private final static int ROUNDS128  = 18;

	private final static int W0_4   = 0xf0000000;
	private final static int W4_8   = 0x0f000000;
	private final static int W8_12  = 0x00f00000;
	private final static int W12_16 = 0x000f0000;
	private final static int W16_20 = 0x0000f000;
	private final static int W20_24 = 0x00000f00;
	private final static int W24_28 = 0x000000f0;
	private final static int W28_32 = 0x0000000f;
	private final static int W0_8   = 0xff000000;
	private final static int W8_16  = 0x00ff0000;
	private final static int W16_24 = 0x0000ff00;
	private final static int W24_32 = 0x000000ff;

	private final static int W0_6   = 0xfe000000;
	private final static int W0_24  = 0xffffff80;
	private final static int W7_31  = 0x01ffffff;
	private final static int W25_31 = 0x0000007f;

	private final static char B0_4   = 0xf0;
	private final static char B4_8   = 0x0f;
	
	private final static int[] con_128 = new int[] { 
			  0xf56b7aeb, 0x994a8a42, 0x96a4bd75, 0xfa854521,
			  0x735b768a, 0x1f7abac4, 0xd5bc3b45, 0xb99d5d62,
			  0x52d73592, 0x3ef636e5, 0xc57a1ac9, 0xa95b9b72,
			  0x5ab42554, 0x369555ed, 0x1553ba9a, 0x7972b2a2,
			  0xe6b85d4d, 0x8a995951, 0x4b550696, 0x2774b4fc,
			  0xc9bb034b, 0xa59a5a7e, 0x88cc81a5, 0xe4ed2d3f,
			  0x7c6f68e2, 0x104e8ecb, 0xd2263471, 0xbe07c765,
			  0x511a3208, 0x3d3bfbe6, 0x1084b134, 0x7ca565a7,
			  0x304bf0aa, 0x5c6aaa87, 0xf4347855, 0x9815d543,
			  0x4213141a, 0x2e32f2f5, 0xcd180a0d, 0xa139f97a,
			  0x5e852d36, 0x32a464e9, 0xc353169b, 0xaf72b274,
			  0x8db88b4d, 0xe199593a, 0x7ed56d96, 0x12f434c9,
			  0xd37b36cb, 0xbf5a9a64, 0x85ac9b65, 0xe98d4d32,
			  0x7adf6582, 0x16fe3ecd, 0xd17e32c1, 0xbd5f9f66,
			  0x50b63150, 0x3c9757e7, 0x1052b098, 0x7c73b3a7};
	
	// The Following are the Substitution matrices
	private final static char[] s0 = new char[] {
			  0x57, 0x49, 0xd1, 0xc6, 0x2f, 0x33, 0x74, 0xfb, 0x95, 0x6d, 0x82, 0xea, 0x0e, 0xb0, 0xa8, 0x1c,
			  0x28, 0xd0, 0x4b, 0x92, 0x5c, 0xee, 0x85, 0xb1, 0xc4, 0x0a, 0x76, 0x3d, 0x63, 0xf9, 0x17, 0xaf,
			  0xbf, 0xa1, 0x19, 0x65, 0xf7, 0x7a, 0x32, 0x20, 0x06, 0xce, 0xe4, 0x83, 0x9d, 0x5b, 0x4c, 0xd8,
			  0x42, 0x5d, 0x2e, 0xe8, 0xd4, 0x9b, 0x0f, 0x13, 0x3c, 0x89, 0x67, 0xc0, 0x71, 0xaa, 0xb6, 0xf5,
			  0xa4, 0xbe, 0xfd, 0x8c, 0x12, 0x00, 0x97, 0xda, 0x78, 0xe1, 0xcf, 0x6b, 0x39, 0x43, 0x55, 0x26,
			  0x30, 0x98, 0xcc, 0xdd, 0xeb, 0x54, 0xb3, 0x8f, 0x4e, 0x16, 0xfa, 0x22, 0xa5, 0x77, 0x09, 0x61,
			  0xd6, 0x2a, 0x53, 0x37, 0x45, 0xc1, 0x6c, 0xae, 0xef, 0x70, 0x08, 0x99, 0x8b, 0x1d, 0xf2, 0xb4,
			  0xe9, 0xc7, 0x9f, 0x4a, 0x31, 0x25, 0xfe, 0x7c, 0xd3, 0xa2, 0xbd, 0x56, 0x14, 0x88, 0x60, 0x0b,
			  0xcd, 0xe2, 0x34, 0x50, 0x9e, 0xdc, 0x11, 0x05, 0x2b, 0xb7, 0xa9, 0x48, 0xff, 0x66, 0x8a, 0x73,
			  0x03, 0x75, 0x86, 0xf1, 0x6a, 0xa7, 0x40, 0xc2, 0xb9, 0x2c, 0xdb, 0x1f, 0x58, 0x94, 0x3e, 0xed,
			  0xfc, 0x1b, 0xa0, 0x04, 0xb8, 0x8d, 0xe6, 0x59, 0x62, 0x93, 0x35, 0x7e, 0xca, 0x21, 0xdf, 0x47,
			  0x15, 0xf3, 0xba, 0x7f, 0xa6, 0x69, 0xc8, 0x4d, 0x87, 0x3b, 0x9c, 0x01, 0xe0, 0xde, 0x24, 0x52,
			  0x7b, 0x0c, 0x68, 0x1e, 0x80, 0xb2, 0x5a, 0xe7, 0xad, 0xd5, 0x23, 0xf4, 0x46, 0x3f, 0x91, 0xc9,
			  0x6e, 0x84, 0x72, 0xbb, 0x0d, 0x18, 0xd9, 0x96, 0xf0, 0x5f, 0x41, 0xac, 0x27, 0xc5, 0xe3, 0x3a,
			  0x81, 0x6f, 0x07, 0xa3, 0x79, 0xf6, 0x2d, 0x38, 0x1a, 0x44, 0x5e, 0xb5, 0xd2, 0xec, 0xcb, 0x90,
			  0x9a, 0x36, 0xe5, 0x29, 0xc3, 0x4f, 0xab, 0x64, 0x51, 0xf8, 0x10, 0xd7, 0xbc, 0x02, 0x7d, 0x8e};

	private final static char[] s1 = new char[] {
			  0x6c, 0xda, 0xc3, 0xe9, 0x4e, 0x9d, 0x0a, 0x3d, 0xb8, 0x36, 0xb4, 0x38, 0x13, 0x34, 0x0c, 0xd9,
			  0xbf, 0x74, 0x94, 0x8f, 0xb7, 0x9c, 0xe5, 0xdc, 0x9e, 0x07, 0x49, 0x4f, 0x98, 0x2c, 0xb0, 0x93,
			  0x12, 0xeb, 0xcd, 0xb3, 0x92, 0xe7, 0x41, 0x60, 0xe3, 0x21, 0x27, 0x3b, 0xe6, 0x19, 0xd2, 0x0e,
			  0x91, 0x11, 0xc7, 0x3f, 0x2a, 0x8e, 0xa1, 0xbc, 0x2b, 0xc8, 0xc5, 0x0f, 0x5b, 0xf3, 0x87, 0x8b,
			  0xfb, 0xf5, 0xde, 0x20, 0xc6, 0xa7, 0x84, 0xce, 0xd8, 0x65, 0x51, 0xc9, 0xa4, 0xef, 0x43, 0x53,
			  0x25, 0x5d, 0x9b, 0x31, 0xe8, 0x3e, 0x0d, 0xd7, 0x80, 0xff, 0x69, 0x8a, 0xba, 0x0b, 0x73, 0x5c,
			  0x6e, 0x54, 0x15, 0x62, 0xf6, 0x35, 0x30, 0x52, 0xa3, 0x16, 0xd3, 0x28, 0x32, 0xfa, 0xaa, 0x5e,
			  0xcf, 0xea, 0xed, 0x78, 0x33, 0x58, 0x09, 0x7b, 0x63, 0xc0, 0xc1, 0x46, 0x1e, 0xdf, 0xa9, 0x99,
			  0x55, 0x04, 0xc4, 0x86, 0x39, 0x77, 0x82, 0xec, 0x40, 0x18, 0x90, 0x97, 0x59, 0xdd, 0x83, 0x1f,
			  0x9a, 0x37, 0x06, 0x24, 0x64, 0x7c, 0xa5, 0x56, 0x48, 0x08, 0x85, 0xd0, 0x61, 0x26, 0xca, 0x6f,
			  0x7e, 0x6a, 0xb6, 0x71, 0xa0, 0x70, 0x05, 0xd1, 0x45, 0x8c, 0x23, 0x1c, 0xf0, 0xee, 0x89, 0xad,
			  0x7a, 0x4b, 0xc2, 0x2f, 0xdb, 0x5a, 0x4d, 0x76, 0x67, 0x17, 0x2d, 0xf4, 0xcb, 0xb1, 0x4a, 0xa8,
			  0xb5, 0x22, 0x47, 0x3a, 0xd5, 0x10, 0x4c, 0x72, 0xcc, 0x00, 0xf9, 0xe0, 0xfd, 0xe2, 0xfe, 0xae,
			  0xf8, 0x5f, 0xab, 0xf1, 0x1b, 0x42, 0x81, 0xd6, 0xbe, 0x44, 0x29, 0xa6, 0x57, 0xb9, 0xaf, 0xf2,
			  0xd4, 0x75, 0x66, 0xbb, 0x68, 0x9f, 0x50, 0x02, 0x01, 0x3c, 0x7f, 0x8d, 0x1a, 0x88, 0xbd, 0xac,
			  0xf7, 0xe4, 0x79, 0x96, 0xa2, 0xfc, 0x6d, 0xb2, 0x6b, 0x03, 0xe1, 0x2e, 0x7d, 0x14, 0x95, 0x1d};
	
	private String plaintxt;
	private String ciphertxt;
	private byte[] bytePlain;
	private byte[] byteCipher;
	private static int[] rk128 = new int[60];
	private int[] wk128 = new int[4];
	
	private byte[] key_128;
	
	public int[] getRk()	{
		return rk128;
	}
	public int[] getWk() {
		return wk128;
	}
	// Constructors - Can be String or Byte Array
	
	public Clefia () {
		
	}
	
	
	// GFN function of 4
	private static void GFN4 (int r, int[] rk, int[] x , int[] y)	{
		// take y as space for ti, t2,t3,t4
		y[0] = x[0];
		y[1] = x[1];
		y[2] = x[2];
		y[3] = x[3];

		// Variable to store buffer values
		int buffer;


		for (int i = 0; i < r ; i++)	{

			y[1] = (y[1] ^ f0(rk[2*i] , y[0]));
			y[3] = (y[3] ^ f1(rk[2*i+1], y[2]));
			
			// Key Rolling
			buffer = y[0];
			y[0] = y[1];
			y[1] = y[2];
			y[2] = y[3];
			y[3] = buffer;
		}
		
		buffer = y[3];
		y[3] = y[2];
		y[2] = y[1];
		y[1] = y[0];
		y[0] = buffer;
		
	}
	
	private static void invGFN4(int r, int[] rk, int[] x , int[] y) {
		int temp;
		
		// We use y[] as an array for storing the temporary values
		y[0] = x[0];
		y[1] = x[1];
		y[2] = x[2];
		y[3] = x[3];
		
		for (int i = 0; i < r; i++) {
		    /* Step 2.1 */
		    y[1] ^= f0(rk[2*(r-i) - 2], y[0]);
		    y[3] ^= f1(rk[2*(r-i) - 1], y[2]);
		    /* Step 2.2 */
		    temp = y[0];
		    y[0] = y[3];
		    y[3] = y[2];
		    y[2] = y[1];
		    y[1] = temp;
		  }
		
		temp = y[0];
		y[0] = y[1];
		y[1] = y[2];
		y[2] = y[3];
		y[3] = temp;
		
		/*
		y[0] = x[1];
		y[1] = x[2];
		y[2] = x[3];
		y[3] = x[0];
		
		for (int i = 0; i < r; i++) {
		    temp = y[0];
			y[0] = y[3];
			y[3] = y[2];
			y[2] = y[1];
			y[1] = temp;
		    
			y[1] ^= f0(rk[2*(r-i) - 2], y[0]);
		    y[3] ^= f1(rk[2*(r-i) - 1], y[2]);
		    }*/
	}
	
	// Takes the key and input 32 bit block and return y
	public static int f0(int rk, int x)	{
		x = rk ^ x;

		int[] m = new int[4];
		
		char[] t = new char[4];
		m[0] = (char) ((x & W0_8) >>> 24);
		m[1] = (char) ((x & W8_16) >>> 16);
		m[2] = (char) ((x & W16_24) >>> 8);
		m[3] = (char) ((x & W24_32));

		t[0] = s0[m[0]];
		t[1] = s1[m[1]];
		t[2] = s0[m[2]];
		t[3] = s1[m[3]];
		
		int y = 0x00000000;
		
		y = y | ((t[0]  ^ mul2(t[1]) ^ mul4(t[2]) ^ mul6(t[3]) ) << 24);
		y = y | (( (mul2(t[0]) ^      t[1]  ^ mul6(t[2]) ^ mul4(t[3])) & 0x000000ff)  << 16);
		y = y | ( ( (mul4(t[0]) ^ mul6(t[1]) ^      t[2]  ^ mul2(t[3]) ) & 0x000000ff)<< 8);
		y = y | ( (mul6(t[0]) ^ mul4(t[1]) ^ mul2(t[2]) ^       t[3]) & 0x000000ff);
		return y;
	}
	
	public static int f1(int rk, int x)	{
		x = rk ^ x;
		
		char[] t = new char[4];
		t[0] = (char) ((x & W0_8) >>> 24);
		t[1] = (char) ((x & W8_16) >>> 16);
		t[2] = (char) ((x & W16_24) >>> 8);
		t[3] = (char) ((x & W24_32));
		
		
		t[0] = s1[t[0]];
		t[1] = s0[t[1]];
		t[2] = s1[t[2]];
		t[3] = s0[t[3]];
		
		int y = 0x00000000;

		y = y | ( (t[0] ^ mul8(t[1]) ^ mul2(t[2]) ^ mulA(t[3]) ) << 24);
		y = y | ( (mul8(t[0]) ^      t[1]  ^ mulA(t[2]) ^ mul2(t[3])) << 16);
		y = y |  ( (mul2(t[0]) ^ mulA(t[1]) ^      t[2]  ^ mul8(t[3]) ) << 8);
		y = y | (mulA(t[0]) ^ mul2(t[1]) ^ mul8(t[2]) ^       t[3]);
		return y;
	}
	
	private static char mul2(char a)	{
		if ((a & 0x80) != 0x00)	
			a ^= 0x0e;
		return (char) (((char) ((a << 1) | (a >> 7))) & 0xff);
	}
	
	private static char mul4(char a)	{
		return (char) (mul2(mul2(a)) & 0xff);
	}
	
	private static char mul6(char a)	{
		return (char) ( (mul2(a) ^ mul4(a) )& 0xff);
	}
	
	private static char mul8(char a)	{
		return (char) (mul2(mul4(a)) & 0xff);
	}
	
	private static char mulA(char a)	{
		return (char) (mul2(a) ^ mul8(a));
	}
	
	private static void sigma(int[] x, int[] y) {

		  y[0] = ((x[0] & W7_31) << 7)   | ((x[1] & W0_6) >>> 25);
		  y[1] = ((x[1] & W7_31) << 7)   | (x[3]  & W25_31);
		  y[2] = (x[0]  & W0_6)          | ((x[2] & W0_24) >>> 7);
		  y[3] = ((x[2] & W25_31) << 25) | ((x[3] & W0_24) >>> 7);
	}
	
	// Used for Key Scheduling, k is the key array and should be an integger array consisting of 4 ints
	private void key_scheduling_128(int[] k) {

		  int[] y = new int[4];
		  int[] l = new int[4];
		  int[] t = new int[4];
		  int i;

		  /* step 1 */
		  GFN4(12, con_128, k, y);

		  l[0] = y[0];
		  l[1] = y[1];
		  l[2] = y[2];
		  l[3] = y[3];
		  
		  /* step 2 */
		  wk128[0] = k[0];
		  wk128[1] = k[1];
		  wk128[2] = k[2];
		  wk128[3] = k[3];

		  /* step 3 */
		  for (i = 0; i < 9; i++) {
		    t[0] = l[0] ^ con_128[24 + (4 * i)];
		    t[1] = l[1] ^ con_128[24 + (4 * i) + 1];
		    t[2] = l[2] ^ con_128[24 + (4 * i) + 2];
		    t[3] = l[3] ^ con_128[24 + (4 * i) + 3];

		    sigma(l, y);
		    
		    l[0] = y[0];
		    l[1] = y[1];
		    l[2] = y[2];
		    l[3] = y[3];

		    if (i % 2 == 1) {
		      t[0] = t[0] ^ k[0];
		      t[1] = t[1] ^ k[1];
		      t[2] = t[2] ^ k[2];
		      t[3] = t[3] ^ k[3];
		    }

		    rk128[4*i] = t[0];
		    rk128[4*i+1] = t[1];
		    rk128[4*i+2] = t[2];
		    rk128[4*i+3] = t[3];
		  }
	}
	
	public void encryption_128(int[] p, int[] c) {

		int[] t = new int[4];
		int[] y = new int[4];


		  /* step 1 */
		  t[0] = p[0];
		  t[1] = p[1] ^ wk128[0];
		  t[2] = p[2];
		  t[3] = p[3] ^ wk128[1];

		  System.out.print("Before GFN :");
		  for (int sh = 0 ; sh <0+ 4 ; sh++)
		    System.out.print(Integer.toHexString(t[sh]) + " ");
		  System.out.println();
		  
		  /* step 2 */
		  GFN4(18, rk128,  t, y);
		  t[0] = y[0];
		  t[1] = y[1];
		  t[2] = y[2];
		  t[3] = y[3];
		  
		  System.out.print("After GFN:");
		  for (int sh = 0 ; sh <0+ 4 ; sh++)
		    System.out.print(Integer.toHexString(t[sh]) + " ");
		  System.out.println();
		  
		  /* step 3 */
		  c[0] = t[0];
		  c[1] = t[1] ^ wk128[2];
		  c[2] = t[2];
		  c[3] = t[3] ^ wk128[3];
		}
	
	public void decryption_128(int[] p, int[] c) {

		  int[] t = new int[4];
		  int[] y = new int[4];


		  /* step 1 */
		  t[0] = c[0];
		  t[1] = c[1] ^ wk128[2];
		  t[2] = c[2];
		  t[3] = c[3] ^ wk128[3];

		  System.out.print("Before GFN Inv :");
		  for (int sh = 0 ; sh <0+ 4 ; sh++)
		    System.out.print(Integer.toHexString(t[sh]) + " ");
		  System.out.println();
		  
		  /* step 2 */
		  invGFN4(18, rk128 , t, y);
		  
		  System.out.print("After GFN inv :");
		  for (int sh = 0 ; sh <0+ 4 ; sh++)
		    System.out.print(Integer.toHexString(y[sh]) + " ");
		  System.out.println();
		    
		  t[0] = y[0];
		  t[1] = y[1];
		  t[2] = y[2];
		  t[3] = y[3];

		  /* step 3 */
		  p[0] = t[0];
		  p[1] = t[1] ^ wk128[0];
		  p[2] = t[2];
		  p[3] = t[3] ^ wk128[1];
		}
	
	// plain - plaintext, cipher - ciphertext, length - length of the plaintext, iv - initialization vector, k - key
	public void clefia_cbc_128_enc(byte[] plain, byte[] cipher, int length, int[] iv, int[] k) {

		  int[] p = new int[4];
		  int[] c = new int[4];

		  int j;
		  int i = 0;

		  int tam = length;

		  if (tam % 16 != 0) {
		    for (j = tam % 16; j < 16; j++) {
		      plain[tam++] = 0;
		    }
		  }

		  c[0] = iv[0];
		  c[1] = iv[1];
		  c[2] = iv[2];
		  c[3] = iv[3];

		  key_scheduling_128(k);
		  
		  while (i < tam) {

		    int bla0 = WORD_FROM_BYTES(plain[i+4*0],plain[i+4*0+1],plain[i+4*0+2],plain[i+4*0+3]);
		    int bla1 = WORD_FROM_BYTES(plain[i+4*1],plain[i+4*1+1],plain[i+4*1+2],plain[i+4*1+3]);
		    int bla2 = WORD_FROM_BYTES(plain[i+4*2],plain[i+4*2+1],plain[i+4*2+2],plain[i+4*2+3]);
		    int bla3 = WORD_FROM_BYTES(plain[i+4*3],plain[i+4*3+1],plain[i+4*3+2],plain[i+4*3+3]);

		    
		    p[0] = bla0 ^ c[0];
		    p[1] = bla1 ^ c[1];
		    p[2] = bla2 ^ c[2];
		    p[3] = bla3 ^ c[3];


		    encryption_128(p, c);
		    cipher[i+0] = byte_from_word(c[0/4], 0 % 4);
		    cipher[i+1] = byte_from_word(c[1/4], 1 % 4);
		    cipher[i+2] = byte_from_word(c[2/4], 2 % 4);
		    cipher[i+3] = byte_from_word(c[3/4], 3 % 4);
		    cipher[i+4] = byte_from_word(c[4/4], 4 % 4);
		    cipher[i+5] = byte_from_word(c[5/4], 5 % 4);
		    cipher[i+6] = byte_from_word(c[6/4], 6 % 4);
		    cipher[i+7] = byte_from_word(c[7/4], 7 % 4);
		    cipher[i+8] = byte_from_word(c[8/4], 8 % 4);
		    cipher[i+9] = byte_from_word(c[9/4], 9 % 4);
		    cipher[i+10] = byte_from_word(c[10/4], 10 % 4);
		    cipher[i+11] = byte_from_word(c[11/4], 11 % 4);
		    cipher[i+12] = byte_from_word(c[12/4], 12 % 4);
		    cipher[i+13] = byte_from_word(c[13/4], 13 % 4);
		    cipher[i+14] = byte_from_word(c[14/4], 14 % 4);
		    cipher[i+15] = byte_from_word(c[15/4], 15 % 4);
		    i = i + 16;
		  }
		}

	// length is total number of characters in the plaintext
	public void clefia_cbc_128_enc(int[] plain, int[] cipher, int length, int[] iv, int[] k) {
		int[] p = new int[4];
		int[] c = new int[4];

		int j;
		int i = 0;

		int tam = length;
			
		int[] p1 = new int[ (tam/4) + ( 4 - ( tam / 4 ) % 4 )];
			
		int length_in_int = 0;
		if (tam % 4 == 0)	
				length_in_int = tam/4;
		if (tam % 4 != 0)
			length_in_int = tam/4+1;
		for (int a = 0; a < length_in_int ; a++)
			p1[a] = plain[a];
	    
		if (tam % 16 != 0) {
			int diff = 16 - (tam % 16);
			for (int a = 0; a < 4 ; a++) {
				if ( (tam % 16)/4 == a)		{
					int temp = p1[(tam/16) + a];
			    	for (j = 1; j < 4 ; j++)	{
			    		if(diff % 4 == j)	{
			    			p1[length_in_int - 1] = temp << (8*j);
			    		}
			    	}
			    }
			}
		}
		c[0] = iv[0];
		c[1] = iv[1];
		c[2] = iv[2];
		c[3] = iv[3];

		key_scheduling_128(k);
		  
		while (i < length_in_int) {

		    int bla0 = p1[i+0];
		    int bla1 = p1[i+1];
		    int bla2 = p1[i+2];
		    int bla3 = p1[i+3];
		    System.out.print("Initial Array :");
		    for (int sh = i ; sh <i+ 4 ; sh++)
		    	System.out.print(Integer.toHexString(p1[sh]) + " ");
		    System.out.println();
		    
		    
		    p[0] = bla0 ^ c[0];
		    p[1] = bla1 ^ c[1];
		    p[2] = bla2 ^ c[2];
		    p[3] = bla3 ^ c[3];
		    
		    System.out.print("Array to be encrypted:");
		    for (int sh = 0 ; sh < 4 ; sh++)
		    	System.out.print(Integer.toHexString(p[sh]) + " ");
		    System.out.println();
		    
		    encryption_128(p, c);
		    cipher[i] = c[0];
		    cipher[i+1] = c[1];
		    cipher[i+2] = c[2];
		    cipher[i+3] = c[3];
		    
		    System.out.print("Encrypted Array:");
		    for (int sh = 0 ; sh < 4 ; sh++)
		    	System.out.print(Integer.toHexString(c[sh]) + " ");
		    System.out.println();
		    
		    
		    i = i + 4;
		  }
	}
	
	public void clefia_cbc_128_dec(byte[] plain, byte[] cipher, int length, int[] iv, int[] k) {

		  int[] p = new int[4];
		  int[] c = new int[4];
		  int[] aux = new int[4];


		  int j;
		  int i = 0;

		  int tam = length;

		  if (tam % 16 != 0) {
		    for (j = tam % 16; j < 16; j++) {
		      cipher[tam++] = 0;
		    }
		  }

		  for (j = 0; j < 4; j++) {
		    aux[j] = iv[j];
		  }

		  key_scheduling_128(k);

		  while (i < tam) {
		    for (j = 0; j < 4; j++) {
		      c[j] = WORD_FROM_BYTES(cipher[i+4*j], cipher[i+4*j+1], cipher[i+4*j+2], cipher[i+4*j+3]);
		    }
		    decryption_128(p, c);

		    for (j = 0; j < 16; j++) {
		      plain[i+j] = (byte) (byte_from_word(p[j/4], j%4) ^ byte_from_word(aux[j/4], j%4));
		    }

		    for (j = 0; j < 4; j++) {
		      aux[j] = c[j];
		    }

		    i = i + 16;
		 }
	}

	public void clefia_cbc_128_dec(int[] plain, int[] cipher, int length, int[] iv, int[] k) {

		  int[] p = new int[4];
		  int[] c = new int[4];
		  int[] aux = new int[4];


		  int j;
		  int i = 0;

		  int tam = length;
		  int[] c1 = new int[ (tam/4) + ( 4 - ( tam / 4 ) % 4 ) ];
		  int length_in_int = 0;
		  if (tam % 4 == 0)	
			  length_in_int = tam/4;
		  if (tam % 4 != 0)
			  length_in_int = tam/4 +1;
		  for (int a = 0; a < length_in_int ; a++)
			  c1[a] = cipher[a];

		  
		  for (j = 0; j < 4; j++) {
		    aux[j] = iv[j];
		  }

		  key_scheduling_128(k);
		  while (i < length_in_int) {
		    for (j = 0; j < 4; j++) {
		      c[j] = c1[i + j];
		    }
		    
		    System.out.print("Array to be decrypted:");
		    for (int sh = 0 ; sh < 4 ; sh++)
		    	System.out.print(Integer.toHexString(c[sh]) + " ");
		    System.out.println();
		    
		    decryption_128(p, c);
		    
		    System.out.print("After decryption:");
		    for (int sh = 0 ; sh < 4 ; sh++)
		    	System.out.print(Integer.toHexString(p[sh]) + " ");
		    System.out.println();
		    

			p[0] = p[0] ^ aux[0];
			p[1] = p[1] ^ aux[1];
			p[2] = p[2] ^ aux[2];
			p[3] = p[3] ^ aux[3];
			
			System.out.print("After XOR");
		    for (int sh = 0 ; sh < 4 ; sh++)
		    	System.out.print(Integer.toHexString(p[sh]) + " ");
		    System.out.println();
		    
			
		    for (j = 0; j < 4; j++) {
		      plain[i+j] = p[j];
		    }

		    for (j = 0; j < 4; j++) {
		      aux[j] = c[j];
		    }

		    i = i + 4;
		  }
	}


	private byte byte_from_word(int i, int j) {
		if (j == 0) {
			byte k =(byte) ((i & 0xff000000) >> 24);
			return k;
		}
		else if (j == 1) {
			byte k =(byte) ((i & 0x00ff0000) >> 16);
			return k;
		}
		else if (j == 2) {
			byte k =(byte) ((i & 0x0000ff00) >> 8);
			return k;
		}
		else
			return (byte) ((byte) i & 0x000000ff);
	}

	private int WORD_FROM_BYTES(byte b, byte c, byte d, byte e) {
		int y = 0;
		y = (y | (b << 24) ) | (y | (c<<16)) | (y | (d << 8)) | (y | e);
		return y;
	}

	public static void main(String[] args)	{
		try {
			int[] plain = new int[]{0x00010203, 0x04050607, 0x0c0d0e0f, 0x0c0d0e0f, 0xefd45};
			int[] cipher = new int[8];
			int[] iv = new int[] {0x00001245, 0xedf00000, 0x00000000, 0x00000000};
			int[] key = new int[] {0xffeeddcc, 0xbbaa9988, 0x77665544, 0x33221100};
			
			Clefia c1 = new Clefia();

			c1.clefia_cbc_128_enc(plain, cipher, 20, iv, key);
			
			for (int i = 0 ; i < 5; i++)	{
				System.out.print(Integer.toHexString(cipher[i]) + " " );
			}
			System.out.println("");
			int[] plain1 = new int[8];
			c1.clefia_cbc_128_dec(plain1, cipher, 32, iv, key);
			
			for (int i = 0; i < 5; i++)	{
				System.out.print(Integer.toHexString(plain1[i]) + " ");
			}
			
		}
		catch(Exception e)	{
			
		}
	}
}
	


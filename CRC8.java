
public class CRC8 {
	
	private static byte[] crctable;
	private boolean calTable = true;
	
	
	public byte[] CRC8(byte[] input)	{
		byte[] out = new byte[ input.length + 1];

		//Calculate the CRC8 Table in case it in not calculated
		if (calTable = true)	
			calculateTable_CRC8();
		
		byte buffer = 0x00;
		// implementing the CRC8
		for (int i = 0; i < input.length; i++)	{
			buffer = (byte) (buffer ^ input[i]);
			buffer = crctable[buffer];
			out[i] = input[i];
		}
		out[input.length] = buffer;
		return out;
	}
	
	public static void calculateTable_CRC8()
	{
	    final byte generator = (byte) 0xA6;
	    crctable = new byte[256];
	    /* iterate over all byte values 0 - 255 */
	    for (int divident = 0; divident < 256; divident++)
	    {
	        byte currByte = (byte)divident;
	        /* calculate the CRC-8 value for current byte */
	        for (byte bit = 0; bit < 8; bit++)
	        {
	            if ((currByte & 0x80) != 0)
	            {
	                currByte <<= 1;
	                currByte ^= generator;
	            }
	            else
	            {
	                currByte <<= 1;
	            }
	        }
	        /* store CRC value in lookup table */
	        crctable[divident] = currByte;
	    }
	}
	
}

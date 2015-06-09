package Parrot;

import java.io.*;
import java.nio.ByteBuffer;

public class ParrotObserver {

	private String file_name;
	static DataOutputStream out = null;
	private static Boolean firstTime = false;

	public ParrotObserver(String name) throws IOException
	{
		this.file_name = name;
		try 
		{
			out = new DataOutputStream(new FileOutputStream(this.file_name + ".parroto.data"));
		}
		finally
		{
			System.out.println("Can not create the file!!!\n");
		}
	}

	public void Finish() throws IOException
	{
		this.out.close();
	}


	public static byte[] toByteArray(Double[] doubleArray)
	{
    	int times = Double.SIZE / Byte.SIZE;
    	byte[] bytes = new byte[doubleArray.length * times];
    	for(int i=0;i<doubleArray.length;i++){
        	ByteBuffer.wrap(bytes, i*times, times).putDouble(doubleArray[i]);
    	}
    	return bytes;
	}

	public static byte[] toByteArray(double[] doubleArray)
	{
    	byte[] bytes = new byte[doubleArray.length * 8];
    	for(int i=0;i<doubleArray.length;i++){
        	ByteBuffer.wrap(bytes, i*8, 8).putDouble(doubleArray[i]);
    	}
    	return bytes;
	}


	// Normal Methods
	public static void write(String parrotName, char inOut, Double value, double rangeA, double rangeB) throws IOException
	{

		if(firstTime == false)
		{
			try
			{
				System.out.println("Amir\n");
				out = new DataOutputStream(new FileOutputStream("parroto.data"));
				firstTime = true;
			}
			catch (IOException e)
			{

				System.out.println("Can not create the file!!!\n");
			}
			
		}
		ByteBuffer b = ByteBuffer.allocate(4);
		b.putInt(parrotName.length() & (0X00FF));
		//System.out.println(parrotName.length());
		byte[] result = b.array();
		//System.out.println(result);
		try
		{
			out.writeInt(parrotName.length());
			// Name
			byte[] nameByte = parrotName.getBytes();	
			out.write(nameByte);
			// InOut
			ByteBuffer inOutByte = ByteBuffer.allocate(2);
			inOutByte.putChar(inOut);
			byte[] inOutByteArr = inOutByte.array();
			out.write(inOutByteArr);
			// Length: Number of elements
			ByteBuffer valueLengthByte = ByteBuffer.allocate(4);
			valueLengthByte.putInt(1);
			byte[] valueLengthByteArr = valueLengthByte.array();
			out.write(valueLengthByteArr);
			// Type
			ByteBuffer typeByte = ByteBuffer.allocate(4);
			typeByte.putInt(4);
			byte[] typeByteArr = typeByte.array();
			out.write(typeByteArr);
			// Value
			ByteBuffer valueByte = ByteBuffer.allocate(8);
			valueByte.putDouble(value);
			byte[] valueByteArr = valueByte.array();
			out.write(valueByteArr);
			// Range A
			ByteBuffer rangeAByte = ByteBuffer.allocate(8);
			rangeAByte.putDouble(rangeA);
			byte[] rangeAByteArr = rangeAByte.array();
			out.write(rangeAByteArr);
			// Range B
			ByteBuffer rangeBByte = ByteBuffer.allocate(8);
			rangeBByte.putDouble(rangeB);
			byte[] rangeBByteArr = rangeBByte.array();
			out.write(rangeBByteArr);

		}
		catch (IOException e)
		{
			System.out.println("Can!!!\n");
		}
	}

	public void write(String parrotName, char inOut, char value, Double rangeA, double rangeB)
	{
		System.out.println("Test!\n");
	}

	public void write(String parrotName, char inOut, Integer value, Double rangeA, double rangeB)
	{
		System.out.println("Test!\n");
	}

	// public void write(String parrotName, char inOut, Double value, Double rangeA, double rangeB)
	// {
	// 	System.out.println("Test!\n");
	// }
	// Array Methods
	public void write(String parrotName, char inOut, Boolean[] value, Integer len, Boolean rangeA, Boolean rangeB)
	{
		System.out.println("Test!\n");
	}
	public void write(String parrotName, char inOut, char[] value, Integer len, char rangeA, char rangeB)
	{
		System.out.println("Test!\n");
	}
	public void write(String parrotName, char inOut, Integer[] value, Integer len, Integer rangeA, Integer rangeB)
	{
		System.out.println("Test!\n");
	}

	public static void write(String parrotName, char inOut, Double[] value, Integer len, double rangeA, double rangeB) throws IOException
	{

		if(firstTime == false)
		{
			try
			{
				out = new DataOutputStream(new FileOutputStream("parroto.data"));
				firstTime = true;
			}
			catch (IOException e)
			{

				System.out.println("Can not create the file!!!\n");
			}
			
		}
		ByteBuffer b = ByteBuffer.allocate(4);
		b.putInt(parrotName.length());
		byte[] result = b.array();
		try
		{
			out.writeInt(parrotName.length());
			out.flush();
			// Name
			byte[] nameByte = parrotName.getBytes();	
			out.write(nameByte);
			// InOut
			ByteBuffer inOutByte = ByteBuffer.allocate(2);
			inOutByte.putChar(inOut);
			byte[] inOutByteArr = inOutByte.array();
			out.write(inOutByteArr);
			// Length: Number of elements
			ByteBuffer valueLengthByte = ByteBuffer.allocate(4);
			valueLengthByte.putInt(len); // double is 8
			byte[] valueLengthByteArr = valueLengthByte.array();
			out.write(valueLengthByteArr);
			// Type: Double
			ByteBuffer typeByte = ByteBuffer.allocate(4);
			typeByte.putInt(4);
			byte[] typeByteArr = typeByte.array();
			out.write(typeByteArr);
			// Value: Array
			byte[] valueByteArr = toByteArray(value);
			out.write(valueByteArr);
			// Range A
			ByteBuffer rangeAByte = ByteBuffer.allocate(8);
			rangeAByte.putDouble(rangeA);
			byte[] rangeAByteArr = rangeAByte.array();
			out.write(rangeAByteArr);
			// Range B
			ByteBuffer rangeBByte = ByteBuffer.allocate(8);
			rangeBByte.putDouble(rangeB);
			byte[] rangeBByteArr = rangeBByte.array();
			out.write(rangeBByteArr);

		}
		catch (IOException e)
		{
			System.out.println("Can!!!\n");
		}
	}

	public static void write(String parrotName, char inOut, double[] value, Integer len, double rangeA, double rangeB) throws IOException
	{

		if(firstTime == false)
		{
			try
			{
				out = new DataOutputStream(new FileOutputStream("parroto.data"));
				firstTime = true;
			}
			catch (IOException e)
			{

				System.out.println("Can not create the file!!!\n");
			}
			
		}
		ByteBuffer b = ByteBuffer.allocate(4);
		b.putInt(parrotName.length());
		byte[] result = b.array();
		try
		{
			out.writeInt(parrotName.length());
			out.flush();
			// Name
			byte[] nameByte = parrotName.getBytes();	
			out.write(nameByte);
			// InOut
			ByteBuffer inOutByte = ByteBuffer.allocate(2);
			inOutByte.putChar(inOut);
			byte[] inOutByteArr = inOutByte.array();
			out.write(inOutByteArr);
			// Length: Number of elements
			ByteBuffer valueLengthByte = ByteBuffer.allocate(4);
			valueLengthByte.putInt(len); // double is 8
			byte[] valueLengthByteArr = valueLengthByte.array();
			out.write(valueLengthByteArr);
			// Type: Double
			ByteBuffer typeByte = ByteBuffer.allocate(4);
			typeByte.putInt(4);
			byte[] typeByteArr = typeByte.array();
			out.write(typeByteArr);
			// Value: Array
			byte[] valueByteArr = toByteArray(value);
			out.write(valueByteArr);
			// Range A
			ByteBuffer rangeAByte = ByteBuffer.allocate(8);
			rangeAByte.putDouble(rangeA);
			byte[] rangeAByteArr = rangeAByte.array();
			out.write(rangeAByteArr);
			// Range B
			ByteBuffer rangeBByte = ByteBuffer.allocate(8);
			rangeBByte.putDouble(rangeB);
			byte[] rangeBByteArr = rangeBByte.array();
			out.write(rangeBByteArr);

		}
		catch (IOException e)
		{
			System.out.println("Can!!!\n");
		}
	}

    public void start() {
        System.out.println(this.file_name);
    }

}

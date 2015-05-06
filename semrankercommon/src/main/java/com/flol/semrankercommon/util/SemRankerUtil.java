package com.flol.semrankercommon.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class SemRankerUtil {

	private static Integer MIN_THREAD_WAIT = 4000;
	private static Integer MAX_THREAD_WAIT = 8000;
	
	private static Integer MIN_BETWEEN_THREAD_WAIT = 400;
	private static Integer MAX_BETWEEN_THREAD_WAIT = 800;

	private static Integer MIN_BETWEEN_CALL_WAIT = 2000;
	private static Integer MAX_BETWEEN_CALL_WAIT = 4000;

	
	
	public static void waitMillis(Integer sleep) {
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	

	public static void waitForFreeThreads()
	{
		try {
			Thread.sleep(NumberUtil.getRandomInteger(MIN_THREAD_WAIT, MAX_THREAD_WAIT));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	public static void waitBetweenThreads()
	{
		try {
			Thread.sleep(NumberUtil.getRandomInteger(MIN_BETWEEN_THREAD_WAIT, MAX_BETWEEN_THREAD_WAIT));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void waitBetweenCalls()
	{
		try {
			Thread.sleep(NumberUtil.getRandomInteger(MIN_BETWEEN_CALL_WAIT, MAX_BETWEEN_CALL_WAIT));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static byte[] compress(byte[] data) throws IOException {
		Deflater deflater = new Deflater();
		deflater.setInput(data);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(
				data.length);

		deflater.finish();
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer); // returns the generated
													// code... index
			outputStream.write(buffer, 0, count);
		}
		outputStream.close();
		byte[] output = outputStream.toByteArray();

//		System.out.println("Original: " + data.length  + " Kb");
//		System.out.println("Compressed: " + output.length  + " Kb");
		return output;
	}
	

	public static byte[] decompress(byte[] data) throws IOException,
			DataFormatException {
		Inflater inflater = new Inflater();
		inflater.setInput(data);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(
				data.length);
		byte[] buffer = new byte[1024];
		while (!inflater.finished()) {
			int count = inflater.inflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		outputStream.close();
		byte[] output = outputStream.toByteArray();

//		LOG.debug("Original: " + data.length);
//		LOG.debug("Compressed: " + output.length);
		return output;
	}

}


//package com.medpro.common.common.utils;
package com.toxicrobots.utils.utilclasses;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


// TODO: Auto-generated Javadoc
/**
 * The Class FileUtils.
 * 
 * @author pzgch7
 */
public final class FileUtils {

	/**
	 * Instantiates a new file utils.
	 */
	private FileUtils() {
		
	}
	
	/**
	 * Logger for this class.
	 */

	/**
	 * Read file to byte array.
	 * 
	 * @param file the file
	 * @return the byte[]
	 */
	public static byte[] readFileToByteArray(final File file) {
		int x, tot = 0;
		final int initSize = 4092;
		final byte[] bt = new byte[initSize];
		byte[] all = null;
		FileInputStream fis = null;
		try {
			// start 1
			fis = new FileInputStream(file);
			x = fis.read(bt);
			while (x >= 0) {
				tot += x;
				x = fis.read(bt);
			}
			fis.close();
			all = new byte[tot];
			fis = new FileInputStream(file);
			final int read = fis.read(all);
		} catch (final Exception e) {
		} finally {
			try {
				fis.close();
			} catch (final IOException e) {
			}
		}
		return all;
	}
	
	
	public static void writeByteArrayToFile(final File file,final byte[] bytes)
	{
		FileOutputStream fos= null;
		try {
			// start 1
			fos = new FileOutputStream(file);
			fos.write(bytes);
			
		} catch (final Exception e) {
		} finally {
			try {
				fos.close();
			} catch (final IOException e) {
			}
		}
	}
	/*
	 * public static byte[] readFileToByteArray(File file) { byte[] b = new
	 * byte[(int) file.length()]; try { FileInputStream fileInputStream = new
	 * FileInputStream(file); fileInputStream.read(b); } catch
	 * (FileNotFoundException e) { logger.debug("File Not Found.");
	 * //e.printStackTrace(); } catch (IOException e1) {
	 * logger.debug("Error Reading The File."); e1.printStackTrace(); } return
	 * b; }
	 */

}

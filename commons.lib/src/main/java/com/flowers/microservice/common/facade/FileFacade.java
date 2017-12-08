/**
 * 
 */
package com.flowers.microservice.common.facade;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import org.owasp.encoder.Encode;

import javax.annotation.Nonnull;
import org.apache.commons.lang.StringEscapeUtils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.flowers.microservice.common.constants.CharacterSet;
import com.flowers.microservice.common.constants.Constants;
import com.flowers.microservice.common.exception.Handler;
import com.flowers.microservice.common.validate.Validate;

/**
 * @author cgordon
 * @created 12/02/2017
 * @version 1.0
 * 
 * facade design pattern implementation with various functions used throughout application.
 *
 */
public class FileFacade {
	
	/**
	 * This method performs read operation and returns he contents of the file as a <code>String</code> object. 
	 * Method also uses owasp encoder to encode input data to mitigate potential {various security} data problems.
	 * 
	 * @param accepts a <code>File</code> object to perform the file move operation 
	 * @returns The contents as a <code>String</code> object.
	 */	
	public static String readEncodeXmlFile(@Nonnull File file) {

		if(!file.exists()) return Constants._WELL_FORMED_EMPTY_DOCUMENT;
		String contents = Constants._BLANK;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), CharacterSet.UTF_8))) {

			contents = br.lines().collect(Collectors.joining(""));

		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			
		} catch (IOException e) {
			
		}
		
		return StringEscapeUtils.unescapeXml(Encode.forXmlContent(contents.toString()));
	}

	/**
	 * This method verifies that the folder passed to it exists. If not then the folder is created.
	 * 
	 * @param folder name as a <code>String</code>
	 * @return boolean flag success
	 */
	public static boolean verifyFolder(@Nonnull String folder){
		
		File dir = new File(folder.toString());
		if(!dir.exists()){
			if (dir.mkdir()) {

			} else {
				return false;
			}
		}

		return true;
	}

	/**
	 * This method performs SAX xml verification 
	 * 
	 * @param input xml <code>File</code> to validate via SAX validation
	 * @return boolean flag success
	 * @throws <code>IOException</code> 
	 */
	public static boolean validateXmlFile(@Nonnull File file) throws IOException{

		if(!file.exists()) return false;

		Validate validate = new Validate();
		return Handler.unchecked(() -> validate.isValidXmlFile(file)).get();
	}
	
	/**
	 * Purpose of method is to standardize the file name call so it can be thread safe
	 * 
	 * @param file <code>File</code> to be renamed
	 * @param new file name <code>String</code> 
	 * @return file <code>File</code> that has been renamed
	 */
	public static File renameFile(@Nonnull File file, String newFilename){

		File f = file;
		if(f.renameTo(new File(newFilename)))
			f = new File(newFilename);
		
		f.setLastModified(new Date().getTime());
		
		return f;	
	}

	/**
	 * Utility function to determine the current directory regardless of the OS Type... 
	 * 
	 * @return string value of the current directory
	 */
	public static String getCurrentDirectory(){

		String currentDir = Constants._BLANK;
		OsUtils.OS ostype=OsUtils.getOs();
		
		switch (ostype) {
		    case WINDOWS: 
		    	
		    	currentDir = System.getProperty("user.dir").concat(File.separator);
		    	
		    	break;
		    case MAC: 
		    case UNIX: 
		    	currentDir = (new File(".").getAbsolutePath()).concat(File.separator);
		    	
		    	break;
		    case OTHER: 
		    	currentDir = File.pathSeparator;
		    	
		    	break;
		default:
			break;
		}
		
		return currentDir;
	}
	
	
	/**
	 * Method compresses files in specified folder to the requested zip file.
	 * 
	 * @param sourceDirPath source folder where files are to be compressed
	 * @param zipFilePath zip file name (path)
	 * @throws IOException IOException exception  
	 */
	public static void pack(String sourceDirPath, String zipFilePath) throws IOException {
		
	    Path p = Files.createFile(Paths.get(zipFilePath));
	    try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
	        Path pp = Paths.get(sourceDirPath);
	        Files.walk(pp)
	          .filter(path -> !Files.isDirectory(path))
	          .forEach(path -> {
	              ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
	              try {
	                  zs.putNextEntry(zipEntry);
	                  Files.copy(path, zs);
	                  zs.closeEntry();
	            } catch (IOException e) {
	                System.err.println(e);
	            }
	          });
	    }
	}

/**
	 * Java method accepts input text filename and reads file from disk. The string value of the text is returned from method.  
	 * 
	 * @param String <String> filename.
	 * @return <String> contents of file.
	 * @throws IOException
	 */
	public String readFile(String file) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    try {
	        while((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            stringBuilder.append(ls);
	        }

	        return stringBuilder.toString();
	    } finally {
	        reader.close();
	    }
	}
	
	/**
	 * Java method writes data provided to the specified file name passed.  
	 * 
	 * @param String <String> filename.
	 * @param <String> data with contents to be written to disc.
	 * @return boolean primitive flag of write success.
	 * @throws none
	 */
	public boolean writeFile(String filename, String data){
		
		Writer writer = null;
		boolean success = true;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"));
		    writer.write(data);
		} catch (IOException e) {
			System.out.printf("ERROR:: ", e.getMessage());
			success = false;
		} finally {
		   try {
			   writer.close();
		   } catch (Exception e) {
			   System.out.printf("ERROR:: ", e.getMessage());
		   }
		}
		   return success;		
	}	
	
	/**
	 * Java method purpose is to process a param provided regular expression against the given string value.   
	 * 
	 * @param character to be evaluated.
	 * @param Regular expression <String> to be evaluated. 
	 * @return boolean primitive flagging results of regular expression execution. 
	 * @throws none
	 */
	public boolean regexp(char chr, String expression){
		
		return String.valueOf(chr).matches(expression);
	}	

	/**
	 * 
	 * @author cgordon
	 * @created 09/05/2017
	 * @version 1.0
	 *
	 * helper class to check the operating system this Java VM runs in
	 *
	 */
	public static class OsUtils{

		public enum OS {
			WINDOWS,
			UNIX,
			POSIX_UNIX,
			MAC,
			OTHER;

			private String version;

			public String getVersion() {
				return version;
			}

			public void setVersion(String version) {
				this.version = version;
			}
		}

		private static OS os = OS.OTHER;

		static {
			try {
				String osName = System.getProperty("os.name");
				if (osName == null) {
					throw new IOException("os.name not found");
				}
				osName = osName.toLowerCase(Locale.ENGLISH);
				if (osName.contains("windows")) {
					os = OS.WINDOWS;
				} else if (osName.contains("linux")
						|| osName.contains("mpe/ix")
						|| osName.contains("freebsd")
						|| osName.contains("irix")
						|| osName.contains("digital unix")
						|| osName.contains("unix")) {
					os = OS.UNIX;
				} else if (osName.contains("mac os")) {
					os = OS.MAC;
				} else if (osName.contains("sun os")
						|| osName.contains("sunos")
						|| osName.contains("solaris")) {
					os = OS.POSIX_UNIX;
				} else if (osName.contains("hp-ux") 
						|| osName.contains("aix")) {
					os = OS.POSIX_UNIX;
				} else {
					os = OS.OTHER;
				}

			} catch (Exception ex) {
				os = OS.OTHER;
			} finally {
				os.setVersion(System.getProperty("os.version"));
			}
		}

		public static final OS getOs() {

			return os;
		}
	}	
}

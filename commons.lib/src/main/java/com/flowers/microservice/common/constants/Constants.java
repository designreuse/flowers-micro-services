package com.flowers.microservice.common.constants;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.flowers.microservice.common.exception.Status;

/**
 * @author cgordon
 * @created 12/02/2017
 * @version 1.0
 *
 * constants are maintained here. general policy to not have or minimize rogue string literals throughout the application
 *
 */
public final class Constants {

	public static final char _PIPE = '|';
	public static final char _UNDERSCORE = '_';
	public static final char CHAR_NULL = '\u0000' ;
	public static final char CHAR_BLANK = '\u0020' ;		

	public static final byte ZERO = (byte) 0x0;
	public static final byte EXCEPTION_CODE_IO = (byte) 0x2;
	public static final byte EXCEPTION_CODE_GENERAL = (byte) 0x3;
	public static final byte EXCEPTION_CODE_ARG_UNDERFLOW = (byte) 0x4;

	public static final short HTTP_KEEP_ALIVE_TIME = (short) 1 * 30 * 1000;
	public static final short HTTP_SO_TIMEOUT = (short) 2000;
	public static final short MAX_PAYLOAD_LENGTH = (short) 8192 ; /** OWASP security recommended ceiling on payload size*/
	public static final short HTTP_CONNECTION_TIME_OUT = (short) 1 * 30 * 1000;
	public static final short DEFAULT_TIMEOUT = (short) 0x1000;
	public static final short DEFAULT_THREAD_SLEEP = (short) 0x100;
	public static final short MAX_STATEMENTS_CACHE = (short) 0x1000;	

	public static final Pattern XML_ENTITY_PATTERN = Pattern.compile("\\&\\#(?:x([0-9a-fA-F]+)|([0-9]+))\\;");

	public static enum SORT { ASC, DESC; }
	public static enum METHOD_TYPE { SETTER, GETTER; }
	public static enum DB { MONGODB, ORACLE, DB2, MYSQL; }
	public static enum QUERY { EXECUTE, EXECUTE_QUERY, EXECUTE_UPDATE, EXECUTE_BATCH; }	

	public static final String _WELL_FORMED_EMPTY_DOCUMENT = "<?xml version='1.0'?><_/>";
	public static final String _DELIMITER = "::";	
	public static final String _WHITESPACE = " ";
	public static final String _BLANK = "";
	public static final String _YES = "Y";
	public static final String _NO = "N";

	public static final String ROLE_VIEW = "ROLE_VIEW";
	public static final String ROLE_DELETE = "ROLE_DELETE";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	public static final String STR_URL_ENCODE_SCHEME = "UTF-8";	
	public static final String STR_HTTP_DELIMITER = "\\A";		
	public static final String STR_STRING_NEWLINE = "\n";
	public static final String STR_ZERO = "0";
	public static final String DATEFORMAT_ISO8601 = "yyyy-MM-dd'T'HH:mm'Z'";	
	public static final String UTF8_BOM = "\uFEFF";	
	public static final String DEFAULT_ALIAS_LABEL = "email";
	public final static String ERROR_SERVICE_MESSAGE = "Service not Found";

	public static final String REGEXP_VALID_XML = "[^\\x20-\\x7e\\x0A]";
	public static final String REGEXP_VALID_EMAIL = "^(.+)@(.+)$";	
	public static final String REGEXP_VALID_FLAG = "^[Y|N]{1}$";
	public static final String REGEXP_TRUE = "^[Y|YES|T|TRUE|1]{1}$";
	public static final String REGEXP_VALID_URL = "^(http://|https://)?(www.)?([a-zA-Z0-9@:%_\\+.~#?&//=-]+).[a-zA-Z0-9@:%_\\+.~#?&//=-]+]*.[a-z]{3}.?([a-zA-Z0-9@:%_\\+.~#?&//=-]+)?$";	

	public static final String BASE_LOCATION_REPOSITORIES = "com.flowers.services.locator.repositories";
	public static final String BASE_LOCATION_SERVICES = "com.flowers.services";	

	public static final String MESSAGE_GENERAL_EXCEPTION = "General exception encountered.";
	public static final String MESSAGE_GENERAL_SQL_RESULTSET = "General Exception raised processing resultset.";
	public static final String MESSAGE_ACCESS_DB_FAIL = "Exception raised while accessing database schema/tables.";
	public static final String MESSAGE_CONNECT_DB_FAIL = "Exception raised while attempting to connect with database.";		
	public static final String MESSAGE_GENERAL_MESG = "%s: message for function execution. Class: %s , Method: %s.";	
	public static final String MESSAGE_GENERAL_MESG_DETAILS = "%s: message for function execution. Class: %s , Method: %s, Details: %s.";	

	/**
	 * Persistence database driver class name(s)
	 */
	public static final String DBDRIVER_ORACLE = "oracle.jdbc.driver.OracleDriver";
	public static final String DBDRIVER_DB2 = "com.ibm.db2.jcc.DB2Driver";	
	public static final String DBDRIVER_MONGODB = "mongodb.jdbc.MongoDriver";
	public static final String DBDRIVER_MYSQL = "com.mysql.jdbc.Driver";	

	public static final short STATUS_OK = (short)200;
	public static final short STATUS_CREATED = (short)201;
	public static final short STATUS_NO_CONTENT = (short)204;
	public static final short STATUS_BAD_REQUEST = (short)400;
	public static final short STATUS_UNAUTHORIZED = (short)401;
	public static final short STATUS_FORBIDDEN = (short)403;
	public static final short STATUS_NOT_FOUND = (short)404;
	public static final short STATUS_METHOD_NOT_ALLOWED = (short)405;
	public static final short STATUS_CONFLICT = (short)409;
	public static final short STATUS_INTERNAL_SERVER_ERROR = (short)500;
	public static final short STATUS_RESOURCE_NOT_FOUND = (short)800;

	public static Map<Short, Status> errorCodeMap = new HashMap<Short, Status>() {
		private static final long serialVersionUID = 1L;		
		{
			put(STATUS_OK, new Status("OK", "General success status code. This is the most common code. Used to indicate success."));
			put(STATUS_CREATED, new Status("CREATED", "Successful creation occurred (via either POST or PUT). Set the Location header to contain a link to the newly-created resource (on POST). Response body content may or may not be present."));
			put(STATUS_NO_CONTENT, new Status("NO CONTENT", "Indicates success but nothing is in the response body, often used for DELETE and PUT operations."));
			put(STATUS_BAD_REQUEST, new Status("BAD REQUEST", "General error for when fulfilling the request would cause an invalid state. Domain validation errors, missing data, etc. are some examples."));
			put(STATUS_UNAUTHORIZED, new Status("UNAUTHORIZED", "Error code response for missing or invalid authentication token."));
			put(STATUS_FORBIDDEN, new Status("FORBIDDEN", "Error code for when the user is not authorized to perform the operation or the resource is unavailable for some reason (e.g. time constraints, etc.)."));
			put(STATUS_NOT_FOUND, new Status("NOT FOUND", "Used when the requested resource is not found, whether it doesn't exist or if there was a 401 or 403 that, for security reasons, the service wants to mask."));
			put(STATUS_METHOD_NOT_ALLOWED, new Status("METHOD NOT ALLOWED", "Used to indicate that the requested URL exists, but the requested HTTP method is not applicable. For example, POST /users/12345 where the API doesn't support creation of resources this way (with a provided ID). The Allow HTTP header must be set when returning a 405 to indicate the HTTP methods that are supported. In the previous case, the header would look like Allow: GET, PUT, DELETE"));
			put(STATUS_CONFLICT, new Status("CONFLICT", "Whenever a resource conflict would be caused by fulfilling the request. Duplicate entries, such as trying to create two customers with the same information, and deleting root objects when cascade-delete is not supported are a couple of examples."));
			put(STATUS_INTERNAL_SERVER_ERROR, new Status("INTERNAL SERVER ERROR", "Never return this intentionally. The general catch-all error when the server-side throws an exception. Use this only for errors that the consumer cannot address from their end."));
			put(STATUS_RESOURCE_NOT_FOUND, new Status("SERVICE NOT FOUND", "Custom error used when the requested controller service is not found."));
		};
	};

	public static Map<Constants.DB, String> databaseDriversMap = new HashMap<Constants.DB, String>() {
		private static final long serialVersionUID = 1L;

		{
			put(DB.MONGODB, DBDRIVER_MONGODB);        	
			put(DB.ORACLE, DBDRIVER_ORACLE);
			put(DB.DB2, DBDRIVER_DB2);
			put(DB.MYSQL, DBDRIVER_MYSQL);            
		};
	};	

	public static Map<Constants.QUERY, String> databaseOperation = new HashMap<Constants.QUERY, String>() {
		private static final long serialVersionUID = 1L;

		{
			put(QUERY.EXECUTE, "execute");        	
			put(QUERY.EXECUTE_QUERY, "executeQuery");
			put(QUERY.EXECUTE_UPDATE, "executeUpdate");
			put(QUERY.EXECUTE_BATCH, "executeBatch");
		};
	};	

	/**
	 *  Declare class final and constructor private to defeat instantiation and extension
	 */
	private Constants(){

	}
}

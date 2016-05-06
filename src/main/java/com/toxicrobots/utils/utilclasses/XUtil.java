
//package com.medpro.common.common.utils;
package com.toxicrobots.utils.utilclasses;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import org.apache.commons.*;
import org.apache.commons.collections.*;

/*
import com.medpro.common.common.IAutoReflectible;
import com.medpro.common.common.beans.ValueMapping;
*/
/**
 * An exclusive utility class that provides common functionality static methods.
 * 
 * */
public final class XUtil {

	/** Log4j instance. */

	/**
	 * Private constructor.
	 */
	private XUtil() {
		// Made private intentionally in order to prevent
		// accidental instantiation.
	}

	/**
	 * DATEFORMAT date formatter.
	 */
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat();

	/**
	 * String for using in test classes.
	 */
	public static final String EMPTY = "value should be empty";

	/**
	 * String for using in test classes.
	 */
	public static final String EQUAL = "both should be equal";

	/**
	 * String for using in test classes.
	 */
	public static final String BUT_WAS = "; but was: [";

	/**
	 * String for using in test classes.
	 */
	public static final String BUT_WAS_CLOSE = "]";

	/**
	 * <p>
	 * Populate this autoReflectible object from properties map, based on the
	 * specified name/value pairs. This method uses the populate() method from
	 * org.apache.commons.beanutils.BeanUtil.
	 * <p>
	 * AutoReflectible is a marker interface, so any class can implement it.
	 * <p>
	 * Example: If an autoReflectible object (say UserDTO) need to be populated
	 * with the contents stored in a map of key->value pairs (like name->john,
	 * age->30 etc.,) then use the following syntax:
	 * XUtil.populate(instanceOfDTO, instanceOfMap);
	 * <p>
	 * In order to understand how this auto-population works, please refer the
	 * doc of Apache BeanUtils class.
	 * <p>
	 * Properties' keys are scanned for old property names, and linked to the
	 * new name if necessary. This modifies the properties map.
	 * <p>
	 * The particular setter method to be called for each property is determined
	 * using the usual JavaBeans introspection mechanism. Thus, you may identify
	 * custom setter methods using a BeanInfo class that is associated with the
	 * class of the bean itself. If no such BeanInfo class is available, the
	 * standard method name conversion ("set" plus the capitalized name of the
	 * property in question) is used.
	 * <p>
	 * <strong>NOTE</strong>: It is contrary to the JavaBeans Specification to
	 * have more than one setter method (with different argument signatures) for
	 * the same property.
	 * 
	 * @param iAutoReflectible
	 *            A Bean or a DTO having fields equal to the map key names
	 *            (exactly, i.e., key name-field matching is case-sensitive).
	 * @param properties
	 *            Map keyed by field to populate, mapped with the corresponding
	 *            (String or String[]) value(s) to be set.
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws java.lang.reflect.InvocationTargetException
	 *             the invocation target exception
	 * @exception IllegalAccessException
	 *                if the caller does not have access to the property
	 *                accessor method.
	 * @exception java.lang.reflect.InvocationTargetException
	 *                if the property accessor method throws an exception.
	 * @see org.apache.commons.beanutils.BeanUtils
	 */
	/*
	public static void populate(final IAutoReflectible iAutoReflectible,
			final Map properties) throws java.lang.IllegalAccessException,
			java.lang.reflect.InvocationTargetException {
		log.debug("Trying to populate... ");
		BeanUtils.populate(iAutoReflectible, properties);
		log.debug("Successful.");
		log.debug("After population DTO: "
				+ ((Object) iAutoReflectible).toString());
	}
*/
	/**
	 * Capitalize a String (whatever the starting case).
	 * 
	 * @param s
	 *            the source string.
	 * @return capitalized string.
	 */
	public static String capitalize(final String s) {
		String cap = "";
		if (isValid(s)) {
			cap = s.trim();
			if (cap.length() == 1) {
				cap = cap.toUpperCase();
			} else {
				cap = cap.substring(0, 1).toUpperCase()
						+ s.substring(1).toLowerCase();
			}
		} else {
			cap = s;
		}
		return cap;
	}

	/**
	 * Converts to camel case of java convention. Examples: Input: Welcome to
	 * jAVA Output: welcomeToJava Input: My_app_data Output: myAppData Input:
	 * My\tFirst\taPP Output: myFirstApp
	 * 
	 * @param s
	 *            the string to be converted to camel case.
	 * @return the final string which is of camel case.
	 */
	public static String toCamelCase(final String s) {
		if (s == null) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(s, "_ \n\r\t"); // blank space
		// is
		// intentional
		StringBuffer buf = new StringBuffer("");
		String eachToken = null;
		while (st.hasMoreTokens()) {
			eachToken = st.nextToken();
			if (!"".equals(buf.toString())) {
				eachToken = capitalize(eachToken);
			} else {
				eachToken = eachToken.toLowerCase(Locale.UK);
			}
			buf.append(eachToken);
		}
		return buf.toString();
	}

	/**
	 * Converts each words first letter into upper case. Examples: Input:
	 * Welcome to jAVA Output: WelcomeToJava Input: My_app_data Output:
	 * MyAppData Input: My\tFirst\taPP Output: MyFirstApp
	 * 
	 * @param s
	 *            the string to be converted to init cap.
	 * @return the final string which is of init cap.
	 */
	public static String toInitCap(final String s) {
		StringTokenizer st = new StringTokenizer(s, " _\n\r\t", true);
		StringBuffer buf = new StringBuffer("");
		String eachToken = null;
		while (st.hasMoreTokens()) {
			eachToken = capitalize(st.nextToken());
			buf.append(eachToken);
		}
		return buf.toString();
	}

	/**
	 * Creates a Comma-Seperated Value (CSV) string from the given list.
	 * 
	 * @param srcList
	 *            list of elements.
	 * @return csv of the list elements.
	 */
	public static String listToCsv(final List<? extends Object> srcList) {

		StringBuffer csv = new StringBuffer("");

		// if srcList is null, return an empty csv string ("").
		if (srcList != null) {
			// Get iterator
			Iterator<? extends Object> itr = srcList.iterator();
			while (itr.hasNext()) {
				// Take each element from the collection
				String eachElem = itr.next().toString();

				// append it to the string buffer
				csv.append(eachElem);
				// Add comma only when more elements are there to fetch.
				if (itr.hasNext()) {
					csv.append(",");
				}
			}
		}
		return csv.toString();
	}

	/**
	 * Creates a Integer array from the given list.
	 * 
	 * @param srcList
	 *            list of elements.
	 * @return integer array of the list elements.
	 */
	public static Integer[] listToIntegerArray(final List<String> srcList) {

		Integer[] resultArr = null;
		// if srcList is null, return an empty csv string ("").
		if (srcList != null) {
			resultArr = new Integer[srcList.size()];
			// Get iterator
			Iterator<? extends Object> itr = srcList.iterator();
			int idx = 0;
			while (itr.hasNext()) {
				// Take each element from the collection
				String eachElem = (String) itr.next();

				// append it to the string buffer
				resultArr[idx++] = Integer.parseInt(eachElem);
			}
		}
		return resultArr;
	}

	/**
	 * Creates a String array from the given list.
	 * 
	 * @param srcList
	 *            list of elements.
	 * @return string array of the list elements.
	 */
	public static String[] listToStringArray(final List srcList) {
		if (srcList == null) {
			throw new IllegalArgumentException("Parameter should not be null");
		}
		int size = srcList.size();
		String[] resultArr = new String[size];

		for (int i = 0; i < size; i++) {
			resultArr[i] = srcList.get(i).toString();
		}

		return resultArr;
	}

	/**
	 * Creates a Comma-Separated Value (CSV) string from the given array.
	 * 
	 * @param array
	 *            create a CSV from this array.
	 * @return CSV of values of the given array.
	 */
	public static String arrayToCsv(final Object[] array) {
		String csv = "";
		if (array != null) {
			// Create list of the passed array and invoke another utility
			// method.
			csv = listToCsv(Arrays.asList(array));
		}
		return csv;
	}

	/**
	 * Creates a list from the given Comma-Separated Value (CSV) string.
	 * Examples: "1,2,3,4,5" -> arraylist(1,2,3,4,5)
	 * 
	 * @param csv
	 *            comma-separated list.
	 * @return list of each element found in the CSV.
	 */
	public static List<String> csvToList(final String csv) {
		return csvToList(csv, ",");
	}
	/**
	 * Creates a list from the given Comma-Separated Value (CSV) string.
	 * Examples: "1,2,3,4,5" -> arraylist(1,2,3,4,5)
	 * 
	 * @param csv
	 *            comma-separated list.
	 * @return list of each element found in the CSV.
	 */
	public static List<String> csvToList(final String csv, final String delim) {
		String[] tokens = csv.split(delim);
		List<String> csvList = new ArrayList<String>(tokens.length);
		
		for (int i = 0; i < tokens.length; i++) {
			if ( ! StringUtils.isEmpty(tokens[i].trim())) {
				csvList.add(tokens[i].trim());
			}
		}

		return csvList;
	}

	/**
	 * Returns true if the given token is available as part of the csv provided.
	 * 
	 * @param token
	 *            the token to search in the csv
	 * @param csv
	 *            comma-separated string of tokens
	 * 
	 * @return true, if the given token matches one of the item in the csv
	 */
	public static boolean tokenMatches(final String token, final String csv) {
		String[] arrOfTokens = csv.split(",");
		return tokenMatches(token, arrOfTokens);
	}

	/**
	 * Returns true if the given token is available as part of the csv provided.
	 * 
	 * @param token
	 *            the token to search in the csv
	 * @param csv
	 *            comma-separated string of tokens
	 * 
	 * @return true, if the given token matches one of the item in the csv
	 */
	public static int tokenMatchesCount(final String token, final String csv) {
		String[] arrOfTokens = csv.split(",");
		String trToken = token.trim();
		int count = 0;
		for (String csvToken : arrOfTokens) {
			if (trToken.equalsIgnoreCase(csvToken.trim())) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Returns true if the given token is available as part of the csv provided.
	 * 
	 * @param token
	 *            the token to search in the csv
	 * @param csv
	 *            comma-separated string of tokens
	 * 
	 * @return true, if the given token matches one of the item in the csv
	 */
	public static boolean tokenMatches(final String token, final String... csv) {
		if (null != token) {
			String trToken = token.trim();
			for (String csvToken : csv) {
				if (trToken.equalsIgnoreCase(csvToken.trim())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Generates Object array from the given primitive int array.
	 * 
	 * @param src
	 *            d
	 * @return d
	 */
	public static Object[] intToObjArray(final int[] src) {
		Object[] target = null;
		if (src != null) {
			target = new Object[src.length];

			for (int i = 0; i < src.length; i++) {
				target[i] = String.valueOf(src[i]);
			}
		}
		return target;
	}

	/**
	 * Generates Object array from the given primitive float array.
	 * 
	 * @param src
	 *            d
	 * @return d
	 */
	public static Object[] floatToObjArray(final float[] src) {
		Object[] target = null;
		if (src != null) {
			target = new Object[src.length];

			for (int i = 0; i < src.length; i++) {
				target[i] = String.valueOf(src[i]);
			}
		}
		return target;
	}

	/**
	 * Generates comma-seperated list (csv) from the given primitive int array.
	 * 
	 * @param src
	 *            int array
	 * @return csv
	 */
	public static String intArrayToCsv(final int[] src) {
		Object[] objArr = intToObjArray(src);
		return arrayToCsv(objArr);
	}

	/**
	 * Generates comma-seperated list (csv) from the given primitive float
	 * array.
	 * 
	 * @param src
	 *            float array
	 * @return csv
	 */
	public static String floatArrayToCsv(final float[] src) {
		Object[] objArr = floatToObjArray(src);
		return arrayToCsv(objArr);
	}

	/**
	 * Utility method to check for validness of parameter of a public method.
	 * 
	 * @param param
	 *            parameter of a public method.
	 * @return <code>true</code> if param is not null and not an empty
	 *         string(""). <code>false</code> otherwise.
	 */
	public static boolean isValid(final Object param) {
		boolean isValid = false;
		if (param instanceof String) {
			isValid = param != null && !"".equals(((String) param).trim());
		} else if (param instanceof Object[]) {
			isValid = param != null && (((Object[]) param).length > 0);
		} else if (param instanceof Collection<?>) {
			isValid = param != null && !((Collection<?>) param).isEmpty();
		} else if (param instanceof Map<?, ?>) {
			isValid = param != null && !((Map<?, ?>) param).isEmpty();
		} else { // Other Objects
			isValid = param != null;
		}

		return isValid;
	}

	/**
	 * Utility method to check for validness of parameter of a public method.
	 * 
	 * @param param
	 *            parameter of a public method.
	 * @return <code>true</code> if isValid returns false. <code>false</code>
	 *         otherwise.
	 */
	public static boolean isNotValid(final Object param) {
		return !isValid(param);
	}

	/**
	 * This method checks whether the given string contains only numbers. For
	 * Example: "32532" -> true "3a323" -> false
	 * 
	 * @param param
	 *            source string
	 * @return <code>true</code> for numbers.
	 */
	public static boolean parseableAsInt(final Object param) {
		if (isNotValid(param)) {
			throw new IllegalArgumentException("param [param] is null");
		}

		String strRepr = param.toString();
		final int len = strRepr.length();

		for (int j = 0; j < len; j++) {
			if (j == 0 && strRepr.charAt(j) == '-') {
				continue;
			}
			if (!Character.isDigit(strRepr.charAt(j))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method checks whether the given string contains only numbers and
	 * when fails returns the default value. For Example: "32532" -> true
	 * "3a323" -> false
	 * 
	 * @param param
	 *            source string
	 * @param defaultValue
	 *            the default value
	 * @return <code>true</code> for numbers.
	 */
	public static boolean parseableAsInt(final Object param,
			final boolean defaultValue) {

		if (isNotValid(param)) {
			return defaultValue;
		}

		String strRepr = param.toString();
		final int len = strRepr.length();

		for (int j = 0; j < len; j++) {
			if (!Character.isDigit(strRepr.charAt(j))) {
				return defaultValue;
			}
		}
		return true;
	}

	/**
	 * This method checks whether the given string contains only numbers and
	 * optionally (only) one dot symbol. For Example: "32532" -> true; "3255.33"
	 * -> true; "3a323" -> false
	 * 
	 * @param param
	 *            source string
	 * @return <code>true</code> for numbers.
	 */
	public static boolean parseableAsDecimal(final Object param) {
		if (isNotValid(param)) {
			throw new IllegalArgumentException("param [param] is null");
		}

		boolean parseable = false;

		String strRepr = param.toString();

		try {
			Double.parseDouble(strRepr);
			parseable = true;
		} catch (NumberFormatException e) {
			parseable = false;
		}

		return parseable;
	}

	/**
	 * Splits the source map into small sub-maps all having equal or lesser size
	 * of the given threshold.
	 * 
	 * @param srcMap
	 *            the source map
	 * @param threshold
	 *            indicates when the source map has to be split
	 * @return list of all sub-maps
	 */
	public static List<Map> splitMap(final Map srcMap, final int threshold) {
		List<Map> tgtList = null;

		// NPE Check
		if (srcMap == null) {
			throw new IllegalArgumentException("param [srcMap] is null");
		}

		tgtList = new ArrayList<Map>();

		Set set = srcMap.entrySet();
		Iterator itr = set.iterator();

		Map<Object, Object> smallMap = new HashMap<Object, Object>();

		while (itr.hasNext()) {
			Map.Entry entry = (Map.Entry) itr.next();

			smallMap.put(entry.getKey(), entry.getValue());

			if (smallMap.size() == threshold || !itr.hasNext()) {
				tgtList.add(smallMap);
				smallMap = new HashMap<Object, Object>();
			}
		}

		return tgtList;
	}

	/**
	 * Returns today's date as string in the given format.
	 * 
	 * @param dateFormat
	 *            required format.
	 * @return dateStr today's date in string format.
	 */
	public static String getFormattedDate(final String dateFormat) {
		if (("").equals(dateFormat) || dateFormat == null) {
			dateFormatter = new SimpleDateFormat();
		} else {
			dateFormatter = new SimpleDateFormat(dateFormat);
		}

		String dateStr = null;
		synchronized (dateFormatter) {
			dateStr = dateFormatter.format(new Date());
		}
		return dateStr;
	}

	/**
	 * Converts String to java.util.Date. (specified by the pattern).
	 * 
	 * @param strDate
	 *            string representation of date to convert to java.util.Date
	 * @param pattern
	 *            date pattern (default is yyyy-MM-dd)
	 * @return java.util.Date equivalent to the string parameter
	 */
	public static Date toDate(final String strDate, final String pattern) {
		String myPattern = pattern;
		if (myPattern == null) {
			myPattern = "yyyy-MM-dd";
		}

		DateFormat df = new SimpleDateFormat(myPattern);
		Date fmtDate = null;

		try {
			fmtDate = df.parse(strDate);
		} catch (ParseException e) {
			myPattern = "MM/dd/yyyy";
			df = new SimpleDateFormat(myPattern);
			try {
				fmtDate = df.parse(strDate);
			} catch (ParseException e2) {
				fmtDate = new Date();
			}
		}
		return fmtDate;
	}

	/**
	 * Generates the data in required format for specific date.
	 * 
	 * @param srcDateFormat
	 *            format of the input.
	 * @param reqDateFormat
	 *            format of the output.
	 * @param dtStr
	 *            date input to be converted.
	 * @return dateStr date in the output format.
	 */
	public static String getFormattedDate(final String srcDateFormat,
			final String reqDateFormat, final String dtStr) {
		Date date = null;
		String dateStr = "";
		if (("").equals(srcDateFormat) || srcDateFormat == null) {
			dateFormatter = new SimpleDateFormat();
		} else {
			dateFormatter = new SimpleDateFormat(srcDateFormat);
		}
		try {
			synchronized (dateFormatter) {
				date = dateFormatter.parse(dtStr);
			}
		} catch (ParseException e) {
		}
		if (("").equals(reqDateFormat) || reqDateFormat == null) {
			dateFormatter = new SimpleDateFormat();
		} else {
			dateFormatter = new SimpleDateFormat(reqDateFormat);
		}
		synchronized (dateFormatter) {
			dateStr = dateFormatter.format(date);
		}
		return dateStr;
	}

	/**
	 * Processes the input string's date to match the format asked for.
	 * 
	 * @param dateFormat
	 *            required format.
	 * @param dtStr
	 *            date.
	 * @return date in Date.
	 * @throws ParseException
	 *             if any.
	 */
	public static Date checkValidDate(final String dateFormat,
			final String dtStr) throws ParseException {
		Date date;
		if (("").equals(dateFormat) || dateFormat == null) {
			dateFormatter = new SimpleDateFormat();
		} else {
			dateFormatter = new SimpleDateFormat(dateFormat);
		}
		synchronized (dateFormatter) {
			date = dateFormatter.parse(dtStr);
		}
		return date;
	}

	/**
	 * Compares the given date and returns true if date1 is greater then date2.
	 * 
	 * @param date1
	 *            date1 as str.
	 * @param date2
	 *            date2 as str.
	 * @param dateFormat
	 *            date format as str
	 * @return true if if Date1 is greater then date2 else false.
	 * @throws ParseException
	 *             if any.
	 */
	public static boolean compareDate(final String date1, final String date2,
			final String dateFormat) throws ParseException {
		if ("".equals(date1) || "".equals(date2) || "".equals(dateFormat)) {
			throw new IllegalArgumentException("requied "
					+ "parameter are not passed.");
		}
		DateFormat df = new SimpleDateFormat(dateFormat);
		Date dateSrc = df.parse(date1);
		Date dateDest = df.parse(date2);
		return dateSrc.after(dateDest);
	}

	/**
	 * Splits the arrayList to the given size.
	 * 
	 * @param values
	 *            list
	 * @param block
	 *            threshHoldValue.
	 * @return List container containing subList's of the main List.
	 */
	public static List<List> splitList(final List values, final int block) {
		int i = values.size();
		List<List> conatinerList = new ArrayList<List>();
		int count = 1;
		int rem = 0;
		int start = 0;
		List list;
		while (i > 0) {
			if (i - block > 0) {
				i = i - block;
				list = values.subList(start, count * block);
				start = count * block;
				count++;
			} else {
				rem = i;
				i = -1;
				list = values.subList(start, start + rem);
			}
			conatinerList.add(list);
		}
		return conatinerList;
	}

	/**
	 * Returns true if string: src contains the string: dest.
	 * 
	 * @param src
	 *            the source string which need to be searched
	 * @param dest
	 *            the string to search
	 * @return <code>true</code> if dest is found in src. <code>false</code>
	 *         otherwise.
	 */
	public static boolean contains(final String src, final String dest) {
		boolean isFound = false;

		if ((src != null && dest != null) && src.indexOf(dest) != -1) {
			isFound = true;
		}

		return isFound;
	}

	/**
	 * Takes a fully qualified class name and strips out the package name.
	 * 
	 * For example: input "com.xx.yy.MyClass", output: "MyClass".
	 * 
	 * @param fullyQualCName
	 *            fully qualified class name.
	 * @return only class name
	 */
	public static String stripPackage(final String fullyQualCName) {
		if (fullyQualCName == null) {
			throw new IllegalArgumentException("Parameter is invalid");
		}
		return fullyQualCName.substring(fullyQualCName.lastIndexOf('.') + 1);
	}

	/**
	 * Takes any object and returns only the class name after stripping out the
	 * package name.
	 * 
	 * @param anObj
	 *            any object whose name without package prefix is required.
	 * @return only class name (without package prefix).
	 */
	public static String stripPackage(final Object anObj) {
		if (anObj == null) {
			throw new IllegalArgumentException("Parameter is invalid");
		}
		String fullyQualCName = anObj.getClass().getName();
		return fullyQualCName.substring(fullyQualCName.lastIndexOf('.') + 1);
	}

	/**
	 * Find all files located in the given directory which contain the given
	 * pattern.
	 * 
	 * @param srcDir
	 *            the directory where to start the search for the pattern.
	 * @param extn
	 *            the extension to search for.
	 * @return an array of files which contain the pattern
	 * @throws IOException
	 *             if any of the files could not be opened.
	 */
	public static List<File> findAllFilesByExtension(final File srcDir,
			final String extn) throws IOException {
		ArrayList<File> fileList = new ArrayList<File>();

		if (srcDir.isDirectory()) {
			String[] children = srcDir.list();
			for (int i = 0; i < children.length; i++) {
				fileList.addAll(findAllFilesByExtension(
						new File(srcDir.getAbsolutePath(), children[i]), extn));
			}
		} else if (srcDir.isFile()) {
			String extnWithDot = extn;
			if (!extnWithDot.startsWith(".")) {
				extnWithDot = "." + extnWithDot;
			}
			if (srcDir.getName().endsWith(extnWithDot)) {
				fileList.add(srcDir);
			}
		}
		return fileList;
	}

	/**
	 * Returns first parameter if it is valid; otherwise returns second
	 * parameter.
	 * 
	 * @param data
	 *            data to check
	 * @param defaultValue
	 *            what to return if data is invalid
	 * @return either data if it is valid or default value
	 */
	public static Object getRationalOf(final Object data,
			final Object defaultValue) {
		try {
			if (isNotValid(data)) {
				return defaultValue;
			}
		} catch (RuntimeException e) {
			return defaultValue;
		}
		assert isValid(data) : "data assumed to be valid here";
		return data;
	}

	/**
	 * Returns the collection's size if it is valid; otherwise returns the
	 * second parameter.
	 * 
	 * @param collectionInst
	 *            collection data to check if it is valid
	 * @param defaultValue
	 *            what to return if collection is invalid
	 * @return either size of the collection or default value
	 */
	public static int getRationalOf(final Collection<Object> collectionInst,
			final int defaultValue) {
		try {
			if (isNotValid(collectionInst)) {
				return defaultValue;
			}
		} catch (RuntimeException e) {
			return defaultValue;
		}

		assert isValid(collectionInst) : "collectionInst assumed to be valid here";
		return collectionInst.size();
	}

	/**
	 * Converts milli-seconds to seconds.
	 * 
	 * @param msecs
	 *            smilli-seconds unit
	 * @return seconds unit
	 */
	public static double msToSecs(final double msecs) {
		return msecs / 1000.0d;
	}

	/**
	 * Converts seconds to minutes.
	 * 
	 * @param secs
	 *            seconds unit
	 * @return minutes unit
	 */
	public static double secsToMins(final double secs) {
		return secs / 60.0d;
	}

	/**
	 * Converts milli-seconds to minutes.
	 * 
	 * @param msecs
	 *            milli-seconds unit
	 * @return minutes unit
	 */
	public static double msToMins(final double msecs) {
		final double secsToMins = secsToMins(msToSecs(msecs));

		String curValue = NumberFormat.getInstance(Locale.UK)
				.format(secsToMins);
		return Double.parseDouble(curValue);
	}

	/**
	 * overrides the to string method for any dataTransfer object.
	 * 
	 * @param objName
	 *            class object
	 * @return the string value of the data transfer object.
	 */
	public static String getToString(final Object objName) {
		if (objName == null) {
			throw new NullPointerException("The Source Object is null");
		}
		StringBuilder finalValue = new StringBuilder();
		try {
			Class<?> dummyClass = Class.forName(objName.getClass().getName());

			Field[] declaredFields = dummyClass.getDeclaredFields();
			List<Object> valueList;
			for (Field field : declaredFields) {
				field.setAccessible(true);
				valueList = new ArrayList<Object>();
				String name = field.getName();
				Object objectValue = null;
				Object object = field.get(objName);
				int count = 0;
				if (object != null) {
					objectValue = getStringRepr(object);
					if (objectValue == null) {
						objectValue = "";
					}
					if (count == 0) {
						valueList.add(objectValue);
					}
				}
				StringBuilder val = new StringBuilder("{");
				for (Object object2 : valueList) {
					val.append(object2);
				}
				val.append("}");
				String value = name + ":: " + val.toString() + "\t";
				finalValue.append(value);
			}
		} catch (ClassNotFoundException e1) {
		} catch (IllegalArgumentException e2) {
		} catch (IllegalAccessException e3) {
		}
		return finalValue.toString();
	}

	/**
	 * Returns the smart string representation of the given object.
	 * 
	 * @param object
	 *            object to be converted to string.
	 * @return Object object value.
	 */
	private static String getStringRepr(final Object object) {
		Object retObj = null;
		StringBuffer sb = new StringBuffer();
		boolean isObjAry = false;
		if (object instanceof Object[]) {
			retObj = "";
			Object[] objArray = (Object[]) object;
			int i = 0;
			for (Object object2 : objArray) {
				if (object2 instanceof Object[]) {
					Object[] eachObject = (Object[]) object2;
					retObj = eachObject;
					isObjAry = true;
					break;
				} else {
					i++;

					if (object2 == null) {
						sb.append("Null");
						if (i != objArray.length) {
							sb.append(",");
						}
					} else {
						sb.append(object2.toString());
						if (i != objArray.length) {
							sb.append(",");
						}
					}
				}
			}
			if (!isObjAry) {
				retObj = sb;
			}

		} else

		if (object instanceof int[]) {
			int[] objArray = (int[]) object;
			retObj = XUtil.intArrayToCsv(objArray);
		} else

		if (object instanceof float[]) {
			float[] objArray = (float[]) object;
			retObj = XUtil.floatArrayToCsv(objArray);
		} else

		if (object instanceof String[]) {
			String[] objArray = (String[]) object;
			retObj = XUtil.arrayToCsv(objArray);
		} else

		if (object instanceof double[]) {
			String obj = "";
			double[] objArray = (double[]) object;
			if (objArray != null) {
				for (double doubleObj : objArray) {
					obj = doubleObj + ",";
				}
				int i = obj.length();
				retObj = obj.substring(0, i - 1);
			}
		} else if (object instanceof char[]) {
			String obj = "";
			char[] objArray = (char[]) object;
			if (objArray != null) {
				for (double doubleObj : objArray) {
					obj = doubleObj + ",";
				}
				int i = obj.length();
				retObj = obj.substring(0, i - 1);
			}
		} else {
			retObj = object;
		}

		return retObj.toString();
	}

	/**
	 * Compares the given two object.
	 * 
	 * @param src
	 *            source object.
	 * @param compare
	 *            object to be compared.
	 * @return true if equals else false.
	 */
	public static boolean equals(final Object src, final Object compare) {
		boolean retValue = false;
		boolean check = false;
		try {
			Class srcClass = Class.forName(src.getClass().getName());
			Class compClass = Class.forName(src.getClass().getName());
			if (src.getClass().getName().equals(compare.getClass().getName())) {
				Field[] srcFields = srcClass.getDeclaredFields();
				Field[] compFields = compClass.getDeclaredFields();
				int srchashCode = hashCode(src);
				int comphashCode = hashCode(compare);
				if (srchashCode == comphashCode) {
					if (srcFields.length == compFields.length) {
						for (int index = 0; index < srcFields.length; index++) {
							srcFields[index].setAccessible(true);
							compFields[index].setAccessible(true);
							Object srcFieldValue = srcFields[index].get(src);
							Object compFieldValue = compFields[index]
									.get(compare);
							if (!compFieldValue.equals(srcFieldValue)) {
								check = true;
								break;
							}
						}
						if (!check) {
							retValue = true;
						}
					}
				}
			}
		} catch (ClassNotFoundException e) {
			retValue = false;
		} catch (IllegalArgumentException e) {
			retValue = false;
		} catch (IllegalAccessException e) {
			retValue = false;
		}
		return retValue;
	}

	/**
	 * Returns the hash code of the given object.
	 * 
	 * @param src
	 *            source object.
	 * @return hash code of the object.
	 */
	public static int hashCode(final Object src) {
		if (src == null) {
			throw new NullPointerException("The Source Object is null");
		}
		double d = Math.random();
		double m = Math.random();
		Double dH = new Double(d);
		Double mH = new Double(m);
		int hashCodeValue = dH.intValue();
		int mulValue = mH.intValue();
		try {
			if (src != null) {
				Class srcClass = Class.forName(src.getClass().getName());
				Field[] srcFields = srcClass.getDeclaredFields();
				for (int index = 0; index < srcFields.length; index++) {
					srcFields[index].setAccessible(true);
					Object object = srcFields[index].get(src);
					int hCode = 0;
					if (object != null) {
						hCode = srcFields[index].get(src).hashCode();
					}
					hashCodeValue = mulValue * hashCodeValue + hCode;
				}
			}
		} catch (ClassNotFoundException e) {
			hashCodeValue = 0;
		} catch (IllegalArgumentException e) {
			hashCodeValue = 0;
		} catch (IllegalAccessException e) {
			hashCodeValue = 0;
		}
		return hashCodeValue;
	}

	/**
	 * Returns the caller name of this method's parent method. Format of output
	 * will be: ClassName.methodName()[LineNumber].
	 * 
	 * @return a string with format ClassName.methodName()
	 */
	public static String getCallerInfo() {
		final StackTraceElement[] stackTrace = new IllegalArgumentException()
				.getStackTrace();
		String classNameWoutPkg = null;
		String methodName = null;
		int lineNumber = -1;
		if (stackTrace != null && stackTrace.length > 2) {
			final StackTraceElement ele = stackTrace[2];
			if (ele != null) {
				classNameWoutPkg = ele.getClassName();
				methodName = ele.getMethodName();
				lineNumber = ele.getLineNumber();
				classNameWoutPkg = classNameWoutPkg.substring(classNameWoutPkg
						.lastIndexOf('.') + 1);
			}
		}

		assert classNameWoutPkg != null : "getCallerInfo failed in className";
		assert methodName != null : "getCallerInfo failed in methodName";

		return classNameWoutPkg + "." + methodName + "()[" + lineNumber + "]";

	}

	/**
	 * Checks if param is a valid email address.
	 * 
	 * @param emailAddressArg
	 *            the email address to be checked
	 * @return true, if it is a valid email address
	 */
	public static boolean isValidEmailAddress(final String emailAddressArg) {
		// Set the email pattern string
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		// Match the given string with the pattern
		Matcher m = p.matcher(emailAddressArg);
		// check whether match is found
		return m.matches();
	}

	/**
	 * Prints the Entire Map as a String.
	 * 
	 * @param m
	 *            map to analyze
	 */
	public static void printMap(final Map<Object, Object> m) {
		Map.Entry<Object, Object> entry;
		StringBuffer sb = new StringBuffer();
		for (Iterator<Map.Entry<Object, Object>> it = m.entrySet().iterator(); it
				.hasNext();) {
			entry = it.next();
			sb.append(entry.getKey()).append(": ").append(entry.getValue())
					.append("\n");
		}
	}

	public static void main(String[] args) { // FIXME Just for test
		/*
		 * //system.out.println(tokenMatches(" IA", "IA,UW,SUW"));
		 * //system.out.println(tokenMatches(" IA", "IA"));
		 * //system.out.println(tokenMatches("ll", "hai", "HELLO", "are",
		 * "you"));
		 */
		String logActivity = "Authority Rules Hit SUW~~2 Excused Claims^^UM~~Claim severities equal to $750,000 and less than $1,000,000 in lifetime.``5 or more claims but less than 8 in the last 10 policy years.``Total excused claim indemnity equal to or greater than $50,000 but less than or equal to $1,000,000.^^";
		// SUW~~2 Excused Claims^^UM~~Claim severities equal to $750,000 and
		// less than $1,000,000 in lifetime.``5 or more claims but less than 8
		// in the last 10 policy years.``Total excused claim indemnity equal to
		// or greater than $50,000 but less than or equal to $1,000,000.^^
		logActivity = logActivity.replaceFirst("Authority Rules Hit ", "");
		if (logActivity.startsWith("^^")) {
			StringBuilder builder = new StringBuilder(logActivity);
			builder.delete(0, 2);
			logActivity = builder.toString();
		}
		if (logActivity.endsWith("^^")) {
			StringBuilder builder = new StringBuilder(logActivity);
			builder.delete(logActivity.length() - 2, logActivity.length());
			logActivity = builder.toString();

		}
		logActivity = logActivity.replaceAll("``",
				"</td></tr><tr><td>&nbsp;</td><td>");
		logActivity = logActivity.replaceAll("~~", "</td><td>");
		logActivity = logActivity.replaceAll("\\^\\^", "</td></tr><tr><td>");
		// system.out.println(logActivity);
	}

	/**
	 * Creates a Comma-Seperated Value (CSV) string from the given list.
	 * 
	 * @param srcList
	 *            list of elements.
	 * @return csv of the list elements.
	 */
	public static String listToCsvWithDoubleCodes(
			final List<? extends Object> srcList) {

		StringBuffer csv = new StringBuffer("");

		// if srcList is null, return an empty csv string ("").
		if (srcList != null) {
			// Get iterator
			Iterator<? extends Object> itr = srcList.iterator();
			while (itr.hasNext()) {
				// Take each element from the collection
				String eachElem = itr.next().toString();

				// appending double codes to eachElem
				csv.append("'");
				// append it to the string buffer
				csv.append(eachElem);
				csv.append("'");
				// Add comma only when more elements are there to fetch.
				if (itr.hasNext()) {
					csv.append(",");
				}
			}
		}
		return csv.toString();
	}
	
	
	/**
	 * String for saying key need as the return value
	 */
	public static final String KEY = "Key";
	
	/**
	 * String for saying key need as the return value
	 */
	public static final String VALUE = "Value";
	
	/**
	 * Convenience method to return a List as a delimited (e.g. CSV)
	 * String. E.g. useful for <code>toString()</code> implementations.
	 * @param List 
	 * @param delim delimiter to use (probably a ",")
	 * @param returnValue whether you want the key or value
	 */
	/*
	public static String listToString(final List<? extends Object> srcList,
			String delim, String returnValue) {

		if (CollectionUtils.isEmpty(srcList)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();

		Iterator it = srcList.iterator();
		while (it.hasNext()) {
			Object listItem = it.next();
			//if instance of value mapping
			if (listItem instanceof ValueMapping) {
				if(KEY.equalsIgnoreCase(returnValue)){
					sb.append(((ValueMapping) listItem).getKey());
				}else if(VALUE.equalsIgnoreCase(returnValue)){
					sb.append(((ValueMapping) listItem).getValue());
				}
				
			}
			//if not a instance of value mapping then return to string of the list
			else{
				sb.append(listItem.toString());
			}
			
			//add the delim only if next item is present
			if (it.hasNext()) {
				sb.append(delim);
			}

		}

		return sb.toString();
	}

*/
}

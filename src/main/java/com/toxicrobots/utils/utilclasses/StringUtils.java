
//package com.medpro.common.utils;
package com.toxicrobots.utils.utilclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public final class StringUtils {
	/** Description: The Constant logger. */
	/**
	 * private constructor to prevent instantiation.
	 */
	private StringUtils() {
	}
	/* empty string */
	public static final String EMPTY_STRING = "";
	/* comma delimiter */
	public static final String DELIMITER_COMMA = ",";
	/* space delimiter */
	public static final String DELIMITER_SPACE = " ";
	/* comma new line */
	public static final String DELIMITER_HTML_NEWLINE = "<br>";
	
	/**
	 * String representation of double value.
	 * @param actual double value
	 * @return string representation
	 */
	public static String getStringValue(final double actual) {
		return String.valueOf(actual);
	}
	/**
	 * Double representation of string value.
	 * @param actual string value
	 * @return double representation
	 */
	public static double getDoubleValue(final String actual) {
		double dblValue = 0;
		try {
			dblValue = Double.valueOf(actual);
		} catch (NullPointerException e) {
			dblValue = 0;
		} catch (NumberFormatException e) {
			dblValue = 0;
		}
		return dblValue;
	}
	
	/**
	 * Integer representation of string value.
	 * @param actual string value
	 * @return Integer representation
	 */
	public static int getIntegerValue(final String actual) {
		int intValue = 0;
		try {
			intValue = Integer.valueOf(actual);
		} catch (NullPointerException e) {
			intValue = 0;
		} catch (NumberFormatException e) {
			intValue = 0;
		}
		return intValue;
	}
	/**
	 * Check if the str is empty string.
	 * @param str string value
	 * @return true if str is empty/null, false otherwise.
	 */
	public static boolean isEmpty(final String str) {
		return (str == null || EMPTY_STRING.equals(str.trim()));
	}
	/**
	 * Constructs a list collections with the values delimited by ',' which is passed as a string.
	 * @param commaDelimitedValues string containing values delimited by ','
	 * @return list of strings.
	 */
	public static List<String> getAsList(final String commaDelimitedValues){
        // we need to change this to split(_) method
		List<String> valuesList = null;
		if ( ! isEmpty(commaDelimitedValues)) {
			valuesList = new ArrayList<String>();
			StringTokenizer tokenizer = new StringTokenizer(commaDelimitedValues, DELIMITER_COMMA);
			if (null != tokenizer) {
				while (tokenizer.hasMoreTokens()) {
					valuesList.add(tokenizer.nextToken());
				}
			}
		}
		return valuesList;
	}
}

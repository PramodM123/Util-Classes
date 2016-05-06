
package com.toxicrobots.utils.utilclasses;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



//import com.medpro.common.common.constants.UWRenewalConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class DateUtils.
 */
public class DateUtils /*implements UWRenewalConstants*/{

	
	
	/**
	 * The logger.
	 */
	

	/**
	 * This method calculates the difference between two dates.
	 *
	 * @param fromDate the from date
	 * @param toDate the to date
	 *
	 * @return number of days between the two dates
	 */
	public static long getDaysBetweenDates(java.util.Date fromDate,java.util.Date toDate){
		Calendar tempCal = new GregorianCalendar();
		tempCal.setTime(fromDate);
		Calendar cal1 = new GregorianCalendar();
		cal1.clear();
		cal1.set(Calendar.YEAR, tempCal.get(Calendar.YEAR));
		cal1.set(Calendar.MONTH, tempCal.get(Calendar.MONTH));
		cal1.set(Calendar.DAY_OF_MONTH, tempCal
				.get(Calendar.DAY_OF_MONTH));
		tempCal.setTime(toDate);
		Calendar cal2 = new GregorianCalendar();
		cal2.clear();
		cal2.set(Calendar.YEAR, tempCal.get(Calendar.YEAR));
		cal2.set(Calendar.MONTH, tempCal.get(Calendar.MONTH));
		cal2.set(Calendar.DAY_OF_MONTH, tempCal
				.get(Calendar.DAY_OF_MONTH));

		long days = (cal2.getTime().getTime() - cal1.getTime().getTime()) / (1000 * 60 * 60 * 24);
		
		return days;
	}

	/**
	 * This method calculates System priority.
	 *
	 * @param needByDate the need by date
	 *
	 * @return returns true if (Todays date - needByDate) <= 21 else false
	 */
	public static boolean isSystemPriority(String needByDate) {
		Date fromDate = null;
		Date sysDate = new Date();
		try {
			fromDate = new SimpleDateFormat(DateConverter.DATE_FORMAT_MM_DD_YYYY).parse(needByDate);
		} catch (ParseException e) {
			
		}
		long noOfDays = getDaysBetweenDates(sysDate,fromDate);
		if(noOfDays <= DateConverter.NO_OF_DAYS_NEED_BY_DATE)
			return true;
		else
			return false;
	}

	/**
	 * Convert date.
	 *
	 * @param inDate the in date
	 * @param inFormat the in format
	 * @param outFormat the out format
	 *
	 * @return the string
	 */
	public static String convertDate(String inDate, String inFormat, String outFormat) {
		String outDate = null;
		Date convertedInDate = null;
		//logger.debug("inDate, String inFormat, String outFormat ="+inDate+"="+inFormat+"="+outFormat);
		try {
			convertedInDate = new SimpleDateFormat(inFormat).parse(inDate);
			outDate = new SimpleDateFormat(outFormat).format(convertedInDate);
			//logger.debug("outDate ="+outDate);
		} catch (ParseException e) {
			
		}

		return outDate;
	}

	/**
	 * Convert tibco to java date.
	 *
	 * @param inDate the in date
	 * @param outFormat the out format
	 *
	 * @return the string
	 */
	public static String convertTibcoToJavaDate(String inDate, String outFormat) {
		String outDate = inDate;
		outDate=convertTibcoToJavaDateLatest(inDate,outFormat);
		/*if(null!=inDate&&inDate.indexOf(TIBCO_FORMAT_T)!=-1){
			outDate=convertDate(inDate.substring(0,inDate.indexOf(TIBCO_FORMAT_T) )
					,DATE_FORMAT_YYYY__MM__DD,outFormat);
		}*/
		return outDate;
	}

	/**
	 * Sub string tibco date.
	 *
	 * @param inDate the in date
	 *
	 * @return the string
	 */
	public static String subStringTibcoDate(String inDate) {
		String outDate =inDate;
		if(null != inDate && inDate.length() >= 10){
			outDate=inDate.substring(0,10);
		}
		//logger.debug(":outDate:"+outDate);
		return outDate;
	}

	/**
	 * Convert tibco to java date latest.
	 *
	 * @param inDate the in date
	 * @param outFormat the out format
	 *
	 * @return the string
	 */
	public static String convertTibcoToJavaDateLatest(String inDate, String outFormat) {
		String outDate = inDate;
		if(null != inDate && inDate.length() >= 10){
			outDate=convertDate(inDate.substring(0,10)
					,DateConverter.DATE_FORMAT_YYYY__MM__DD,outFormat);
		}
		return outDate;
	}

	/**
	 * Convert string2 date.
	 *
	 * @param inDate the in date
	 * @param inFormat the in format
	 *
	 * @return the date
	 */
	public static Date convertString2Date(String inDate, String inFormat) {
		Date convertedInDate = null;


		//logger.debug("inDate, String inFormat, String outFormat ="+inDate+"="+inFormat);
		if(null!=inDate && null!=inFormat){
			try {
				convertedInDate = new SimpleDateFormat(inFormat).parse(inDate);
				//logger.debug("convertedInDate ="+convertedInDate);
			} catch (ParseException e) {
				
			}
		}
		//logger.debug(":convertedInDate:"+convertedInDate);
		return convertedInDate;
	}

	/**
	 * Checks if is saturday or sunday.
	 *
	 * @param date the date
	 *
	 * @return true, if is saturday or sunday
	 */
	public static boolean isSaturdayOrSunday(Date date) {
		boolean retBln = false;
		if(null!=date){
			int day = date.getDay();
			if(day ==0 || day ==6){
				retBln =true;
			}
		}
		return retBln;
	}

	/**
	 * Gets the date.
	 *
	 * @param inFormat the in format
	 *
	 * @return the date
	 */
	public static String getDate(String inFormat) {
		String outDate = null;

		SimpleDateFormat format = new SimpleDateFormat(inFormat);
		Calendar calendar = Calendar.getInstance();
		outDate = format.format(calendar.getTime());
		//logger.debug("Today date is: " + outDate);
		return outDate;
	}

	/**
	 * Adds the days to date.
	 *
	 * @param noOfDays the no of days
	 * @param outFormat the out format
	 *
	 * @return the string
	 */
	public static String addDaysToDate(int noOfDays, String outFormat) {
		String outDate = null;
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, noOfDays);
		SimpleDateFormat format = new SimpleDateFormat(outFormat);
		outDate = format.format(calendar.getTime());
		//logger.debug("outDate "+noOfDays +" is: " + outDate);
		return outDate;
	}

	/**
	 * Adds the days to date.
	 *
	 * @param inDate the in date
	 * @param inFormat the in format
	 * @param noOfDays the no of days
	 * @param outFormat the out format
	 * @return the string
	 */
	public static String addDaysToDate(String inDate, String inFormat, int noOfDays, String outFormat) {
		String outDate = null;

		Calendar calendar = dateString2Calendar(inDate,inFormat);
		calendar.add(Calendar.DAY_OF_MONTH, noOfDays);
		SimpleDateFormat format = new SimpleDateFormat(outFormat);
		outDate = format.format(calendar.getTime());
		//logger.debug("outDate "+noOfDays +" is: " + outDate);
		return outDate;
	}
	/**
	 * Adds the days to date.
	 *
	 * @param inDate the in date
	 * @param inFormat the in format
	 * @param noOfDays the no of days
	 * @param outFormat the out format
	 * @return the string
	 */
	public static Date addYearsToDate(Date inDate, int noOfYears) {
		if (inDate != null) {
			Calendar calendar = date2Calendar(inDate);
			calendar.add(Calendar.YEAR, noOfYears);
			return calendar.getTime();
		}
		return null;
	}

	/**
	 * Date string2 calendar.
	 *
	 * @param inDate the in date
	 * @param inFormat the in format
	 *
	 * @return the calendar
	 */
	public static Calendar dateString2Calendar(String inDate, String inFormat) {
		Calendar cal=Calendar.getInstance();
		try {
			
			if(inDate!=null && !inDate.trim().equals("")){
				
			SimpleDateFormat df=new SimpleDateFormat(inFormat);
			Date d1=df.parse(inDate);
			cal.setTime(d1);
			cal.clear(Calendar.ZONE_OFFSET);
			
			}
			
		} catch (ParseException e) {
		}
		return cal;
	}

	/**
	 * Date2 calendar.
	 *
	 * @param inDate the in date
	 * @return the calendar
	 */
	public static Calendar date2Calendar(Date inDate) {
		Calendar cal=Calendar.getInstance();
		cal.setTime(inDate);
		cal.clear(Calendar.ZONE_OFFSET);
		return cal;
	}
	/**
	 * Date to string.
	 *
	 * @param date the date
	 * @param outFormat the out format
	 *
	 * @return the string
	 */
	public static String dateToString(Date date, String outFormat) {
		String strdate=null;
		if(null!=date){
			try {
				SimpleDateFormat df=new SimpleDateFormat(outFormat);
				String d1=df.format(date);
				strdate=d1;

			}catch ( Exception e) {
			}
		}
		return strdate;
	}

	/**
	 * Cal to string.
	 *
	 * @param cal the cal
	 * @param outFormat the out format
	 * @return the string
	 */
	public static String calToString(Calendar cal, String outFormat) {
		String strdate=null;
		if(null!=cal){
			Date date = cal.getTime();
			try {
				SimpleDateFormat df=new SimpleDateFormat(outFormat);
				String d1=df.format(date);
				strdate=d1;

			}catch ( Exception e) {
			}
		}
		return strdate;
	}

	/**
	 * Greater than.
	 *
	 * @param firstDate the first date
	 * @param secondDate the second date
	 * @param inFormat the in format
	 *
	 * @return true, if greater than
	 */
	public static boolean greaterThan(String firstDate, String secondDate, String inFormat) {
		boolean result = false;
		if(null!=firstDate&&null!=secondDate&&null!=inFormat){
			//returns true if the first date is after second date
			SimpleDateFormat df = new SimpleDateFormat(inFormat);
			try {
				// Get Date 1
				Date fDate = df.parse(firstDate);

				// Get Date 2
				Date sDate = df.parse(secondDate);

				if (fDate.after(sDate))
					result = true;
			} catch (ParseException e) {
			}
		}
		return result;
	}

	/**
	 * Less than.
	 *
	 * @param firstDate the first date
	 * @param secondDate the second date
	 * @param inFormat the in format
	 *
	 * @return true, if less than
	 */
	public static boolean lessThan(String firstDate, String secondDate, String inFormat) {
		boolean result = false;

		//returns true if the first date is before second date
		SimpleDateFormat df = new SimpleDateFormat(inFormat);

		try {
			// Get Date 1
			Date fDate = df.parse(firstDate);

			// Get Date 2
			Date sDate = df.parse(secondDate);

			if (fDate.before(sDate))
				result = true;
		} catch (ParseException e) {
		}
		return result;
	}

	/**
	 * Greater than.
	 *
	 * @param firstDate the first date
	 * @param secondDate the second date
	 *
	 * @return true, if greater than
	 */
	public static boolean greaterThan(Date firstDate, Date secondDate) {
		boolean result = false;

		if (firstDate.after(secondDate)) {
			result = true;
		}

		//logger.debug(":firstDate:"+firstDate);
		return result;
	}



	/**
	 * Greater than or equal.
	 *
	 * @param firstDate the first date
	 * @param secondDate the second date
	 * @return true, if successful
	 */
	public static boolean greaterThanOrEqual(Date firstDate, Date secondDate) {
		boolean result = false;

		//returns true if the first date is after second date


	int firstValue=firstDate.getDate();
	int secondValue=secondDate.getDate();
	int firstYear=firstDate.getYear();
	int secondYear=secondDate.getYear();
	int firstMonth=firstDate.getMonth();
	int secondMonth=secondDate.getMonth();

	if(firstValue==secondValue && firstYear==secondYear && firstMonth==secondMonth ){
		
		return true;
	}

		if (firstDate.after(secondDate)) {
			result = true;
		}

		//logger.debug(":firstDate:"+firstDate);
		return result;
	}


	/**
	 * Less than.
	 *
	 * @param firstDate the first date
	 * @param secondDate the second date
	 *
	 * @return true, if less than
	 */
	public static boolean lessThan(Date firstDate, Date secondDate) {
		boolean result = false;

		//returns true if the first date is after second date
		if (firstDate.after(secondDate))
			result = true;
		return result;
	}
	
	/**
	 * Less than or equal.
	 *
	 * @param firstDate the first date
	 * @param secondDate the second date
	 *
	 * @return true, if less than or equal
	 */
	public static boolean greaterthanOrEqual(Date firstDate, Date secondDate) {
		boolean result = false;
		int firstValue=firstDate.getDate();
		int secondValue=secondDate.getDate();

		if(firstValue==secondValue){
			return true;
		}
		//returns true if the first date is after second date
		if (firstDate.after(secondDate))
			result = true;
		return result;
	}

	/**
	 * This method calculates the difference between two dates.
	 *
	 * @param cal1 the date1
	 * @param cal2 the date2
	 *
	 * @return number of years between the two dates
	 */
	public static long getYearsBetweenDates(Calendar cal1,Calendar cal2){
		long years = 1000;
		if(cal1 != null && cal2 != null) {
			try {
				years = (((cal2.getTime().getTime() - cal1.getTime().getTime()) + ((1000 * 60 * 60 * 24) * 2)) / (1000 * 60 * 60 * 24))/365;
			} catch(Exception e) {
			}
		}
		return years;
	}

	/**
	 * The main method.
	 *
	 * @param st the arguments
	 */
	public static void main (String st[]){
		//String inDate = "12/25/2007";
		//String inDate = "Fri Jul 04 00:00:00 IST 2008";
		String inDate = "2008-05-01T00:00:00+05:30";
		//String inFormat= "MM/dd/yyyy";
		String inFormat= "EEE MMM dd HH:mm:ss zzz yyyy";

		String outFormat = "dd/MM/yyyy";
		String outFormat1 = "yyyy-MM-dd";
		//String outDate = convertDate(inDate, "yyyy-MM-dd HH:mm:ssZ", outFormat);
		//logger.debug("outDate ="+outDate);
		Calendar c = dateString2Calendar("Tue Jan 18 00:00:00 IST 2008", inFormat);

	}

 	/**
 	 * Now.
 	 *
 	 * @param dateFormat the date format
 	 * @return the string
 	 */
 	public static String now(String dateFormat) {
		    Calendar cal = Calendar.getInstance();
		    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		    return sdf.format(cal.getTime());

		  }


	/**
	 * Validate year.
	 *
	 * @param date the date
	 *
	 * @return true, if successful
	 */
	public static boolean validateYear(Date date){
		if(null == date)
		{
			return true;
		}
		else
		{
			Calendar newDate = dateString2Calendar(date.toString(),
				DateConverter.DATE_FORMAT_EEE_MMM_dd_HH_mm_ss_zzz_yyyy);

			Calendar checkStart = Calendar.getInstance();
			checkStart.set(99,11,31);

			Calendar checkEnd = Calendar.getInstance();
			checkEnd.set(1900,01,01);

			Calendar checkLimit = Calendar.getInstance();
			checkLimit.set(9999,11,31);

			if(null!=newDate &&
				((newDate.after(checkStart) && newDate.before(checkEnd)) ||
				(newDate.after(checkLimit))))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Convert year.
	 *
	 * @param date the date
	 *
	 * @return the date
	 */
	public static Date convertYear(Date date) {

		if(null == date)
		{
			return date;
		}
		else
		{
			Calendar newDate = dateString2Calendar(date.toString(),
				DateConverter.DATE_FORMAT_EEE_MMM_dd_HH_mm_ss_zzz_yyyy);

			//100 - 999
			//
			Calendar checkStart = Calendar.getInstance();
			checkStart.set(100,01,01);

			Calendar checkEnd = Calendar.getInstance();
			checkEnd.set(1000,01,01);

			if(null!=newDate) {
				if (newDate.before(checkEnd)) {
					if(newDate.before(checkStart)) {
						int year = newDate.get(Calendar.YEAR);
						if(year >= 0 && year <= 49) {
							newDate.add(Calendar.YEAR, 2000);
						}else if(year >= 50 && year <100) {
							newDate.add(Calendar.YEAR, 1900);
						}
					}
				}
			}
			return newDate.getTime();
		}
	}
	/**
	 * Convert Date To String
	 *
	 * @param inDate the in date
	 * @param outFormat the out format
	 *
	 * @return the string
	 */
	public static String convertDateToJavaString(Date inDate, String outFormat) {

		String outDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat(outFormat);
		outDate = formatter.format(inDate);
		return outDate;
	}
	
	/**
	 * Calendar dates equal.
	 *
	 * @param calDateOne the cal date one
	 * @param calDateTwo the cal date two
	 * @return true, if successful
	 */
	public static boolean calendarDatesEqual(Calendar calDateOne,Calendar calDateTwo){
		if(null==calDateOne && null==calDateTwo ){
			return true;
		}
		if(null==calDateOne || null==calDateTwo ){
			return false;
		}
		// here both are not NULL
		if(calDateOne.compareTo(calDateTwo)==0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * Returns true when the first date is strictly less the the second date 
	 * when the first date is equal to second date it returns false
	 *
	 * @param firstDate the first date
	 * @param secondDate the second date
	 *
	 * @return true, if less than
	 */
	public static boolean lessThanButNotEqual(Date firstDate, Date secondDate) {
		boolean result = false;

		//returns true if the first date is before second date
		if (firstDate.before(secondDate))
			result = true;
		
		//returns false if the first date and second date are on the same day
		if(org.apache.commons.lang.time.DateUtils.isSameDay(firstDate, secondDate))
			result=false;
		
		
		return result;
	}
	
	/**
	 * 
	 * Returns true when the first date is strictly less than or equal to the the second date 
	 *
	 * @param firstDate the first date
	 * @param secondDate the second date
	 *
	 * @return true, if less than or equal to
	 */
	public static boolean lessThanEqualTo(Date firstDate, Date secondDate) {
		boolean result = false;

		//returns true if the first date is before second date
		if (firstDate.before(secondDate))
			result = true;
		
		//returns true if the first date and second date are on the same day
		if(org.apache.commons.lang.time.DateUtils.isSameDay(firstDate, secondDate))
			result=true;
		
		
		return result;
	}
	
	//
	public static Date calendar2Date(Calendar cal){
		// returns the date object represented by Calendar object
		Date date = cal.getTime();
		// if you want to a get tomorrow's date and calendar object, do the following
		/*cal.add(Calendar.DATE, 1);
		date = cal.getTime();*/
		return date;
		
	} 
	
	/** strInvalideDate */
	/**  added for renewal to store entered value for date*/	
	public static String strInvalideDate = "";
	//DRW-757 & 758
	public static int comparingDate(boolean descending, String one,String two){
			Date oneDate=null;
			Date twoDate=null;
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
			        try {

			        	// Ranga added .trim().equals("") on 2-10-2012
			        	if((null!=one && !one.trim().equals("")) 
			        			&& (null!=two && !two.trim().equals(""))){
			        	oneDate = df.parse(one);
			        	twoDate = df.parse(two);
			        	}else{

			        		// return 0 if both are null
			        		return 0;
			        	}
			        }catch (Exception e) {
						e.printStackTrace();
					}
			    //  1. positive � o1 is greater than o2
				//	2. zero � o1 equals to o2
				//	3. negative � o1 is less than o1
			if(null==oneDate ||null==twoDate){return 0;}
			int result=0;
			if(descending){

				if(oneDate.getTime()>twoDate.getTime())
					result=1;
				else if(oneDate.getTime()<twoDate.getTime())
					result=-1;
				else
					result=0;

			}else{
				if(twoDate.getTime()>oneDate.getTime())
					result=1;
				else if(twoDate.getTime()<oneDate.getTime())
					result=-1;
				else
					result=0;
			}
			return result;


		}
		
	/**
	 * Adds the days to date.
	 *
	 * @param noOfDays the no of days
	 * @param outFormat the out format
	 *
	 * @return the string
	 */
	public static void addMinutesToDate(Calendar calendar, int minutes) {		
		calendar.add(Calendar.MINUTE, minutes);		
		}		

}
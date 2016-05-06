
package com.toxicrobots.utils.utilclasses;

import java.util.Calendar;
import java.util.Date;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;

//import com.medpro.common.common.constants.UWRenewalConstants;


 
public class DateConverter extends DateTimeConverter /*implements UWRenewalConstants */{
	 
	
	public static final String DATE_FORMAT_MM_DD_YYYY = null;
	public static final long NO_OF_DAYS_NEED_BY_DATE = 0;
	public static final String DATE_FORMAT_EEE_MMM_dd_HH_mm_ss_zzz_yyyy = null;
	public static final String DATE_FORMAT_YYYY__MM__DD = null;

	
	public DateConverter() {
		this.setPattern(DATE_FORMAT_MM_DD_YYYY);
		this.setTimeZone(Calendar.getInstance().getTimeZone());
	}
	
	public Object getAsObject(FacesContext facesContext, 
		UIComponent uiComponent, 
		String param) {
		Date date = null;
		// added this static variable to 
		// to store entered date for current field
		DateUtils.strInvalideDate = param;
		
		if(null == param || param.trim().equals("")){
			
		}else {
			try{
				/*
				 * Finding if year 00 or 0000 is present and converted to 2000.
				 * This is done to prevent converter exception in the next step
				 */
				String initSub = (param.substring(0,param.lastIndexOf("/")+1));
				String sub = param.substring(param.lastIndexOf("/")+1, param.length());
				if(null != sub && (sub.trim().equals("00") || sub.trim().equals("0000"))) {
					param = initSub.concat("2000");
				}
				
				/*
				 * Calling getAsObject of parent to validate the input date.
				 */
				Date newDate = (Date)super.getAsObject(facesContext, uiComponent, param);

				/*
				 * Convert validated Date to string. 
				 */
				String checkStr = DateUtils.dateToString(newDate, DATE_FORMAT_MM_DD_YYYY);
				
				/*
				 * Check if 2 digit year is entered in UI. Throw converter exception if some
				 * invalid character was entered in the year column and the parser
				 * automatically converted to a valid date. 
				 * Eg: 20s09 will be converted to 0020. 
				 */
				if(null != checkStr) {
					String validatedYear  = checkStr.substring(checkStr.lastIndexOf("/")+1, checkStr.length());
					String paramYear  = param.substring(param.lastIndexOf("/")+1, param.length());
					int yrLength  = paramYear.length();
					if(yrLength != 2  && null != paramYear && 
						!paramYear.trim().equalsIgnoreCase(validatedYear)) { 
							throw new ConverterException();
					}
				}
				
				/*
				 * Validate the entered year (If it falls in 2 digit or 4 digit year)
				 */
				if(!DateUtils.validateYear(newDate)){
					
					/*
					 * If not valid, then set the old value to the component.
					 * Set the converter message boolean to true. 
					 * This will internally invoke the error popup to be displayed.
					 */
					
					ValueExpression valueExp = uiComponent.getValueExpression("converterMessage");
					if(null != valueExp){
						valueExp.setValue(facesContext.getELContext(),true);
					}
					date = (Date)uiComponent.getAttributes().get("value");
				} else {
					/*
					 * If date is valid and is 2 digit,
					 * 01 - 49 ---- Changed to 21st century.
					 * 50 - 99 ---- Changed to 20th century.
					 */
					date = DateUtils.convertString2Date(param, DATE_FORMAT_MM_DD_YYYY);
					date = DateUtils.convertYear(date);
				}
			}catch(ConverterException e)
			{
				/*
				 * If date is not valid, the old value is set back to the component.
				 * Set the converter message boolean to true. 
				 * This will internally invoke the error popup to be displayed.
				 */
				
				ValueExpression valueExp = uiComponent.getValueExpression("converterMessage");
				if(null != valueExp){
					valueExp.setValue(facesContext.getELContext(),true);
				}
				date = (Date)uiComponent.getAttributes().get("value");
				
			}
		}
		return date;
	}

public String getAsString(FacesContext facesContext, 
		UIComponent uiComponent, 
		Object obj) {
	
	return DateUtils.dateToString((Date)obj, DATE_FORMAT_MM_DD_YYYY);
}
}
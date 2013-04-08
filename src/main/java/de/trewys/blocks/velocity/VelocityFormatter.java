/**
 * Projekt: wg-mates-blocks
 * 
 * $RCSfile: VelocityFormatter.java,v $
 * 
 * @version $Revision: 1.1 $
 * @author  $Author: og $
 *          trewys GmbH
 *          www.trewys.de
 * 
 * $Log: VelocityFormatter.java,v $
 * Revision 1.1  2012-06-22 09:21:43  og
 * *** empty log message ***
 *
 * Revision 1.2  2012-03-15 16:38:51  pvo
 * *** empty log message ***
 *
 * Revision 1.1  2012-03-12 17:36:59  sg
 * - velocity
 *
 * Revision 1.1  2012-03-06 08:02:23  og
 * *** empty log message ***
 *
 *                                      
 */
package de.trewys.blocks.velocity;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author og
 *
 */
public class VelocityFormatter {
	
	private DateFormat defaultDateFormat = new SimpleDateFormat(
			"dd.MM.yyyy");
	
	private DateFormat defaultDateTimeFormat = new SimpleDateFormat(
			"dd.MM.yyyy HH:mm");

//	private DateFormat defaultTimeFormat = new SimpleDateFormat(
//			"HH:mm");

	private NumberFormat defaultIntFormat = new DecimalFormat("###,##0", DecimalFormatSymbols.getInstance(Locale.GERMAN));
//	private NumberFormat defaultDoubleFormat = new DecimalFormat("###,##0.00", DecimalFormatSymbols.getInstance(Locale.GERMAN));
	
	public String formatDate(Date date) {
		if (date == null)
			return "";
		return defaultDateFormat.format(date);
	}

	public String formatDateTime(Date date) {
		if (date == null)
			return "";
		return defaultDateTimeFormat.format(date);
	}

	public String formatInt(Number number) {
		if (number == null)
			return "";
		return defaultIntFormat.format(number);
	}
}

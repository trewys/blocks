/*
 * Copyright 2013 trewys GmbH 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
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

package leandroportfolio.league.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {
	public static String getFormatedDate(long l) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(l);
		Date d = c.getTime();
		String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()). format(d);
		return date;
	}
}

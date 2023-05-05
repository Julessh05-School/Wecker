package fachkonzept;

import java.util.Calendar;

public class AktuelleZeit extends Uhrzeit
{
	public int liesMinuten()
	{
		final Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MINUTE);
	}
	
	public int liesSekunden()
	{
		final Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.SECOND);
	}

	public int liesStunden()
	{
		final Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
}

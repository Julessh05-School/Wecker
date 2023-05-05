package fachkonzept;

import java.util.Timer;
import java.util.TimerTask;

import steuerung.Steuerung;

public class Uhrwerk
{
	private final Steuerung dieSteuerung;
	private final AktuelleZeit dieAktuelleZeit;
	private final Weckzeit dieWeckzeit;
	
	private boolean eingeschaltetWeckfunktion;
	
	public Uhrwerk(Steuerung pSteuerung)
	{
		dieSteuerung = pSteuerung;
		dieAktuelleZeit = new AktuelleZeit();
		dieWeckzeit = new Weckzeit();
		eingeschaltetWeckfunktion = false;
		
		final Timer timer = new Timer();
		final long delay = 1000;
		final long frequency = 1000;
		timer.scheduleAtFixedRate(new TimerTask()
		{
			public void run()
			{
				verarbeiteSekundenimpuls();
			}
		}, delay, frequency);
	}
	
	public void inkrementiereWeckzeitMinuten()
	{
		dieWeckzeit.inkrementiereMinuten();
	}
	
	public void inkrementiereWeckzeitStunden()
	{
		dieWeckzeit.inkrementiereStunden();
	}
	
	public int liesAktuelleZeitMinuten()
	{
		return dieAktuelleZeit.liesMinuten();
	}
	
	public int liesAktuelleZeitStunden()
	{
		return dieAktuelleZeit.liesStunden();
	}
	
	public boolean istWeckfunktionEingeschaltet()
	{
		return eingeschaltetWeckfunktion;
	}
	
	public int liesWeckzeitMinuten()
	{
		return dieWeckzeit.liesMinuten();
	}
	
	public int liesWeckzeitStunden()
	{
		return dieWeckzeit.liesStunden();
	}
	
	private void loeseAlarmAus()
	{
		dieSteuerung.ausgeloestAlarm();
	}
	
	private void pruefeWeckzeit()
	{
		if (dieAktuelleZeit.liesStunden() == dieWeckzeit.liesStunden()
				&& dieAktuelleZeit.liesMinuten() == dieWeckzeit.liesMinuten()
				&& dieAktuelleZeit.liesSekunden() == 0)
		{
			loeseAlarmAus();
		}
	}

	public void schalteAusWeckfunktion()
	{
		eingeschaltetWeckfunktion = false;
	}
	
	public void schalteEinWeckfunktion()
	{
		eingeschaltetWeckfunktion = true;
	}
	
	private void verarbeiteSekundenimpuls()
	{
		dieSteuerung.geaendertAktuelleZeit();
		if (eingeschaltetWeckfunktion)
		{
			pruefeWeckzeit();
		}
	}
}

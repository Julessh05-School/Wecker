package steuerung.zustaende;

import steuerung.Steuerung;

public class AngezeigtAktuelleZeit extends Zustand
{
	public AngezeigtAktuelleZeit(Steuerung pSteuerung)
	{
		super(pSteuerung);
		entry();
	}

	public void ausgeloestAlarm()
	{
		exit();
		dieSteuerung.setzeZustand(new Alarm(dieSteuerung));
	}

	private void entry()
	{
		zeigeAktuelleZeit();
	}
	
	private void exit()
	{
		// Ereignis in diesem Zustand nicht definiert
	}

	public void geaendertAktuelleZeit()
	{
		exit();
		dieSteuerung.setzeZustand(new AngezeigtAktuelleZeit(dieSteuerung));
	}

	public void gedruecktMode()
	{
		exit();
		dieSteuerung.setzeZustand(new EinstellbarWeckzeitStunden(dieSteuerung));
	}

	public void gedruecktSet()
	{
		// Ereignis in diesem Zustand nicht definiert
	}

	private void zeigeAktuelleZeit()
	{
		dieSteuerung.zeige('A', false, false, false);
	}
}

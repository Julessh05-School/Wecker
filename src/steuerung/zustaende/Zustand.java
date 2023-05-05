package steuerung.zustaende;

import steuerung.Steuerung;

public abstract class Zustand
{
	protected Steuerung dieSteuerung;

	public Zustand(Steuerung pSteuerung)
	{
		dieSteuerung = pSteuerung;
	}

	public abstract void ausgeloestAlarm();
	public abstract void geaendertAktuelleZeit();
	public abstract void gedruecktMode();
	public abstract void gedruecktSet();
}

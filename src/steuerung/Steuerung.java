package steuerung;

import fachkonzept.Uhrwerk;
import benutzerschnittstelle.Benutzerschnittstelle;
import steuerung.zustaende.AngezeigtAktuelleZeit;
import steuerung.zustaende.Zustand;

public final class Steuerung
{
	private final Benutzerschnittstelle dieBenutzerschnittstelle;
	private final Uhrwerk dasUhrwerk;
	private Zustand derZustand;

	public Steuerung(Benutzerschnittstelle pBenutzerschnittstelle)
	{
		dieBenutzerschnittstelle = pBenutzerschnittstelle;
		dasUhrwerk = new Uhrwerk(this);
		derZustand = new AngezeigtAktuelleZeit(this);
	}

	public void ausgeloestAlarm()
	{
		derZustand.ausgeloestAlarm();
	}

	public void geaendertAktuelleZeit()
	{
		derZustand.geaendertAktuelleZeit();
	}

	public void gedruecktMode()
	{
		derZustand.gedruecktMode();
	}

	public void gedruecktSet()
	{
		derZustand.gedruecktSet();
	}

	public void inkrementiereWeckzeitMinuten()
	{
		dasUhrwerk.inkrementiereWeckzeitMinuten();
	}

	public void inkrementiereWeckzeitStunden()
	{
		dasUhrwerk.inkrementiereWeckzeitStunden();
	}

	public boolean istWeckfunktionEingeschaltet()
	{
		return dasUhrwerk.istWeckfunktionEingeschaltet();
	}

	public void schalteAusAlarm()
	{
		dieBenutzerschnittstelle.schalteAusAlarm();
	}

	public void schalteAusWeckfunktion()
	{
		dasUhrwerk.schalteAusWeckfunktion();
	}

	public void schalteEinAlarm()
	{
		dieBenutzerschnittstelle.schalteEinAlarm();
	}

	public void schalteEinWeckfunktion()
	{
		dasUhrwerk.schalteEinWeckfunktion();
	}

	public void setzeZustand(Zustand pZustand)
	{
		derZustand = pZustand;
	}

	public void zeige(char pZeitart, boolean pEinstellbarStunden,
			boolean pEinstellbarMinuten, boolean pEinstellbarWeckfunktion)
	{
		dieBenutzerschnittstelle.zeigeZeitart(pZeitart);
		int stunden = 0;
		int minuten = 0;
		switch (pZeitart) {
			case 'A' -> {
				stunden = dasUhrwerk.liesAktuelleZeitStunden();
				minuten = dasUhrwerk.liesAktuelleZeitMinuten();
			}
			case 'W' -> {
				stunden = dasUhrwerk.liesWeckzeitStunden();
				minuten = dasUhrwerk.liesWeckzeitMinuten();
			}
		}
		dieBenutzerschnittstelle.zeigeStunden(stunden, pEinstellbarStunden);
		dieBenutzerschnittstelle.zeigeMinuten(minuten, pEinstellbarMinuten);
		dieBenutzerschnittstelle.zeigeWeckfunktion(
				dasUhrwerk.istWeckfunktionEingeschaltet(),
				pEinstellbarWeckfunktion);
	}
}

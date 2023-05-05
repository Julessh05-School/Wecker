package steuerung.zustaende;

import steuerung.Steuerung;

public final class EinstellbarWeckzeitMinuten extends Zustand {
    public EinstellbarWeckzeitMinuten(Steuerung pSteuerung) {
        super(pSteuerung);
        entry();
    }

    @Override
    public void ausgeloestAlarm() {
        exit();
        dieSteuerung.setzeZustand(new Alarm(dieSteuerung));
        dieSteuerung.schalteEinAlarm();
        dieSteuerung.ausgeloestAlarm();
    }

    @Override
    public void geaendertAktuelleZeit() {
        // Zustand nicht definiert
    }

    @Override
    public void gedruecktMode() {
        exit();
        dieSteuerung.setzeZustand(new EinstellbarWeckfunktion(dieSteuerung));
    }

    @Override
    public void gedruecktSet() {
        dieSteuerung.inkrementiereWeckzeitMinuten();
        entry();
    }

    private void entry() {
        dieSteuerung.zeige('W', false, true, false);
    }

    private void exit() {
        dieSteuerung.zeige('A', false, false, false);
    }
}

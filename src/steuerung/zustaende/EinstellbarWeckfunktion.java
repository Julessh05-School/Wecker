package steuerung.zustaende;

import steuerung.Steuerung;

public final class EinstellbarWeckfunktion extends Zustand {
    public EinstellbarWeckfunktion(Steuerung pSteuerung) {
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
        dieSteuerung.setzeZustand(new AngezeigtAktuelleZeit(dieSteuerung));
    }

    @Override
    public void gedruecktSet() {
        if (dieSteuerung.istWeckfunktionEingeschaltet()) {
            dieSteuerung.schalteAusWeckfunktion();
        } else {
            dieSteuerung.schalteEinWeckfunktion();
        }
        entry();
    }

    private void entry() {
        dieSteuerung.zeige('W', false, false, true);
    }

    private void exit() {
        dieSteuerung.zeige('A', false, false, false);
    }
}

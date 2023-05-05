package steuerung.zustaende;

import steuerung.Steuerung;

public final class Alarm extends Zustand {
    public Alarm(Steuerung pSteuerung) {
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
    }

    @Override
    public void gedruecktMode() {
        exit();
    }

    @Override
    public void gedruecktSet() {
        exit();
    }

    private void entry() {
        dieSteuerung.schalteEinAlarm();
        dieSteuerung.zeige('A', false, false, false);
    }

    private void exit() {
        dieSteuerung.schalteAusAlarm();
        dieSteuerung.setzeZustand(new AngezeigtAktuelleZeit(dieSteuerung));
    }
}

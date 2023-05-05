package steuerung.zustaende;

import steuerung.Steuerung;

public final class EinstellbarWeckzeitStunden extends Zustand{
    public EinstellbarWeckzeitStunden(Steuerung pSteuerung) {
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
        dieSteuerung.setzeZustand(new EinstellbarWeckzeitMinuten(dieSteuerung));
    }

    @Override
    public void gedruecktSet() {
        dieSteuerung.inkrementiereWeckzeitStunden();
        entry();
    }

    private void entry() {
        dieSteuerung.zeige('W', true, false, false);
    }

    private void exit() {
        dieSteuerung.zeige('A', false, false, false);
    }
}

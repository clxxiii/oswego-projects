package mmw;

import note.SNote;

public class TheLick {
    public static void main(String[] args) {
        SNote note = new SNote();
        note.rp();
        note.text();
        note.play();
        note.rp(); note.play();
        note.rp(); note.play();
        note.rp(); note.play();
        note.x2(); note.lp(2); note.play();
        note.s2(); note.lp(2); note.play();
        note.rp(); note.play();
        note.untext();
    }
}

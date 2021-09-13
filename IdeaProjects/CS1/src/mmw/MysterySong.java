/*
 * Uses an SNote to play a song
 */
package mmw;
import note.SNote;

public class MysterySong {
    public static void main(String[] args) {
        SNote note = new SNote();
        note.text();
        note.play();
        note.rp(); note.play();
        note.rp(); note.play();
        note.lp(2);note.play();
        note.play();
        note.rp(); note.play();
        note.rp(); note.play();
        note.lp(2); note.play();
        note.rp(2); note.play();
        note.rp(); note.play();
        note.rp(); note.x2(); note.play(); note.s2();
        note.lp(2); note.play();
        note.rp(); note.play();
        note.rp(); note.x2(); note.play(); note.s2();
        note.untext();
    }
}

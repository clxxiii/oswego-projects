/*
 * The Pitch class models the pitch of a note in a manner that will facilitate
 * the chromesthetic processing of the pitch. A Pitch object will have five
 * properties:
 * - String name | ABC notation pitch name
 * - SPainter painter | the painter agent
 * - SNote note | a note that will be set to the pitch corresponding to the
 * ABS notation pitch name
 * - SRectangle box | an SRectangle object that will chromesthetically
 * represent the pitch.
 */
package chromesthesia0;

import note.SNote;
import painter.SPainter;
import shapes.SRectangle;

import java.awt.*;

public class Pitch {

    // PIVs
    private String abcName;
    private SPainter painter;
    private SRectangle box;
    private SNote note;
    private Color color;

    public Pitch(String abcName, SPainter painter) {
        this.abcName = abcName;
        this.painter = painter;
        this.box = new SRectangle(painter.painterHeight - 50,painter.painterWidth - 50);
        this.note = createNoteForThisPitch(abcName);
        this.color = getPitchClassColor(abcName.substring(0,1).toUpperCase());
    }

    @Override
    public String toString() {
        return "[ " + abcName + " | " + note.degree() + " | " + color + " ]";
    }

    public String abcName() {
        return abcName;
    }

    private SNote createNoteForThisPitch(String abcPitchClassName) {
        SNote note = new SNote();
        if (abcPitchClassName.equals("C")) {
            // nothing to do
        } else if (abcPitchClassName.equals("C,")) {
            note.lp(7);
        } else if (abcPitchClassName.equals("c")) {
            note.rp(7);
        } else if (abcPitchClassName.equals("D")) {
            note.rp(1);
        } else if (abcPitchClassName.equals("D,")) {
            note.lp(6);
        } else if (abcPitchClassName.equals("d")) {
            note.rp(8);
        } else if (abcPitchClassName.equals("E")) {
            note.rp(2);
        } else if (abcPitchClassName.equals("E,")) {
            note.lp(5);
        } else if (abcPitchClassName.equals("e")) {
            note.rp(9);
        }
        return note;
    }

    private Color getPitchClassColor(String letter) {
        if ( letter.equalsIgnoreCase("C") ) {
            return Color.blue;
        }
        else if ( letter.equalsIgnoreCase("D") ) {
            return Color.green;
        }
        else if ( letter.equalsIgnoreCase("E") ) {
            return new Color(127,0,127);
        }
        return Color.black;
    }

    public void play(String d) {
        painter.setColor(color);
        painter.paint(box);
        painter.setColor(randomColor());
        painter.draw(box);
        if ( d.equals("1") ) {
            note.play();
        }
    }

    private static Color randomColor() {
        int rv = (int) (Math.random()*256);
        int gv = (int) (Math.random()*256);
        int bv = (int) (Math.random()*256);
        return new Color(rv, gv, bv);
    }
}

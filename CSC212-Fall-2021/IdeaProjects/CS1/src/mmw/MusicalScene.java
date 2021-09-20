package mmw;

import composer.SComposer;

public class MusicalScene {
    public static void main(String[] args) {
        SComposer c = new SComposer();
        c.text();
        c.mms_86_HillStones();
        c.rp(); c.mms_86_HillStones();
        c.lp(); c.mms_87_Stroll();
        c.mms_87_Stagger();
        c.mms_85_StrollDown();
    }
}

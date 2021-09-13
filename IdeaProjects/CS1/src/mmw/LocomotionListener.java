package mmw;

import composer.SComposer;

public class LocomotionListener {
    public static void main(String[] args) {
        SComposer c = new SComposer();
        c.text();
        System.out.println("c.mms_31_JSB_M1() ..."); c.mms_31_JSB_M1(); space(c);
        System.out.println("c.mms_33_JSB_M2() ..."); c.mms_33_JSB_M2(); space(c);
        System.out.println("c.mms_33_JSB_M1() ..."); c.mms_33_JSB_M3(); space(c);
        System.out.println("c.mms_33_JSB_M1() ..."); c.mms_33_JSB_M4(); space(c);
        System.out.println("c.mms_33_JSB_M5() ..."); c.mms_33_JSB_M5(); space(c);
        System.out.println("c.mms_34_JSB_M6() ..."); c.mms_34_JSB_M6(); space(c);
        System.out.println("c.mms_34_JSB_M7() ..."); c.mms_34_JSB_M7(); space(c);
        System.out.println("c.mms_34_JSB_M8() ..."); c.mms_34_JSB_M8(); space(c);
        System.out.println("c.mms_35_JSB_M9() ..."); c.mms_35_JSB_M9(); space(c);
        System.out.println("c.mms_35_JSB_M10() ..."); c.mms_35_JSB_M10(); space(c);
        System.out.println("c.mms_35_JSB_M11() ..."); c.mms_35_JSB_M11(); space(c);
        System.out.println("c.mms_35_JSB_M12() ..."); c.mms_35_JSB_M12(); space(c);
        System.out.println("c.mms_35_JSB_M13() ..."); c.mms_35_JSB_M13(); space(c);
        System.out.println("c.mms_36_JSB_M14() ..."); c.mms_36_JSB_M14(); space(c);
        System.out.println("c.mms_36_JSB_M15() ..."); c.mms_36_JSB_M15(); space(c);
        System.out.println("c.mms_85_HillFlat() ..."); c.mms_85_HillFlat(); space(c);
        System.out.println("c.mms_86_HillStones() ..."); c.mms_86_HillStones(); space(c);
        System.out.println("c.mms_87_Hill() ..."); c.mms_87_Hill(); space(c);
        System.out.println("c.mms_88_Hills() ..."); c.mms_88_Hills(); space(c);
        System.out.println("c.mms_86_PrepJump() ..."); c.mms_86_PrepJump(); space(c);
        System.out.println("c.mms_87_Stagger() ..."); c.mms_87_Stagger(); space(c);
        System.out.println("c.mms_87_StaggerUpDown() ..."); c.mms_87_StaggerUpDown(); space(c);
        System.out.println("c.mms_87_Stroll() ..."); c.mms_87_Stroll(); space(c);
        System.out.println("c.mms_87_StrollUpDown() ..."); c.mms_87_StrollUpDown(); space(c);
        System.out.println("c.mms_85_StrollDown() ..."); c.mms_85_StrollDown(); space(c);
        System.out.println("c.mms_87_ZagZig() ..."); c.mms_87_ZagZig(); space(c);
        System.out.println("c.mms_87_ZigZag() ..."); c.mms_87_ZigZag(); space(c);
        c.untext();
    }
    public static void space(SComposer c) {
        c.untext(); c.rest(2); c.text();
    }
}

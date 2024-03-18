public class CaughtSpeeding {
    public static void main(String[] args) {
        System.out.println("(61, false) Ticket Value: " + caughtSpeeding(61, false));
        System.out.println("(65, true) Ticket Value: " + caughtSpeeding(65, true));
    }

    private static int caughtSpeeding(int speed, boolean isBirthday) {
        if (isBirthday) { speed -= 5; }
        if (speed >= 81) { return 2; }
        if (speed >= 61) { return 1; }
        return 0;
    }
}

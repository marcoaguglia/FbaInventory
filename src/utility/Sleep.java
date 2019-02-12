package utility;

public class Sleep {
    public static void sleepSecond(int secondi) {
        try {
            Thread.sleep(secondi * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void sleepQuarterSecond(int secondi) {
        try {
            Thread.sleep(secondi * 250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

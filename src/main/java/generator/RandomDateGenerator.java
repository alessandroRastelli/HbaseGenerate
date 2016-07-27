package generator;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class RandomDateGenerator {

    public static String generate() {

        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(2015, 2015);

        gc.set(Calendar.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));

        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        Random rn = new Random();
        int hour = rn.nextInt(23 - 0 + 1) + 0;
        int minute = rn.nextInt(59 - 0 + 1) + 0;

        return (gc.get(Calendar.YEAR) + "-" + gc.get(Calendar.MONTH) + "-" + gc.get(Calendar.DAY_OF_MONTH) + "," + hour + ":" + minute);

    }

    public static Long generateLong() {

        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(2015, 2016);

        gc.set(Calendar.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));

        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
        Random rn = new Random();
        int hour = rn.nextInt(23 - 0 + 1) + 0;
        int minute = rn.nextInt(59 - 0 + 1) + 0;

        gc.set(Calendar.HOUR, hour);
        gc.set(Calendar.MINUTE, minute);


        return gc.getTimeInMillis();
    }

    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }
}

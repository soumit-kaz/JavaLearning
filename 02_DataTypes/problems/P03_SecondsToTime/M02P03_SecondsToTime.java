public class M02P03_SecondsToTime {

    public static void main(String[] args) {
        long totalSeconds = 100_000L;

        // integer division drops the remainder
        long hours = totalSeconds / 3600;
        // take away the full hours, then count whole minutes
        long minutes = (totalSeconds - hours * 3600) / 60;
        long seconds = totalSeconds - hours * 3600 - minutes * 60;
        System.out.println(totalSeconds + " s = " + hours + " h " + minutes + " min " + seconds + " s");

        // dividing by 3600.0 keeps the fraction
        double exactHours = totalSeconds / 3600.0;
        System.out.println("exact hours = " + exactHours);

        // a long can be cast down to int when the value fits
        int totalMinutes = (int) (totalSeconds / 60);
        System.out.println("total minutes = " + totalMinutes);

        long secondsPerYear = 365L * 24 * 60 * 60;
        System.out.println("seconds in a year = " + secondsPerYear);
    }
}

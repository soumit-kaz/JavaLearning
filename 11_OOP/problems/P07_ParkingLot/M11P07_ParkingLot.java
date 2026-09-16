public class M11P07_ParkingLot {

    enum Size {
        SMALL, MEDIUM, LARGE
    }

    static class Vehicle {
        final String plate;

        Vehicle(String plate) {
            this.plate = plate;
        }

        Size size() {
            return Size.MEDIUM;
        }

        int hourlyRate() {
            return 2;
        }

        // First hour is free for everyone
        int fee(int hours) {
            return Math.max(0, hours - 1) * hourlyRate();
        }
    }

    static class Motorbike extends Vehicle {
        Motorbike(String plate) {
            super(plate);
        }

        @Override
        Size size() {
            return Size.SMALL;
        }

        @Override
        int hourlyRate() {
            return 1;
        }
    }

    static class Truck extends Vehicle {
        Truck(String plate) {
            super(plate);
        }

        @Override
        Size size() {
            return Size.LARGE;
        }

        @Override
        int hourlyRate() {
            return 5;
        }

        // Trucks also pay a fixed entry charge
        @Override
        int fee(int hours) {
            return 10 + super.fee(hours);
        }
    }

    static class ParkingLot {
        private final Size[] spots;
        private final Vehicle[] parked;

        ParkingLot(Size[] spots) {
            this.spots = spots.clone();
            this.parked = new Vehicle[spots.length];
        }

        // A vehicle fits any free spot that is at least its size
        int park(Vehicle v) {
            for (int i = 0; i < spots.length; i++) {
                if (parked[i] == null && spots[i].ordinal() >= v.size().ordinal()) {
                    parked[i] = v;
                    return i;
                }
            }
            return -1;
        }

        int leave(String plate, int hours) {
            for (int i = 0; i < parked.length; i++) {
                if (parked[i] != null && parked[i].plate.equals(plate)) {
                    int fee = parked[i].fee(hours);
                    parked[i] = null;
                    return fee;
                }
            }
            throw new IllegalArgumentException("not parked: " + plate);
        }

        int free() {
            int count = 0;
            for (Vehicle v : parked) {
                if (v == null) {
                    count++;
                }
            }
            return count;
        }
    }

    static boolean failed = false;

    // print one PASS or FAIL line and remember any failure
    static void check(String label, Object actual, String expected) {
        boolean ok = String.valueOf(actual).equals(expected);
        if (!ok) {
            failed = true;
        }
        System.out.println(label + " -> " + actual + " " + (ok ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(new Size[]{Size.SMALL, Size.MEDIUM, Size.LARGE});
        check("park bike", lot.park(new Motorbike("M1")), "0");
        check("park car", lot.park(new Vehicle("C1")), "1");
        check("park truck", lot.park(new Truck("T1")), "2");
        check("park another car", lot.park(new Vehicle("C2")), "-1");
        check("free spots", lot.free(), "0");
        check("car leaves after 3h", lot.leave("C1", 3), "4");
        check("truck leaves after 1h", lot.leave("T1", 1), "10");
        check("bike leaves after 5h", lot.leave("M1", 5), "4");
        check("bike takes large spot", new ParkingLot(new Size[]{Size.LARGE}).park(new Motorbike("M2")), "0");
        try {
            lot.leave("ZZZ", 1);
            check("leave unknown", "no error", "error: not parked: ZZZ");
        } catch (IllegalArgumentException e) {
            check("leave unknown", "error: " + e.getMessage(), "error: not parked: ZZZ");
        }

        if (failed) {
            System.exit(1);
        }
    }
}

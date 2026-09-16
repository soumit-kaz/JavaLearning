public class M13P03_Payroll {

    // all money is in cents to avoid rounding errors
    static class Employee {
        final String name;
        final long base;

        Employee(String name, long base) {
            this.name = name;
            this.base = base;
        }

        long pay() {
            return base;
        }

        String title() {
            return "Employee";
        }

        @Override
        public String toString() {
            return title() + " " + name + ": " + money(pay());
        }
    }

    static class Manager extends Employee {
        final int reports;

        Manager(String name, long base, int reports) {
            super(name, base);
            this.reports = reports;
        }

        // Employee pay plus 100.00 per report
        @Override
        long pay() {
            return super.pay() + 10_000L * reports;
        }

        @Override
        String title() {
            return "Manager";
        }
    }

    static class Director extends Manager {
        final int bonusPercent;

        Director(String name, long base, int reports, int bonusPercent) {
            super(name, base, reports);
            this.bonusPercent = bonusPercent;
        }

        // Manager pay plus a bonus on top of it
        @Override
        long pay() {
            long managerPay = super.pay();
            return managerPay + managerPay * bonusPercent / 100;
        }

        @Override
        String title() {
            return "Director";
        }
    }

    static class Contractor extends Employee {
        final int hours;
        final long rate;

        Contractor(String name, int hours, long rate) {
            super(name, 0);
            this.hours = hours;
            this.rate = rate;
        }

        // hours above 160 are paid 1.5 times
        @Override
        long pay() {
            int normal = Math.min(hours, 160);
            int extra = hours - normal;
            return normal * rate + extra * rate * 3 / 2;
        }

        @Override
        String title() {
            return "Contractor";
        }
    }

    static long total(Employee[] staff) {
        long sum = 0;
        for (Employee e : staff) {
            sum += e.pay();
        }
        return sum;
    }

    static String money(long cents) {
        return cents / 100 + "." + (cents % 100 < 10 ? "0" : "") + cents % 100;
    }

    static int failures = 0;

    static void check(String label, Object actual, Object expected) {
        boolean ok = String.valueOf(actual).equals(String.valueOf(expected));
        if (!ok) {
            failures++;
        }
        System.out.println(label + " -> " + actual + "  " + (ok ? "PASS" : "FAIL expected " + expected));
    }

    public static void main(String[] args) {
        Employee e = new Employee("Eve", 300_000);
        Employee m = new Manager("Max", 500_000, 3);
        Employee d = new Director("Dia", 800_000, 2, 10);
        Employee c = new Contractor("Cyd", 170, 4_000);

        check("Employee", e, "Employee Eve: 3000.00");
        check("Manager", m, "Manager Max: 5300.00");
        check("Director", d, "Director Dia: 9020.00");
        check("Contractor", c, "Contractor Cyd: 7000.00");
        check("total", money(total(new Employee[] {e, m, d, c})), "24320.00");
        if (failures > 0) {
            System.exit(1);
        }
    }
}

public class M04P04_GradeCalculator {

    public static void main(String[] args) {
        int score;
        String expected;
        String grade;

        // test 1
        score = 95;
        expected = "A";
        // invalid scores first, then from the highest band down
        if (score < 0 || score > 100) {
            grade = "invalid";
        } else if (score >= 90) {
            grade = "A";
        } else if (score >= 80) {
            grade = "B";
        } else if (score >= 70) {
            grade = "C";
        } else if (score >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }
        System.out.println(score + " -> " + grade + (grade.equals(expected) ? "  PASS" : "  FAIL (expected " + expected + ")"));

        // test 2: exactly on a boundary
        score = 80;
        expected = "B";
        if (score < 0 || score > 100) {
            grade = "invalid";
        } else if (score >= 90) {
            grade = "A";
        } else if (score >= 80) {
            grade = "B";
        } else if (score >= 70) {
            grade = "C";
        } else if (score >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }
        System.out.println(score + " -> " + grade + (grade.equals(expected) ? "  PASS" : "  FAIL (expected " + expected + ")"));

        // test 3: the same bands with a switch expression on the tens digit
        score = 59;
        expected = "F";
        grade = switch (score / 10) {
            case 10, 9 -> "A";
            case 8 -> "B";
            case 7 -> "C";
            case 6 -> "D";
            default -> "F";
        };
        System.out.println(score + " -> " + grade + (grade.equals(expected) ? "  PASS" : "  FAIL (expected " + expected + ")"));

        // test 4: out of range
        score = 101;
        expected = "invalid";
        if (score < 0 || score > 100) {
            grade = "invalid";
        } else if (score >= 90) {
            grade = "A";
        } else if (score >= 80) {
            grade = "B";
        } else if (score >= 70) {
            grade = "C";
        } else if (score >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }
        System.out.println(score + " -> " + grade + (grade.equals(expected) ? "  PASS" : "  FAIL (expected " + expected + ")"));
    }
}

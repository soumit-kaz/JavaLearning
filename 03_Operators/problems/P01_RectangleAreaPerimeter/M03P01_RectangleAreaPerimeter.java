public class M03P01_RectangleAreaPerimeter {

    public static void main(String[] args) {
        double width = 4.5;
        double height = 2.0;

        // area is width times height
        double area = width * height;

        // perimeter adds all four sides
        double perimeter = 2 * (width + height);

        // the diagonal comes from Pythagoras
        double diagonal = Math.sqrt(width * width + height * height);

        System.out.println("area = " + area);
        System.out.println("perimeter = " + perimeter);
        System.out.printf("diagonal = %.2f%n", diagonal);
    }
}

import java.util.ArrayList;

class Rectangle
{
    int length = 0;
    int breadth = 0;

    public Rectangle(int length, int breadth)
    {
        this.length = length;
        this.breadth = breadth;
    }

}

public class RectangleMain {

    public static Rectangle add(Rectangle rectangle1, Rectangle rectangle2)
    {
        Rectangle result = new Rectangle(0, 0);
        result.length = rectangle1.length + rectangle2.length;
        result.breadth = Math.max(rectangle1.breadth, rectangle2.breadth);
        return result;
    }

    public static Rectangle add(ArrayList<Rectangle> rectangles)
    {
        Rectangle result = new Rectangle(0, 0);
        for (Rectangle rectangle : rectangles)
        {
            result.length += rectangle.length;
            result.breadth = Math.max(result.breadth, rectangle.breadth);
        }
        return result;
    }
    public static void main(String[] args) {
        //  10 by 20, 50 by 30, and 20 by 90

        Rectangle r1 = new Rectangle(10, 20);
        Rectangle r2 = new Rectangle(50, 30);
        Rectangle r3 = new Rectangle(20, 90);

        ArrayList<Rectangle> rectangles = new ArrayList<>();
        rectangles.add(r1);
        rectangles.add(r2);
        rectangles.add(r3);

        Rectangle result = add(rectangles);

        System.out.println("Length: " + result.length + ", Breadth: " + result.breadth);

    }


}

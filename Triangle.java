/*
 * Lab U3.5 - The Triangle Class
 * AP / IB Computer Science - Unit 3, Days 41-42
 *
 * You drew the UML for this class on paper before opening this file.
 * Build what your diagram says, one member at a time, in the order it lists.
 *
 * HERON'S FORMULA - you are given it, not asked to remember it:
 *
 *     double s = (side1 + side2 + side3) / 2.0;
 *     double area = Math.sqrt( s * (s - side1) * (s - side2) * (s - side3) );
 *
 * DECLARE THE SIDES double, NOT int. Because they are doubles, (side1 + side2
 * + side3) is a double and Java promotes the 2 for you - so / 2 and / 2.0 give
 * exactly the same answer here. But declare them int and the division truncates
 * and the area comes back silently wrong, with no error at all.
 *
 * Write 2.0 anyway. It states what you mean, and it survives somebody changing
 * the type later.
 *
 * NEVER COMPARE TWO doubles WITH ==. Most decimals cannot be stored exactly,
 * so 0.1 + 0.2 == 0.3 is FALSE in Java (0.1 + 0.2 is 0.30000000000000004).
 * Instead, ask whether two doubles are CLOSE ENOUGH:
 *
 *     Math.abs(side1 - side2) < TOLERANCE      // within 0.01 of each other
 *
 * TOLERANCE is a class constant - static (one copy, shared by the whole class)
 * and final (it can never change). That is Day 40, put to work.
 *
 * THE SECOND BORROWED TOOL. You have been lent two operators from Unit 4:
 *
 *     &&   AND - true only when both sides are true
 *     ||   OR  - true when either side is true
 *
 * You need them in requirement 5 and nowhere else. Day 46 teaches them properly.
 *
 * You do NOT need an if statement anywhere in this class. A comparison already
 * IS a boolean - so return it.
 */

public class Triangle
{
    // TODO 1 - one class constant:
    //              private static final double TOLERANCE = 0.01;
    //          then three private double instance variables: side1, side2, side3.

    private static final double TOLERANCE = 0.01;
    private String triangleName;
    private double side1;
    private double side2;
    private double side3;

    // TODO 2 - a public constructor taking all three sides, in the same order
    //          as your diagram. Use this.side1 = side1; for each.

    public Triangle(double side1, double side2, double side3, String triangleName){
        this.side1=side1;
        this.side2=side2;
        this.side3=side3;
        this.triangleName=triangleName;
    }

    // TODO 3 - getSide1(), getSide2(), getSide3().
    //          Each returns a double. One line of body each.

    public double getSide1(){
        return side1;
    }
    public double getSide2(){
        return side2;
    }
    public double getSide3(){
        return side3;
    }

    // TODO 4 - perimeter()
    //          Returns the three sides added together.

    public double perimeter(double s1, double s2, double s3){
        double added = s1+s2+s3;
        return added;
    }

    // TODO 5 - area()
    //          Heron's formula, from the comment at the top.
    //          Check your TODO 1 declarations say double, not int.

    public double area(double s1, double s2, double s3){
        double s = (side1 + side2 + side3) / 2.0;
        double area = Math.sqrt( s * (s - side1) * (s - side2) * (s - side3) );
        return area;
    }


    // TODO 6 - isEquilateral()
    //          All three sides equal. Returns boolean. Needs && .
    //          Compare with Math.abs(...) < TOLERANCE, never == .

    public boolean isEquilateral(Triangle triangle){
        if (Math.abs(triangle.side1-triangle.side2)<0.001 && Math.abs(triangle.side1-triangle.side3)<0.001 && Math.abs(triangle.side2-triangle.side3)<0.001) {
            return true;
        }
        else{
            return false;
        }
    }

    // TODO 7 - isIsosceles()
    //          AT LEAST two sides equal. Returns boolean. Needs || .
    //          Compare with Math.abs(...) < TOLERANCE, never == .
    //          Careful: an equilateral triangle IS isosceles. Test 5-5-5.

    public boolean isIsoceles(Triangle triangle){
        if(Math.abs(triangle.side1-triangle.side2) == 0 ||Math.abs(triangle.side1-triangle.side3)==0 || Math.abs(triangle.side2-triangle.side3)==0){
            return true;
        }
        else{
            return false;
        }
    }

    // TODO 8 - toString()
    //          A readable line: the three sides, the perimeter and the area.


    public String toString(){
            return this.triangleName+" " + this.side1 + " "
                    + this.side2 + " "
                    + this.side3;
    }


    public static void main(String[] args)
    {
        Triangle t1 = new Triangle(5,5,5, "equilateral");
        Triangle t2 = new Triangle(5, 5, 8, "isoceles");
        Triangle t3 = new Triangle(3, 4, 5, "scalene");
        String equilateralCheck="";
        String isocelesCheck="";
        System.out.print(t1+"\n");
        System.out.println(t2+"\n");
        System.out.println(t3+"\n");


        if (t1.isEquilateral(t1)==true){
            equilateralCheck +=" "+t1;
        }
        if (t2.isEquilateral(t2)==true){
            equilateralCheck +=" "+ t2;
        }
        if (t3.isEquilateral(t3)==true){
            equilateralCheck +=" "+t3;
        }
        if (t1.isIsoceles(t1)==true){
            isocelesCheck +=" "+t1;
        }
        if (t2.isIsoceles(t2)==true){
            isocelesCheck += " "+t2;
        }
        if (t3.isIsoceles(t3)==true){
            isocelesCheck +=" "+t3;
        }
        System.out.println("isoceles triangles: "+isocelesCheck);
        System.out.println("equilateral triangles: "+equilateralCheck);

        // TODO 9 - construct three Triangles and print each one:
        //
        //              equilateral   5, 5, 5
        //              isosceles     5, 5, 8
        //              scalene       3, 4, 5
        //
        //          For each, also print isEquilateral() and isIsosceles().
        //          The 5-5-5 triangle must print true for BOTH.
        //
        //          Then one more, to prove TOLERANCE works:
        //
        //              new Triangle(0.1 + 0.2, 0.3, 0.3)
        //
        //          Its isEquilateral() must print true. With == it prints false.
        //
        // OPTIONAL - see a triangle drawn (IntelliJ only, not Codespaces).
        //          Once EVERY TODO above is done, rename DrawTriangle.java.txt
        //          to DrawTriangle.java, then add:
        //
        //              new DrawTriangle(yourTriangle).showWindow();

    }
}

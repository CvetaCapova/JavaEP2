import java.awt.*;

// This class represents vectors in a 3D vector space.
public class Vector3 {

    //TODO: change modifiers.
    private double x;
    private double y;
    private double z;

    //TODO: define constructor.
    Vector3(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    Vector3(){
        this.x = 0;
        this.y = 0;
        this.z = 0;

    }

    public double showX() {
        return x;
    }

    public double showY() {
        return y;
    }
    public double showZ() {
        return z;
    }




    // Returns the sum of this vector and vector 'v'.
    public Vector3 plus(Vector3 v) {

        //TODO: implement method.
        Vector3 result = new Vector3(this.x + v.x,this.y + v.y,this.z + v.z );

        return result;

    }

    // Returns the product of this vector and 'd'.
    public Vector3 times(double d) {

        //TODO: implement method.
        Vector3 result = new Vector3(this.x*d,this.y*d,this.z*d);

        return result;

    }

    // Returns the sum of this vector and -1*v.
    public Vector3 minus(Vector3 v) {

        //TODO: implement method.
        Vector3 result = new Vector3(this.x - v.x,this.y - v.y,this.z - v.z);

        return result;

    }

    // Returns the Euclidean distance of this vector
    // to the specified vector 'v'.
    public double distanceTo(Vector3 v) {

        //TODO: implement method.
        double dX = this.x - v.x;
        double dY = this.y - v.y;
        double dZ = this.z - v.z;

        return Math.sqrt(dX * dX + dY * dY + dZ * dZ);

    }

    // Returns the length (norm) of this vector.
    public double length() {

        //TODO: implement method.
        return this.distanceTo(new Vector3()); // distance to origin.

    }

    // Normalizes this vector: changes the length of this vector such that it becomes one.
    // The direction and orientation of the vector is not affected.
    public void normalize() {

        //TODO: implement method.
        double length = this.length();
        this.x /=length;
        this.y /=length;
        this.z /=length;

    }

    // Draws a filled circle with the center at (x,y) coordinates of this vector
    // in the existing StdDraw canvas. The z-coordinate is not used.
    public void drawAsDot(double radius, Color color) {

        //TODO: implement method.
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(this.x,this.y,radius);
    }

    // Returns the coordinates of this vector in brackets as a string
    // in the form "[x,y,z]", e.g., "[1.48E11,0.0,0.0]".
    @Override
    public String toString() {

        //TODO: implement method.

        return String.format("[%1$s, %2$s, %3$s]", this.x, this.y, this.z);
    }

    // Prints the coordinates of this vector in brackets to the console (without newline)
    // in the form [x,y,z], e.g.,
    // [1.48E11,0.0,0.0]
    public void print() {

        //TODO: implement method.
        System.out.println(this);
    }

}


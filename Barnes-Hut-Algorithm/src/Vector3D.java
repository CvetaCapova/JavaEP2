/**
 * Class Vector 3D has tree coordinates. Vectors could be added (method plus) or compared (equals).
 */

public class Vector3D {

    private double x;
    private double y;
    private double z;

    public Vector3D(){

    }
    //constructors
    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(Vector3D vector){
        this(vector.x, vector.y, vector.z);
    }

    //getters and setters
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }

    //adds two vectors
    public Vector3D plus(double a, double b, double c) {

        return new Vector3D(this.x + a, this.y + b, this.z + c);
    }

    public Vector3D plus(Vector3D vector3D) {

        return new Vector3D(this.x + vector3D.x,this.y + vector3D.y,this.z + vector3D.z);
    }

    // Returns the product of this vector and 'd'.
    public Vector3D times(double d) {

        return new Vector3D(this.x*d,this.y*d,this.z*d);
    }

    // Returns the sum of this vector and -1*v.
    public Vector3D minus(Vector3D v) {

        return new Vector3D(this.x - v.x,this.y - v.y,this.z - v.z);
    }

    // Returns the Euclidean distance of this vector
    // to the specified vector 'v'.
    public double distanceTo(Vector3D v) {

        double dX = this.x - v.x;
        double dY = this.y - v.y;
        double dZ = this.z - v.z;

        return Math.sqrt(dX * dX + dY * dY + dZ * dZ);
    }

    // Returns the length (norm) of this vector.
    public double length() {

        return this.distanceTo(new Vector3D()); // distance to origin.
    }

    public void normalize() {

        double length = this.length();
        this.x /= length;
        this.y /= length;
        this.z /= length;
    }

    //compares two vectors and returns true if they are equal
    public boolean equals(Vector3D vector) {
        return vector != null && this.x == vector.x && this.y == vector.y && this.z == vector.z;
    }

    public String toString() {
        return String.format("[%1$s, %2$s, %3$s]", this.x, this.y, this.z);
    }
}
/*
Class Body represents an object(to be added in octtree).
 */


import java.awt.*;
import java.util.Objects;

public class CelestialBody {

    private Vector3D position;
    private Vector3D currentMovement;
    private Vector3D forceOnBody;
    private double mass; //sum of the mass of all bodies in this cube
    private boolean isInternalNode;
    private double radius;
    private Color color;

    public CelestialBody() {
    }

    public CelestialBody(Vector3D position, double mass, boolean isInternalNode) {
        this.position = position;
        this.mass = mass;
        this.isInternalNode = isInternalNode;
    }

    public CelestialBody(double mass, double radius, Vector3D position, Vector3D velocity, Color color ) {
        this.mass = mass;
        this.radius = radius;
        this.position = position;
        this.currentMovement = velocity;
        this.color = color;
        this.isInternalNode = false;
    }

    //getter and setter

    public Vector3D getPosition() {
        return position;
    }

    public double getMass() {
        return mass;
    }

    public boolean isInternalNode() {
        return isInternalNode;
    }

    //returns a new body that represents the center-of-mass of the two bodies a and b
    public CelestialBody add(CelestialBody b) {

        double m = this.mass + b.mass;

        double x = (this.getPosition().getX() * this.mass + b.getPosition().getX() * b.mass) / m;
        double y = (this.getPosition().getY() * this.mass + b.getPosition().getY() * b.mass) / m;
        double z = (this.getPosition().getZ() * this.mass + b.getPosition().getZ() * b.mass) / m;

        return new CelestialBody(new Vector3D(x, y, z), m, true);
    }

    public void addForce(Vector3D vector3D){
        this.forceOnBody = this.forceOnBody.plus(vector3D);
    }

    public void move() {
        Vector3D timesVector = forceOnBody.times(1 / this.mass);

        Vector3D newPosition = this.position.plus(timesVector).plus(this.currentMovement);

        Vector3D newMovement = newPosition.minus(this.position); // new minus old position.

        this.position = newPosition;
        this.currentMovement = newMovement;

    }

    // Returns a vector representing the gravitational force exerted by 'body' on this celestial body.
    public Vector3D gravitationalForce(CelestialBody body) {
        Vector3D direction = body.position.minus(this.position);
        double r = direction.length();
        double G = 6.6743e-11;
        direction.normalize();
        double force = G * this.mass * body.mass / (r * r);
        return direction.times(force);
    }

    public void resetForce(){
        this.forceOnBody = new Vector3D(0,0,0);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CelestialBody body = (CelestialBody) o;
        return Double.compare(body.mass, mass) == 0 &&
                position.equals(body.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, mass);
    }

    public void draw() {
        StdDraw.setPenColor(this.color);
        StdDraw.filledCircle(this.position.getX(), this.position.getY(), 1.5e8 * Math.log10(this.radius));
    }

    public CelestialBody generateRandom(double xFactor, double xSection,
                                        double yFactor, double ySection,
                                        double zFactor, double zSection){
        double mass = (Math.random() * ((Simulation.Max_Mass - Simulation.Min_Mass) + 1)) + Simulation.Min_Mass;
        double radius = (Math.random() * ((Simulation.Max_Radius - Simulation.Min_Radius) + 1)) + Simulation.Min_Radius;
        double px = ((Math.random() * xFactor + xSection) * (4 * Simulation.AU + 1)) - (2 * Simulation.AU); //position
        double py = ((Math.random() * yFactor + ySection) * (4 * Simulation.AU + 1)) - (2 * Simulation.AU);
        double pz = ((Math.random() * zFactor + zSection) * (4 * Simulation.AU + 1)) - (2 * Simulation.AU);
        double vx = (Math.random() * ((Simulation.Max_Speed-Simulation.Min_Speed) + 1)) + Simulation.Min_Speed; //currentMovement
        double vy = (Math.random() * ((Simulation.Max_Speed-Simulation.Min_Speed) + 1)) + Simulation.Min_Speed; ;
        double vz = (Math.random() * ((Simulation.Max_Speed-Simulation.Min_Speed) + 1)) + Simulation.Min_Speed;;
        int r = (int) (Math.random() * 255); //colors
        int g = (int) (Math.random() * 255);
        int b = (int) (Math.random() * 255);
        Color randomColor = new Color(r, g, b);
        CelestialBody randomBody = new CelestialBody(mass, radius, new Vector3D(px, py, pz), new Vector3D(vx, vy, vz), randomColor);
        randomBody.forceOnBody = new Vector3D(0,0,0);
        return randomBody;
    }
}
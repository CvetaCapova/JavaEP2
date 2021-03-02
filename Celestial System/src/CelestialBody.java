import java.awt.*;
import java.util.Objects;

// This class represents celestial bodies like stars, planets, asteroids, etc..
public class CelestialBody {

    //TODO: change modifiers.
    private String name;
    private double mass;
    private double radius;
    private Vector3 position; // position of the center.
    private Vector3 currentMovement;
    private Color color; // for drawing the body.

    //TODO: define constructor.

    CelestialBody(){

    }

    CelestialBody(String name, double mass, double radius, Vector3 position, Vector3 currentMovement,Color color) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.color = color;
        this.position = position;
        this.currentMovement = currentMovement;
    }

    CelestialBody(CelestialBody body, Vector3 position, Vector3 velocity) {
        this(body.name, body.mass, body.radius, body.position, body.currentMovement, body.color);
        this.position = position;
        this.currentMovement = velocity;

    }

    CelestialBody(CelestialBody body){
        this(body.name, body.mass, body.radius, body.position, body.currentMovement, body.color);
    }


    public void editPosition(Vector3 position) {
        this.position = position;
    }

    public void editCurrentMovement(Vector3 currentMovement) {
        this.currentMovement = currentMovement;
    }

    public String getName() {
        return this.name;
    }


    // Returns the distance between this celestial body and the specified 'body'.
    public double distanceTo(CelestialBody body) {

        //TODO: implement method.
        double xDistancePower = Math.pow((this.position.showX() - body.position.showX()), 2);
        double yDistancePower = Math.pow((this.position.showY() - body.position.showY()), 2);
        double zDistancePower = Math.pow((this.position.showZ() - body.position.showZ()), 2);

        double distance = Math.sqrt(xDistancePower + yDistancePower + zDistancePower);
        return distance;
    }

    // Returns a vector representing the gravitational force exerted by 'body' on this celestial body.
    public Vector3 gravitationalForce(CelestialBody body) {

        //TODO: implement method.
        Vector3 direction = body.position.minus(this.position);
        double r = direction.length();
        double G = 6.6743e-11;
        direction.normalize();
        double force = G * this.mass * body.mass / (r * r);
        return direction.times(force);

    }


    // Moves this body to a new position, according to the specified force vector 'force' exerted
    // on it, and updates the current movement accordingly.
    // (Movement depends on the mass of this body, its current movement and the exerted force.)
    public void move(Vector3 force) {

        //TODO: implement method.
        Vector3 timesVector = force.times(1 / this.mass);

        Vector3 newPosition = this.position.plus(timesVector).plus(this.currentMovement);

        Vector3 newMovement = newPosition.minus(this.position); // new minus old position.

        this.editPosition(newPosition);
        this.editCurrentMovement(newMovement);

    }

    // Returns a string with the information about this celestial body including
    // name, mass, radius, position and current movement. Example:
    // "Earth, 5.972E24 kg, radius: 6371000.0 m, position: [1.48E11,0.0,0.0] m, movement: [0.0,29290.0,0.0] m/s."
    @Override
    public String toString() {

        //TODO: implement method.
        return String.format("%1$s, %2$s, %3$s %4$s %5$s", this.name, this.mass, this.radius, this.position, this.currentMovement);
    }

    // Prints the information about this celestial body including
    // name, mass, radius, position and current movement, to the console (without newline).
    // Earth, 5.972E24 kg, radius: 6371000.0 m, position: [1.48E11,0.0,0.0] m, movement: [0.0,29290.0,0.0] m/s.
    public void print() {

        System.out.println(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CelestialBody body = (CelestialBody) o;
        return Objects.equals(name, body.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    // Draws the celestial body to the current StdDraw canvas as a dot using 'color' of this body.
    // The radius of the dot is in relation to the radius of the celestial body
    // (use a conversion based on the logarithm as in 'Simulation.java').
    public void draw() {

        StdDraw.setPenColor(this.color);
        StdDraw.filledCircle(this.position.showX(), this.position.showY(),
                1e9 * Math.log10(this.radius));
    }


}


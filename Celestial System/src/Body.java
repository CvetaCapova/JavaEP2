
// Body represents a body or object in space with 3D-position, velocity and mass.
// This class will be used by the executable class 'Space'. A body moves through space according to
// its inertia and - if specified - an additional force exerted on it (for example the
// gravitational force of another mass).
public class Body {
    //TODO: class definition.

    /* Body has no constructor, but its fields will be set with setters. They are:
      private double x,y,z- define the position
      protected double vx,vy,vz- define the velocity
      double mass

    All field will be reached by getters.
    vx, vy, vz are protected because they will be used in another class MyBall
    The class MyBall will extends Body and will override the method move(fx,fy,fz), because in vacuum the mass should not be considered.
    A method "move"will be defined, which finds the new position of the object after one move(1 sek). Overload will be used:
    move()- default, if no external crafts exist: (x+vx,y+vy,z+vz)
    move(fx,fy,fz)- in the other case: ((vx+fx, vy+fy, vz+fz)/mass) + (x,y,z)
    */


    private double mass;
    private double x;
    private double y;
    private double z;
    protected double vx;
    protected double vy;
    protected double vz;




    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public double getZ() {
        return z;
    }

    public double getVx() {
        return vx;
    }


    public double getVy() {
        return vy;
    }


    public double getVz() {
        return vz;
    }


    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setVelocity(double vx, double vy, double vz) {
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
    }
    public void setPosition(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //returns the new position after one move
    public void move(){
        this.x = this.getX() + this.getVx();
        this.y = this.getY() + this.getVy();
        this.z = this.getZ() + this.getVz();

    }

    //changes the velocity, according to the external forces
    //returns the new position after one move
    public void move(double fx, double fy, double fz){
        this.vx = (this.getVx()+fx)/this.getMass();
        this.vy = (this.getVy()+fy)/this.getMass();
        this.vz = (this.getVz()+fz)/this.getMass();
        move();



    }
}

class MyBall extends Body{
    @Override public void move(double fx, double fy, double fz){
        super.vx = (this.getVx()+fx);
        this.vy = (this.getVy()+fy);
        this.vz = (this.getVz()-fz);
        move();
    }
}






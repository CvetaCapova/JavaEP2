import java.text.DecimalFormat;
import java.util.Random;
import java.util.Vector;

// Space is the actual program (executable class) using objects of class 'Body'.
public class Space {

    // Some constants helpful for the simulation (particularly the 'falling ball' example).
    public static final double G = 6.6743e-11; // gravitational constant
    public static final double MASS_OF_EARTH = 5.972e24; // kg
    public static final double MASS_OF_BALL = 1; // kg
    public static final double RADIUS_OF_EARTH = 6.371e6; // m (meters)


    // On the surface of earth the gravitational force on the ball weighing 1kg is
    // approximately as follows:
    public static final double GRAVITATIONAL_FORCE_ON_BALL =
            G * MASS_OF_EARTH * MASS_OF_BALL / (RADIUS_OF_EARTH * RADIUS_OF_EARTH); // kg*m/sec² ... F = mass * acc
    // This means each second its speed increases about 9.82 meters per second.


    //TODO: further variables, if needed.
    public static final double SPEED_OF_ROCKET = 5e6;
    public static final int START_RANDOM = 0; //random speed of wind
    public static final int END_RANDOM = 10;
    public static final int RANGE_RANDOM = END_RANDOM-START_RANDOM;
    public static DecimalFormat df = new DecimalFormat("#.##");
    public static final int FX_ROCKET = 40;
    public static final int FY_ROCKET = 60;
    public static final int FZ_ROCKET = 70;




    // The main simulation method using instances of other classes.
    public static void main(String[] args) {

        //TODO: implement method.

        //One test case is given:
        //Results for the falling ball on the surface of earth are as follows:
        //Height 10m: 2 (sec = number of move(fx,fy,fz) calls)
        //Height 100m: 5 (sec = number of move(fx,fy,fz) calls)

        Body ball1 = new MyBall();
        ball1.setPosition(0,0,100); // 100m height.
        ball1.setVelocity(0,0,0);
        ball1.setMass(1); // 1 kg
        System.out.println("ball1:"+ fallToGround(ball1)); // 5

        ball1.setPosition(0,0,10); // 10m height.
        ball1.setVelocity(0,0,0);
        System.out.println("ball1: "+fallToGround(ball1)); // 2

        Body ball2 = new MyBall();
        ball2.setPosition(0,0,100); // 100m height.
        ball2.setVelocity(0,0,0);
        ball2.setMass(15); // 15 kg
        System.out.println("ball2: "+fallToGround(ball2)); // 5


        //Further examples are to be tested (body in empty space, rocket, feather).


//        Test body in empty space
//        The velocity is constant. There are no external crafts.
//        The time is given. int time
//        Print the new position
        //Results:
        // for time = 5: 150.0, 200.0, 750.0
        //for time = 60: 1800.0, 2400.0, 9000.0

        Body planet = new Body();
        planet.setPosition(0,0,0);
        planet.setVelocity(30,40,150);
        int time = 60;
        moveCelestialBody(planet,time); //1800.0, 2400.0, 9000.0

        //Test rocket
        //Results for the new position of the rocket are:
        // for time = 20 sek => 0.0, 0.0, 143051.28315332957
        // for time = 60 sek =>0.0, 0.0, 429174.31605747814
        Body rocket = new Body();
        rocket.setPosition(0,0,0);
        rocket.setVelocity(0,0,SPEED_OF_ROCKET);
        rocket.setMass(700);
        time = 60;
        flyingRocket(rocket,time); //3.433394528459826, 5.1500917926897385, 7159.084263028503

        //Test feather
        Body feather = new Body();
        feather.setPosition(2.5, 4.8, 3.9);
        feather.setVelocity(0, 0, 0);
        feather.setMass(0.02);
        time = 7;
        flutter(feather, time);


    }

    // Returns the number of move(fx,fy,fz) calls needed for 'b' hitting the ground, i.e.,
    // the method reduces the z-coordinate of 'b' until it becomes 0 or negative.
    public static int fallToGround(Body b) {
        if (b.getZ() >= 0) {
            b.move(0, 0, GRAVITATIONAL_FORCE_ON_BALL);
            return fallToGround(b) + 1;
        } else {
            return 0;
        }

    }

    //Prints the new position of celestial body; there are no external forces
    public static void moveCelestialBody(Body b, int time) {
        if (time > 0) {
            b.move();
            moveCelestialBody(b, time - 1);
        } else {
            System.out.println("new celestial body position: "+b.getX() + " " + b.getY() + " " + b.getZ());
        }

    }

    //Prints the new position of a rocket; with external forces
    public static void flyingRocket(Body b, int time) {
        if (time > 0) {
            b.move(FX_ROCKET, FY_ROCKET, FZ_ROCKET);
            flyingRocket(b, time - 1);
        } else {
            System.out.println("new rocket position: "+b.getX() + " " + b.getY() + " " + b.getZ());
        }
    }

    public static void flutter(Body b, int time) {
        if (time > 0) {
            Random rd = new Random();
            double fx = START_RANDOM+(rd.nextDouble()*RANGE_RANDOM); //random double number in range from 0 to 10
            double fy = START_RANDOM+(rd.nextDouble()*RANGE_RANDOM);
            double fz = START_RANDOM+(rd.nextDouble()*RANGE_RANDOM);
            System.out.println("new random feather position " + df.format(b.getX()) + " " + df.format(b.getY()) + " " + df.format(b.getZ())); //formats double with 2 decimal places
            b.move(fx, fy, fz);
            flutter(b, time - 1);
        } else {
            System.out.println("final feather position " + df.format(b.getX()) + " " + df.format(b.getY()) + " " + df.format(b.getZ()));


        }
    }

    //TODO: Define further methods as needed.

}




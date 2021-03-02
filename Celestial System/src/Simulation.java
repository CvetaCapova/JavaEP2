import java.awt.*;
import java.io.IOException;

public class Simulation {

    // gravitational constant
    public static final double G = 6.6743e-11;

    // one astronomical unit (AU) is the average distance of earth to the sun.
    public static final double AU = 150e9;

    // all quantities are based on units of kilogram respectively second and meter.

    // The main simulation method using instances of other classes.
    public static void main(String[] args) throws IOException {

        try{
           if(args.length< 2){
               throw new IOException("Error: wrong number of arguments (2 arguments needed).");
           }

            if(args[0]!= "D:\\Uni\\2.Semester\\EP2\\11923551\\src\\Configuration.csv"){
                throw new IOException("Error: File not found!");
            }

            if(Integer.parseInt(args[1])<0 ||Integer.parseInt(args[1])> 366){
                throw new IOException("Invalid day argument");
            }


            CelestialBody sun = new CelestialBody("Sol", 1.989e30, 696340e3, new Vector3(0, 0, 0), new Vector3(0, 0, 0), StdDraw.YELLOW);
            // sun is the reference point and assumed not to move.


            CelestialBody earth = new CelestialBody("Earth", 5.972e24, 6371e3,new Vector3(148e9, 0, 0),new Vector3(0, 29.29e3, 0), StdDraw.BLUE); //mass-kg, radius-meters
            // minimal distance to sun in meters.
            // viewing from z direction movement is counter-clockwise
            // orbital speed in meters per second (at minimal distance).


            CelestialBody mercury = new CelestialBody("Mercury", 3.301e23, 2.4397e3,new Vector3(-46.0e9, 0, 0),new Vector3(0, -47.87e3, 0), StdDraw.RED);
            // arbitrary initialisation: position opposite to the earth with maximal distance.
            // meters
            // viewing from z direction movement is counter-clockwise
            // meters per second
            CelestialBody mars = new CelestialBody("Mars", 0.641712e24, 3389.5e3, new Vector3(0, 0, 0), new Vector3(0, 0, 0), StdDraw.RED);

            CelestialBody venus = new CelestialBody("Venus", 4.86747e24, 6051.8e3, new Vector3(0, 0,0), new Vector3(0, 0, 0), StdDraw.PINK);

            CelestialBody earth2 = new CelestialBody("Earth2", 5.972e24, 6371e3,new Vector3(148e9, 0, 0),new Vector3(0, 29.29e3, 0), StdDraw.BLUE); //mass-kg, radius-meters


            CelestialBody mercury2 = new CelestialBody("Mercury2", 3.301e23, 2.4397e3,new Vector3(-46.0e9, 0, 0),new Vector3(0, -47.87e3, 0), StdDraw.RED);

            CelestialBody planet = new CelestialBody("Planet", 3.301e23, 2.4397e3,new Vector3(-46.0e9, 0, 0),new Vector3(0, -47.87e3, 0), StdDraw.RED);


            CelestialSystem bodies2 = new CelestialSystem();
            bodies2.add(earth);
            bodies2.add(sun);
            bodies2.add(mercury);

            CelestialSystem bodies3 = new CelestialSystem();
            bodies3.add(mercury2);
            bodies3.add(earth2);

            CelestialSystem bodies4 = new CelestialSystem();
            bodies2.add(earth);
            bodies2.add(sun);
            bodies2.add(mercury);

            CelestialSystemIndexTreeVariantC treeVariantC = new CelestialSystemIndexTreeVariantC(new CelestialBodyNameComparator());

            //Test cases:
            System.out.println(treeVariantC.add(bodies2));
            System.out.println(treeVariantC.get(mercury2));
            System.out.println("Tree contains: " + treeVariantC.contains(earth));



            CelestialBodyCollection view = treeVariantC.bodies();

            for (CelestialBody body : view) {
                System.out.println("Body in collection " + body);
            }
            view.add(mercury2);

            for (CelestialBody body : treeVariantC) {
                System.out.println(body + "printed by treeVariantC iterator");
            }

            treeVariantC.add(bodies3);



            CelestialSystem copy = treeVariantC.bodiesAsCelestialSystem();

            copy.add(planet);

            for (CelestialBody body : copy) {
                System.out.println("Body in copy: "+body);
            }


            for (CelestialBody body : view) {
                System.out.println("Body in collection " + body);
            }


            CelestialSystemIndexTreeVariantC treeVariantC2 = new CelestialSystemIndexTreeVariantC(new CelestialBodyNameComparator());
            System.out.println(treeVariantC2.add(bodies2));
            CelestialSystemIndexTreeVariantC treeVariantC3 = new CelestialSystemIndexTreeVariantC(new CelestialBodyNameComparator());
            System.out.println(treeVariantC3.add(bodies4));
            System.out.println("Equals tree and view: " + treeVariantC2.equals(treeVariantC3));


            CelestialSystem bodiesRead = new CelestialSystem();
            bodiesRead.add(mercury);
            bodiesRead.add(sun);
            bodiesRead.add(venus);
            bodiesRead.add(earth);
            bodiesRead.add(mars);
            String path = "D:\\Uni\\2.Semester\\EP2\\11923551\\src\\Configuration.csv";
            ReadDataUtil.readConfiguration(path,bodiesRead,60);
            //ReadDataUtil.readConfiguration("gg",bodiesRead,59);







            CelestialSystem bodies = ReadDataUtil.initialize(60);
            Vector3[] forceOnBody = new Vector3[bodies.size()];

            StdDraw.setCanvasSize(500, 500);
            StdDraw.setXscale(-2 * AU, 2 * AU);
            StdDraw.setYscale(-2 * AU, 2 * AU);
            double pixelWidth = 4 * AU / 500;
            StdDraw.enableDoubleBuffering();
            StdDraw.clear(StdDraw.BLACK);

            double seconds = 0;

            // simulation loop
            while (true) {

                seconds++; // each iteration computes the movement of the celestial bodies within one second.

                // for each body (with index i): compute the total force exerted on it.
                for (int i = 0; i < bodies.size(); i++) {
                    forceOnBody[i] = new Vector3(); // begin with zero
                    for (int j = 0; j < bodies.size(); j++) {
                        if (i == j) continue;
                        Vector3 forceToAdd = bodies.get(i).gravitationalForce(bodies.get(j));
                        forceOnBody[i] = forceOnBody[i].plus(forceToAdd);
                    }
                }
                // now forceOnBody[i] holds the force vector exerted on body with index i.

                // for each body (with index i): move it according to the total force exerted on it.
                for (int i = 0; i < bodies.size(); i++) {
                    bodies.get(i).move(forceOnBody[i]);
//
                }

                // show all movements in StdDraw canvas only every 3 hours (to speed up the simulation)
                if (seconds % (3 * 3600) == 0) {
                    // clear old positions (exclude the following line if you want to draw orbits).
                    StdDraw.clear(StdDraw.BLACK);

                    // draw new positions
                    for (int i = 0; i < bodies.size(); i++) {
                        bodies.get(i).draw();
                    }

                    // show new positions
                    StdDraw.show();
                }

            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }





    }

    //TODO: remove static methods below.


}

//TODO: answer additional questions of 'Aufgabenblatt2'.
/*
1. Datenkapselung: Das Zusammenfügen von Variablen und Methoden in Klassen.
2. Data hiding: Das Verstecken von Implementierungsdetails vor Zugriffen von außen bzw. möglichst viele Variablen und Methoden private zu sein
 */



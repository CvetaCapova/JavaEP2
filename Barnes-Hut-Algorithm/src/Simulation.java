public class Simulation {

    public static final double AU = 1e11; // 150e9;
    public static final int N = 10000;

    public static final double Max_Mass = 1e36;    // 1.989e30 = 2e30
    public static final double Min_Mass = 1e23;    // 3.301e23 = 3.5e23
    public static final double Max_Radius = 1e10;  // 696340e3 = 7e8
    public static final double Min_Radius = 1e2;   // 2.4397e3 = 2.5e3
    public static final double Max_Speed = 1e8;    // 47.87e3 = 50e3
    public static final double Min_Speed = -1e8;   // -47.87e3 = -50e3

     public static void main(String[] args) {

        CelestialBody[] random = new CelestialBody[N];

        for (int i = 0; i < N; i++) {
            //random[i] = new CelestialBody().generateRandom(2, 2, 2);
            /*if (i % 3 == 0) {
                random[i] = new CelestialBody().generateRandom(0.2, 0.2,0.2, 0.2,0.2, 0.2);
            } else if (i % 3 == 1) {
                random[i] = new CelestialBody().generateRandom(0.2, 0.7,0.2, 0.7,0.2, 0.7);
            } else {
            random[i] = new CelestialBody().generateRandom(1, 0,1, 0,1, 0);
            }*/

            if (i % 3 == 0) {
                random[i] = new CelestialBody().generateRandom(-Math.random(), 1, -Math.random(), 1, -Math.random(), 1);
            } else {
                random[i] = new CelestialBody().generateRandom(Math.random(), 0, Math.random(), 0, Math.random(), 0);
            }
        }

        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(-2 * AU, 2 * AU);
        StdDraw.setYscale(-2 * AU, 2 * AU);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);

        StdDraw.show();

//         For each step of the simulation, create a new Barnes-Hut tree from scratch, and insert all of the bodies.
//         After inserting all of the bodies, reset the net forces acting on each body and call updateForce for each body to re-calculate them.
//         Then, update the positions of the bodies and plot them

        StdDraw.show();

        while (true) {
            Octtree tree = new Octtree(new Vector3D(-2 * AU, -2 * AU, -2 * AU), 4 * AU);
            for (int i = 0; i < N; i++) {
                tree.add(random[i]);
            }

            tree.updateAllForces();
            tree.move();

            // clear old positions (exclude the following line if you want to draw orbits).
            StdDraw.clear(StdDraw.BLACK);
            // draw new positions
            tree.draw();
            // show new positions
            StdDraw.show();
        }
    }
}
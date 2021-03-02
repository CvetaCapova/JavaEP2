public class MyTestCelestialSystemIndex {
    public static void main(String[] args) {
        CelestialBody sun = new CelestialBody("Sol", 1.989e30, 696340e3, new Vector3(0, 0, 0), new Vector3(0, 0, 0), StdDraw.YELLOW);
        CelestialBody earth = new CelestialBody("Earth", 5.972e24, 6371e3, new Vector3(148e9, 0, 0), new Vector3(0, 29.29e3, 0), StdDraw.BLUE); //mass-kg, radius-meters
        CelestialBody mercury = new CelestialBody("Mercury", 3.301e23, 2.4397e3, new Vector3(-46.0e9, 0, 0), new Vector3(0, -47.87e3, 0), StdDraw.RED);
        CelestialBody europa = new CelestialBody("Europa", 1.989e30, 696340e3, new Vector3(0, 0, 0), new Vector3(0, 0, 0), StdDraw.YELLOW);
        CelestialBody moon = new CelestialBody("Moon", 1.989e30, 696340e3, new Vector3(0, 0, 0), new Vector3(0, 0, 0), StdDraw.YELLOW);
        CelestialBody jupiter = new CelestialBody("Jupiter", 1.989e30, 696340e3, new Vector3(0, 0, 0), new Vector3(0, 0, 0), StdDraw.YELLOW);
        CelestialBody neptun = new CelestialBody("Neptun", 1.989e30, 696340e3, new Vector3(0, 0, 0), new Vector3(0, 0, 0), StdDraw.YELLOW);
        CelestialBody venus = new CelestialBody("Venus", 1.989e30, 696340e3, new Vector3(0, 0, 0), new Vector3(0, 0, 0), StdDraw.YELLOW);
        CelestialBody mars = new CelestialBody("Mars", 1.989e30, 696340e3, new Vector3(0, 0, 0), new Vector3(0, 0, 0), StdDraw.YELLOW);
        CelestialBody io = new CelestialBody("Io", 1.989e30, 696340e3, new Vector3(0, 0, 0), new Vector3(0, 0, 0), StdDraw.YELLOW);
        CelestialBody kalisto = new CelestialBody("Kalisto", 1.989e30, 696340e3, new Vector3(0, 0, 0), new Vector3(0, 0, 0), StdDraw.YELLOW);
        CelestialBody nothing = new CelestialBody();

//        System.out.println(sun.hashCode());
//        System.out.println(sun.hashCode());
//        System.out.println(earth.hashCode());
//        System.out.println(sun.equals(sun));

        CelestialSystem bodies = new CelestialSystem("Solar system");
        bodies.add(earth);
        bodies.add(sun);
        bodies.add(mercury);
        bodies.add(nothing);

        for (CelestialBody body : bodies) {
            System.out.println(body.getName() + " printed by iterator");
        }
//
//        System.out.println("BODIES SIZE: " + bodies.size());

//        CelestialSystem bodies2 = new CelestialSystem("Solar system 2");
//        bodies2.add(earth);
//        bodies2.add(mercury);
//        bodies2.add(sun);
//        bodies2.add(0,moon);
//        bodies2.add(bodies2.size(),neptun);
//        System.out.println(bodies2.toString());
//        System.out.println(bodies2.reverse().toString());

//        System.out.println("System equals:" + bodies.equals(bodies2) );
//        System.out.println("hash 1:" + bodies.hashCode() );
//        System.out.println("hash 2:" + bodies2.hashCode() );



        CelestialSystem jup = new CelestialSystem("Jup");
        jup.add(jupiter);
        jup.add(europa);
        jup.add(io);
        jup.add(kalisto);

        CelestialSystem jupCopy = new CelestialSystem("JupCopy");
        jup.add(jupiter);
        jup.add(europa);
        jup.add(io);
        jup.add(kalisto);


        CelestialSystem mixed = new CelestialSystem("Mixed");
        mixed.add(neptun);
        mixed.add(mars);
        mixed.add(europa);
        mixed.add(moon);
        mixed.add(2, kalisto);
        mixed.add(10, io);
        mixed.add(mixed.size(), jupiter);
        System.out.println("mixeed " + mixed.toString());

//        CelestialSystemIndexTreeVariant variant = new CelestialSystemIndexTreeVariant();
//        variant.add(bodies);
//        variant.printInOrder(variant.getRoot());

//
//        CelestialSystemIndexTree index = new CelestialSystemIndexTree();
//        index.add(bodies);
//        index.add(jup);
//        index.add(mixed);
//        index.printInOrder(index.getRoot());
//        System.out.println(index.numberOfBodies());
//        System.out.println(index.numberOfSystems());
//        System.out.println(index.get("Io").toString());

//        CelestialSystemIndexMap testSytem = new CelestialSystemIndexMap();
//        testSytem.add(bodies);
//        testSytem.add(null);
//        System.out.println(testSytem.toString());
//
        CelestialSystemIndexMap mapSystem = new CelestialSystemIndexMap();
        mapSystem.add(bodies);
        mapSystem.add(jup);
        System.out.println(mapSystem.toString());

        CelestialSystemIndexMap mapSystem2 = new CelestialSystemIndexMap();
        mapSystem2.add(bodies);
        mapSystem2.add(jupCopy);
        mapSystem2.add(mixed);
        System.out.println(mapSystem2.toString());

        System.out.println(mapSystem.hashCode());
        System.out.println(mapSystem2.hashCode());

        System.out.println(mapSystem.equals(mapSystem2));
        System.out.println(mapSystem.toString().equals(mapSystem2.toString()));

    }
}

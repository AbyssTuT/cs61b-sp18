public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int planetNum = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName) {


        In in = new In(fileName);
        int planetNum = in.readInt();
        Planet[] planets = new Planet[planetNum];
        double radius = in.readDouble();
        for (int i = 0; i < planetNum; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] =  new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return planets;
    }

    public static void main(String[] args) {
        Double T  = Double.parseDouble(args[0]);
        Double dt = Double.parseDouble(args[1]);
        String filename  = args[2];
        double r = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        // set the universe scale
        StdDraw.setXscale(-r, r);
        StdDraw.setYscale(-r, r);
        StdDraw.enableDoubleBuffering();

        int t =0;
        int num = planets.length;

        while(t < T){
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            for (int i = 0; i < num; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            //draw backgroud
            StdDraw.picture(0, 0, "images/starfield.jpg");
            //update  planet
            for (int i = 0; i < num; i++) {
                planets[i].update(dt,xForces[i],yForces[i]);
            }
            //draw planets
            for (Planet planet : planets) {
                planet.draw();
            }

            StdDraw.show();
            //pause for 10 milliseconds
            StdDraw.pause(10);
            t += dt;
        }

        //print out the final state of the universe
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }

}
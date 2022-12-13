import javax.xml.stream.util.StreamReaderDelegate;

public class NBody {
  public static String image_toDrawn = "./images/starfield.jpg";

  public static double readRadius(String file){
    In in = new In(file);
    int firstItem = in.readInt();
    double secondItem = in.readDouble();
    return secondItem;
  }

  public static Planet[] readPlanets(String file){
    In in = new In(file);
    int firstItem = in.readInt();
    double secondItem = in.readDouble();
    Planet[] rlt = new Planet[firstItem];
    for(int i = 0;i < firstItem;i++){
      Planet p = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
      rlt[i] = p;
    }
    return rlt;
  }

  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double radius = NBody.readRadius(filename);
    Planet[] planets = NBody.readPlanets(filename);
    int n = planets.length;

    StdDraw.setScale(-radius,radius);
    StdDraw.enableDoubleBuffering();
    double time = 0;
    while(time <= T){
      double[] xForces = new double[n];
      double[] yForces = new double[n];
      for(int i = 0;i < n;i++){
        xForces[i] = planets[i].calcNetForceExertedByX(planets);
        yForces[i] = planets[i].calcNetForceExertedByY(planets);
      }
      
      for(int i = 0;i < n;i++){
        planets[i].update(dt, xForces[i], yForces[i]);
      }
      // 画出背景
      StdDraw.picture(0, 0, NBody.image_toDrawn);

      for(Planet planet : planets){
        planet.draw();
      }

      StdDraw.show();
      StdDraw.pause(10);
      time += dt;
    }
    StdOut.printf("%d\n", bodies.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < bodies.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
    }
  }
}

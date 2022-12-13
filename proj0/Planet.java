public class Planet {
  // static varible
  public static double G = 6.67e-11;

  double xxPos; // Its current x position
  double yyPos; // Its current y position
  double xxVel; // Its current velocity in the x direction
  double yyVel; // Its current velocity in the y direction
  double mass;  // Its mass
  String imgFileName;

  public Planet(double xP,double yP,double xV,double yV,double m,String img){
    this.xxPos = xP;
    this.yyPos = yP;
    this.xxVel = xV;
    this.yyVel = yV;
    this.mass = m;
    this.imgFileName = img;
  }
  public Planet(Planet b){
    this.xxPos = b.xxPos;
    this.yyPos = b.yyPos;
    this.xxVel = b.xxVel;
    this.yyVel = b.yyVel;
    this.mass = b.mass;
    this.imgFileName = b.imgFileName;
  }

  public double calcDistance(Planet another){
    double xxPos_sub = this.xxPos - another.xxPos;
    double yyPos_sub = this.yyPos - another.yyPos;
    double xxsquare_rlt = Math.pow(xxPos_sub, 2);
    double yysquare_rlt = Math.pow(yyPos_sub, 2);
    double add_rlt = xxsquare_rlt + yysquare_rlt;
    return Math.sqrt(add_rlt);
  }

  public double calcForceExertedBy(Planet another){
    double distance = this.calcDistance(another);
    double square_distance = Math.pow(distance,2);
    // 分子
    double denominator = Planet.G * this.mass * another.mass;
    double F = denominator / square_distance;
    return F;
  }

  // 这个行星所收到的力
  public double calcForceExertedByX(Planet another){
    double dx = another.xxPos - this.xxPos;
    double r = calcDistance(another);
    double rlt = calcForceExertedBy(another) * dx / r;
    return rlt;
  }

  public double calcForceExertedByY(Planet another){
    double dy = another.yyPos - this.yyPos;
    double r = calcDistance(another);
    double rlt = calcForceExertedBy(another) * dy / r;
    return rlt;
  }

  public double calcNetForceExertedByX(Planet[] allPlanet){
    double F_Net_X = 0;
    for(Planet another : allPlanet){
      if(another.equals(this))
        continue;
      else{
        F_Net_X += calcForceExertedByX(another);
      }
    }
    return F_Net_X;
  }

  public double calcNetForceExertedByY(Planet[] allPlanet){
    double F_Net_Y = 0;
    for(Planet another : allPlanet){
      if(another.equals(this))
        continue;
      else{
        F_Net_Y += calcForceExertedByY(another);
      }
    }
    return F_Net_Y;
  }

  public void update(double dt,double Fx,double Fy){
    double ax = Fx / this.mass;
    double ay = Fy / this.mass;
    double vx = this.xxVel + ax * dt;
    double vy = this.yyVel + ay * dt;
    this.xxVel = vx;
    this.yyVel = vy;
    this.xxPos += vx * dt;
    this.yyPos += vy * dt;
  }

  public void draw(){
    StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
    StdDraw.show();
  }
}

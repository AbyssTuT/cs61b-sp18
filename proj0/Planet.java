/**
 * class Planet and Constructors
 */
public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double G = 6.67e-11;
	
	public Planet(double xxPos,double yyPos,double xxVel,double yyVel,double mass,String imgFileName){
		this.xxPos =xxPos;
		this.yyPos= yyPos;
		this.xxVel = xxVel;
		this.yyVel = yyVel;
		this.mass = mass;
		this.imgFileName = imgFileName;
	}
	
	public Planet(Planet p){
		this.xxPos =p.xxPos;
		this.yyPos= p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}
	
	public double calcDistance(Planet p){
		return Math.sqrt((p.xxPos -this.xxPos) * (p.xxPos -this.xxPos) + (p.yyPos -this.yyPos) * (p.yyPos -this.yyPos));
	}
	
	public double calcForceExertedBy(Planet p){
		return G*this.mass*p.mass/calcDistance(p)/calcDistance(p);
	}
	
	public double calcForceExertedByX(Planet p){
		return calcForceExertedBy(p)*(p.xxPos - this.xxPos)/calcDistance(p);
	}
	
	public double calcForceExertedByY(Planet p){
		return calcForceExertedBy(p)*(p.yyPos - this.yyPos)/calcDistance(p);
	}
	
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double x = 0;
		for(Planet p : allPlanets){
			if (this.equals(p)){
				continue;
			}else{
				x += calcForceExertedByX(p);
			}
		}
		return x;
	}
	
	public double calcNetForceExertedByY(Planet[] allPlanets){
		double y = 0;
		for(Planet p : allPlanets){
			if (this.equals(p)){
				continue;
			}else{
				y += calcForceExertedByY(p);
			}
		}
		return y;
	}
	
	public void update(double dt, double fX, double fY){
		this.xxVel +=dt*fX/this.mass;
		this.yyVel +=dt*fY/this.mass;
		this.xxPos +=dt*this.xxVel;
		this.yyPos +=dt*this.yyVel;
	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/"+imgFileName);
		StdDraw.show();
	}
}
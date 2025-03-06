public class Planet
{
    public double xxPos;    //Its current x position
    public double yyPos;    //Its current y position
    public double xxVel;    //Its current velocity in the x direction
    public double yyVel;    //Its current velocity in the y direction
    public double mass;     //Its mass
    public String imgFileName;  //The name of the file that corresponds to the image that depicts the body

    public Planet(double xP,double yP,double xV,double yV,double m,String img)
    {
        xxPos =xP;
        yyPos =yP;
        xxVel =xV;
        yyVel =yV;
        mass =m;
        imgFileName =img;
    }

    public Planet(Planet p)
    {
        xxPos =p.xxPos;
        yyPos =p.yyPos;
        xxVel =p.xxVel;
        yyVel =p.yyVel;
        mass =p.mass;
        imgFileName =p.imgFileName;
    }

    public double calcDistance(Planet that)
    {
        double dx;
        double dy;
        dx=(this.xxPos - that.xxPos);
        dy=(this.yyPos - that.yyPos);
        return Math.sqrt(dx*dx + dy*dy);
    }

    public double calcForceExertedBy(Planet that)
    {
        return ((6.67 * Math.pow(10,-11) * this.mass * that.mass) / (Math.pow(calcDistance(that),2)));
    }

    public double calcForceExertedByX(Planet that)
    {
        double dx=(that.xxPos - this.xxPos);
        return this.calcForceExertedBy(that) *dx / this.calcDistance(that);
    }

    public double calcForceExertedByY(Planet that)
    {
        double dy=(that.xxPos - this.xxPos);
        return this.calcForceExertedBy(that) *dy / this.calcDistance(that);
    }

    public double calcNetForceExertedByX(Planet[] planets)
    {
        double totalforce =0.0;
        for(Planet planet:planets)
        {
            if(this.equals(planet))
                continue;
            totalforce += this.calcForceExertedByX(planet);
        }
        return  totalforce;
    }

    public double calcNetForceExertedByY(Planet[] planets)
    {
        double totalforce =0.0;
        for(Planet planet:planets)
        {
            if(this.equals(planet))
                continue;
            totalforce += this.calcForceExertedByY(planet);
        }
        return  totalforce;
    }

    public void update(double s,double xN,double yN)
    {
        double xAcc = xN / mass;
        double yAcc = yN / mass;
        xxVel += xAcc * s;
        yyVel += yAcc * s;
        xxPos += xxVel * s;
        yyPos += yyVel * s;
    }
    public void draw()
    {
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
        return;
    }
}

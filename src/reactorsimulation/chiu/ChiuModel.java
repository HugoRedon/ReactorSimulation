package reactorsimulation.chiu;

/**
 *
 * @author Hugo Redon Rivera
 */
public class ChiuModel {


    public  double e(double Tr){
           double dm = dm(Tr);
           double dp = dp();

           return (dm - dp)/ dp;

       }

    protected double kp(double Tr, double x,double lambda_0){
           double kp0 = kp0(Tr);
           double tetha_p = theta_p(Tr);
           double fi_m = fi_m(Tr, x);

           double A = A(Tr);
           double B = B();

           //double lambda_0 = lambda_0();

           double result = (1/kp0) + tetha_p * lambda_0 / Math.exp(2.3 * fi_m / ( A + B * fi_m));

           return 1/result;

       }
    protected double theta_p(double Tr){
      
        double b = 35.1285;
        double m = -13976.6302;
        double x = 1d/Tr;
        
       return Math.exp(-m *x - b);
         // return 3.5e3;
       
       }

    protected double fi_m(double Tr, double x){
           double e = e(Tr);
           return( 1- x) / (1 + e * x);

       }
    
    
    
      
   protected double f(){
       return 0.58;
   }
    protected double kd(double Tr){
       return 6.32e16 * Math.exp(-15.43e3 / Tr);
   }
   protected double kt0(double Tr){
       return 5.88e9 * Math.exp(-701.0d / (1.987* Tr));
        //return 1e7 * Math.exp(-701.0d / (1.987* Tr));
   }
    protected double kp0(double Tr){
           return 2.95e7 * Math.exp(-4353d / (1.987 * Tr));
       }
    protected double ktc(){
       return 0;
   }

    protected  double dp(){
           return 1.2;
       }
    protected  double dm(double Tr){
           return 0.973 - 1.164e-3 * (Tr- 273);
       }
protected double tgp(){
       return 114;
   }
   
   
   protected double theta_t(double Tr, double I0){
//       review the dependency of this parameter with the initial concentration I0
       double b = I0 *103.349418604651 +44.466978;
       //double m = -17469.5;
       double m = -19095.0694767439 * I0  -17075.6213385;
       
//       double m = -17371;
//       double b = 46.067;
       double x = 1d/ Tr;
       
    return Math.exp(- m * x - b); 
       
      // return 83; // 2.33e3;
   }
   

   
   protected double kt(double Tr, double I0,double x,double lambda_0){
       double kt0 = kt0(Tr);
       double tetha_t = theta_t(Tr, I0);
       double fi_m = fi_m(Tr, x);
       
       double A = A(Tr);
       double B = B();
       
       //double lambda_0 = lambda_0();
       
       double result = (1d/kt0) + tetha_t * lambda_0 / Math.exp(2.3 * fi_m / ( A + B * fi_m));
       
       return 1d/result;
       
   }


    protected double A(double Tr){

           double x = Math.pow(Tr-273.15-tgp(),2);

           // from paper 
           double m = -0.008247;
           double b = 167.832701;
           
           

           return (m * x + b)/1000;
        //  return 0.134;

       }

    protected double B(){
           return 0.03;
       }

}

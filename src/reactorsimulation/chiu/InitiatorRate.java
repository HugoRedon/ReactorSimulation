package reactorsimulation.chiu;

import differentialEquation.DifferentialEquation;

/**
 *
 * @author Hugo Redon Rivera
 */
public class InitiatorRate extends ChiuModel implements DifferentialEquation{

    @Override
    public double dydx(double t, double... y) {
        double Tr = y[0];
        double Tj = y[1];
        double I = y[2];
        double x = y[3];
        double lambda_o = y [4];
        
        double kd = kd(Tr);
      
        double e = e(Tr);
        double kp = kp(Tr, x, lambda_o);
        
        return -kd * I - ((e * I )/(1 + e* x) )* lambda_o * ( 1-x)* kp;
    //    return I;
    }
    

    
}

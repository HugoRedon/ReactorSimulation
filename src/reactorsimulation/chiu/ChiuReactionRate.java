package reactorsimulation.chiu;

import differentialEquation.DifferentialEquation;

/**
 *
 * @author Hugo Redon Rivera
 * 
 */
public class ChiuReactionRate extends ChiuModel implements DifferentialEquation{

    private double dummy=0;
    private double startAt;
    
//    private double Tset ;
    @Override
    public double dydx(double t, double... y) {
        double Tr = y[0]; 
        
        double x = y[3];
        double lambda_0 = y[4];
        
        double kp = kp(Tr, x, lambda_0);
        
         dummy = ( /*Tr < getTset()  &*/ t < startAt)? dummy:1; 
        
        return dummy *kp * (1-x) * lambda_0;
        
    }
    
    private double Cao;

    /**
     * @return the Cao
     */
    public double getCao() {
        return Cao;
    }

    /**
     * @param Cao the Cao to set
     */
    public void setCao(double Cao) {
        this.Cao = Cao;
    }
//
//    /**
//     * @return the Tset
//     */
//    public double getTset() {
//        return Tset;
//    }
//
//    /**
//     * @param Tset the Tset to set
//     */
//    public void setTset(double Tset) {
//        this.Tset = Tset;
//    }

    /**
     * @return the startAt
     */
    public double getStartAt() {
        return startAt;
    }

    /**
     * @param startAt the startAt to set
     */
    public void setStartAt(double startAt) {
        this.startAt = startAt;
    }
    
  
   

    
}

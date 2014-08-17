package reactorsimulation;

import differentialEquation.DifferentialEquation;
import reactorsimulation.chiu.ChiuModel;
import reactorsimulation.chiu.ChiuReactionRate;

/**
 *
 * @author Hugo Redon Rivera
 */
public class ReactorBalance implements differentialEquation.DifferentialEquation{

    private double  A;
    private double U; 
    private double cp;
    private double density;
    private double Volume;
    private double heatOfReaction;
    private double PM;
    
    private DifferentialEquation reactionRate;
    
//    @Override
//    public double dydx(double x, double... y) {
//        double Tr = y[0];
//        double Tj = y[1];
//        
//        return - ((U * A )/(density * cp * Volume)) * (Tr - Tj);
//    }
    
       @Override
    public double dydx(double t, double... y) {
        double Tr = y[0];
        double Tj = y[1];
        double x = y[3];
        
        ChiuReactionRate rr =(ChiuReactionRate) getReactionRate();
        double cao = rr.getCao();
        double Rp =- rr.dydx(t, y) * cao;
        double e = rr.e(Tr);
        
        double volume = Volume *( 1 - x * e) ;
        
        return - ((U * A )/(density * cp * volume)) * (Tr - Tj) +  (getHeatOfReaction() / (getDensity() * getCp())) * Rp;
    }

    /**
     * @return the A
     */
    public double getA() {
        return A;
    }

    /**
     * @param A the A to set
     */
    public void setA(double A) {
        this.A = A;
    }

    /**
     * @return the U
     */
    public double getU() {
        return U;
    }

    /**
     * @param U the U to set
     */
    public void setU(double U) {
        this.U = U;
    }

    /**
     * @return the cp
     */
    public double getCp() {
        return cp;
    }

    /**
     * @param cp the cp to set
     */
    public void setCp(double cp) {
        this.cp = cp;
    }

    /**
     * @return the density
     */
    public double getDensity() {
        return density;
    }

    /**
     * @param density the density to set
     */
    public void setDensity(double density) {
        this.density = density;
    }

    /**
     * @return the Volume
     */
    public double getVolume() {
        return Volume;
    }

    /**
     * @param Volume the Volume to set
     */
    public void setVolume(double Volume) {
        this.Volume = Volume;
    }

    /**
     * @return the reactionRate
     */
    public DifferentialEquation getReactionRate() {
        return reactionRate;
    }

    /**
     * @param reactionRate the reactionRate to set
     */
    public void setReactionRate(DifferentialEquation reactionRate) {
        this.reactionRate = reactionRate;
    }

    /**
     * @return the PM
     */
    public double getPM() {
        return PM;
    }

    /**
     * @param PM the PM to set
     */
    public void setPM(double PM) {
        this.PM = PM;
    }

    /**
     * @return the heatOfReaction
     */
    public double getHeatOfReaction() {
        return heatOfReaction;
    }

    /**
     * @param heatOfReaction the heatOfReaction to set
     */
    public void setHeatOfReaction(double heatOfReaction) {
        this.heatOfReaction = heatOfReaction;
    }
    
}

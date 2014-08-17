/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reactorsimulation;

/**
 *
 * @author Chilpayate
 */
public class ReactionRate implements differentialEquation.DifferentialEquation {

    private double A;
    private double EA;
    private double R;
    private double Tset;
    private double Ca0;
    
    private double dummy = 0;
    @Override
    public double dydx(double x, double... y) {
        
        double Tj = y[0];
        double Ca = y[2];
        
        dummy = ( Tj < Tset )? dummy:1; 
        return -dummy* getA() * Math.exp(-  EA /(R * Tj)) * Math.pow(Ca,2);  
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
     * @return the EA
     */
    public double getEA() {
        return EA;
    }

    /**
     * @param EA the EA to set
     */
    public void setEA(double EA) {
        this.EA = EA;
    }

    /**
     * @return the R
     */
    public double getR() {
        return R;
    }

    /**
     * @param R the R to set
     */
    public void setR(double R) {
        this.R = R;
    }

    /**
     * @return the Tset
     */
    public double getTset() {
        return Tset;
    }

    /**
     * @param Tset the Tset to set
     */
    public void setTset(double Tset) {
        this.Tset = Tset;
    }

    /**
     * @return the Ca0
     */
    public double getCa0() {
        return Ca0;
    }

    /**
     * @param Ca0 the Ca0 to set
     */
    public void setCa0(double Ca0) {
        this.Ca0 = Ca0;
    }
    
}

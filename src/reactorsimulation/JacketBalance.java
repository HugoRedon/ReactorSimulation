package reactorsimulation;

/**
 *
 * @author Hugo Redon Rivera
 */
public class JacketBalance implements differentialEquation.DifferentialEquation{
    
   
    private double density;
    private double volume;
    private double U;
    private double A;
    private double F;
    private double Th;
    private double Tc;
    private double cp;
    
    private double Tset;
    
  //  private double Controller controller;
    
    @Override
    public double dydx(double x, double... y) {
          double Tr = y[0];
         double Tj = y[1];
        
        double   s = valve(Tr);
         
         return ((U * A) / (density * getCp() * volume)) * (Tr - Tj) + (F / ( volume))*(s * Th + (1-s)* Tc - Tj);
        
    }
    
    private double valve(double Tr){
        
        double steadyS = (Tset - Tc)/(Th- Tc);
        double k = 1;
        
        double calcs = steadyS + k * (Tset- Tr);
        
        if ( calcs > 1){
            return 1;
        }else if ( calcs < 0 ){
            return 0;
        }
     
        return calcs;
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
     * @return the volume
     */
    public double getVolume() {
        return volume;
    }

    /**
     * @param volume the volume to set
     */
    public void setVolume(double volume) {
        this.volume = volume;
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
     * @return the F
     */
    public double getF() {
        return F;
    }

    /**
     * @param F the F to set
     */
    public void setF(double F) {
        this.F = F;
    }

    /**
     * @return the Th
     */
    public double getTh() {
        return Th;
    }

    /**
     * @param Th the Th to set
     */
    public void setTh(double Th) {
        this.Th = Th;
    }

    /**
     * @return the Tc
     */
    public double getTc() {
        return Tc;
    }

    /**
     * @param Tc the Tc to set
     */
    public void setTc(double Tc) {
        this.Tc = Tc;
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
    
}

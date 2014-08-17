package reactorsimulation.chiu;

/**
 *
 * @author Hugo Redon Rivera
 */
public class Lambda0Rate extends ChiuModel implements differentialEquation.DifferentialEquation {
    

    private double io;
    
    @Override
    public double dydx(double t, double... y) {
          double Tr = y[0];
        double Tj = y[1];
        double I = y[2];
        double x = y[3];
        double lambda_o = y [4];
        
        double e = e(Tr);
        double kp = kp(Tr, x, lambda_o);
        double f = f();
        double kd = kd(Tr);
        double kt = kt(Tr, io/*for parameter calculation must not change with reaction course*/, x, lambda_o);
        
        return (-e * Math.pow(lambda_o,2) * (1-x) * kp / (1 + e * x) +  2 * f * kd * I  - kt* Math.pow(lambda_o,2));
 
    }

    /**
     * @return the io
     */
    public double getIo() {
        return io;
    }

    /**
     * @param io the io to set
     */
    public void setIo(double io) {
        this.io = io;
    }
    
}

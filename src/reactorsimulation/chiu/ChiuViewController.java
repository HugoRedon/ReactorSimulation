/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reactorsimulation.chiu;

import differentialEquation.ODESystem;
import differentialEquation.solver.euler.Euler;
import differentialEquation.solver.rungekutta.RungeKutta4;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Chilpayate
 */
public class ChiuViewController implements Initializable {

    
    @FXML LineChart chiuChart;
    @FXML LineChart tethaChart,aChart;
    
    @FXML TextField tempTextField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tempTextField.setText("50");
        i0TextField.setText("0.01548");
        stepTextField.setText("0.001");
        lastXTextField.setText("375");
        
    }    
    
    @FXML protected void experimental70(ActionEvent event){
               ObservableList<XYChart.Data<Double,Double>> datas = FXCollections.observableArrayList(
                               
                new Data<>(0d,0d),
                new Data<>(10.4717,0.0509),
                new Data<>( 15.283,0.0862),
                
                new Data<>( 20.0943,0.1144),
                new Data<>( 32.4057,0.1906),
                
                new Data<>( 49.9528,0.2797),
                
                new Data<>( 60.4245,0.3812),
                
                new Data<>( 65.3774,0.4854),
                
                new Data<>( 70.3302,0.8258),
                
                new Data<>( 85.4717,0.8993),
                new Data<>( 90.283,0.9079),
                new Data<>(100.3302,0.9152)
        
                );
        
        Series expData = new Series(datas);
        
        chiuChart.getData().add(expData);
    }
    
    @FXML protected void experimental(ActionEvent event){
        
        ObservableList<XYChart.Data<Double,Double>> datas = FXCollections.observableArrayList(
                new Data<>(0d,0d),
                new Data<>( 60.3529	,0.0647),
                new Data<>( 83.2039	,0.0912),
                new Data<>( 119.5384,	0.1207),
                new Data<>( 159.9668,	0.1677),
                new Data<>( 199.2312,	0.2002),
                new Data<>( 219.1351,	0.2529),
                new Data<>( 239.6322,	0.2939),
                new Data<>( 249.5808,	0.3261),
                new Data<>( 261.866,	0.3729),
                new Data<>( 271.7735,	0.4752),
                new Data<>( 279.3716,	0.5161),
                new Data<>( 289.8346,	0.671),
                new Data<>( 299.1575,	0.7703),
                new Data<>( 299.1386,	0.8024),
                new Data<>( 319.6392,	0.8376),
                new Data<>( 338.3945,	0.8495),
                new Data<>( 368.2897,	0.8614)
                
                );
        
        Series expData = new Series(datas);
        
        chiuChart.getData().add(expData);
        
    }
    
    @FXML protected void i270Experimental (ActionEvent event){
 
      ObservableList<XYChart.Data<Double,Double>> datas = FXCollections.observableArrayList(
                new Data<>(0d,0d),
                new Data<>( 5.3774	,		0.0395),
                new Data<>( 9.7642	,		0.0776),
                new Data<>( 20.0943	,		0.1482),
                new Data<>(35.0943	,		0.2512),
                new Data<>(45.283	,		0.333),
                new Data<>( 55.4717	,		0.5147),
                new Data<>( 55.3302	,		0.5273),
                new Data<>( 60.283	,		0.7637),
                new Data<>( 65.2358	,		0.8735),
                new Data<>( 70.0472	,		0.8919),
                new Data<>(79.9528	,		0.9133),
                new Data<>( 80.0943	,		0.8978)
       
                );
        
        Series expData = new Series(datas);
        
        chiuChart.getData().add(expData);
        
    }
    
    
    @FXML TextField i0TextField,lastXTextField,stepTextField;
    @FXML  protected void solve(ActionEvent event){
        ConstantReactorTemp fake1 = new ConstantReactorTemp();
        ConstantReactorTemp fake2 = new ConstantReactorTemp();

        double i0 = Double.valueOf(i0TextField.getText());
        double lastx = Double.valueOf(lastXTextField.getText());
        double step = Double.valueOf(stepTextField.getText());
        
        
         InitiatorRate i = new InitiatorRate();
        ChiuReactionRate x = new ChiuReactionRate();
       Lambda0Rate L0 = new Lambda0Rate();
       
       L0.setIo(i0);
//         x.setTset(-10);
         x.setStartAt(0);
       
       ODESystem chiuSis = new ODESystem(fake1, fake2,i,x,L0);
       
       RungeKutta4 rk4 = new RungeKutta4();
       
       //double lastx = 360;
//       double step = lastx  / 800;
//       step = 0.001;
       
       double Temp = Double.valueOf(tempTextField.getText()) +273.15;
       
       double[] initialCondition = {0,Temp,0,i0,0,0};
        double[][] result =  rk4.solveOde(chiuSis, initialCondition, lastx, step);
 
        //Series expData = new Series(datas);
       Series model = new Series();
        for( int w = 0; w < result.length; w += 1000){
            double[] res = result[w];
            
            double t = res[0];
            double Tr = res[1];
            double ic = res[3];
            double xc = res[4];
            double lc = res[5];
            
           //double logk = Math.log(x.kt(Tr, i0, xc, lc));
            
//            if(Double.isNaN(xc) ||Double.isNaN(logk) || Double.isInfinite(logk) ){
//                continue;
//            }
            if(Double.isNaN(xc)|| Double.isInfinite(xc)) {
                continue;
            }
             
            model.getData().add(new XYChart.Data<Number,Number>(t,xc));
//            model.getData().add(new XYChart.Data<Number,Number>(t1,y2s));
//            convSeries.getData().add(new XYChart.Data<Number,Number>(t1,x));
        }
        
        chiuChart.getData().add(model);    
    }

    protected void a(){
       
         double i0 = Double.valueOf(i0TextField.getText());
        
        ChiuModel m = new ChiuModel();
        
        Series a = new Series();
        
        for (double T : temps){
            double x = Math.pow(T-273.15- m.tgp(),2);
            double y = m.A(T);
            
            a.getData().add(new Data(x,y));
        }
        
        aChart.getData().add(a);
        
        
    }
     double[] temps = {50+273.15,70 + 273.15, 90+273.15};
    @FXML  protected void tetha(ActionEvent event){
  
           double i0 = Double.valueOf(i0TextField.getText());
        ChiuModel m = new ChiuModel();
        
       
        Series tethap = new Series();
        Series tethat = new Series();
        Series tethat2 = new Series();
        
        Series tethaExp = new Series();
        
        for(double T: temps){
            double x = 1/T;
            double yp = Math.log(1/m.theta_p(T));
            double yt = Math.log(1/m.theta_t(T,i0));
            
            double y2t = Math.log(1/ m.theta_t(T, 0.0258));
            
            
            
            tethap.getData().add(new Data(x, yp));
            tethat.getData().add(new Data(x,yt));
           tethat2.getData().add(new Data(x, y2t));
        }
        tethaChart.getData().addAll(tethap,tethat,tethat2);
        a();
    }
    
    @FXML protected void articulo(ActionEvent event){
        Series tethapExp = new Series();
        Series tethatExp = new Series();
        Series tethat2Exp = new Series();
        Series aexp = new Series();

        tethapExp.getData().add(new Data(0.003094538, -8.160518247));
        tethapExp.getData().add(new Data(0.002914177,-5.521460918));
        tethapExp.getData().add(new Data(0.002753683, -3.401197382));
        
        tethatExp.getData().add(new Data(0.003094538	,-7.313220387));
        tethatExp.getData().add(new Data(0.002914177,	-3.891820298));
        tethatExp.getData().add(new Data(0.002753683,	-1.335001067));
        
       tethat2Exp.getData().add(new Data(0.003094538 ,  -7.753623547));
        tethat2Exp.getData().add(new Data(0.002914177  , -4.418840608));
        tethat2Exp.getData().add(new Data(0.002753683  ,  -1.840549633));
    
	

        aexp.getData().add(new Data(4096, 0.134));
        aexp.getData().add(new Data(1936, 0.152));
        aexp.getData().add(new Data(576, 0.163));
        
       
        tethaChart.getData().addAll(tethapExp,tethatExp,tethat2Exp);
        aChart.getData().add(aexp);
        
    }
    
    
}

class ConstantReactorTemp implements differentialEquation.DifferentialEquation{
    
    @Override
    public double dydx(double x, double... y) {
       return 0;
    }
    
}



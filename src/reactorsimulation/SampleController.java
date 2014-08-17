package reactorsimulation;

import differentialEquation.DifferentialEquation;
import differentialEquation.F1;
import differentialEquation.ODESystem;
import differentialEquation.solver.euler.Euler;
import differentialEquation.solver.rungekutta.RungeKutta4;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import reactorsimulation.chiu.ChiuModel;
import reactorsimulation.chiu.ChiuReactionRate;
import reactorsimulation.chiu.InitiatorRate;
import reactorsimulation.chiu.Lambda0Rate;

/**
 *
 * @author Hugo Redon Rivera
 */
public class SampleController implements Initializable {
    
    @FXML AnchorPane anchor ;
    @FXML LineChart<Number,Number> chart, xchart;
    @FXML TextField y0TextField,x0TextField,xLasTextField,y20TextField,tsetTextField;
    
    @FXML TextField u, a, th, tc, dr, cpr, vr, dj, cpj, vj , f;
    @FXML TextField convTextField, hr;
    
    RungeKutta4 rk4 = new RungeKutta4();
    DifferentialEquation f1 = new F1();
    
    Series<Number,Number> serie = new XYChart.Series<>();
    
    ObservableList<XYChart.Data<Number,Number>> data = FXCollections.observableArrayList();    
    
    @FXML protected void drawSolution(ActionEvent event){
//        data.clear();
        
        double x0 = Double.parseDouble(x0TextField.getText());
        double y0 = Double.parseDouble(y0TextField.getText());
        double lastX =  Double.parseDouble(xLasTextField.getText());
        
        double[] initialCondition = {x0,y0};
        double step = 1;
        double[][] result = rk4.solve(f1, initialCondition, lastX, step);
        
        //for( double[] xy : result){
        for(int i = 0; i <result.length; i++){
            
            double[] xy = result[i];
            
            if( data.size() <= i){
                data.add(new XYChart.Data<Number,Number>());
            }
            XYChart.Data d = data.get(i);
            d.setXValue(xy[0]);
            d.setYValue(xy[1]);
            //XYChart.Data d = new XYChart.Data<> (xy[0],xy[1]) ;
           // serie.getData().add(d);
        }
        //chart.getData().add(serie);

    }
    
  
    
   @FXML  TextField ioTextField;
    
    
        @FXML protected void solveReactorSimulation(ActionEvent event){
        
        double area = Double.valueOf(a.getText());//71.71;
        double U = Double.valueOf(u.getText());//942.4 / 60;
        double Th = Double.valueOf(th.getText());// 347.15;//74 + 273.15;
        double Tc = Double.valueOf(tc.getText());//  307.15;//34.14 + 273.15;
        double Tset = Double.valueOf(tsetTextField.getText()); //330;
        
        // for reactor 
        double reactorDensity = Double.valueOf(dr.getText()); // 878;
        double reactorCp =  Double.valueOf(cpr.getText()); //2.477;
        double reactorVolume = Double.valueOf(vr.getText());// 12;
        
        // for jacket
        double jacketDensity = Double.valueOf(dj.getText()); //  1000;
        double jacketCp = Double.valueOf(cpj.getText());//4.27;
        double jacketVolume =Double.valueOf(vj.getText()); // 10;
        double F = Double.valueOf(f.getText());  //220  * 1000/ 60; 
        
        
        // for reaction;
//        double preexponencialA = Double.valueOf(pa.getText());
//        double EA = Double.valueOf(ea.getText());
//        double R = Double.valueOf(r.getText());
       // double PM = Double.valueOf(pm.getText());
        double Hr = Double.valueOf(hr.getText());
        double Ca0 = Double.valueOf(convTextField.getText());
        double io = Double.valueOf(ioTextField.getText());
        
        
        ReactorBalance rb = new ReactorBalance();
            rb.setA(area);
            rb.setCp(reactorCp);
            rb.setDensity(reactorDensity);
            rb.setVolume(reactorVolume);
            rb.setU(U);
//            rb.setPM(PM);
            rb.setHeatOfReaction(Hr);
            
         JacketBalance jb = new JacketBalance();
            jb.setA(area);
            jb.setU(U);
            jb.setCp(jacketCp);
            jb.setDensity(jacketDensity);
            jb.setF(F);
            jb.setTc(Tc);
            jb.setTh(Th);
            jb.setVolume(jacketVolume);
            jb.setTset(Tset);
            
//        ReactionRate rr = new ReactionRate();
//            rr.setA(preexponencialA);
//            rr.setEA(EA);
//            rr.setR(R);
//            rr.setCa0(Ca0);
//            rr.setTset(Tset);
//            
//            rb.setReactionRate(rr);
            
            
            
            InitiatorRate ir = new InitiatorRate();
            ChiuReactionRate crr = new ChiuReactionRate();
            Lambda0Rate l0 = new Lambda0Rate();
            
            l0.setIo(io);
            
            crr.setCao(Ca0);
//            crr.setTset(Tset);
            crr.setStartAt(2000);
            
            rb.setReactionRate(crr);
            
        ODESystem reactorODE = new ODESystem(rb,jb,ir,crr,l0);
    
        
        double t = Double.parseDouble(x0TextField.getText());
        double Tr0 = Double.parseDouble(y0TextField.getText());
        double Tj0= Double.parseDouble(y20TextField.getText());
        double conv0 = Double.parseDouble(convTextField.getText());
        double lastX =  Double.parseDouble(xLasTextField.getText());
        
        double[] initialCondition = {t,Tr0,Tj0,io,conv0,0};
       // double lastX = 5;
       // double stepPaint = (lastX - t) / 00;
        double step = 0.007;
        
           double[][] result = rk4.solveOde(reactorODE,initialCondition, lastX, step);
        
        Series<Number,Number> y1Series = new Series<>();
        y1Series.setName("Tr[K]");
        Series<Number,Number> y2Series = new Series<>();
        y2Series.setName("Tj[K]");
        Series<Number,Number> convSeries = new Series<>();
        convSeries.setName("Conversión");
        
        double stepPaint = result.length / 100;
        
        for( int i = 0; i<result.length; i += stepPaint){
            
            double[] res = result[i];
            double t1 = res[0];
            double y1s = res[1];
            double y2s = res[2];
            double x = res[4];
            
            
            y1Series.getData().add(new XYChart.Data<Number,Number>(t1,y1s));
            y2Series.getData().add(new XYChart.Data<Number,Number>(t1,y2s));
            convSeries.getData().add(new XYChart.Data<Number,Number>(t1,x));
        }
        
        chart.getData().addAll(y1Series,y2Series);
        xchart.getData().add(convSeries);
        
        
    }
    
//    @FXML protected void solveReactorSimulation(ActionEvent event){
//        
//        double area = Double.valueOf(a.getText());//71.71;
//        double U = Double.valueOf(u.getText());//942.4 / 60;
//        double Th = Double.valueOf(th.getText());// 347.15;//74 + 273.15;
//        double Tc = Double.valueOf(tc.getText());//  307.15;//34.14 + 273.15;
//        double Tset = Double.valueOf(tsetTextField.getText()); //330;
//        
//        // for reactor 
//        double reactorDensity = Double.valueOf(dr.getText()); // 878;
//        double reactorCp =  Double.valueOf(cpr.getText()); //2.477;
//        double reactorVolume = Double.valueOf(vr.getText());// 12;
//        
//        // for jacket
//        double jacketDensity = Double.valueOf(dj.getText()); //  1000;
//        double jacketCp = Double.valueOf(cpj.getText());//4.27;
//        double jacketVolume =Double.valueOf(vj.getText()); // 10;
//        double F = Double.valueOf(f.getText());  //220  * 1000/ 60; 
//        double s =1;
//   
//        
//        
//        ReactorBalance rb = new ReactorBalance();
//            rb.setA(area);
//            rb.setCp(reactorCp);
//            rb.setDensity(reactorDensity);
//            rb.setVolume(reactorVolume);
//            rb.setU(U);
//            
//         JacketBalance jb = new JacketBalance();
//            jb.setA(area);
//            jb.setU(U);
//            jb.setCp(jacketCp);
//            jb.setDensity(jacketDensity);
//            jb.setF(F);
//            jb.setTc(Tc);
//            jb.setTh(Th);
//            jb.setVolume(jacketVolume);
//            jb.setS(s);
//            jb.setTset(Tset);
//            
//        ODESystem reactorODE = new ODESystem(rb,jb);
//        
//        
//        
//        double t = Double.parseDouble(x0TextField.getText());
//        double Tr0 = Double.parseDouble(y0TextField.getText());
//        double Tj0= Double.parseDouble(y20TextField.getText());
//        double lastX =  Double.parseDouble(xLasTextField.getText());
//        
//        double[] initialCondition = {t,Tr0,Tj0};
//       // double lastX = 5;
//        double step =0.01;
//        
//        
//           double[][] result = rk4.solveOde(reactorODE,initialCondition, lastX, step);
//        
//        Series<Number,Number> y1Series = new Series<>();
//        y1Series.setName("Tr[K]");
//        Series<Number,Number> y2Series = new Series<>();
//        y2Series.setName("Tj[K]");
//        
//        for( double[] res : result){
//            double x = res[0];
//            double y1s = res[1];
//            double y2s = res[2];
//            
//            y1Series.getData().add(new XYChart.Data<Number,Number>(x,y1s));
//             y2Series.getData().add(new XYChart.Data<Number,Number>(x,y2s));
//        }
//        
//        chart.getData().addAll(y1Series,y2Series);
//        
//        
//    }
    @FXML VBox vbox;
    @FXML  protected void cleanCharts(ActionEvent event){
     
        String tm = "t [min]";
        String Tm = "T [K]";
        
       vbox.getChildren().removeAll(chart,xchart);
       Axis<Number> taxis = new NumberAxis();
       taxis.setLabel(tm);
       NumberAxis Taxis = new NumberAxis();
       Taxis.setLabel(Tm);
       Taxis.setForceZeroInRange(false);
  
       chart = new LineChart<>(taxis,Taxis);
       chart.setCreateSymbols(false);
       
       Axis<Number> xtaxis = new NumberAxis();
       xtaxis.setLabel(tm);
       NumberAxis xTaxis = new NumberAxis();
       xTaxis.setLabel("Ca [kgmol / m^3]");
       xTaxis.setForceZeroInRange(false);
       
       xchart = new LineChart<>(xtaxis,xTaxis);
       xchart.setCreateSymbols(false);
       
       vbox.getChildren().addAll(chart,xchart);
        
        
    }
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        x0TextField.setText("0");
        y0TextField.setText("307.15");
       y20TextField.setText("307.15");
       xLasTextField.setText("5000");
       tsetTextField.setText("330");
       
       a.setText("5.40");//71.71;
        u.setText("3550");//942.4 / 60 =  15.7
        th.setText("347.15");// 347.15;//74 + 273.15;
        tc.setText("307.15");//  307.15;//34.14 + 273.15;
        
        
        // for reactor 
        dr.setText("19.2"); // 878;
        cpr.setText("1.815e5"); //2.477;
        vr.setText("7.08");// 12;
        
        // for jacket
        dj.setText("1000"); //  1000;
        cpj.setText("4184");//4.27;
        vj.setText("1.82"); // 10;
        f.setText("0.020");  //220  * 1000/ 60 = 3666.66
        
        convTextField.setText("0.1");
//        ea.setText("1.182e7");//1.182e7
//        r.setText("8314.39");//8314.39 j / kgmol - K
//        pa.setText("0.0744"); //0.0744 m3 / s - kgmol
//        pm.setText("146.3");
        hr.setText("-9.86e7"); // J / kgmol
       
        ioTextField.setText("0.01548");
         chart.setCreateSymbols(false);
          xchart.setCreateSymbols(false);
//        serie.setData(data);
//         chart.getData().add(serie);
        
    }    
    
    @FXML  protected void deployChiu(ActionEvent event) throws IOException{
        Pane root = FXMLLoader.load(ChiuModel.class.getResource("ChiuView.fxml"));
        Scene scene = new Scene(root);
        
        Stage secondary = new Stage();
        
        secondary.setScene(scene);
        
        secondary.show();
    }
    
}

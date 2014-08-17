package NumericSolutions;

import differentialEquation.DifferentialEquation;
import differentialEquation.ODESystem;
import differentialEquation.Y1;
import differentialEquation.Y2;
import differentialEquation.solver.rungekutta.RungeKutta4;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Chilpayate
 */
public class FXMLNumericController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        x0TextField.setText("0");
        y10TextField.setText("1");
        y20TextField.setText("1");
        xLasTextField.setText("4");
    }    
    
    @FXML LineChart<Number,Number>  chart;
    @FXML  TextField x0TextField,y10TextField,y20TextField,xLasTextField;
    
     @FXML protected void drawToDiff(ActionEvent event){
        DifferentialEquation y1 = new Y1();
        DifferentialEquation y2 = new Y2();
        
        ODESystem sis = new ODESystem(y1,y2);
        
           double x0 = Double.parseDouble(x0TextField.getText());
        double y0 =Double.parseDouble(y10TextField.getText());
        double y20 = Double.parseDouble(y20TextField.getText());
        double lastX =  Double.parseDouble(xLasTextField.getText());
        
        
        
        double[] initialCondition = {x0,y0,y20};
     //   double lastX = 1.5;
        double step = 0.5;
        RungeKutta4 rk4 = new RungeKutta4();
        
     //   double[][] result = rk4.solveOde(sis,initialCondition, lastX, step);
        
        double[][] result = rk4.solveOde(sis, initialCondition, lastX, step);
        
        XYChart.Series<Number,Number> y1Series = new XYChart.Series<>();
        XYChart.Series<Number,Number> y2Series = new XYChart.Series<>();
        
        for( double[] res : result){
            double x = res[0];
            double y1s = res[1];
            double y2s = res[2];
            
            y1Series.getData().add(new XYChart.Data<Number,Number>(x,y1s));
             y2Series.getData().add(new XYChart.Data<Number,Number>(x,y2s));
        }
        
        chart.getData().addAll(y1Series,y2Series);
        
    }
}

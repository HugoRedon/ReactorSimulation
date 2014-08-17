/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reactorsimulation.chiu;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Chilpayate
 */
public class ChiuModelTest {
    
    public ChiuModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }



    /**
     * Test of e method, of class ChiuModel.
     */
    @Test
    public void testE() {
        System.out.println("e");
        double Tr =323.15;
        ChiuModel instance = new ChiuModel();
        double expResult = -0.237812166666667;
        double result = instance.e(Tr);
        assertEquals(expResult, result, 0.00001);
    }

    /**
     * Test of kp method, of class ChiuModel.
     */
    @Test
    public void testKp() {
        System.out.println("kp");
        double Tr = 323.15;
        double x = 0.1;
        double lambda_0 = 1e-4;
        ChiuModel instance = new ChiuModel();
        double expResult = 32768.9163750302;
        double result = instance.kp(Tr, x, lambda_0);
        assertEquals(expResult, result, 0.0010);
       
    }

    /**
     * Test of theta_p method, of class ChiuModel.
     */
    @Test
    public void testTheta_p() {
        System.out.println("theta_p");
        double Tr = 323.15;
        ChiuModel instance = new ChiuModel();
        double expResult = 3500;
        double result = instance.theta_p(Tr);
     
        assertEquals(expResult, result, 0.00010);
        
//        expResult = 2.5e2;
//        result =  instance.theta_p(343.15);
//        
//        assertEquals(expResult, result,30);
                
    }

    /**
     * Test of fi_m method, of class ChiuModel.
     */
    @Test
    public void testFi_m() {
        System.out.println("fi_m");
        double Tr = 323.15;
        double x = 0.1;
        ChiuModel instance = new ChiuModel();
        double expResult =0.921924485950699;
        double result = instance.fi_m(Tr, x);
        assertEquals(expResult, result, 0.001);
      
    }

    /**
     * Test of f method, of class ChiuModel.
     */
    @Test
    public void testF() {
        System.out.println("f");
        ChiuModel instance = new ChiuModel();
        double expResult = 0.58;
        double result = instance.f();
        assertEquals(expResult, result, 0.001);
       
    }

    /**
     * Test of kd method, of class ChiuModel.
     */
    @Test
    public void testKd() {
        System.out.println("kd");
        double Tr = 323.15;
        ChiuModel instance = new ChiuModel();
        double expResult = 0.000115800368924531;
        double result = instance.kd(Tr);
        assertEquals(expResult, result, 0.001);

    }

    /**
     * Test of kt0 method, of class ChiuModel.
     */
    @Test
    public void testKt0() {
        System.out.println("kt0");
        double Tr =323.15d;
        ChiuModel instance = new ChiuModel();
        double expResult =1973532110.02622 ;
        double result = instance.kt0(Tr);
        assertEquals(expResult, result, 0.001);

    }

    /**
     * Test of kp0 method, of class ChiuModel.
     */
    @Test
    public void testKp0() {
        System.out.println("kp0");
        double Tr = 323.15;
        ChiuModel instance = new ChiuModel();
        double expResult = 33542.6461594426;
        double result = instance.kp0(Tr);
        assertEquals(expResult, result, 0.0001);
       
    }

    /**
     * Test of ktc method, of class ChiuModel.
     */
    @Test
    public void testKtc() {
        System.out.println("ktc");
        ChiuModel instance = new ChiuModel();
        double expResult = 0.0;
        double result = instance.ktc();
        assertEquals(expResult, result, 0.00001);

    }

    /**
     * Test of dp method, of class ChiuModel.
     */
    @Test
    public void testDp() {
        System.out.println("dp");
        ChiuModel instance = new ChiuModel();
        double expResult = 1.2;
        double result = instance.dp();
        assertEquals(expResult, result, 0.0001);
     
    }

    /**
     * Test of dm method, of class ChiuModel.
     */
    @Test
    public void testDm() {
        System.out.println("dm");
        double Tr = 323.15;
        ChiuModel instance = new ChiuModel();
        double expResult =0.9146254;
        double result = instance.dm(Tr);
        assertEquals(expResult, result, 0.0001);

    }

    /**
     * Test of tgp method, of class ChiuModel.
     */
    @Test
    public void testTgp() {
        System.out.println("tgp");
        ChiuModel instance = new ChiuModel();
        double expResult = 114;
        double result = instance.tgp();
        assertEquals(expResult, result, 0.001);

    }

    /**
     * Test of theta_t method, of class ChiuModel.
     */
    @Test
    public void testTheta_t() {
        System.out.println("theta_t");
        double Tr = 323.15;
        double I0 =  0.01584;
        ChiuModel instance = new ChiuModel();
        double expResult = 2330;
        double result = instance.theta_t(Tr, I0);
        assertEquals(expResult, result, 0.0010);

    }

    /**
     * Test of kt method, of class ChiuModel.
     */
    @Test
    public void testKt() {
        System.out.println("kt");
        double Tr = 323.15;
        double I0 = 0.01584;
        double x = 0.1;
        double lambda_0 = 1e-4;
        ChiuModel instance = new ChiuModel();
        double expResult = 2131635.13039983;
        double result = instance.kt(Tr, I0, x, lambda_0);
        assertEquals(expResult, result, 0.001);
   
    }

    /**
     * Test of A method, of class ChiuModel.
     */
    @Test
    public void testA() {
        System.out.println("A");
        double Tr = 323.15;
        ChiuModel instance = new ChiuModel();
        double expResult = 0.134;
        double result = instance.A(Tr);
        assertEquals(expResult, result, 0.0001);

    }

    /**
     * Test of B method, of class ChiuModel.
     */
    @Test
    public void testB() {
        System.out.println("B");
        ChiuModel instance = new ChiuModel();
        double expResult = 0.03;
        double result = instance.B();
        assertEquals(expResult, result, 0.0001);

    }
}

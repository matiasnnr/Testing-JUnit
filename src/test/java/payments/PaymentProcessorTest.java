package payments;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PaymentProcessorTest {

    private PaymentGateway paymentGateway;
    private PaymentProcessor paymentProcessor;

    @Before //Para que JUnit sepa que tiene que ejecutar este método cada vez antes de ejecutar otro método
    public void setup(){
        paymentGateway = Mockito.mock(PaymentGateway.class);
        paymentProcessor = new PaymentProcessor(paymentGateway);
    }

    @Test
    public void payment_is_success(){

        //1. Preparación del escenario u objetos (objetos que queremos crear y utilizar en el test)
        Mockito.when(paymentGateway.requestPayment(Mockito.any()))
                .thenReturn(new PaymentResponse(PaymentResponse.PaymentStatus.OK));

        //2. Llamada al método que queremos probar (ejecución del método)
        boolean result = paymentProcessor.makePayment(1000);

        //3. Comprobación de que el resultado es el esperado
        assertTrue(result);
    }


    @Test
    public void payment_is_wrong(){

        Mockito.when(paymentGateway.requestPayment(Mockito.any()))
                .thenReturn(new PaymentResponse(PaymentResponse.PaymentStatus.ERROR));

        assertFalse(paymentProcessor.makePayment(1000));
    }

}
package toncarvalho.statistics.mediasmoveis;

import java.text.NumberFormat;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ton on 5/16/14.
 */
public class MediaMovelPonderadaTest {

    private NumberFormat nf = NumberFormat.getInstance();

    @Test
    public void testByMediaMovelPonderada() {

        MyTimeSeries[] tsArray = MyTimeSeries.getTimeSeries();

        MediaMovelPonderada mediaMovelPonderada = new MediaMovelPonderada(tsArray);

        double mmPonderada = mediaMovelPonderada.getAverageValue();

        System.out.println("O valor da Média Móvel PONDERADA é: " + mmPonderada);

        assertNotNull("Não calculou a média móvel PONDERADA, o resultado veio nulo ", mmPonderada);
    }
}

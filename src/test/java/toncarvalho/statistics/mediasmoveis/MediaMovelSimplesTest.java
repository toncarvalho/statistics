package toncarvalho.statistics.mediasmoveis;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ton on 5/16/14.
 */
public class MediaMovelSimplesTest {

    private NumberFormat nf = NumberFormat.getInstance();

    @Test
    public void testByMediaMovelSimples() {

        MyTimeSeriesA[] tsArray = MyTimeSeriesA.getTimeSeries();

        MediaMovelSimples mediaMovelSimples = new MediaMovelSimples(tsArray);

        Map<String, Double> valuesMap = mediaMovelSimples.getAverageValues();

        double mmSimples = valuesMap.get("mediaMovelSimples");
        double erroMedioAbsoluto = valuesMap.get("erroMedioAbsoluto");

        System.out.println("O valor da Média Móvel SIMPLES é: " + mmSimples);
        System.out.println("O valor da ERRO MEDIO ABSOLUTO é: " + erroMedioAbsoluto);

        assertNotNull("Não calculou a média móvel SIMPLES, o resultado veio nulo ", mmSimples);
        assertNotNull("Não calculou o ERRO MEDIO ABSOLUTO, o resultado veio nulo ", erroMedioAbsoluto);

        nf.setRoundingMode(RoundingMode.UP);
        nf.setMinimumFractionDigits(8);
        nf.setMaximumFractionDigits(8);
        System.out.println(" formatado : " +
                           nf.format(new Double(erroMedioAbsoluto)));

        assertEquals("782.55000000", nf.format(new Double(erroMedioAbsoluto)));
        assertEquals(new Double(5231.6423077), new Double(mmSimples));


    }
}

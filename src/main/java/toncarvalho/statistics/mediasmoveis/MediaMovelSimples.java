package toncarvalho.statistics.mediasmoveis;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ton on 5/16/14.
 */
public class MediaMovelSimples {

    private static final int TAMANHO_DO_BLOCO = 2;
    private MyTimeSeriesA[] tsArray;

    public MediaMovelSimples(final MyTimeSeriesA[] tsArray) {
        this.tsArray = tsArray;
    }

    public Map<String, Double> getAverageValues() {

        // devo trabalhar com grupos de 2 em dois períodos e sempre descartar o mais antigo

        System.out.println(" qtdPeriodos: " + tsArray.length);

        int iteracoes = new Double(tsArray.length - 1).intValue();
        System.out.println(" Iterações: " + iteracoes);

        List<MediaAux> projetadasList = new LinkedList<MediaAux>();

        for (int i = 0; i < (iteracoes - 1); i++) {
            MyTimeSeriesA ts0 = tsArray[i];
            MyTimeSeriesA ts1 = tsArray[(i + 1)];

            //somatoria das demandas
            double sd = calculaSomatoriaDemandas(ts0, ts1);

            // ou demanda projetada
            double media = sd / TAMANHO_DO_BLOCO;

            // ja no primeiro preciso passar a  demanda real de 1998 ( o terceiro item da lista )pra achar o erro
            double erroMedio = Math.abs(getErro(tsArray[(i + TAMANHO_DO_BLOCO)].getDemanda(), media));

            System.out.println("previsao do proximo periodo: " + media + " erroMedio: " + erroMedio);
            System.out.println();

            projetadasList.add(new MediaAux(ts1.getAno(), ts1.getDemanda(), media, erroMedio));
        }

        Map<String, Double> resultado = new TreeMap<String, Double>();
        resultado = evaluate(projetadasList);

        return resultado;
    }

    private Map<String, Double> evaluate(final List<MediaAux> projetadasList) {

        double somatoriaErro = 0;
        double somatoriaProjecoes = 0;

        for (MediaAux obj : projetadasList) {

            System.out.println("erro: " + obj.erro);
            somatoriaErro += obj.erro;
            somatoriaProjecoes += obj.demandaProjetada;
        }

        System.out.println(" somatoria do erro: " + somatoriaErro);

        Map<String, Double> resultado = new TreeMap<String, Double>();

        //o -1 descarta o primeiro valor

        final Double erroMedioAbsoluto = (somatoriaErro / (projetadasList.size()));
        resultado.put("erroMedioAbsoluto", new BigDecimal(erroMedioAbsoluto).setScale(8, BigDecimal.ROUND_UP).doubleValue());

        final Double mediaMovelSimples = (somatoriaProjecoes / projetadasList.size());
        resultado.put("mediaMovelSimples", new BigDecimal(mediaMovelSimples).setScale(8, BigDecimal.ROUND_UP).doubleValue());

        return resultado;
    }

    private Double calculaSomatoriaDemandas(MyTimeSeriesA... timeSerieses) {

        Double somatoria = new Double(0);

        for (int i = 0; i < timeSerieses.length; i++) {
            System.out.println("ano: " + timeSerieses[i].ano + " Demanda: " + timeSerieses[i].demanda);
            somatoria = somatoria + timeSerieses[i].demanda;
        }

        return somatoria;
    }

    /**
     * Calculo de erro médio
     * <p/>
     * Calculamos o erro médio calculando a diferença entre a demanda real no período e a demanda projetada.
     *
     * @return double
     */
    private double getErro(final double demandaReal, final double demandaProjetada) {

        return demandaReal - demandaProjetada;
    }

    private class MediaAux {
        private double ano;
        private double demandaReal;
        private double demandaProjetada;
        private double erro;

        public MediaAux(final double ano, final double demandaReal, final double demandaProjetada, final double erro) {
            this.ano = ano;
            this.demandaReal = demandaReal;
            this.demandaProjetada = demandaProjetada;
            this.erro = erro;
        }

        @Override
        public String toString() {
            return "Aux{" +
                   "ano=" + ano +
                   ", demandaReal=" + demandaReal +
                   ", demandaProjetada=" + demandaProjetada +
                   ", erro=" + erro +
                   '}';
        }
    }
}

package toncarvalho.statistics.mediasmoveis;

/**
 * Created by ton on 5/16/14.
 */
public class MediaMovelPonderada {


    private MyTimeSeries[] tsArray;
    private double resultado;

    public MediaMovelPonderada(final MyTimeSeries[] tsArray) {
        this.tsArray = tsArray ;
    }

    public double getAverageValue() {


        //1º atribuindo pesos aos perídos da série temporal.
        tsArray = atribuiPesosAosPeriodos(tsArray);

        //2º multiplicando valores pelos seus respectivos pesos para obter novos valores de demanda para o calculo.
        for (int i = 0; i < tsArray.length; i++) {
            double demandaOriginal = tsArray[i].demanda;
            double peso = tsArray[i].peso;
            double novaDemanda = demandaOriginal * peso;
            // atualizando valor da demanda multiplicada pelo peso.
            tsArray[i].demanda = novaDemanda;

            System.out.println(
                    " ano: " + tsArray[i].ano
                    + " demandaOriginal: " + demandaOriginal + " peso: " + tsArray[i].peso + " novaDemanda: "
                    + tsArray[i].demanda
            );
        }

        //3º obtendo somatória entre os valores de demanda

        Double somatoriaDemandas = calculaSomatoriaDemandas(tsArray);

        System.out.println(" o somatório das demandas é : " + somatoriaDemandas);

        //4º somatória dos pesos
        Double somatoriaPesos = calculaSomatoriaPesos(tsArray);

        System.out.println(" o somatório dos Pesos é : " + somatoriaPesos);

        //5º calulando a média móvel
        resultado = somatoriaDemandas / somatoriaPesos;



        return resultado;
    }



    private MyTimeSeries[] atribuiPesosAosPeriodos(final MyTimeSeries[] tsArray) {


        //1º descobrir quantidade de períodos
        int qtdPeriodos = tsArray.length;
        // qtdPeriodos alem da quantidade de períodos é também o número correspondente ao maior peso

        // o primeiro período recebe o menor peso, e o peso vai sendo incrementado até o ultimo período, como saber qual é o menor peso ?
        // o item mais recente deve receber o maior peso, e o mais distante na série temporal deve receber o menor


        //vamos agora instanciar um array de pesos de acordo com a quantidade de períodos.

        double[] pesos = new double[qtdPeriodos];

        for(int j = 0  ; j < pesos.length ; j++){
            pesos[j] = j + 1 ;
        }



        for (int i = 0; i < qtdPeriodos; i++) {
            //atribuindo ao primeiro item da lista de series temporais ó ultimo peso da lista de pesos, portanto o maior.
            for(int p = pesos.length ; p > i ; p--) {
                tsArray[i].peso = pesos[p - 1];
            }

            System.out.println("Item: ano " + tsArray[i].ano + " Demanda: " + tsArray[i].demanda
                               + " peso: " + tsArray[i].peso);
        }


        return tsArray ;
    }

    private Double calculaSomatoriaDemandas(final MyTimeSeries[] tsArray) {

        Double x = new Double(0);

        for (int i = 0; i < tsArray.length; i++) {
            System.out.println("X: " + x.toString());

            x = x + tsArray[i].demanda;
        }

        return x;
    }

    private Double calculaSomatoriaPesos(final MyTimeSeries[] tsArray) {

        Double x = new Double(0);

        for (int i = 0; i < tsArray.length; i++) {
            System.out.println("X: " + x.toString());

            x = x + tsArray[i].peso;
        }

        return x;
    }


}

package toncarvalho.statistics.mediasmoveis;

/**
 * Created by ton on 5/16/14.
 */
public class MyTimeSeries {
    public Double ano;
    public Double demanda;
    public Double peso;

    public MyTimeSeries() { }

    public static MyTimeSeries[] getTimeSeries() {

        MyTimeSeries tm2008 = new MyTimeSeries();
        tm2008.ano = new Double(2008);
        tm2008.demanda = new Double(80);
        //     tm2008.peso = new Double(1);

        MyTimeSeries tm2009 = new MyTimeSeries();
        tm2009.ano = new Double(2009);
        tm2009.demanda = new Double(85);
        //   tm2009.peso = new Double(2);

        MyTimeSeries tm2010 = new MyTimeSeries();
        tm2010.ano = new Double(2010);
        tm2010.demanda = new Double(90);
        //    tm2010.peso = new Double(3);

        MyTimeSeries tm2011 = new MyTimeSeries();
        tm2011.ano = new Double(2011);
        tm2011.demanda = new Double(110);
        //  tm2011.peso = new Double(4);

        MyTimeSeries tm2012 = new MyTimeSeries();
        tm2012.ano = new Double(2012);
        tm2012.demanda = new Double(120);
        //tm2012.peso = new Double(5);

        MyTimeSeries tm2013 = new MyTimeSeries();
        tm2013.ano = new Double(2013);
        /*tm2013.demanda = new Double(120);
        tm2013.peso =  new Double(6) ;*/

        MyTimeSeries[] tsArray = new MyTimeSeries[5];
        tsArray[0] = tm2008;
        tsArray[1] = tm2009;
        tsArray[2] = tm2010;
        tsArray[3] = tm2011;
        tsArray[4] = tm2012;

        return tsArray;
    }

    public Double getAno() {
        return ano;
    }

    public void setAno(final Double ano) {
        this.ano = ano;
    }

    public Double getDemanda() {
        return demanda;
    }

    public void setDemanda(final Double demanda) {
        this.demanda = demanda;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(final Double peso) {
        this.peso = peso;
    }
}

package charts;

import java.awt.Color;
import motorMetricas.valores.Conjunto;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

public class Graficos
{
	public static ChartPanel crearGraficoTarta(String[] leyendas, int[] valores, String titulo)
	{
		DefaultPieDataset data = new DefaultPieDataset();
        for(int i = 0; i< leyendas.length; i++)
        {
            data.setValue(leyendas[i], valores[i]);
        }
        
        JFreeChart chart = ChartFactory.createPieChart3D(titulo, data, true, true, false);
        
        chart.setBackgroundPaint(Color.WHITE);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        // add the chart to a panel...
         plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.6f);
        plot.setNoDataMessage("No data to display");
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({1}) - {2} "));
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        
        return chartPanel;
	}
	
	public static ChartPanel crearGraficoBarra3d(Conjunto valores, String titulo, String tituloX, String tituloY){
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        for(String key : valores.getValor().keySet())
        {
            data.setValue(valores.getValor().get(key).getValor(), key , ""); 
        }

        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart3D(titulo, tituloX, tituloY, data, PlotOrientation.VERTICAL, false, true, false);

        chart.setBackgroundPaint(Color.WHITE);
       
        ChartPanel chartPanel = new ChartPanel(chart);
       // chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

        return chartPanel;
    }
}
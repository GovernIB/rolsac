package es.caib.rolsac.back2.util;

import java.awt.Color;
import java.awt.GradientPaint;
import java.util.List;

import org.ibit.rol.sac.model.Estadistica;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.axis.CategoryAnchor;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.SlidingCategoryDataset;

public class Graficas {

	public Graficas() {
		
	}

	public static JFreeChart pintarGrafica(List<Estadistica> dadesEstadistica) {

		SlidingCategoryDataset dataset = new SlidingCategoryDataset(
				crearDataset(dadesEstadistica), 0, 12);

		JFreeChart chart = ChartFactory.createBarChart("", // chart title
				"", // domain axis label
				"", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				false, // include legend
				false, // tooltips?
				false // URLs?
				);

		chart.setBackgroundPaint(Color.white);
		chart.setAntiAlias(true);

		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		// Color de fons
		plot.setBackgroundAlpha(0);
		// Veure el grid
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinesVisible(true);
		// Canviar color grid
		plot.setDomainGridlinePaint(Color.gray);
		plot.setRangeGridlinePaint(Color.gray);

		// Posicio inici del grid
		plot.setDomainGridlinePosition(CategoryAnchor.START);

		// Valores en barras
		CategoryItemRenderer categoryRenderer = plot.getRenderer();
		categoryRenderer
				.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		categoryRenderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);

		CategoryAxis domainAxis = plot.getDomainAxis();

		domainAxis.setMaximumCategoryLabelWidthRatio(0.8f);
		domainAxis.setLowerMargin(0.02);
		domainAxis.setUpperMargin(0.02);

		// Ocultar category
		domainAxis.setTickLabelsVisible(false);
		domainAxis.setTickMarksVisible(false);

		// set the range axis to display integers only...
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		// Pintar etiquetes laterals
		rangeAxis.setTickLabelsVisible(true);
		rangeAxis.setTickMarksVisible(true);

		// AutoEscala
		rangeAxis.setAutoRange(true);
		// Marge superior
		rangeAxis.setUpperMargin(0.10);

		rangeAxis.setAutoRangeIncludesZero(true);
		rangeAxis.setAutoRangeStickyZero(true);

		
		Axis axis = plot.getDomainAxis();
		axis.setTickMarksVisible(false);

		// disable bar outlines...
		BarRenderer barRenderer = (BarRenderer) plot.getRenderer();
		barRenderer.setDrawBarOutline(false);

		// set up gradient paints for series...
		// GradientPaint gp0 = new GradientPaint(0.0f, 0.0f,Color.blue,
		// 0.0f, 0.0f, new Color(0, 0, 64));

		GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, new Color(41, 115,
				188), // Color.blue,
				0.0f, 0.0f, new Color(41, 115, 188));
		barRenderer.setSeriesPaint(0, gp0);
		
		return chart;

	}

	private static CategoryDataset crearDataset(List<Estadistica> dadesEstadistica) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int i = 0;
		for (Estadistica estadistica : dadesEstadistica){
			dataset.addValue(estadistica.getContador(), "Mes", "Mes " + i++);
		}
		return dataset;
	}

}

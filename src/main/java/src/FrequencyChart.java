package src;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

/**
 * Esta clase se encarga de generar la campana de gauus a partir del array de
 * frecuencias generado por la máquina de Dalton.
 * 
 * @author Miguel
 *
 */
public class FrequencyChart extends JFrame {

	public FrequencyChart() {
		super("Campana de Gauus");
	}

	public void crearCampanaGaus(double[] arrayFrecuencias) {
		// Create dataset
		CategoryDataset dataset = createDataset(arrayFrecuencias);

		// Create chart
		JFreeChart chart = ChartFactory.createBarChart("Frequency Chart", // Chart title
				"Categories", // X-Axis label
				"Frequency", // Y-Axis label
				dataset, PlotOrientation.VERTICAL, false, false, false);

		// Create Panel
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
		setContentPane(chartPanel);
	}

	private CategoryDataset createDataset(double[] data) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i < data.length; i++) {
			dataset.addValue(data[i], "Category " + (i + 1), "");
		}

		return dataset;
	}

}
package com.dam.Deck_Constructor_TFG.Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.dam.Deck_Constructor_TFG.Relaciones.Controlador.RecuperarEstadísticas;

public class EstadisticasWindow extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<String[]> deckCards;
	Color colorPrimario = new Color(41, 41, 41); 
	Color colorPanel = new Color(51, 51, 51);
	Color colorBoton = new Color(34, 34, 34); 
	Color colorTexto = new Color(221, 221, 221); 
	double CC1 ;
	double CC2;
	double CC3;
	double CC4;
	double CC5;
	double CC6 ;
	double CC7;
	double CC8;
	double CC9;
	double CC10;
	double promedio;
//Colores
	private int U = 0; // Blue
	private int W = 0; // White
	private int B = 0; // Black
	private int R = 0; // Red
	private int G = 0; // Green
	private int C = 0; // Colorless
//Tipos
	private int Creature =0;
	private int Sorcery=0;
	private int Instant=0;
	private int Enchantment=0;
	private int Artifact=0;
	private int Planeswalker=0;
	private int Land=0;
	private static final String[] tiposDeCarta = {
            "Creature", "Sorcery", "Instant", "Enchantment",
            "Artifact", "Planeswalker", "Land"
    };
	public EstadisticasWindow(JFrame parentFrame, ArrayList<String[]> deckCards) throws IOException {
        super(parentFrame, "Estadísticas del mazo", true);
        setPreferredSize(new Dimension(1100, 400));
        setBounds(new Rectangle(0, 0, 809, 0));
        setBackground(colorPrimario);
        this.deckCards = deckCards;
        setColores();
        setTipos();
        setCostes();
        setCostes1();
        //recogerValores();
        // Create and configure the dialog
        setSize(838, 652);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBackground(colorPanel);
        panel.setLayout(new GridLayout(0, 3, 0, 0));
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        
        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setBackground(colorPanel);
        buttonPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.setBackground(colorBoton);
		cerrarButton.setForeground(colorTexto);
        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
        buttonPanel2.add(cerrarButton);
        getContentPane().add(buttonPanel2, BorderLayout.SOUTH);
        
        JPanel paneIZQ = new JPanel();
        paneIZQ.setBackground(colorPanel);
        JPanel paneCEN = new JPanel();
        paneCEN.setBackground(colorPanel);
        JPanel paneDER = new JPanel();
        paneDER.setBackground(colorPanel);

        // Create the bar chart panel
        // Add the panels to the dialog
        panel.add(paneIZQ);
        panel.add(paneCEN);
        panel.add(paneDER);
        //double promedio = calcularPromedio();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(CC1, "Series1", "CC1");
        dataset.setValue(CC2, "Series1", "CC2");
        dataset.setValue(CC3, "Series1", "CC3");
        dataset.setValue(CC4, "Series1", "CC4");
        dataset.setValue(CC5, "Series1", "CC5");
        dataset.setValue(CC6, "Series1", "CC6");
        dataset.setValue(CC7, "Series1", "CC7");
        dataset.setValue(CC8, "Series1", "CC8");
        dataset.setValue(CC9, "Series1", "CC9");
        dataset.setValue(CC10, "Series1", "CC10");
        JFreeChart barChart = ChartFactory.createBarChart("Curva de maná - "+promedio+" promedio", "", "", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        plot.setBackgroundPaint(colorPrimario);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAutoRangeIncludesZero(false); 
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        double maxCC = Math.max(CC1, Math.max(CC2, Math.max(CC3, Math.max(CC4, Math.max(CC5, Math.max(CC6, Math.max(CC7, Math.max(CC8, Math.max(CC9, CC10)))))))));
        rangeAxis.setRange(0, Math.ceil(maxCC));
        double tickUnitSize = Math.ceil(maxCC / 5.0); // Establecer el tamaño de la unidad de los ticks
        rangeAxis.setTickUnit(new NumberTickUnit(tickUnitSize));
        // Create and add the chart panel
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setFont(new Font("Tahoma", Font.PLAIN, 8));
        chartPanel.setBounds(10, 62, 341, 242);
        chartPanel.setBackground(new Color(255, 255, 255));
        chartPanel.setForeground(new Color(255, 255, 255));

      //PANEL COLORES MAZO
        DefaultPieDataset dataset1 = new DefaultPieDataset();
		 
		dataset1.setValue("Isla", U);
		dataset1.setValue("Llanura", W);
		dataset1.setValue("Pantano", B);
		dataset1.setValue("Montaña", R);
		dataset1.setValue("Bosque", G);
		dataset1.setValue("Incoloro", C);
		 
		JFreeChart pieChart1 = ChartFactory.createPieChart("Colores del mazo", dataset1);
		pieChart1.setBackgroundPaint(Color.WHITE);
		PiePlot plot1 = (PiePlot) pieChart1.getPlot();
		plot1.setBackgroundPaint(colorPrimario);
		plot1.setLabelGenerator(null);
        plot1.setSimpleLabels(true);
        plot1.setLabelBackgroundPaint(Color.WHITE);

        // Asignar colores específicos a cada sección
		plot1.setSectionPaint("Isla", Color.decode("#1E90FF")); // Azul para Islas
        plot1.setSectionPaint("Llanura", Color.decode("#FFFACD")); // Blanco para Llanuras
        plot1.setSectionPaint("Pantano", Color.decode("#2F4F4F")); // Negro para Pantanos
        plot1.setSectionPaint("Montaña", Color.decode("#FF4500")); // Rojo para Montañas
        plot1.setSectionPaint("Bosque", Color.decode("#228B22")); // Verde para Bosques
        plot1.setSectionPaint("Incoloro", Color.decode("#A9A9A9")); // Gris para Incoloro
		
		ChartPanel chartPanel1 = new ChartPanel(pieChart1);
		chartPanel1.setBounds(new Rectangle(10, 63, 341, 243));
        chartPanel1.setBackground(colorBoton);
        chartPanel1.setForeground(colorTexto);
		
		//PANEL TIPOS CARTA
        DefaultPieDataset dataset2 = new DefaultPieDataset();
		 
        dataset2.setValue("Criatura", Creature);
        dataset2.setValue("Conjuro", Sorcery);
        dataset2.setValue("Instantáneo", Instant);
        dataset2.setValue("Encantamiento", Enchantment);
        dataset2.setValue("Artefacto", Artifact);
        dataset2.setValue("Planeswalker", Planeswalker);
        dataset2.setValue("Tierra", Land);
		 
		JFreeChart pieChart2 = ChartFactory.createPieChart("Tipos de carta", dataset2);
		pieChart2.setBackgroundPaint(Color.WHITE);
		PiePlot plot2 = (PiePlot) pieChart2.getPlot();
		plot2.setBackgroundPaint(colorPrimario);
		plot2.setLabelGenerator(null);
        plot2.setSimpleLabels(true);
        plot2.setLabelBackgroundPaint(Color.WHITE);
		

        // Asignar colores específicos a cada sección
        plot2.setSectionPaint("Criatura", Color.decode("#FFD700")); // Dorado para Criatura
        plot2.setSectionPaint("Conjuro", Color.decode("#8A2BE2")); // Azul Violeta para Conjuro
        plot2.setSectionPaint("Instantáneo", Color.decode("#00FFFF")); // Cian para Instantáneo
        plot2.setSectionPaint("Encantamiento", Color.decode("#FF69B4")); // Rosa para Encantamiento
        plot2.setSectionPaint("Artefacto", Color.decode("#B0C4DE")); // Azul claro para Artefacto
        plot2.setSectionPaint("Planeswalker", Color.decode("#FF6347")); // Tomate para Planeswalker
        plot2.setSectionPaint("Tierra", Color.decode("#A52A2A")); // Marrón para Tierra
		
		ChartPanel chartPanel2 = new ChartPanel(pieChart2);
		chartPanel2.setBounds(new Rectangle(0, 0, 200, 0));
		chartPanel2.setPreferredSize(new Dimension(200, 420));
		chartPanel2.setBounds(10, 63, 341, 238);
		chartPanel2.setBackground(colorBoton);
        chartPanel2.setForeground(colorTexto);
        paneIZQ.setLayout(null);


        // Add the panels to the dialog
        paneIZQ.add(chartPanel);
        paneCEN.setLayout(null);
        paneCEN.add(chartPanel1);
        paneDER.setLayout(null);
        paneDER.add(chartPanel2);
        
        

        // Pack and display the dialog
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
//	private double recogerValores() {
//		double suma = 0.0;
//		int contador = 0;
//
//		if (CC1 > 0) {
//		    suma += CC1;
//		    contador++;
//		}
//		if (CC2 > 0) {
//		    suma += CC2;
//		    contador++;
//		}
//		if (CC3 > 0) {
//		    suma += CC3;
//		    contador++;
//		}
//		if (CC4 > 0) {
//		    suma += CC4;
//		    contador++;
//		}
//		if (CC5 > 0) {
//		    suma += CC5;
//		    contador++;
//		}
//		if (CC6 > 0) {
//		    suma += CC6;
//		    contador++;
//		}
//		if (CC7 > 0) {
//		    suma += CC7;
//		    contador++;
//		}
//		if (CC8 > 0) {
//		    suma += CC8;
//		    contador++;
//		}
//		if (CC9 > 0) {
//		    suma += CC9;
//		    contador++;
//		}
//		if (CC10 > 0) {
//		    suma += CC10;
//		    contador++;
//		}
//		// Calcular el promedio solo si hay valores válidos
//		
//
//		return promedio;
//	}
	void setColores()throws IOException{
		Map<String, Integer> colorCount = RecuperarEstadísticas.getColoresMazo(deckCards);

	    for (Map.Entry<String, Integer> entry : colorCount.entrySet()) {
	        String key = entry.getKey();
	        int count = entry.getValue();
	        switch (key) {
	            case "U":
	                U = count;
	                break;
	            case "W":
	                W = count;
	                break;
	            case "B":
	                B = count;
	                break;
	            case "R":
	                R = count;
	                break;
	            case "G":
	                G = count;
	                break;
	            case "C":
	                C = count;
	                break;
	        }
	    }
	}
    void setTipos()throws IOException{
    	 Map<String, Integer> tipoCount = RecuperarEstadísticas.getTiposMazo(deckCards);

    	 
    	    for (Map.Entry<String, Integer> entry : tipoCount.entrySet()) {
    	        String key = entry.getKey();
    	        int count = entry.getValue();
    	        switch (key) {
    	            case "Creature":
    	                Creature = count;
    	                break;
    	            case "Sorcery":
    	                Sorcery = count;
    	                break;
    	            case "Instant":
    	                Instant = count;
    	                break;
    	            case "Enchantment":
    	                Enchantment = count;
    	                break;
    	            case "Artifact":
    	                Artifact = count;
    	                break;
    	            case "Planeswalker":
    	                Planeswalker = count;
    	                break;
    	            case "Land":
    	                Land = count;
    	                break;
    	        }
    	    }
    }
    void setCostes1()throws IOException{
    	Map<String, Integer> CCCount = RecuperarEstadísticas.getCostesConvertidosMazo(deckCards);
    	for (Map.Entry<String, Integer> entry : CCCount.entrySet()) {
            String key = entry.getKey();
            int count = entry.getValue();
            switch (key) {
                case "CC1":
                    CC1 = (double) count;
                    break;
                case "CC2":
                    CC2 = (double) count;
                    break;
                case "CC3":
                    CC3 = (double) count;
                    break;
                case "CC4":
                    CC4 = (double) count;
                    break;
                case "CC5":
                    CC5 = (double) count;
                    break;
                case "CC6":
                    CC6 = (double) count;
                    break;
                case "CC7":
                    CC7 = (double) count;
                    break;
                case "CC8":
                    CC8 = (double) count;
                    break;
                case "CC9":
                    CC9 = (double) count;
                    break;
                case "CC10":
                    CC10 = (double) count;
                    break;
            }
        }
    }
    public void setCostes()throws IOException{
    	Map<String, Integer> CCCount = RecuperarEstadísticas.getCostesConvertidosMazo(deckCards);

        // Asignar los valores de los costos convertidos
        CC1 = CCCount.getOrDefault("CC1", 0);
        CC2 = CCCount.getOrDefault("CC2", 0);
        CC3 = CCCount.getOrDefault("CC3", 0);
        CC4 = CCCount.getOrDefault("CC4", 0);
        CC5 = CCCount.getOrDefault("CC5", 0);
        CC6 = CCCount.getOrDefault("CC6", 0);
        CC7 = CCCount.getOrDefault("CC7", 0);
        CC8 = CCCount.getOrDefault("CC8", 0);
        CC9 = CCCount.getOrDefault("CC9", 0);
        CC10 = CCCount.getOrDefault("CC10", 0);

        // Calcular el promedio ponderado
        double totalCosteConvertido = 0;
        int totalCartas = 0;

        totalCosteConvertido += CC1 * 1 + CC2 * 2 + CC3 * 3 + CC4 * 4 + CC5 * 5 + CC6 * 6 + CC7 * 7 + CC8 * 8 + CC9 * 9 + CC10 * 10;
        totalCartas += CC1 + CC2 + CC3 + CC4 + CC5 + CC6 + CC7 + CC8 + CC9 + CC10;

        if (totalCartas > 0) {
            promedio = totalCosteConvertido / totalCartas;
            promedio = Math.round(promedio * 100.0) / 100.0;
        }

    }
    
}
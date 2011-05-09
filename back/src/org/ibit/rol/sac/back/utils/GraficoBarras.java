package org.ibit.rol.sac.back.utils;

import oracle.charts.axischart.AxisChart;
import oracle.charts.codec.GIFEncoder;
import org.ibit.rol.sac.model.Estadistica;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

public class GraficoBarras {
    private int width;
    private int height;
    private Color colorFondo;
    private Color colorSerie;

    public GraficoBarras(int width, int height) {
        this.width = width;
        this.height = height;
        colorFondo = new Color(222, 222, 230, 255);
        colorSerie = new Color(82, 89, 98, 255);

    }

    private static ByteArrayOutputStream EncodeGifFile(RenderedImage ri) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            GIFEncoder encoder = new GIFEncoder(out);
            encoder.encode(ri);
        } catch (Exception e) {
            System.err.println("EXCEPCION: " + e.toString());
        }
        return out;
    }

    private ByteArrayOutputStream realizaGrafico(String titulo, Date[] meses, double[] datos) throws Exception {
        AxisChart grafico = new AxisChart();
        grafico.setSize(width, height);
        grafico.getTitle().setText(titulo);
        grafico.setXSeries(meses);
        grafico.setYSeries("grafico", datos);
        grafico.setSeriesGraphType("grafico", AxisChart.BAR);
        grafico.setSeriesPointLabels("grafico");
        grafico.setBackground(colorFondo);
        grafico.setSeriesColor("grafico", colorSerie);
        grafico.setPlotBackground(Color.WHITE);

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_INDEXED);
        Graphics2D g2d = bi.createGraphics();
        grafico.drawBuffer(g2d);
        ByteArrayOutputStream imagen = EncodeGifFile(bi);

        return imagen;
    }

    public ByteArrayOutputStream crearGrafico(String titulo, List datosEstadistica) throws Exception {
        int nMeses = datosEstadistica.size();
        Date[] meses = new Date[nMeses];
        double[] datos = new double[nMeses];

        for (int i = 0; i < nMeses; i++) {
            Estadistica estadistica = (Estadistica) datosEstadistica.get(i);
            meses[i] = estadistica.getFecha();
            datos[i] = estadistica.getContador();
        }

        return realizaGrafico(titulo, meses, datos);
    }
}

package com.mistdale.test2;

import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;

public class BarGraph {
	
	public GraphicalView getMainView(Context context, ArrayList<Integer> results) {
		// --data--
		int[] y = {13, 24};
		y[0] = results.get(9);
		y[1] = results.get(8);
		double[] lowerBound = {17, 3.4};
		double[] upperBound = {25, 10};
		
		// series - first column
		
		CategorySeries series = new CategorySeries("Ваш результат");
		for (int i = 0; i < y.length; ++i) {
			series.add("Bar " + (i+1), y[i]);
		}
		
		CategorySeries seriesMin = new CategorySeries("Нижняя граница нормы");
		for (int i = 0; i < lowerBound.length; ++i) {
			seriesMin.add("Barr " + (i+1), lowerBound[i]);
		}
		
		
		CategorySeries seriesMax = new CategorySeries("Верхняя граница нормы");
		for (int i = 0; i < upperBound.length; ++i) {
			seriesMax.add("Barr " + (i+1), upperBound[i]);
		}
		
		// series - second column
		
		// multiple series
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		
		// renderer
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setDisplayChartValues(true);
		renderer.setChartValuesSpacing((float) 0.5); //?
		//renderer.setColor(Color.GREEN);
		renderer.setColor(Color.argb(245, 0, 255, 0));
		renderer.setChartValuesTextAlign(Align.RIGHT);
		renderer.setChartValuesTextSize(14);
				
		// renderer min
		XYSeriesRenderer rendererMin = new XYSeriesRenderer();
		rendererMin.setDisplayChartValues(true);
		rendererMin.setChartValuesSpacing((float) 0.5); //?
		//rendererMin.setColor(Color.BLUE);
		rendererMin.setColor(Color.argb(120, 210, 255, 0));
		rendererMin.setChartValuesTextAlign(Align.RIGHT);
		rendererMin.setChartValuesTextSize(14);
		
		// renderer max
		XYSeriesRenderer rendererMax = new XYSeriesRenderer();
		rendererMax.setDisplayChartValues(true);
		rendererMax.setChartValuesSpacing((float) 0.5); //?
		///rendererMin.setColor(Color.RED);
		rendererMax.setColor(Color.argb(190, 190, 140, 0)); 
		rendererMax.setChartValuesTextAlign(Align.RIGHT);
		rendererMax.setChartValuesTextSize(14);

		// multiple renderer
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
			
		dataset.addSeries(seriesMin.toXYSeries());
		dataset.addSeries(series.toXYSeries());
		dataset.addSeries(seriesMax.toXYSeries());
		
		mRenderer.addSeriesRenderer(rendererMin);
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.addSeriesRenderer(rendererMax);
		
		// Main Renderer Settings
		
		mRenderer.setChartTitle("Диаграмма результатов");
		//mRenderer.setXTitle("Параметры");
		//mRenderer.setYTitle("Величина");
		mRenderer.setBarSpacing(0.2);
		
		mRenderer.setXLabels(0);
		mRenderer.addXTextLabel(1, "Агрессивность");
		mRenderer.addXTextLabel(2, "Враждебность");
		//mRenderer.setShowGridX(true);
	
		
		mRenderer.setClickEnabled(true);
		mRenderer.setSelectableBuffer(100);
		
		mRenderer.setZoomRate((float) 0.3);
		//mRenderer.setInScroll(false);
		//mRenderer.setPanEnabled(false);
		
		mRenderer.setYAxisMin(0);
		mRenderer.setYAxisMax(37); //max 34
		mRenderer.setXAxisMin(0.4);
		mRenderer.setXAxisMax(2.6);
		mRenderer.setLabelsTextSize(15);
		mRenderer.setFitLegend(true);
		mRenderer.setPanEnabled(false, false);
		//mRenderer.setShowLegend(true);
		mRenderer.setScale((float) 0.6);
		//mRenderer.setOrientation(Orientation.HORIZONTAL);
		//mRenderer.setZoomEnabled(false);

		
		// --view--
		GraphicalView gv = ChartFactory.getBarChartView(context, dataset, mRenderer, Type.DEFAULT);
		
		return gv;
	}
	
	public GraphicalView getDetailedView(Context context, ArrayList<Integer> results) {
		// --data--
		int[] y = {4, 6, 5, 1, 0, 9, 13, 5};
		y[0] = results.get(0) +1;
		y[1] = results.get(1) +1;
		y[2] = results.get(2) +1;
		y[3] = results.get(3) +1;
		y[4] = results.get(4) +1;
		y[5] = results.get(5) +1;
		y[6] = results.get(6) +1;
		y[7] = results.get(7) +1;
		
		// series
		CategorySeries series = new CategorySeries("Ваши результаты");
		for (int i = 0; i < y.length; ++i) {
			series.add("Bar " + (i+1), y[i]);
		}
		
		// multiple series
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series.toXYSeries());
		
		// renderer
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setDisplayChartValues(false);
		renderer.setChartValuesSpacing((float) 0.5); //?
		renderer.setColor(Color.GREEN);
		renderer.setChartValuesTextAlign(Align.RIGHT);
		renderer.setChartValuesTextSize(14);
		
		// multiple renderer
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.setChartTitle("Подробные результаты");
		//mRenderer.setXTitle("X values");
		//mRenderer.setYTitle("Y values");
		mRenderer.setBarSpacing(0.2);
		
		mRenderer.setXLabels(0);
		mRenderer.setXLabelsAngle(-82);
		mRenderer.setXLabelsAlign(Align.RIGHT);
		mRenderer.setMargins(new int[] {10, 10, 115, 10});
		mRenderer.addXTextLabel(1, "Физ. агрессия");
		mRenderer.addXTextLabel(2, "Косв. агрессия");
		mRenderer.addXTextLabel(3, "Раздражение");
		mRenderer.addXTextLabel(4, "Негативизм");
		mRenderer.addXTextLabel(5, "Обида");
		mRenderer.addXTextLabel(6, "Подозрительность");
		mRenderer.addXTextLabel(7, "Верб. агрессия");
		mRenderer.addXTextLabel(8, "Чувство вины");
		//mRenderer.setShowGridX(true);
	
		
		mRenderer.setClickEnabled(true);
		mRenderer.setSelectableBuffer(300);
		
		mRenderer.setZoomRate((float) 0.3);
		mRenderer.setYLabels(0);
		mRenderer.addYTextLabel(1, "0");
		mRenderer.addYTextLabel(3, "2");
		mRenderer.addYTextLabel(5, "4");
		mRenderer.addYTextLabel(7, "6");
		mRenderer.addYTextLabel(9, "8");
		mRenderer.addYTextLabel(11, "10");
		mRenderer.addYTextLabel(13, "12");
		//mRenderer.setInScroll(false);
		//mRenderer.setPanEnabled(false);
		
		mRenderer.setYAxisMin(0);
		mRenderer.setYAxisMax(15); //max 13
		mRenderer.setXAxisMin(0.4);
		mRenderer.setXAxisMax(8.6);
		mRenderer.setLabelsTextSize(15);
		mRenderer.setFitLegend(true);
		mRenderer.setPanEnabled(false, false);
		//mRenderer.setShowLegend(true);
		mRenderer.setScale((float) 0.6);
		
		// --view--
		//Intent intent = ChartFactory.getBarChartIntent(context, dataset, mRenderer, Type.DEFAULT);
		GraphicalView gv = ChartFactory.getBarChartView(context, dataset, mRenderer, Type.DEFAULT);
		
		return gv;
	}
}

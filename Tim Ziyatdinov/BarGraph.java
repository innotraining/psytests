package com.mistdale.test2;

import java.util.Arrays;
import java.util.Comparator;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.BasicStroke;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer.Orientation;
import org.achartengine.renderer.XYSeriesRenderer;

import com.mistdale.test2.R.color;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;

public class BarGraph {
	
	class SeriesRendererBucket {
		
		public SeriesRendererBucket() {
			container = new SeriesRendererContainer[3];
			container[0] = new SeriesRendererContainer();
			container[1] = new SeriesRendererContainer();
			container[2] = new SeriesRendererContainer();
		}
		
		class SeriesRendererContainer {
			public CategorySeries series = null;
			public XYSeriesRenderer renderer = null;
		}
		
		public SeriesRendererContainer[] container;
		
		public void sort() {
			Arrays.sort(container, new Comparator<SeriesRendererContainer>() {

				@Override
				public int compare(SeriesRendererContainer o1,
						SeriesRendererContainer o2) {
					if (o1.series.getValue(0) < o2.series.getValue(0)) {
						return 1;
					} else if (o1.series.getValue(0) > o2.series.getValue(0)) {
						return -1;
					}
					return 0;
				}
				
			});
		}
	}

	
	public GraphicalView getView(Context context) {
		// --data--
		//int[] y = {123, 123, 256, 128, 71, 0, 345, 4, 666, 8};
		int[] y = {13, 24};
		double[] lowerBound = {17, 3.4};
		double[] upperBound = {25, 10};
		
		SeriesRendererBucket bucket = new SeriesRendererBucket();
		SeriesRendererBucket bucket2 = new SeriesRendererBucket();
		
		// series - first column
		
		CategorySeries series = new CategorySeries("Ваша Агрессивность");
		//for (int i = 0; i < y.length; ++i) {
			//series.add("Bar " + (i+1), y[i]);
		//}
		series.add("Bar 1", y[0]);
		
		CategorySeries seriesMin = new CategorySeries("Нижняя граница нормы");
		//for (int i = 0; i < lowerBound.length; ++i) {
			//seriesMin.add("Barr " + (i+1), lowerBound[i]);
		//}
		seriesMin.add("Bar 1", lowerBound[0]);
		
		CategorySeries seriesMax = new CategorySeries("Верхняя граница нормы");
		//for (int i = 0; i < upperBound.length; ++i) {
			//seriesMax.add("Barr " + (i+1), upperBound[i]);
		//}
		seriesMax.add("Bar 1", upperBound[0]);
		
		// series - second column
		
		CategorySeries series2 = new CategorySeries("Ваша Враждебность");
		series2.add("Bar 2", y[1]);
		
		CategorySeries seriesMin2 = new CategorySeries("Нижняя граница нормы");
		seriesMin2.add("Bar 2", lowerBound[1]);
		
		CategorySeries seriesMax2 = new CategorySeries("Верхняя граница нормы");
		seriesMax2.add("Bar 2", upperBound[1]);
		
		
		// multiple series
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

		//dataset.addSeries(XYSeries[0]);
		//dataset.addSeries(XYSeries[1]);
		//dataset.addSeries(XYSeries[2]);
		
		// renderer
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setDisplayChartValues(true);
		renderer.setChartValuesSpacing((float) 0.5); //?
		renderer.setColor(Color.GREEN);
		//renderer.setColor(Color.argb(120, 0, 255, 0));
		renderer.setChartValuesTextAlign(Align.LEFT);
		renderer.setChartValuesTextSize(14);
		
		
		XYSeriesRenderer renderer2 = new XYSeriesRenderer();
		renderer2.setDisplayChartValues(true);
		renderer2.setChartValuesSpacing((float) 0.5); //?
		renderer2.setColor(Color.GREEN);
		//renderer.setColor(Color.argb(120, 0, 255, 0));
		renderer2.setChartValuesTextAlign(Align.LEFT);
		renderer2.setChartValuesTextSize(14);
		
		
		
		
		
		// renderer min
		XYSeriesRenderer rendererMin = new XYSeriesRenderer();
		rendererMin.setDisplayChartValues(true);
		rendererMin.setChartValuesSpacing((float) 0.5); //?
		rendererMin.setColor(Color.BLUE);
		//rendererMin.setColor(Color.argb(120, 0, 255, 0));
		rendererMin.setChartValuesTextAlign(Align.LEFT);
		rendererMin.setChartValuesTextSize(14);
		
		// renderer max
		XYSeriesRenderer rendererMax = new XYSeriesRenderer();
		rendererMax.setDisplayChartValues(true);
		rendererMax.setChartValuesSpacing((float) 0.5); //?
		rendererMin.setColor(Color.RED);
		//rendererMax.setColor(Color.argb(120, 0, 255, 0));
		rendererMax.setChartValuesTextAlign(Align.LEFT);
		rendererMax.setChartValuesTextSize(14);

		// multiple renderer
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		// Учесть порядок добавления рендереров в зависимости от значения
		// Сортировка порядка отрисовки
		
		bucket.container[0].series = series;
		bucket.container[1].series = seriesMin;
		bucket.container[2].series = seriesMax;
	
		bucket.container[0].renderer = renderer;
		bucket.container[1].renderer = rendererMin;
		bucket.container[2].renderer = rendererMax;
		
		bucket.sort();
		
		
		
		bucket2.container[0].series = series2;
		bucket2.container[1].series = seriesMin2;
		bucket2.container[2].series = seriesMax2;
	
		bucket2.container[0].renderer = renderer2;
		bucket2.container[1].renderer = rendererMin;
		bucket2.container[2].renderer = rendererMax;
		
		bucket2.sort();
		
		/*dataset.addSeries(series.toXYSeries());
		dataset.addSeries(seriesMin.toXYSeries());
		dataset.addSeries(seriesMax.toXYSeries());
		
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.addSeriesRenderer(rendererMin);
		mRenderer.addSeriesRenderer(rendererMax);*/
	
		dataset.addSeries(bucket.container[0].series.toXYSeries());
		dataset.addSeries(bucket.container[1].series.toXYSeries());
		dataset.addSeries(bucket.container[2].series.toXYSeries());
		
		dataset.addSeries(bucket2.container[0].series.toXYSeries());
		dataset.addSeries(bucket2.container[1].series.toXYSeries());
		dataset.addSeries(bucket2.container[2].series.toXYSeries());
		
		
		mRenderer.addSeriesRenderer(bucket.container[0].renderer);
		mRenderer.addSeriesRenderer(bucket.container[1].renderer);
		mRenderer.addSeriesRenderer(bucket.container[2].renderer);
		
		mRenderer.addSeriesRenderer(bucket2.container[0].renderer);
		mRenderer.addSeriesRenderer(bucket2.container[1].renderer);
		mRenderer.addSeriesRenderer(bucket2.container[2].renderer);
		
		// Main Renderer Settings
		
		mRenderer.setChartTitle("Диаграмма результатов");
		mRenderer.setXTitle("Параметры");
		mRenderer.setYTitle("Величина");
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
		mRenderer.setPanEnabled(false, false);
		//mRenderer.setShowLegend(true);
		mRenderer.setScale((float) 0.6);
		//mRenderer.setOrientation(Orientation.HORIZONTAL);
		//mRenderer.setZoomEnabled(false);

		
		// --view--
		GraphicalView gv = ChartFactory.getBarChartView(context, dataset, mRenderer, Type.STACKED);
		
		return gv;
	}
	
	public Intent getIntent(Context context) {
		// --data--
		int[] y = {123, 123, 256, 128, 71, 0, 345, 4, 666, 8};
		
		// series
		CategorySeries series = new CategorySeries("Demo Bar Graph");
		for (int i = 0; i < y.length; ++i) {
			series.add("Bar " + (i+1), y[i]);
		}
		
		// multiple series
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series.toXYSeries());
		
		// renderer
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setDisplayChartValues(true);
		renderer.setChartValuesSpacing((float) 0.5); //?
		renderer.setColor(Color.GREEN);
		
		// multiple renderer
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.setChartTitle("Demo graph title");
		mRenderer.setXTitle("X values");
		mRenderer.setYTitle("Y values");
		mRenderer.setBarSpacing(0.2);
		
		
		mRenderer.setClickEnabled(true);
		mRenderer.setSelectableBuffer(100);
		
		mRenderer.setZoomRate((float) 0.3);
		//mRenderer.setInScroll(false);
		//mRenderer.setPanEnabled(false);
		//mRenderer.setShowLegend(true);
		mRenderer.setScale((float) 0.6);
		//mRenderer.setOrientation(Orientation.HORIZONTAL);
		//mRenderer.setZoomEnabled(false);

		
		// --intent--
		Intent intent = ChartFactory.getBarChartIntent(context, dataset, mRenderer, Type.STACKED);
		
		return intent;
	}
}

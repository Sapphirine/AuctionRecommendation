package com.prediction.RecommendApp;

import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

/*
 * a hold-out test. trainingset: 90% testset: 10%
 */

/*
 * Create EvaluateRecommender class with main method and
 * inner class MyRecommenderBuilder.
 */
public class EvaluateRecommender {
	public static void main( String[] args ) throws IOException, TasteException
	{
		new EvaluateRecommender();
	}
	public EvaluateRecommender() throws IOException, TasteException
	{
		DataModel model = 
				new FileDataModel(new File("data/ydata-ymusic-rating-study-v1_0-train.txt"));
		RecommenderEvaluator evaluator = 
				new AverageAbsoluteDifferenceRecommenderEvaluator();
		RecommenderBuilder builder = new MyRecommenderBuilder();
		double result = evaluator.evaluate(builder, null, model, 0.95, 0.05);
		System.out.println(result);
	}

}

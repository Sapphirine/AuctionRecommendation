package com.prediction.RecommendApp;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class MyRecommenderBuilder implements RecommenderBuilder {

	public Recommender buildRecommender(DataModel arg0) throws TasteException {
		UserSimilarity similarity = new PearsonCorrelationSimilarity(arg0);
		UserNeighborhood neighborhood = 
				new NearestNUserNeighborhood(2, similarity, arg0);
		return new GenericUserBasedRecommender(arg0, neighborhood, similarity);
		
	}

}

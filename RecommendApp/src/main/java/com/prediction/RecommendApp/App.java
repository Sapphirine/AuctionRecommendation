package com.prediction.RecommendApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
/* import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood; */
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
/* import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender; */
/* import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity; */
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
/* import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood; */
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
/* import org.apache.mahout.cf.taste.recommender.UserBasedRecommender; */
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
/* import org.apache.mahout.cf.taste.similarity.UserSimilarity; */

public class App 
{
    public static void main( String[] args ) throws IOException, TasteException
    {
		/*
		 * use model, similarity and neighborhood to create recommender
		 */
    	int i;
    	String pathname = "/Users/xiaowen/Desktop/BD_project/data/tanimoto_itembased.txt";
    	DataModel model = new FileDataModel(new File("data/advertiser_bidding.csv"));
		ItemSimilarity similarity = new TanimotoCoefficientSimilarity(model);
		/* UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.5, similarity, model); */
		ItemBasedRecommender recommender = new GenericItemBasedRecommender(model, similarity);
		
		FileWriter writer = new FileWriter(pathname);
		for (i = 0; i < 193582; i++) {
			List<RecommendedItem> recommendations = recommender.recommend(i+1, 3);
			
			for (RecommendedItem recommendation : recommendations) {
				writer.write(Integer.toString(i+1) + ": " + recommendation + '\n');
				System.out.println(Integer.toString(i+1) + ": " + recommendation);
			}
					
		}
		writer.close();
    }
}

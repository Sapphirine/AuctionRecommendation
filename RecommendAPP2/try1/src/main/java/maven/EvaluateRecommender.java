package maven;

import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;



public class EvaluateRecommender {
	public static void main(String[] args) throws IOException, TasteException{
		new EvaluateRecommender();
	}
	
	public EvaluateRecommender() throws IOException, TasteException {
		DataModel model = new FileDataModel(new File("/your_path/Project/advertiser_bidding.csv"));		
		RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
		RecommenderBuilder builder = new MyRecommenderBuilder();
		for (int i = 0; i < 10; i++) {
			double result = evaluator.evaluate(builder, null, model, 0.95, 0.05);
			System.out.println("the " + i + " time: " + result);
		}
		
		
	}
	
	public class MyRecommenderBuilder implements RecommenderBuilder {
		public Recommender buildRecommender(DataModel dataModel)
				throws TasteException {
			UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel,Weighting.WEIGHTED);
			UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.5, similarity, dataModel);
			return new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
		}
	}
}

package maven;

import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;



public class EvaluateRecommender1 {
	public static void main(String[] args) throws IOException, TasteException{
		new EvaluateRecommender1();
	}
	
	public EvaluateRecommender1() throws IOException, TasteException {
		DataModel model = new FileDataModel(new File("/your_path/advertiser_bidding.csv"));		
		RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
		RecommenderBuilder builder = new MyRecommenderBuilder();
		for (int i = 0; i < 10; i++) {
			double result = evaluator.evaluate(builder, null, model, 0.95, 0.05);
			System.out.println(result);
		}
	}
	
	public class MyRecommenderBuilder implements RecommenderBuilder {
		public Recommender buildRecommender(DataModel dataModel) throws TasteException{
			ItemSimilarity similarity = new PearsonCorrelationSimilarity(dataModel,Weighting.WEIGHTED);
			// TODO Auto-generated method stub
			return new GenericItemBasedRecommender(dataModel, similarity);
		}
	}
}

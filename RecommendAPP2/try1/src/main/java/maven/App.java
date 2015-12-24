package maven;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;




public class App {
	public static void main(String[] args) throws IOException, TasteException {
		DataModel model = new FileDataModel(new File("/your_path/advertiser_bidding.csv"));//load data from file
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model,Weighting.WEIGHTED);//compute the correlation coefficient between their interactions
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.5,similarity,model);//define the threshold for recommendation
		UserBasedRecommender recommender = new GenericUserBasedRecommender(model,neighborhood,similarity);//create recommenders
		List<RecommendedItem> recommendations = null;

		//count the number of lines in a file and get the largest number of id
		BufferedReader reader = new BufferedReader(new FileReader("/your_path/advertiser_bidding.csv"));
		String line = reader.readLine();
		String final_line = null;
		//int line_num = 0;
		while (line != null) {
			//line_num ++;
			final_line = line;
			line = reader.readLine();
		}
		reader.close();
		int numberOfID = Integer.parseInt(final_line.split(",")[0]); //the number of advertiser in total
		
		// write the recommendation into file
		PrintWriter writer = new PrintWriter("userbased_recommender.txt"); //write the result to a file
		for (int i = 1; i <= numberOfID; i++) {
			recommendations = recommender.recommend(i,3);
			if (!recommendations.isEmpty()) {
				print:
				for (int j = 0; j < 3; j++ ) {
					if (recommendations.size() >= j + 1) {
						String raw = recommendations.get(j).toString();
						int item_index_start = raw.indexOf(":") + 1;
						int item_index_end = raw.indexOf(",");
						int value_index_start = raw.lastIndexOf(":") + 1;
						int value_index_end = raw.lastIndexOf("]");
						String item = raw.substring(item_index_start, item_index_end);
						String value = raw.substring(value_index_start, value_index_end);
						String message = i + " " + item + " " + value;
						//System.out.println(recommendations.get(j));
						System.out.println(message);
						writer.println(message);
					}
					else {
						break print;
					}
					
				}
			}
		}
		writer.close();
	}
}

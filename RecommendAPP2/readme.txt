
1. ydata-ysm-advertiser-phrase-graph-v1_0.txt: the raw data, need to be processed by partition.py

2. partition.py: the Python program to process raw data into “advertiser-phrase-bidding value” entries and generate “advertiser_bidding.csv”


3. try1: the folder in Eclipse workspace, the .java file conduct the recommendation action on the file we get in the last step “advertiser_bidding.csv”:

   under /try1/src/main/java/maven/, there are four .java file:
 - App.java: user-based, Pearson correlation-based similarity+weighting recommendation
 - APp1.java: item-based, Pearson correlation-based similarity+weighting recommendation
 - EvaluateRecommender.java:user-based,Pearson correlation-based similarity+weighting evaluation
 - EvaluateRecommender1.java:item-based, Pearson correlation-based similarity+weighting evaluation
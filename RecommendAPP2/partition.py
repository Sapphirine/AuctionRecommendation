# -*- coding: utf-8 -*-
import random

fileread = open("/your_path/ydata-ysm-advertiser-phrase-graph-v1_0.txt")

ad_ph_price = open("/your_path/advertiser_bidding.csv", "w")
ad_ph_price.write("advertise,phrase,price\n")
read = fileread.readlines()
for i in range(0, 193582):
	line = read[i + 459679].strip("\n")
	number = len(line.split(" "))
	for j in range(0, number):
		ran = random.randrange(1, 200)
		ad_ph_price.write(str(i + 1))
		out = line.split(" ")[j]
		ad_ph_price.write(","+out+","+str(ran)+"\n")
fileread.close()
ad_ph_price.close()
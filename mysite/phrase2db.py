import os
os.environ.setdefault("DJANGO_SETTINGS_MODULE", "mysite.settings")
import django
django.setup()

def main():
    from polls.models import Phrase
    f = open('bidding-out.txt')
    lphraseid = "0"
    luserid = ""
    lprice = ""

    cnt = 0
    for line in f:
    	cnt += 1
    	if cnt%1000 == 1:
    		print cnt
        phraseid,userid,price = line.split(" ")
        price = price[:len(price)-1]
        if phraseid == lphraseid:
        	luserid = luserid + ' ' + userid
        	lprice = lprice + ' ' + price
        else:
        	if lphraseid != "0":
        		phrase_number_id = int(lphraseid)
        		phrase_id = 'Phrase' + lphraseid
        		phrase_bidding_user = luserid
        		phrase_bidding_price = lprice
        		Phrase.objects.create(phrase_number_id=phrase_number_id,
        			phrase_id=phrase_id,
        			phrase_bidding_user=phrase_bidding_user,
        			phrase_bidding_price=phrase_bidding_price)

        	lphraseid = phraseid
        	lprice = price
        	luserid = userid


    f.close()

if __name__ == "__main__":
    main()
    print('Done!')
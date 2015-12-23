import os
os.environ.setdefault("DJANGO_SETTINGS_MODULE", "mysite.settings")
import django
django.setup()

def main():
	from polls.models import User
	f = open('rec-out.txt','r')

	cnt = 0
	for line in f:
		cnt += 1
		if cnt%1000 == 1:
			print cnt
		user,phrase,price=line.split(',')
		price=price[:len(price)-1]
		u1 = User.objects.get(user_number_id=user)
		u1.user_bidding_newphrase = phrase
		u1.user_bidding_newprice = price
		u1.save()


		



	f.close()

if __name__ == "__main__":
    main()
    print('Done!')
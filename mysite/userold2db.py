import os
os.environ.setdefault("DJANGO_SETTINGS_MODULE", "mysite.settings")
import django
django.setup()
import csv

def main():
    from polls.models import User
    f = file('bidding.csv','rb')
    reader = csv.reader(f)
    lphrase = ""
    luser = "0"
    lprice = ""
    luserid = ""

    cnt = 0
    for line in reader:
        cnt += 1
        if cnt%1000 == 1:
            print cnt

        user,phrase,price = line
        if luser == user:
            lphrase1 = lphrase + ' ' + phrase
            lprice1 = lprice + ' ' + price
            if len(lphrase1)<256 and len(lprice1) < 256:
                lprice = lprice1
                lphrase = lphrase1
        else:
            if luser != "0":
                user_number_id = int(luser)
                user_id = luserid
                user_bidding_oldphrase = lphrase
                user_bidding_oldprice = lprice
                User.objects.create(user_number_id=user_number_id,
                    user_id=user_id,
                    user_bidding_oldphrase=user_bidding_oldphrase,
                    user_bidding_oldprice=user_bidding_oldprice)


            lphrase = phrase
            lprice = price
            luser = user
            luserid = "User"+user+"@auction.com"




    f.close()

if __name__ == "__main__":
    main()
    print('Done!')
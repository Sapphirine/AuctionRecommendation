from __future__ import unicode_literals
from django.shortcuts import render, get_object_or_404
from django.http import HttpResponse, Http404, HttpResponseRedirect
from .models import User, Phrase
from django.template import RequestContext, loader
from django.core.urlresolvers import reverse
from django.views import generic
import json


def index(request):
    list = [1,2,3]
    return render(request, 'polls/index.html', {'context':json.dumps(list)})

def login(request):
    email = str(request.GET['id'] )
    user = get_object_or_404(User, user_id=email)
    newphrase = user.user_bidding_newphrase
    oldphrase = user.user_bidding_oldphrase
    oldprice = user.user_bidding_oldprice
    newprice = user.user_bidding_newprice

    oldphrases = oldphrase.split(' ')
    newphrases = newphrase.split(' ')
    oldprices = oldprice.split(' ')
    newprices = newprice.split(' ')
    num = len(newphrases)
    num1 = len(oldphrases)
    chart = {"name":"Total","children":[{"name":"Recommended Bidding","children":[]}, {"name":"Previous Bidding","children":[]}]}
    for j in range(num1):
    	item = {}
    	item["name"] = "Phrase" + oldphrases[j]
    	item["size"] = int(oldprices[j])
    	chart["children"][1]["children"].append(item)

    for i in range(num):
		item = {}
		item["name"] = "Phrase" + newphrases[i]
		item["size"] = int(newprices[i])
		chart["children"][0]["children"].append(item)


    
    json.dump(chart,open("/home/cookie/mysite/polls/static/polls/js/readme.json","w"))

    return render(request, 'polls/detail.html', {'user': user})

def search(request):
	phraseName = str(request.GET["phraseSearch"] )
	phrase = get_object_or_404(Phrase, phrase_id=phraseName)
	user = phrase.phrase_bidding_user
	oldprice = phrase.phrase_bidding_price
	users = user.split(' ')
	oldprices = oldprice.split(' ')
	num = len(users)
	chart = {"name":"Total","children":[{"name":"Previous Bidding","children":[]}]}
	for i in range(num):
		item = {}
		item["name"] = "User" + users[i]
		item["size"] = int(oldprices[i])
		chart["children"][0]["children"].append(item)

	json.dump(chart,open("/home/cookie/mysite/polls/static/polls/js/readme.json","w"))

	return render(request, 'polls/results.html', {'phrase': phrase})




# Create your views here.

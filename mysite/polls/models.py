from __future__ import unicode_literals

import datetime
from django.db import models
from django.utils import timezone

# Create your models here.


class User(models.Model):
	user_number_id = models.IntegerField(default=0)
	user_id = models.CharField(max_length=255,default=" ")
	user_bidding_oldphrase = models.CharField(max_length=255,default=" ")
	user_bidding_oldprice = models.CharField(max_length=255,default=" ")
	user_bidding_newphrase = models.CharField(max_length=255,default=" ")
	user_bidding_newprice = models.CharField(max_length=255,default=" ")
	def __unicode__(self):
		return self.user_id


class Phrase(models.Model):
	phrase_number_id = models.IntegerField(default=0)
	phrase_id = models.CharField(max_length=255,default=" ")
	phrase_bidding_user = models.CharField(max_length=255,default=" ")
	phrase_bidding_price = models.CharField(max_length=255,default=" ")
	def __unicode__(self):
		return self.phrase_id
	
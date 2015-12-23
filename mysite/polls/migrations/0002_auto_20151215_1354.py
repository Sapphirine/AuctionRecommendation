# -*- coding: utf-8 -*-
# Generated by Django 1.9 on 2015-12-15 18:54
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('polls', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Phrase',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('phrase_number_id', models.IntegerField()),
                ('phrase_id', models.CharField(max_length=255)),
                ('phrase_bidding_user', models.CharField(max_length=255)),
                ('phrase_bidding_price', models.CharField(max_length=255)),
            ],
        ),
        migrations.CreateModel(
            name='User',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('user_number_id', models.IntegerField()),
                ('user_id', models.CharField(max_length=255)),
                ('user_bidding_phrase', models.CharField(max_length=255)),
                ('user_bidding_oldprice', models.CharField(max_length=255)),
                ('user_bidding_newprice', models.CharField(max_length=255)),
            ],
        ),
        migrations.RemoveField(
            model_name='choice',
            name='question',
        ),
        migrations.DeleteModel(
            name='Choice',
        ),
        migrations.DeleteModel(
            name='Question',
        ),
    ]
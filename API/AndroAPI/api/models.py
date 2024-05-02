from django.db import models

class Concert(models.Model):
    name = models.CharField(max_length=200)
    description = models.TextField()
    award = models.CharField(max_length=200)

class Human(models.Model):
    name = models.CharField(max_length=200)
    birth = models.DateField()
    bio = models.TextField()

class Film(models.Model):
    name = models.CharField(max_length=200)
    duration = models.IntegerField()  
    score = models.FloatField()
    desc = models.TextField()
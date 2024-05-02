from rest_framework import serializers
from .models import Concert, Human, Film

class ConcertSerializer(serializers.ModelSerializer):
    class Meta:
        model = Concert
        fields = '__all__'

class HumanSerializer(serializers.ModelSerializer):
    class Meta:
        model = Human
        fields = '__all__'

class FilmSerializer(serializers.ModelSerializer):
    class Meta:
        model = Film
        fields = '__all__'
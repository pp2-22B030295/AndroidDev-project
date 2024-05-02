from rest_framework import viewsets
from .models import Concert, Human, Film
from .serializers import ConcertSerializer, HumanSerializer, FilmSerializer

class ConcertViewSet(viewsets.ModelViewSet):
    queryset = Concert.objects.all()
    serializer_class = ConcertSerializer

class HumanViewSet(viewsets.ModelViewSet):
    queryset = Human.objects.all()
    serializer_class = HumanSerializer

class FilmViewSet(viewsets.ModelViewSet):
    queryset = Film.objects.all()
    serializer_class = FilmSerializer
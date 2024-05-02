from django.urls import include, path
from rest_framework.routers import DefaultRouter
from . import views

router = DefaultRouter()
router.register(r'concerts', views.ConcertViewSet)
router.register(r'humans', views.HumanViewSet)
router.register(r'films', views.FilmViewSet)

urlpatterns = [
    path('', include(router.urls)),
]
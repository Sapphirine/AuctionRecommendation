from django.conf.urls import url

from . import views

app_name = 'polls'
urlpatterns = [
    url(r'^$', views.index, name='index'),
    #url(r'^u(?P<user_number_id>[0-9]+)/$', views.login, name='login'),
    url(r'^login/$', views.login, name='login'),
    url(r'^p/$', views.search, name='search'),
]
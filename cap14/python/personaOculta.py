from datetime import datetime
from dateutil.relativedelta import relativedelta

class PersonaOculta:

    def __init__(self, nombre, apellidos, fechaNac, colorFavorito):
        self.__nombre = nombre
        self.__apellidos = apellidos
        self.__fechaNac = fechaNac
        self.__colorFavorito = colorFavorito
        print("---", nombre, "nació el", fechaNac) # debug

    def esAdulto(self):
        edad = self.__edad()
        return edad.years >= 18

    def cumpleHoy(self):
        edad = self.__edad()
        return edad.months == 0 and edad.days == 0

    def nombre(self):
        return self.__nombre

    @property
    def apellidos(self):
        return self.__apellidos

    @property
    def colorFavorito(self):
        return self.__colorFavorito

    @colorFavorito.setter
    def colorFavorito(self, nuevoColor):
        self.__colorFavorito = nuevoColor

    def __edad(self):
        return relativedelta(datetime.now(), self.__fechaNac)
### Fin class PersonaOculta

edadHoy17 = datetime.now() - relativedelta(years = 17)
edadHoy20YMedio = datetime.now() \
    - relativedelta(months = 6) - relativedelta(years = 20)

p1 = PersonaOculta("Paula", "Gala", edadHoy17, "rojo")
print(p1.nombre(), "es mayor de edad:", p1.esAdulto())
if p1.cumpleHoy():
    print("Hoy hay que felicitar a", p1.nombre(), p1.apellidos)

p2 = PersonaOculta("Pablo", "Prego", edadHoy20YMedio, "azul")
print(p2.nombre(), "es mayor de edad:", p2.esAdulto())
if p2.cumpleHoy():
    print("Hoy hay que felicitar a", p2.nombre, p2.apellidos)

print("Colores Favoritos: " , p1.nombre(), p1.colorFavorito, 
      p2.nombre(), p2.colorFavorito)
# Ahora a Pablo le gusta el color favorito de Paula
p2.colorFavorito = p1.colorFavorito
print("Colores Favoritos: " , p1.nombre(), p1.colorFavorito, 
      p2.nombre(), p2.colorFavorito)

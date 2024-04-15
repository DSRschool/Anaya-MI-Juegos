from enum import Enum, auto

class Nivel(Enum):
    FACIL = auto()
    MEDIO = auto()
    DIFICIL = auto()
    IMPOSIBLE = auto()

class RangoEdad(Enum):
    INFANTIL = [0, 6]
    PRIMARIA = [6, 12]
    SECUNDARIA = [12, 16]
    BACHILLERATO = [16, 18]
    
    def enRango(self, edad):
        return edad >= self.value[0] and edad <= self.value[1]

# Prueba Nivel
print("PRUEBA DE NIVEL")
nivel = Nivel.DIFICIL
print("Te toca el nivel", nivel)
print("Te toca el nivel", nivel.name)

if (nivel == Nivel.FACIL):
    print("Tú puedes con más, no te quedes ahí")
else:
    print("¡Olé tu valentía!")

print("Los niveles posibles son:", list(Nivel))
print()

# Prueba RangoEdad
print("PRUEBA DE RANGO DE EDAD")
edades = [2, 6, 11, 18]
for edad in edades:
    for rango in list(RangoEdad):
        print("La edad", edad, 
              "sí" if rango.enRango(edad) else "no", 
              "está en rango para", rango.name)

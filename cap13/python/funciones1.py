from math import sqrt

def cuadrado(n):
    return n * n

def hipotenusa(a, b):
    return sqrt(cuadrado(a) + cuadrado(b))

hipotenusa = hipotenusa(3, 4)
print("La hipotenusa de un triángulo de base 3 y altura 4 es", hipotenusa)
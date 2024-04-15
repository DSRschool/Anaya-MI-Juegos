PALABRA = "PAPA" # "ABRACADABRA"

COMODIN = "_"

TITULO = "=== El Ahorcado ==="
DIME = "Dime una letra\n"
ENHORABUENA = "Enhorabuena, has resuelto la palabra buscada:"

print(TITULO)
objetivo = PALABRA
enJuego = COMODIN * len(objetivo) # Para generar "____"
print(enJuego)
adivinado = False
while not adivinado:
    letra = input(DIME)
    for i in range(len(objetivo)):
        if objetivo[i] == letra:
            enJuego = enJuego[:i] + letra + enJuego[i+1:]
    adivinado = COMODIN not in enJuego
    print(enJuego)
print(ENHORABUENA, objetivo)

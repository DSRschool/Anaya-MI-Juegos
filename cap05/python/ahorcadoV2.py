import random

PALABRAS = ["PELOTA", "CAMISA", "PESADO", "AMIGAS", "ZAPATO",
            "TOMATE", "JUGADA", "ALMEJA", "PETATE", "LUCERO",
			"AMIGA", "BUCEO", "CASPA", "DEDAL", "ELITE", "FUEGO"] 
NUM_FALLOS = 5

COMODIN = "_"

TITULO = "=== El Ahorcado ==="
DIME = "Dime una letra\n"
ENHORABUENA = "Enhorabuena, has resuelto la palabra buscada:"
PERDIDO = (
    "¡Oooh! Ya no te quedan intentos para dar con la palabra buscada:"
    )
ERROR_MUCHAS = "No vale, solo una letra"
INTENTOS = "Intentos:"
INTENTOS_GASTADOS = "*"
INTENTOS_RESTANTES = "·"

def escogerObjetivo():
    return PALABRAS[random.randint(0, len(PALABRAS)-1)]

print(TITULO)
objetivo = escogerObjetivo()
enJuego = COMODIN * len(objetivo)
print(enJuego, "\t", INTENTOS, INTENTOS_RESTANTES * NUM_FALLOS)

fallos = 0
adivinado = False
while not adivinado and fallos < NUM_FALLOS:
    acierto = False
    letra = input(DIME).upper()
    if len(letra) != 1:
        print(ERROR_MUCHAS)
        continue
    for i in range(len(objetivo)):
        if objetivo[i] == letra:
            acierto = True
            enJuego = enJuego[:i] + letra + enJuego[i+1:]
    if not acierto:
        fallos += 1
    adivinado = COMODIN not in enJuego
    print(enJuego, "\t", INTENTOS, 
          INTENTOS_GASTADOS * fallos 
          + INTENTOS_RESTANTES * (NUM_FALLOS - fallos))

if adivinado:
    print(ENHORABUENA, objetivo)
else:
    print(PERDIDO, objetivo)

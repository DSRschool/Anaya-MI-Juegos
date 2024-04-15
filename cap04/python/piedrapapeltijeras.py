import random

# Declaración de constantes
JUEGO = ["P", "L", "T"]
SALIR = "S"

EMPATE = 0
GANAS = 1
PIERDES = 2
ERROR_NO_ENCONTRADA = -1

BIENVENIDA = "=== Vamos a jugar al Piedra, Papel, Tijeras ==="
DESPEDIDA = "¡Gracias por jugar!"
PEDIR_JUGADA = ("¿Cuál es tu jugada? "
    "[P]iedra, pape[L], [T]ijeras o [S]alir\n")
MSJ_GANAS = "¡Has ganado!"
MSJ_PIERDES = "¡Has perdido!"
MSJ_EMPATE = "¡Hemos empatado!"
MSJ_ERROR = "No te he entendido."

def determinarGanador(pc, jugador):
    res = jugador - pc
    if res < 0:
        res += len(JUEGO)
    return res

def pintarResultado(pc, jugador, resultado):
    print("Tú has jugado", JUEGO[jugador], "y el ordenador", 
          JUEGO[pc], end=" ")
    if resultado == GANAS:
        print(MSJ_GANAS)
    elif resultado == PIERDES:
        print(MSJ_PIERDES)
    else:
        print(MSJ_EMPATE)
        
print(BIENVENIDA)

while True:
    eleccionPc = random.randint(0, len(JUEGO)-1)
    eleccionJugador = input(PEDIR_JUGADA)
    if eleccionJugador == SALIR:
        break; # salimos del bucle
    if eleccionJugador not in JUEGO:
        print(MSJ_ERROR)
        continue; # saltamos a la siguiente iteración
    codElecJug = JUEGO.index(eleccionJugador)
    resultado = determinarGanador(eleccionPc, codElecJug)
    pintarResultado(eleccionPc, codElecJug, resultado)
    
print(DESPEDIDA)

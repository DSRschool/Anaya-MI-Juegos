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

print(BIENVENIDA)
eleccionPc = random.randint(0, len(JUEGO)-1)
print(eleccionPc)
sEleccionJugador = input(PEDIR_JUGADA)
print(sEleccionJugador)
print(DESPEDIDA)
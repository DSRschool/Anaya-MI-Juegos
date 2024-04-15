# constantes
X = "X"
O = "O"
VACIA = " "
N = "\n"

# tableros de ejemplo
fichasTableroVacio = [
        [VACIA, VACIA, VACIA],
        [VACIA, VACIA, VACIA],
        [VACIA, VACIA, VACIA]
    ]

tableroLleno = [ 
        [VACIA, O, VACIA], 
        [X, O, X], 
        [X, O, VACIA]
    ]

def pintarTablero(tablero):
    texto = ""
    for i in range(len(tablero)):
        # línea con las fichas
        for j in range(len(tablero[i])):
            texto += " " + tablero[i][j] + " "
            if j != len(tablero[i]) - 1:
                texto += "|"
            else:
                texto += N
        # línea de separación
        if i != len(tablero) - 1:
            for j in range(len(tablero[i])):
                texto += "---"
                if j != len(tablero[i]) - 1:
                    texto += "+"
                else:
                    texto += N
        else:
            texto += N
    print(texto)

print("Tablero vacío:")
pintarTablero(fichasTableroVacio)
         
print("Tablero lleno:")
pintarTablero(tableroLleno)

print("Tablero vacío en 1 línea:")
tableroVacio1Linea = \
     ("   |" * 2 + "   \n" + "---+" * 2 + "---\n") * 2 \
    + "   |" * 2 + "   \n"
print(tableroVacio1Linea)

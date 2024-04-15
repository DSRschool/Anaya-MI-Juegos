from enum import Enum
import random

N = "\n"    
TAMANYO = 3

### Enum ficha
class Ficha(Enum):
    X = 0
    O = 1

    def siguiente(self):
        if self == self.X:
            return self.O
        if self == self.O:
            return self.X
        return None # Caso imposible
### Fin enum ficha

### Clase partida
class Partida:

    def __init__(self, tamanyo):
        self.tablero = Tablero(tamanyo)
        self.turno = random.choice(list(Ficha))

    def jugar(self, fila, columna):
        if self.tablero.jugar(self.turno, fila, columna):
            self.turno = self.turno.siguiente()
        else:
            print("Casilla [%s, %s] ocupada, vuelve a jugar %s" 
                  % (fila, columna, self.turno.name))

    def terminada(self):
        return self.tablero.estaLleno() or self.ganador() != None

    def ganador(self):
        for jugador in list(Ficha):
            if self.tablero.gana(jugador):
                return jugador   
        return None

    def __str__(self):
        return str(self.tablero) + "\nTurno: " + self.turno.name
### Fin clase Partida

### Clase Tablero  
class Tablero:

    def __init__(self, tamanyo):
        self.tablero = []
        for i in range(tamanyo):
            fila = []
            for j in range(tamanyo):
                fila.append(None)
            self.tablero.append(fila)

    def jugar(self, ficha, fila, columna):
        if self.tablero[fila][columna] == None:
            self.tablero[fila][columna] = ficha
            return True
        return False

    def estaLleno(self):
        for linea in self.tablero:
            for ficha in linea:
                if ficha == None:
                    return False
        return True

    def gana(self, jugador):
        return self.ganaHorizontal(jugador) \
            or self.ganaVertical(jugador) \
            or self.ganaDiagonalDirecta(jugador) \
            or self.ganaDiagonalInversa(jugador)

    def ganaHorizontal(self, jugador):
        gana = False
        for linea in self.tablero:
            gana = True
            for ficha in linea:
                gana &= ficha == jugador
            if gana:
                break
        return gana

    def ganaVertical(self, jugador):
        gana = False
        for i in range(len(self.tablero)):
            gana = True
            for j in range(len(self.tablero[i])):
                gana &= self.tablero[j][i] == jugador
            if gana:
                break
        return gana

    def ganaDiagonalDirecta(self, jugador): 
        gana = True
        for i in range(len(self.tablero)):
            gana &= self.tablero[i][i] == jugador
        return gana

    def ganaDiagonalInversa(self, jugador): 
        gana = True
        for i in range(len(self.tablero)):
            gana &= self.tablero[len(self.tablero)-1-i][i] == jugador
        return gana

    def __str__(self):
        texto = ""
        for i in range(len(self.tablero)):
            # línea con las fichas
            for j in range(len(self.tablero[i])):
                texto += " " + self.valueOf(self.tablero[i][j]) + " "
                if j != len(self.tablero[i]) - 1:
                    texto += "|"
                else:
                    texto += N
            # línea de separación
            if i != len(self.tablero) - 1:
                for j in range(len(self.tablero[i])):
                    texto += "---"
                    if j != len(self.tablero[i]) - 1:
                        texto += "+"
                    else:
                        texto += N
            else:
                texto += N
        return texto

    def valueOf(self, ficha):
        if ficha == None:
            return " "
        return ficha.name
### Fin clase Tablero
    
def pideJugada(tipo):
    while True:
        pos = int(input("Indica la " + tipo + " "))
        if pos >= 0 and pos < TAMANYO:
            return pos
        print("El valor debe estar entre 0 y " + str(TAMANYO - 1))

# main
print("=== 3 en raya ===")
partida = Partida(TAMANYO)
print(partida)
while (not partida.terminada()):
    fila = pideJugada("fila")
    columna = pideJugada("columna")
    partida.jugar(fila, columna)
    print(partida)
    if partida.terminada():
        print("Partida terminada")
        ganador = partida.ganador()
        if ganador != None:
            print("Ha ganado", ganador.name)
        else:
            print("Tablas")

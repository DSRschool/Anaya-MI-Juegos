from enum import Enum
import random
import tkinter as tk
import tkinter.font as font
from functools import partial

TAM_BOTON = 3
TAM_FUENTE = 13
#FUENTE = font.Font(size=TAM_FUENTE, weight="bold")

ROJO = "#ff0000"
VERDE = "#00ff00"

N = "\n"
TAM_TABLERO = 3

TITULO = "3 en raya"
INICIO = "Empieza jugando {}"
TERMINADA = "Partida terminada: ganó {}"
EMPATE = "Partida terminada: empate"
TURNO = "Turno: {}"
OCUPADA = "Casilla ocupada - Turno: {}"
GANADOR = "¡{} gana!"

### Enum ficha
class Ficha(Enum):
    X = ROJO
    O = VERDE

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
        self.tamanyo = tamanyo
        TableroGrafico(self)

    def jugar(self, fila, columna):
        posible = self.tablero.jugar(self.turno, fila, columna)
        if posible:
            self.turno = self.turno.siguiente()
        return posible

    def terminada(self):
        return self.tablero.estaLleno() or self.ganador() != None

    def ganador(self):
        for jugador in list(Ficha):
            if self.tablero.gana(jugador):
                return jugador
        return None
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
### Fin clase Tablero

### Clase TableroGrafico
class TableroGrafico(tk.Frame):

    def __init__(self, partida):
        root = tk.Tk()
        root.title(TITULO)
        super().__init__(root)
        self.pack()
        self.listener = JugadaListener(partida, self)
        self.casillero = Casillero(self, partida, self.listener)
        self.barraEstado = tk.Label(self, 
            text = INICIO.format(partida.turno.name))
        self.barraEstado.pack()
        root.mainloop()

    def actualizarEstado(self, mensaje):
        self.barraEstado.config(text = mensaje)

    def jugar(self, fila, columna, jugador):
        self.casillero.jugar(fila, columna, jugador)
### Fin clase TableroGrafico

### Clase Casillero
class Casillero(tk.Frame):

    def __init__(self, gui, partida, listener):
        super().__init__(gui)
        self.casillas = []
        for i in range(partida.tamanyo):
            fila = []
            for j in range(partida.tamanyo):
                pulsarIJ = partial(listener.pulsar, i, j)
                casilla = Casilla(self, i, j, pulsarIJ)
                fila.append(casilla)
            self.casillas.append(fila)
        self.pack()

    def jugar(self, fila, columna, jugador):
        self.casillas[fila][columna].ocupar(jugador)
### Fin clase Casillero

### Clase Casilla
class Casilla(tk.Button):

    def __init__(self, casillero, fila, col, pulsarIJ):
        super().__init__(casillero, command=pulsarIJ, 
                            height=TAM_BOTON, width=TAM_BOTON)
        self.grid(row=fila, column=col)

    def ocupar(self, jugador):
        self["font"] = font.Font(size=TAM_FUENTE, weight="bold") #FUENTE
        self["text"] = jugador.name
        self["fg"] = jugador.value # color
### Fin clase Casilla

### Clase JugadaListener
class JugadaListener:
    def __init__(self, partida, gui):
        self.partida = partida
        self.gui = gui # TableroGrafico

    def pulsar(self, fila, columna):
        if self.partida.terminada():
            self.gui.actualizarEstado(
                TERMINADA.format(self.partida.ganador().name))
            return
        jugador = self.partida.turno
        if self.partida.jugar(fila, columna):
            self.gui.jugar(fila, columna, jugador)
            ganador = self.partida.ganador()
            if ganador == None:
                if self.partida.terminada():
                    self.gui.actualizarEstado(EMPATE)
                else:
                    self.gui.actualizarEstado(
                        TURNO.format(self.partida.turno.name))
            else:
                self.gui.actualizarEstado(GANADOR.format(jugador.name))
        else:
            self.gui.actualizarEstado(OCUPADA.format(jugador.name))
### Fin clase JugadaListener

# main
partida = Partida(TAM_TABLERO)

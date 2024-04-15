from enum import Enum
import random
import tkinter as tk
import tkinter.font as font
from functools import partial

TAM_BOTON = 6
TAM_FUENTE = 25

ROJO = "#ff0000"
VERDE = "#00ff00"
AZUL = "#0000ff"
CIAN = "#00ffff"
NARANJA = "#ffc800"
MAGENTA = "#ff00ff"
GRIS =  "#808080"
ROSA = "#ffafaf"

NUM_REPETICIONES = 2

# Mensajes IU
TITULO = "Memory"
EMPIEZA = "Selecciona dos tarjetas"
NO_JUGABLE = "Clica en otra casilla"
PAREJA = "¡Pareja!"
SIGUE_JUGANDO = "Sigue jugando..."
TERMINADA = "Partida terminada en {} intentos"

# Consola
NUMERO_COLUMNAS = "Número de columnas: ";
NUMERO_FILAS = "Número de filas: ";

# Excepciones
DIMENSIONES_INVIABLES = "Las dimensiones solicitadas no son viables: {}.";
EXC_CASILLAS_IMPARES = "hay un número impar de casillas";
EXC_SIMBOLOS_INSUFICIENTES = "no hay símbolos suficientes para rellenar el tablero";

### Enum Simbolo
class Simbolo(Enum):
    A = ROJO
    B = VERDE
    C = AZUL
    D = CIAN
    E = NARANJA
    F = MAGENTA
    G = GRIS
    H = ROSA
### Fin enum Simbolo

### Enum Estado
class Estado(Enum):
    OCULTA = 0
    VISIBLE = 1
    EMPAREJADA = 2
### Fin enum estado

### Clase partida
class Partida:

    def __init__(self, alto, ancho):
        self.tablero = Tablero(alto, ancho)
        self.intentos = 0
        self.alto = alto
        self.ancho = ancho
        TableroGrafico(self)

    def registrarIntento(self):
        self.intentos += 1

    def terminada(self):
        return self.tablero.estaLleno()
    
    def getTarjeta(self, fila, columna):
        return self.tablero.getTarjeta(fila, columna)
### Fin clase Partida

### Clase Tarjeta
class Tarjeta:
    def __init__(self, simbolo):
        self.simbolo = simbolo
        self.estado = Estado.OCULTA

    def emparejar(self):
        self.estado = Estado.EMPAREJADA

    def mostrar(self):
        self.estado = Estado.VISIBLE

    def ocultar(self):
        self.estado = Estado.OCULTA
### Fin clase Tarjeta

### Excepcion CasillasImpares
class CasillasImparesException(Exception):
    pass
### Fin excepcion CasillasImpares

### Excepcion SimbolosInsuficientes
class SimbolosInsuficientesException(Exception):
    pass
### Fin excepcion SimbolosInsuficientes

### Clase Tablero
class Tablero:

    def __init__(self, alto, ancho):
        self.tablero = []
        self.alto = alto
        self.ancho = ancho
        self.numParejas = int((alto * ancho) / NUM_REPETICIONES)
        self.validarDimensiones()
        simbolos = self.generarListaSimbolos()
        for i in range(alto):
            fila = []
            for j in range(ancho):
                fila.append(Tarjeta(simbolos[i * ancho + j]))
            self.tablero.append(fila)

    def validarDimensiones(self):
        if ((self.alto * self.ancho) % 2 != 0):
            raise CasillasImparesException()
        if (self.numParejas > len(Simbolo)):
            raise SimbolosInsuficientesException()

    def generarListaSimbolos(self):
        simbolos = []
        cont = 0
        for simbolo in Simbolo:
            simbolos.append(simbolo)
            simbolos.append(simbolo)
            cont += 1
            if cont == self.numParejas:
                break
        random.shuffle(simbolos)
        return simbolos

    def estaLleno(self):
        for linea in self.tablero:
            for tarjeta in linea:
                if tarjeta.estado != Estado.EMPAREJADA:
                    return False
        return True

    def getTarjeta(self, fila, columna):
        return self.tablero[fila][columna]
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
        self.barraEstado = tk.Label(self, text = EMPIEZA)
        self.barraEstado.pack()
        root.mainloop()

    def actualizarEstado(self, mensaje):
        self.barraEstado.config(text = mensaje)
### Fin clase TableroGrafico

### Clase Casillero
class Casillero(tk.Frame):

    def __init__(self, gui, partida, listener):
        super().__init__(gui)
        self.casillas = []
        for i in range(partida.alto):
            fila = []
            for j in range(partida.ancho):
                casilla = Casilla(self, partida.getTarjeta(i, j), i, j)
                pulsarIJ = partial(listener.pulsar, casilla)
                casilla.setListener(pulsarIJ)
                fila.append(casilla)
            self.casillas.append(fila)
        self.pack()
### Fin clase Casillero

### Clase Casilla
class Casilla(tk.Button):

    def __init__(self, casillero, tarjeta, fila, col):
        super().__init__(casillero, 
                            height=TAM_BOTON, width=TAM_BOTON)
        self.grid(row=fila, column=col)
        self.tarjeta = tarjeta
        self["font"] = font.Font(size=TAM_FUENTE, weight="bold")
        self["text"] = ""

    def setListener(self, listener):
        self.configure(command = listener)

    def mostrar(self):
        self.tarjeta.mostrar()
        simbolo = self.tarjeta.simbolo
        self["text"] = simbolo.name
        self["fg"] = simbolo.value # color

    def ocultar(self):
        self.tarjeta.ocultar()
        self["text"] = ""

    def bloquear(self):
        self.tarjeta.emparejar()

    def esJugable(self):
        return self.tarjeta.estado == Estado.OCULTA
### Fin clase Casilla

### Clase JugadaListener
class JugadaListener:
    def __init__(self, partida, gui):
        self.partida = partida
        self.gui = gui # TableroGrafico
        self.primeraCasilla = None
        self.segundaCasilla = None
        self.pareja = False

    def pulsar(self, casilla):
        if casilla.esJugable():
            if self.segundaCasilla != None:
                if not self.pareja:
                    self.primeraCasilla.ocultar()
                    self.segundaCasilla.ocultar()
                self.primeraCasilla = None
                self.segundaCasilla = None
                self.pareja = False
            casilla.mostrar()
            if self.primeraCasilla == None:
                self.primeraCasilla = casilla
            else:
                self.partida.registrarIntento()
                self.segundaCasilla = casilla
                if self.segundaCasilla.tarjeta.simbolo == self.primeraCasilla.tarjeta.simbolo:
                    self.primeraCasilla.bloquear()
                    self.segundaCasilla.bloquear()
                    self.pareja = True
                    self.gui.actualizarEstado(PAREJA)
                    if (self.partida.terminada()):
                        self.gui.actualizarEstado(
                            TERMINADA.format(self.partida.intentos))
                        return
                else: # no son iguales
                    self.pareja = False
                    self.gui.actualizarEstado(SIGUE_JUGANDO)
        else: # no es jugable
            self.gui.actualizarEstado(NO_JUGABLE)
### Fin clase JugadaListener

# main
alto = int(input(NUMERO_FILAS))
ancho = int(input(NUMERO_COLUMNAS))
try:
    partida = Partida(alto, ancho)
except CasillasImparesException:
    print(DIMENSIONES_INVIABLES.format(EXC_CASILLAS_IMPARES))
except SimbolosInsuficientesException:
    print(DIMENSIONES_INVIABLES.format(EXC_SIMBOLOS_INSUFICIENTES))

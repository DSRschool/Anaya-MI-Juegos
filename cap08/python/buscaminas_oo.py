from enum import Enum
import random
import tkinter as tk
import tkinter.font as font
from functools import partial

# Mensajes IU
TITULO = "Buscaminas"
EMPIEZA = "Localiza {} minas"

# Consola
NUMERO_COLUMNAS = "Número de columnas: "
NUMERO_FILAS = "Número de filas: "
NUMERO_MINAS = "Número de minas: "
DEMASIADAS_MINAS = \
    "Has pedido demasiadas minas para el tamaño del tablero"
VALORES_NO_POSITIVOS = "Los valores deben ser positivos"

# Tamaños
TAM_FUENTE = 14

DENSIDAD = 0.3 # % max de celdas con minas en el tablero
COLORES = ["#CBE5ED", "#021AFD", "#1D7E00", "#F80701", "#02077F", 
           "#830203", "#1E8080", "#464646", "#808080", "#000000"]

### Enum Resultado
class Resultado(Enum):
    BOMBA = "¡Bomba! ¡Has perdido!"
    FIN = "Ya hemos terminado"
    GANADA = "¡Bien! ¡Has ganado!"
    ANOTADO = "¡Anotado!"
    BIEN = "¡Bien!"
    ERROR = "Quizá deberías revisar las marcas"
### Fin Resultado

### Excepcion CifrasException
class CifrasException(Exception):
    pass
### Fin excepcion CifrasException

### Class Tablero
class Tablero:

    def __init__(self, alto, ancho, numMinas):
        self.alto = alto
        self.ancho = ancho
        self.numMinas = numMinas
        self.explotado = False
        self.__validarCifras()
        self.celdas = []
        for i in range(alto):
            fila = []
            for j in range(ancho):
                fila.append(Celda(self, i, j))
            self.celdas.append(fila)
        self.__establecerContornos()
        self.__ponerMinas()
        print(self) # Para debug
        TableroGrafico(self)

    def __validarCifras(self):
        if self.numMinas <= 0 or self.alto <= 0 or self.ancho <= 0:
            raise CifrasException(VALORES_NO_POSITIVOS)
        if self.numMinas > self.alto * self.ancho * DENSIDAD:
            raise CifrasException(DEMASIADAS_MINAS)

    def getCelda(self, fila, columna):
        return self.celdas[fila][columna]

    def partidaGanada(self):
        cont = 0
        for fila in self.celdas:
            for celda in fila:
                casilla = celda.casilla
                if casilla.descubierta():
                    cont += 1
        return cont == self.ancho * self.alto - self.numMinas

    def partidaPerdida(self):
        return self.explotado

    def explotar(self):
        self.explotado = True

    def __establecerContornos(self):
        for fila in self.celdas:
            for celda in fila:
                self.__establecerContorno(celda)

    def __establecerContorno(self, celda):
        contorno = []
        xIni = celda.x - 1 if celda.x != 0 else 0
        xFin = celda.x + 1 if celda.x != self.alto - 1 \
            else self.alto - 1
        yIni = celda.y - 1 if celda.y != 0 else 0
        yFin = celda.y + 1 if celda.y != self.ancho - 1 \
            else self.ancho - 1
        for i in range(xIni, xFin + 1):
            for j in range(yIni, yFin + 1):
                contorno.append(self.celdas[i][j])
        celda.setContorno(contorno)

    def __ponerMinas(self):
        for i in range(self.numMinas):
            hayMina = True
            while hayMina:
                x = random.randint(0, self.alto - 1)
                y = random.randint(0, self.ancho - 1)
                celda = self.celdas[x][y]
                hayMina = celda.hayMina()
                if not hayMina:
                    celda.ponerMina()

    def __str__(self):
        s = ""
        for fila in self.celdas:
            for celda in fila:
                s += str(celda)
            s += "\n"
        return s
### Fin class Tablero

### Class Celda
class Celda:
    def __init__(self, tablero, x, y):
        self.tablero = tablero
        self.x = x
        self.y = y
        self.esMina = False
        self.contador = 0
        self.marcada = False

    def ponerMina(self):
        self.esMina = True
        self.__incrementarVecinas()

    def hayMina(self):
        return self.esMina

    def incrementarContador(self):
        self.contador += 1

    def invertirMarca(self):
        self.marcada = not self.marcada

    def explotar(self):
        self.tablero.explotar()

    def setContorno(self, contorno):
        self.contorno = contorno

    def __incrementarVecinas(self):
        for vecina in self.contorno:
            if not vecina.hayMina():
                vecina.incrementarContador()

    def __str__(self):
        return "X" if self.esMina else str(self.contador)
### Fin class Celda

### Class TableroGrafico
class TableroGrafico(tk.Frame):

    def __init__(self, tablero):
        root = tk.Tk()
        root.title(TITULO)
        self.MINA = tk.PhotoImage(file = "mina.png")
        self.EXPLOSION = tk.PhotoImage(file = "explosion.png")
        super().__init__(root)
        self.pack()
        self.listener = JugadaListener(tablero, self)
        self.casillero = Casillero(self, tablero, self.listener)
        self.barraEstado = tk.Label(self, 
            text = EMPIEZA.format(tablero.numMinas))
        self.barraEstado.pack()
        root.mainloop()

    def actualizarEstado(self, mensaje):
        self.barraEstado.config(text = mensaje)
### Fin class TableroGrafico

### Clase Casillero
class Casillero(tk.Frame):

    def __init__(self, gui, tablero, listener):
        super().__init__(gui)
        self.gui = gui
        self.casillas = []
        for i in range(tablero.alto):
            fila = []
            for j in range(tablero.ancho):
                casilla = Casilla(self, tablero.getCelda(i, j), i, j)
                pulsarIJ = partial(listener.pulsar, casilla)
                casilla.setListener(pulsarIJ)
                fila.append(casilla)
            self.casillas.append(fila)
        self.pack()
### Fin clase Casillero

### Clase Casilla
class Casilla(tk.Button):

    def __init__(self, casillero, celda, fila, col):
        super().__init__(casillero)
        self.grid(row = fila, column = col, ipadx = 1, ipady = 1)
        self.casillero = casillero
        self.visible = False
        self.celda = celda
        self.celda.casilla = self
        self["font"] = font.Font(size = TAM_FUENTE, weight = "bold")
        self["text"] = " "

    def setListener(self, listener):
        self.bind("<Button>", listener)
        self.bind("<Button-2>", listener)

    def mostrar(self):
        self.visible = True
        if not self.celda.marcada:
            if self.celda.esMina:
                self["image"] = self.casillero.gui.EXPLOSION
                self.celda.explotar()
                return Resultado.BOMBA
            else:
                self["text"] = self.celda.contador
                self["font"] = font.Font(weight = "bold")
                self["fg"] = COLORES[self.celda.contador]
                # desactivar clics
                self.unbind("<Button>")
                self.unbind("<Button-2>")
                if self.celda.contador == 0:
                    self.__destaparContorno()
                return Resultado.BIEN
        return Resultado.ERROR

    def invertirMarca(self):
        self.celda.invertirMarca()
        if self.celda.marcada:
            self["image"] = self.casillero.gui.MINA
        else:
            self.configure(image = "")

    def descubierta(self):
        return self.visible

    def __destaparContorno(self):
        for celdaVecina in self.celda.contorno:
            casillaVecina = celdaVecina.casilla
            if not casillaVecina.descubierta():
                casillaVecina.mostrar()
### Fin clase Casilla

### Clase JugadaListener
class JugadaListener:
    def __init__(self, tablero, gui):
        self.tablero = tablero
        self.gui = gui # TableroGrafico

    def pulsar(self, casilla, evento):
        if self.tablero.partidaPerdida() or self.tablero.partidaGanada():
            self.gui.actualizarEstado(Resultado.FIN.value)
        else:
            if evento.num == 1: # botón principal
                resultado = casilla.mostrar()
                self.gui.actualizarEstado(resultado.value)
                if not self.tablero.partidaPerdida() \
                        and self.tablero.partidaGanada():
                    self.gui.actualizarEstado(Resultado.GANADA.value)
            elif evento.num == 2: # botón secundario
                casilla.invertirMarca()
                self.gui.actualizarEstado(Resultado.ANOTADO.value)
### Fin clase JugadaListener

# main
alto = int(input(NUMERO_FILAS))
ancho = int(input(NUMERO_COLUMNAS))
numMinas = int(input(NUMERO_MINAS))
try:
    Tablero(alto, ancho, numMinas)
except CifrasException as ce:
    print(ce)

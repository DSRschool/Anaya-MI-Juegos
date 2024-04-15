import lingo_modelo as modelo
import lingo_controlador as controlador

import tkinter as tk
from functools import partial

### Mensajes IU
TITULO = "Tablero Lingo"

# Botonera
NUEVA = "Nueva"
MAS_PISTAS = "+ Pistas"
SOLUCION = "Solución"
SALIR = "Salir"
PISTA_ALEATORIA = "Pista Aleatoria"

# Menú
LONGITUD_PALABRAS = "Longitud Palabras"
OPCIONES = "Opciones"

# Marcador
PUNTUACION_LINEA = "Puntuación línea:"
PUNTUACION_PALABRA = "Puntuación palabra:"
PUNTUACION_TOTAL = "Puntuación total:"
PALABRAS_JUGADAS = "Palabras jugadas:"
PALABRAS_GANADAS = "Palabras ganadas:"

# Mensajes
MENS_NUEVA = "Pulse nueva para empezar con una nueva palabra"
MENS_AYUDA = "Pulse + Pistas para ver otra letra"
MENS_FELIZ = "FELICIDADES, palabra encontrada"
MENS_TERMINADA = "OOOHHH, tablero terminado, la solución era {}"
MENS_ENTRADA_NOVALIDA = "'{}' no está en el diccionario de {} letras"
MENS_NOMASPISTAS = "No se le pueden dar más pistas, puede solucionar"
MENS_SOLUCION =  "La solución es {}"

### Class LingoGrafico
class LingoGrafico(tk.Frame):
    def __init__(self, lingo):
        self.root = tk.Tk()
        self.root.title(TITULO)
        super().__init__(self.root)
        self.lingo = lingo
        self.logotipo = Logotipo(self)
        self.tablero = tk.Frame(self)
        self.marcador = Marcador(self.tablero, lingo.puntuacion)
        self.panel = Panel(self.tablero, lingo)
        self.botonera = Botonera(self.tablero, 
            controlador.BotoneraController(lingo, self))
        self.entrada = Entrada(self.tablero,
            controlador.EntradaController(lingo, self))
        self.tablero.pack()
        self.barraEstado = BarraEstado(self)
        self.pack()
        self.menu = Menu(controlador.MenuController(lingo))
        self.root.config(menu=self.menu)
        self.root.mainloop()

    def reiniciar(self):
        if self.lingo.numeroLineas != self.panel.tamanyo:
            self.panel.destroy()
            self.panel = Panel(self.tablero, self.lingo)
        else:
            self.panel.limpiar()
        self.botonera.reiniciar()
        self.entrada.abrir()

    def rellenarCasilla(self, posicion, letra):
        self.panel.rellenarCasilla(
            self.lingo.estado.lineaActual, posicion, letra)

    def actualizarEstado(self, mensaje):
        self.barraEstado.actualizarEstado(mensaje)

    def repintarPuntuacion(self):
        self.marcador.repintarPuntuacion()

    def pintarJugada(self):
        self.panel.pintarPista()
        self.entrada.limpiar()
        self.entrada.abrir()

    def prepararLineaSiguiente(self):
        if self.lingo.estado.terminada:
            self.barraEstado.actualizarEstado(
                MENS_TERMINADA.format(self.lingo.objetivo.palabra))
            self.entrada.bloquear()
            self.botonera.noMasPistas()
        else:
            self.panel.prepararLineaSiguiente()

    def solucionar(self):
        self.barraEstado.actualizarEstado(MENS_FELIZ)
        self.entrada.bloquear()
        self.botonera.resolver()

    def entradaNoValida(self, palabraNoValida):
        self.entrada.limpiar()
        self.entrada.abrir()
        self.barraEstado.actualizarEstado(
            MENS_ENTRADA_NOVALIDA.format(
                palabraNoValida, self.lingo.longitudPalabra))

    def noMasPistas(self):
        self.botonera.noMasPistas()
        self.barraEstado.actualizarEstado(MENS_NOMASPISTAS)

    def resolver(self):
        self.botonera.resolver()
        self.entrada.bloquear()
        self.barraEstado.actualizarEstado(
            MENS_SOLUCION.format(self.lingo.objetivo.palabra))

    def salir(self):
#        self.root.destroy()
        self.root.quit()

    def error(self, e):
        self.barraEstado.actualizarEstado(e)
        self.botonera.inhabilitar()
### Fin class LingoGrafico

### Class Logotipo
class Logotipo(tk.Frame):
    def __init__(self, panel):
        super().__init__(panel)
        self.img = tk.PhotoImage(file='minilingo.gif')
        self.logo = tk.Label(self, image = self.img)
        self.logo.pack()
        self.pack()
### Fin class Logotipo

### Class Botonera
class Botonera(tk.Frame):
    def __init__(self, panel, listener):
        super().__init__(panel)
        self.bNueva = tk.Button(self, text = NUEVA)
        pulsarNueva = partial(listener.tratarBotonNueva)
        self.bNueva.configure(command = pulsarNueva)
        self.bNueva.grid()
        self.bMasPistas = tk.Button(self, text = MAS_PISTAS, 
            state = tk.DISABLED)
        pulsarMasPistas = partial(listener.darPista)
        self.bMasPistas.configure(command = pulsarMasPistas)
        self.bMasPistas.grid()
        self.bSolucion = tk.Button(self, text = SOLUCION, 
            state = tk.DISABLED)
        pulsarSolucion = partial(listener.solucionar)
        self.bSolucion.configure(command = pulsarSolucion)
        self.bSolucion.grid()
        self.bSalir = tk.Button(self, text = SALIR)
        pulsarSalir = partial(listener.salir)
        self.bSalir.configure(command = pulsarSalir)
        self.bSalir.grid()
        self.cbAleatorio = tk.Checkbutton(self, text = PISTA_ALEATORIA)
        pulsarPistaAleatoria = partial(listener.pistaAleatoria)
        self.cbAleatorio.configure(command = pulsarPistaAleatoria)
        self.cbAleatorio.grid()
        self.grid(row=0, column=2, rowspan=2)

    def resolver(self):
        self.bMasPistas.config(state = tk.DISABLED)
        self.bSolucion.config(state = tk.DISABLED)

    def inhabilitar(self):
        self.bNueva.config(state = tk.DISABLED)
        self.bMasPistas.config(state = tk.DISABLED)
        self.bSolucion.config(state = tk.DISABLED)

    def noMasPistas(self):
        self.bMasPistas.config(state = tk.DISABLED)

    def reiniciar(self):
        self.bMasPistas.config(state = tk.NORMAL)
        self.bSolucion.config(state = tk.NORMAL)
### Fin class Botonera

### Class Marcador
class Marcador(tk.Frame):
    def __init__(self, panel, puntuacion):
        super().__init__(panel)
        self.puntuacion = puntuacion
        self.puntuacionLineaTxt = tk.Label(self, text = PUNTUACION_LINEA)
        self.puntuacionLineaTxt.grid()
        self.puntuacionLineaVal = tk.Label(self, text = "0")
        self.puntuacionLineaVal.grid()
        self.puntuacionPalabraTxt = tk.Label(self, text = PUNTUACION_PALABRA)
        self.puntuacionPalabraTxt.grid()
        self.puntuacionPalabraVal = tk.Label(self, text = "0")
        self.puntuacionPalabraVal.grid()
        self.puntuacionTotalTxt = tk.Label(self, text = PUNTUACION_TOTAL)
        self.puntuacionTotalTxt.grid()
        self.puntuacionTotalVal = tk.Label(self, text = "0")
        self.puntuacionTotalVal.grid()
        self.numeroPalabrasTxt = tk.Label(self, text = PALABRAS_JUGADAS)
        self.numeroPalabrasTxt.grid()
        self.numeroPalabrasVal = tk.Label(self, text = "0")
        self.numeroPalabrasVal.grid()
        self.palabrasGanadasTxt = tk.Label(self, text = PALABRAS_GANADAS)
        self.palabrasGanadasTxt.grid()
        self.palabrasGanadasVal = tk.Label(self, text = "0")
        self.palabrasGanadasVal.grid()
        self.grid(row=0, column=0, rowspan=2)

    def repintarPuntuacion(self):
        self.puntuacionLineaVal.config(
            text = self.puntuacion.puntuacionLinea)
        self.puntuacionPalabraVal.config(
            text = self.puntuacion.puntuacionPalabra)
        self.puntuacionTotalVal.config(
            text = self.puntuacion.puntuacionTotal)
        self.numeroPalabrasVal.config(
            text = self.puntuacion.palabrasTotales)
        self.palabrasGanadasVal.config(
            text = self.puntuacion.palabrasGanadas)
### Fin class Marcador

### Class Entrada
class Entrada(tk.Frame):
    def __init__(self, panel, listener):
        super().__init__(panel)
        contenido = tk.StringVar()
        self.campoEntrada = tk.Entry(self, state = tk.DISABLED, 
            textvariable = contenido)
        recibirEntrada = partial(listener.recibirEntrada, contenido)
        self.campoEntrada.bind("<Return>", recibirEntrada)
        self.campoEntrada.pack()
        self.grid(row=1, column=1)

    def limpiar(self):
        self.campoEntrada.delete(0, tk.END)

    def bloquear(self):
        self.campoEntrada.config(state = tk.DISABLED)

    def abrir(self):
        self.campoEntrada.config(state = tk.NORMAL)
### Fin class Entrada

### Class BarraEstado
class BarraEstado(tk.Label):
    def __init__(self, panel):
        super().__init__(panel)
        self.config(text = MENS_NUEVA)
        self.pack()

    def actualizarEstado(self, mensaje):
        self.config(text = mensaje)
### Fin class BarraEstado

### Class Panel
class Panel(tk.Frame):
    def __init__(self, panelPadre, lingo):
        super().__init__(panelPadre)
        self.lingo = lingo
        self.__generarCasillas()
        self.grid(row = 0, column = 1)

    def __generarCasillas(self):
        self.casillas = []
        self.numeracion = []
        for i in range(self.lingo.numeroLineas):
            labelNumLinea = tk.Label(self, text = str(i + 1))
            labelNumLinea.grid(row = i, column = 0)
            self.numeracion.append(labelNumLinea)
            fila = []
            for j in range(self.lingo.longitudPalabra):
                casilla = tk.Entry(self, width = 2, justify = "center",
                    background = modelo.EstadoLetra.DESCONOCIDA.color)
                casilla.grid(row = i, column = j + 1)
                fila.append(casilla)
            self.casillas.append(fila)

    def rellenarCasilla(self, linea, posicion, letra):
        self.casillas[linea][posicion].delete(0, tk.END)
        self.casillas[linea][posicion].insert(0, letra.letra)

    def pintarPista(self):
        pista = self.lingo.jugada.entrada
        lineaActual = self.lingo.estado.lineaActual
        for i in range(self.lingo.longitudPalabra):
            letra  = pista.getLetra(i)
            self.rellenarCasilla(lineaActual, i, letra)
            self.casillas[lineaActual][i].config(
                background = letra.estado.color)

    def prepararLineaSiguiente(self):
        lineaActual = self.lingo.estado.lineaActual
        for i in range(self.lingo.longitudPalabra):
            # escribimos las ya colocadas en la siguiente línea
            letra = self.lingo.objetivo.getLetra(i)
            if letra.estado == modelo.EstadoLetra.OK:
                self.rellenarCasilla(lineaActual, i, letra)

    def limpiar(self):
        for i in range(len(self.casillas)):
            self.numeracion[i].config(text = str(i + 1))
            for j in range(len(self.casillas[i])):
                self.casillas[i][j].delete(0, tk.END)
                self.casillas[i][j].config(
                    background = modelo.EstadoLetra.DESCONOCIDA.color)

    @property
    def tamanyo(self):
        return len(self.casillas)
### Fin class Panel

### Class Menu
class Menu(tk.Menu):
    def __init__(self, listener):
        super().__init__()
        mOpciones = tk.Menu(self, tearoff = 0)
        self.add_cascade(label = OPCIONES, menu = mOpciones)
        mLongsPal = tk.Menu(mOpciones, tearoff = False)
        for i in range(modelo.MIN_LONG, modelo.MAX_LONG + 1):
            clicarMenu = partial(listener.clicarMenu, i)
            mLongsPal.add_command(label = str(i),
                command = clicarMenu)
        mOpciones.add_cascade(label = LONGITUD_PALABRAS, 
            menu = mLongsPal)
### Fin class Menu

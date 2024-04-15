from enum import Enum
import random

MAX_LONG = 8
MIN_LONG = 4
LONG_INICIAL = 5

LETRA_VACIA = ' '

ERROR_DICCIONARIO = "El diccionario de palabras de longitud {} no está disponible"

### Enum EstadoLetra
class EstadoLetra(Enum):
    DESCONOCIDA = [0 , "#cccccc"]
    MAL = [0 , "#ff0000"]
    ENCONTRADA = [3 , "#ffff00"]
    OK = [15 , "#00ff00"]
    
    @property
    def puntos(self):
        return self.value[0]
    
    @property
    def color(self):
        return self.value[1]
### Fin EstadoLetra

### Class Puntuacion
class Puntuacion:
    def __init__(self):
        self.puntuacionLinea = 0
        self.puntuacionPalabra = 0
        self.puntuacionTotal = 0
        self.palabrasGanadas = 0
        self.palabrasTotales = 0
        self.pistas = 0

    def otraPista(self):
        self.pistas += 1

    def nuevaPalabra(self, aleatoria):
        self.puntuacionLinea = 0
        self.puntuacionPalabra = 0
        self.palabrasTotales += 1
        if aleatoria:
            self.pistas = 0
        else:
            self.pistas = 1

    def puntuar(self, resultado):
        self.puntuacionLinea = 0
        for letra in resultado.entrada.letras:
            self.puntuacionLinea += letra.estado.puntos
        self.puntuacionLinea -= EstadoLetra.OK.puntos * self.pistas
        self.puntuacionPalabra += self.puntuacionLinea

    def solucionar(self, lineaActual, numeroLineas):
        if lineaActual == 0:
            ratioLinea = 1
        else:
            ratioLinea = numeroLineas - lineaActual
        self.puntuacionPalabra *= ratioLinea
        self.puntuacionTotal += self.puntuacionPalabra
        self.palabrasGanadas += 1
### Fin class Puntuacion

### Class Estado
class Estado:
    def __init__(self):
        self.reiniciarEstado()

    def reiniciarEstado(self):
        self.lineaActual = 0
        self.terminada = False
        self.solucionada = False

    def avanzarLinea(self):
        self.lineaActual += 1

    def terminar(self):
        self.terminada = True

    def solucionar(self):
        self.solucionada = True
### Fin class Estado

### Class Configuracion
class Configuracion:
    def __init__(self):
        self.longitudPalabra = LONG_INICIAL
        self.numeroLineas = self.longitudPalabra
        self.aleatoria = False
        self.distintaLongitud = True
        self.nuevaLongitud = LONG_INICIAL

    def alternarAleatoria(self):
        self.aleatoria = not self.aleatoria
        return self.aleatoria

    def cambiarLongitud(self, nuevaLongitud):
        self.distintaLongitud = self.nuevaLongitud != nuevaLongitud
        self.nuevaLongitud = nuevaLongitud

    def comprobarLongitud(self):
        if self.distintaLongitud:
            self.longitudPalabra = self.nuevaLongitud
            self.numeroLineas = self.longitudPalabra
            self.distintaLongitud = False
        return self.longitudPalabra
### Fin class Configuracion

### Class Diccionario
class Diccionario:
    @classmethod
    def cargarDiccionario(cls, longitudPalabra):
        try:
            cls.longitudPalabra = longitudPalabra
            fichero = open("dicc" + str(longitudPalabra) + ".dic")
            datos = fichero.read()
            cls.diccionario = datos.split("\n")
            cls.longitudDiccionario = len(cls.diccionario)
            fichero.close()
        except FileNotFoundError:
            raise NoDiccionarioException(
                ERROR_DICCIONARIO.format(longitudPalabra))

    @classmethod
    def obtenerPalabra(cls):
        return cls.diccionario[
            random.randint(0, cls.longitudDiccionario - 1)]

    @classmethod
    def palabraValida(cls, palabra):
        return palabra in cls.diccionario

    @classmethod
    def longitudPalabra(cls):
        return cls.longitudPalabra

### Fin class Diccionario

### Class Palabra
class Palabra:
    def __init__(self, palabra):
        self.palabra = palabra
        self.longitudPalabra = len(palabra)
        self.letras = []
        for i in range(self.longitudPalabra):
            self.letras.append(Letra(palabra[i]))

    def getLetra(self, pos):
        return self.letras[pos]

    def letraColocada(self, pos):
        self.letras[pos].estado = EstadoLetra.OK

    def comparar(self, entrada):
        clonada = Palabra(self.palabra)
        self.__buscarVerdes(entrada, clonada)
        self.__buscarAmarillas(entrada, clonada)
        self.__rechazarDesconocidas(entrada)
        palabraOk = self.__verificarFinalizacion(entrada)
        return Jugada(entrada, palabraOk)

    def __buscarVerdes(self, entrada, clonada):
        for i in range(self.longitudPalabra):
            if self.getLetra(i).letra == entrada.getLetra(i).letra:
                clonada.letraColocada(i)
                self.letraColocada(i)
                entrada.letraColocada(i)

    def __buscarAmarillas(self, entrada, clonada):
        for i in range(self.longitudPalabra):
            letraEntrada = entrada.getLetra(i)
            if letraEntrada.estado == EstadoLetra.DESCONOCIDA:
                # aún no ha sido encontrada
                for j in range(self.longitudPalabra):
                    letraClonada = clonada.getLetra(j)
                    if letraClonada.estado == EstadoLetra.DESCONOCIDA \
                            and letraClonada.letra == letraEntrada.letra:
                        letraClonada.estado = EstadoLetra.ENCONTRADA
                        letraEntrada.estado = EstadoLetra.ENCONTRADA
                        letraObjetivo = self.getLetra(j)
                        if letraObjetivo.estado == EstadoLetra.DESCONOCIDA:
                            letraObjetivo.estado = EstadoLetra.ENCONTRADA
                        break # a por la siguiente letra

    def __rechazarDesconocidas(self, entrada):
        for i in range(self.longitudPalabra):
            entradaI = entrada.getLetra(i)
            if entradaI.estado == EstadoLetra.DESCONOCIDA:
                entradaI.estado = EstadoLetra.MAL

    def __verificarFinalizacion(self, entrada):
        palabraOk = True
        for i in range(self.longitudPalabra):
            palabraOk = palabraOk and \
                (entrada.getLetra(i).estado == EstadoLetra.OK)
        return palabraOk

    def darPista(self):
        restantes = self.__contarNoColocadas()
        if restantes <= 1:
            raise NoMasPistasException()
        letra = Letra(LETRA_VACIA)
        letra.estado = EstadoLetra.OK;
        while letra.estado == EstadoLetra.OK:
            pos = random.randint(0, self.longitudPalabra - 1)
            letra = self.getLetra(pos)
        letra.estado = EstadoLetra.OK
        return pos

    def __contarNoColocadas(self):
        cont = 0
        for letra in self.letras:
            if letra.estado != EstadoLetra.OK:
                cont += 1
        return cont

    def __str__(self):
        s = ""
        for letra in self.letras:
            s += str(letra)
        return s
### Fin class Palabra

### Class Letra
class Letra:
    def __init__(self, letra):
        self.letra = letra
        self.estado = EstadoLetra.DESCONOCIDA

    def __str__(self):
        if self.estado == EstadoLetra.OK:
            return "[" + self.letra + "]"
        if self.estado == EstadoLetra.ENCONTRADA:
            return "(" + self.letra + ")"
        return " " + self.letra + " "
### Fin class Letra

### Class Jugada
class Jugada:
    def __init__(self, entrada, solucionada):
        if entrada == None:
            self.valida = False
        else:
            self.entrada = entrada
            self.solucionada = solucionada
            self.valida = True
### Fin class Jugada

### Class Lingo
class Lingo:
    def __init__(self):
        self.configuracion = Configuracion()
        self.puntuacion = Puntuacion()
        self.estado = Estado()

    @property
    def longitudPalabra(self):
        return self.configuracion.longitudPalabra

    @property
    def numeroLineas(self):
        return self.configuracion.numeroLineas

    def nuevaPalabra(self):
        longitud = self.configuracion.comprobarLongitud()
        if Diccionario.longitudPalabra != longitud:
            Diccionario.cargarDiccionario(longitud)
        self.puntuacion.nuevaPalabra(self.configuracion.aleatoria)
        self.estado.reiniciarEstado()
        self.objetivo = Palabra(Diccionario.obtenerPalabra())
        print(self.objetivo.palabra) # TODO no mostrar
        return self.objetivo

    def jugar(self, texto):
        texto = texto.upper()
        if Diccionario.palabraValida(texto):
            entrada = Palabra(texto)
            resultado = self.objetivo.comparar(entrada)
            if resultado.valida:
                self.puntuacion.puntuar(resultado)
            if resultado.solucionada:
                self.estado.solucionar()
            self.jugada = resultado
        else:
            self.jugada = None
        if self.estado.lineaActual == self.numeroLineas - 1:
            self.estado.terminar()
        return self.jugada

    def avanzarLinea(self):
        self.estado.avanzarLinea()

    def solucionar(self):
        self.puntuacion.solucionar(self.estado.lineaActual, 
            self.configuracion.longitudPalabra)

    def otraPista(self):
        self.puntuacion.otraPista()

    def alternarAleatoria(self):
        self.configuracion.alternarAleatoria()

    def nuevaLongitud(self, nuevoValor):
        self.configuracion.cambiarLongitud(nuevoValor)
### Fin class Lingo

### Excepcion NoMasPistasException
class NoMasPistasException(Exception):
    pass
### Fin excepcion NoMasPistasException

### Excepcion NoDiccionarioException
class NoDiccionarioException(Exception):
    def __init__(self, mensaje):
        self.mensaje = mensaje
### Fin exception NoDiccionarioException

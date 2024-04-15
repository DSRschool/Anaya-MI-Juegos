class Persona:

    def __init__(self, nombre, apellidos):
        self.__nombre = nombre
        self.__apellidos = apellidos

    def firmarConNombreCompleto(self):
        return self.__nombre + " " + self.__apellidos

    def firmarConIniciales(self):
        return self.__nombre[0] + self.__apellidos[0]
### Fin class Persona

class Alumno(Persona):

    def __init__(self, nombre, apellidos, numMatricula):
        super().__init__(nombre, apellidos)
        self.__numMatricula = numMatricula

    def entregarTarea(self):
        print("Tarea entregada por", self.__numMatricula)
### Fin class Alumno

class Empleado(Persona):

    def __init__(self, nombre, apellidos, salarioAnual):
        super().__init__(nombre, apellidos)
        self.__salarioAnual = salarioAnual

    def cobrar(self):
        print("Nómina:", self.__salarioAnual / 12)
### Fin class Empleado

class Profesor(Empleado):

    def __init__(self, nombre, apellidos, salarioAnual, numColegiado):
        super().__init__(nombre, apellidos, salarioAnual)
        self.__numColegiado = numColegiado

    def calificarTarea(self):
        print("Tarea calificada por", self.__numColegiado)
### Fin class Profesor

class PAS(Empleado):

    def __init__(self, nombre, apellidos, salarioAnual):
        super().__init__(nombre, apellidos, salarioAnual)

    def generarInforme(self):
        print("Informe generado")

    def firmarConNombreCompleto(self):
        return super().firmarConNombreCompleto() + " VALIDADO"
### Fin class PAS

## Prueba persona
jefe = Persona("Juan", "Blanco");
print(jefe.firmarConNombreCompleto())
print(jefe.firmarConIniciales())

presi = Persona("Ana", "Zapata");
print(presi.firmarConNombreCompleto())
print(presi.firmarConIniciales())

## Prueba escuela
alumna1 = Alumno("Lola", "Montero", "AB1234")
print(alumna1.firmarConNombreCompleto())
alumna1.entregarTarea()

profe1 = Profesor("Marta", "Nogal", 43210, "ZX6543")
print(profe1.firmarConNombreCompleto())
profe1.cobrar()
profe1.calificarTarea()

pas1 = PAS("Yolanda", "Burgos", 32100)
print(pas1.firmarConNombreCompleto())
pas1.cobrar()
pas1.generarInforme()
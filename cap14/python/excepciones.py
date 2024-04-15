class DatoInvalidoException(Exception):
    pass

class Calculadora:
    def calcularPrevisor(self, a, b, c):
        try:
            aux = self.__dividir(b, c)
        except DatoInvalidoException:
            aux = 0
        return a + aux

    def calcularValiente(self, a, b, c):
        return a + self.__dividir(b, c)

    def __dividir(self, a, b):
        if b == 0:
            raise DatoInvalidoException()
        return a / b

# Prueba
c = Calculadora()

print("# version previsora")
print(c.calcularPrevisor(3, 4, 0))

print("# version valiente")
try:
    print(c.calcularValiente(3, 4, 0))
except DatoInvalidoException:
    print("Se ha producido un error")

print("# version kamikaze")
print(c.calcularValiente(3, 4, 0))

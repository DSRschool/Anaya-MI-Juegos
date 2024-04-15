import random

# Título del juego
print("=== Adivina un número del 1 al 100 ===")

# Generación del número aleatorio
objetivo = random.randint(1, 100)
# Chuleta para pruebas, mantener comentado
# print(objetivo)

# Inicialización de variables auxiliares
adivinado = False 
contador = 0

# Bucle para repetir hasta que no se haya adivinado
while not adivinado:
    # Incremento del contador de iteraciones
    contador += 1
    # Petición de jugada al usuario
    entrada = input("Dime un número del 1 al 100: ")
    # Conversión de texto a entero
    numero = int(entrada)
        
    # Evaluación
    if numero == objetivo: # Acertado
        print("¡¡Bien!! Era", numero)
        adivinado = True
    else:
        if numero < objetivo: # Número demasiado pequeño
            print("Mayor, mayor")
        else: # Número demasiado grande
            print("Menor, menor")

# Al terminar, indicación del número de intentos    
print("Lo has conseguido en", contador, "intentos.")
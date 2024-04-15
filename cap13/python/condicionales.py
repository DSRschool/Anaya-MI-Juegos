num = int(input("Dame un número entero: "))

# una rama
if num == 0:
    print("Es cero")
    
# dos ramas
if num % 2 == 0:
    print("Es par")
else:
    print("Es impar")
    
# varias ramas
if num < -100:
    print("Es muy negativo")
elif num < 0:
    print("Es negativo")
elif num == 0:
    print("Ya te dije que es cero")
elif num > 100:
    print("Es muy positivo")
else: # entre 1 y 99
    print("Es positivo")
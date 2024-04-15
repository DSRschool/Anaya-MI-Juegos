import random

ordenador = random.randint(1, 2)
jugador = input("¿1 o 2?\n")

if not jugador == "1" and not jugador == "2":
    print("¡Mal!")
else:  
    numero = int(jugador)
    
    suma = ordenador + numero
    
    if suma % 2 == 0:
        print("¡Pares!")
    else:
        print("¡Nones!")
        
    print("El ordenador dijo", str(ordenador), "y el jugador", jugador)

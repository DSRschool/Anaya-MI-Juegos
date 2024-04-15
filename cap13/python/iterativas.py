print("# while")
a = 0
while a <= 5:
    print(a*a, end = " ")
    a += 1
print()

print("# for lista")
for color in ['rojo', 'verde', 'amarillo', 'azul']:
    print(color, end = " ")
print()
  
print("# for range")
for i in range(5):
    print(i, end = " ")
print()
    
print("# rectángulo 3 x 8")
for x in range(3):
    for y in range(8):
        print("X", end="")
    print()
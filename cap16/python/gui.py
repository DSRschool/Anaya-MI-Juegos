import tkinter as tk

root = tk.Tk()
root.title("Muestrario GUI con Tkinter")
root.geometry("300x600")
root.config(bg="skyblue")

barraEstado = tk.Label(root, text = "barra de estado")

bloquePack = tk.Frame(root)
bloquePack.config(bg = "coral")

eti1 = tk.Label(bloquePack, text = "1º izquierda")
eti1.pack(side = "left")
eti2 = tk.Label(bloquePack, text = "2º derecha")
eti2.pack(side = "right")
eti3 = tk.Label(bloquePack, text = "3º derecha")
eti3.pack(side = "right")
eti4 = tk.Label(bloquePack, text = "4º abajo")
eti4.pack(side = "bottom")
eti5 = tk.Label(bloquePack, text = "5º arriba")
eti5.pack(side = "top")
eti6 = tk.Label(bloquePack, text = "6º ¿dónde?")
eti6.pack()
img = tk.PhotoImage(file = 'explosion.png')
eti7 = tk.Label(bloquePack, image = img)
eti7.pack()

bloquePack.pack()

bloqueGrid = tk.Frame(root)
bloqueGrid.config(bg = "lavender")
for i in range(3):
    for j in range(4):
        botoncito = tk.Button(bloqueGrid, text = str(i) + "-" + str(j))
        botoncito.grid(row = i, column = j)

varCheck = tk.IntVar()
def marcado():
    barraEstado.config(
        text = "check box {} marcado".format(varCheck.get()))
check = tk.Checkbutton(bloqueGrid, text = "check box", 
            command = marcado, variable = varCheck)
check.grid(columnspan = 2)

bloqueRadios = tk.Frame(bloqueGrid)
bloqueRadios.config(bg = "burlywood")
varRadio = tk.IntVar()
def seleccionado():
    barraEstado.config(
        text = "radio {} seleccionado".format(varRadio.get()))
for i in range(3):
    radio = tk.Radiobutton(bloqueRadios, 
                text = "Opción " + str(i)*((2*i)+1), 
                variable = varRadio, value = i, command = seleccionado)
    radio.grid()
bloqueRadios.grid(row = 3, column = 2, columnspan = 2, rowspan = 3)

def eleccion(ev):
    barraEstado.config(
        text = "elemento {} elegido".format(lista.curselection()))
lista = tk.Listbox(bloqueGrid, selectmode = tk.BROWSE, width = 12)
lista.bind("<<ListboxSelect>>", eleccion)
for t in ["hola", "qué tal", "adiós"]:
    lista.insert(tk.END, t)
lista.grid(columnspan = 4)
bloqueGrid.pack()

bloquePlace = tk.Frame(root, width = 150, height = 100)
bloquePlace.config(bg = "darkseagreen")

contenido = tk.StringVar()
def escribiendo(ev):
    barraEstado.config(text = contenido.get())
campoEntrada = tk.Entry(bloquePlace, textvariable = contenido)
campoEntrada.bind("<KeyRelease>", escribiendo)
campoEntrada.place(x = 0, y = 0)
campoGrande = tk.Text(bloquePlace, height = 3, width = 15)
campoGrande.place(x = 5, y = 45)
def escribiendoMucho(ev):
    barraEstado.config(text = campoGrande.get("1.0", tk.END)) #  line one, character zero 
campoGrande.bind("<KeyRelease>", escribiendoMucho)

place8010 = tk.Label(bloquePlace, text = ". (80, 10)")
place8010.place(x = 80, y = 10)
place2050 = tk.Label(bloquePlace, text = ". (20, 50)")
place2050.place(x = 20, y = 50)
place9085 = tk.Label(bloquePlace, text = ". (90, 85)")
place9085.place(x = 90, y = 85)
bloquePlace.pack()

barraEstado.pack()
root.mainloop()
import tkinter as tk

def clicFuera(evento):
    espia.config(text = str(evento))
    mensaje.config(text = "Has clicado fuera del botón")

def dentro(evento):
    espia.config(text = str(evento))
    mensaje.config(text = "Te has metido dentro del botón")

def fuera(evento):
    espia.config(text = str(evento))
    mensaje.config(text = "Has salido del botón")

def botonPrincipal(evento):
    espia.config(text = str(evento))
    mensaje.config(text = "Has pulsado con el botón principal")

def botonSecundario(evento):
    espia.config(text = str(evento))
    mensaje.config(text = "Has pulsado con el botón secundario")

root = tk.Tk()
root.title("Eventos")
frame = tk.Frame(root)
frame.bind("<Button-1>", clicFuera)
frame.pack()
boton = tk.Button(frame, text = "Botón de los eventos")
boton.pack()
boton.bind("<Enter>", dentro)
boton.bind("<Leave>", fuera)
boton.bind("<Button-1>", botonPrincipal)
boton.bind("<Button-2>", botonSecundario)
espia = tk.Label(frame, text = "Datos del evento")
espia.pack()
mensaje = tk.Label(frame, text = "Zona de mensajes")
mensaje.pack()
root.mainloop()

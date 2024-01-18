# dependencias del codigo
import cv2
import numpy as np
#from apriltag import apriltag

# variables globales
testVar = 0

# Para cambiar una variable local dentro de una funcion
# vuelve a declarala con la palabra clave global
def incrementTestVar():
    global testVar
    testVar = testVar + 1
    if testVar == 1000:
        print("Areli")
    if testVar >= 2000:
        print("Peter")
        testVar = 0

# Decorar la imagen actual, agregando el texto indicado
# en la posicion indicada, con la letra y el color indicado
def drawDecorations(image):
    cv2.putText(image, 
        'Pedro y Areli ya modificaron el codigo', 
        (0, 230), 
        cv2.FONT_HERSHEY_SIMPLEX, 
        .5, (0, 255, 0), 1, cv2.LINE_AA
    )
    
# la funcion runPipeline() es llamada todos los frames por el
# backend de la limelight (aqui se va a establecer la programacion
# utilizando funciones o escribiendolo unicamente dentro de esta
# funcion). Recibe los metodos image que es la imagen de entrada
# y el otro aun no se que sea
def runPipeline(image, llrobot):
    # Convertir una imagen a una escala de color
    img_hsv = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    # Seleccionar un rango de color que se encuentren en la imagen 
    img_threshold = cv2.inRange(img_hsv, (0, 0, 0), (80, 80, 80))
   
    # Seleccionar los contornos en una imagen, el primer parametro indica
    # la imagen a seleccionar, el segundo como se van a detectar los contornos
    # y el tercero el metodo con el cual se almacena la informacion
    contours, _ = cv2.findContours(img_threshold, 
    cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
    
    # crea un array de numpy 
    largestContour = np.array([[]])
    llpython = [0,0,0,0,0,0,0,0]

    # Condicional que comprueba si la longitud de los contornos es de mas de 0
    if len(contours) > 0: # si es mayor a 0 dibuja en la imagen
        # Primer parametro indica la imagen, el segundo indica la lista obtenida
        # al buscar dichos contornos, el tercero indica que contronos se dibujan
        # (si el valor es negativo dibuja todos), cuarto parametro el color del
        # contorno, y el quinto parametro indica el grosor de la linea
        cv2.drawContours(image, contours, -1, 255, 2) 
        # obtener el contorno mas grande en la lista de contornos
        largestContour = max(contours, key=cv2.contourArea)
        # Obtener ek rectangulo mas pequeño que contenga todos lo puntos del contorno
        # x es el vertice superior izquierdo del rectangulo
        # y es el vertice superiot izquierdo del rectangulo
        # w es el ancho
        # h es el alto
        x,y,w,h = cv2.boundingRect(largestContour)
        # Dibuja el rectangulo que delimita el contorno, imagen, punto de inicio
        # coordenadas del vertice inferior derecho, color del rectangulo y grosor
        # del rectagulo
        cv2.rectangle(image,(x,y),(x+w,y+h),(0,255,255),2)
        # sustituir algunos valores de la tupla, principalmente los puntos iniciales
        # y el ancho y largo del rectangulo
        llpython = [1,x,y,w,h,9,8,7]  
  
    incrementTestVar()
    drawDecorations(image)
       
    # asegurese de devolver el contorno
    # a la image a transmitir
    # y opcionalmente un array de hasta 8 valores para "llpython"
    # matriz de tablas de red
    # networktables array

    # retornar la imagen, el contorno mas largo y la tupla de datos
    return largestContour, image, llpython
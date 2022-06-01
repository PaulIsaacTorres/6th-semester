COMMENT !
        EXAMEN FINAL

        Realizar un programa en ensamblador donde el alumno implemente en MASM una
        calculadora aritmética básica, mostrando en pantalla un menú de opciones numéricas que
        permitirá al usuario seleccionar la operación de SUMA, RESTA, MULTIPLICACION O
        DIVISION, después de seleccionada la opción entonces se solicitara por teclado ingresar un
        primer numero y un segundo numero para hacer la operación previamente seleccionada,
        entonces se deberá mostrar el resultado con un mensaje como “El resultado de SUMAR # y
        # es: ##” de acuerdo con la operación indicada. Este menú debe estar siempre en pantalla
        hasta que el usuario seleccione la opción de salir

        Autor: Torres Enriquez Paul Isaac
        Fecha: 01/06/2022
        !
;DIRECTIVAS de ensmablador por acuerdo se escribiran en MAYUSCULAS y en la primera columna de la izquierda
.MODEL SMALL
.586
.STACK  100h
.DATA
    ;variables definidas
    ;mensaje0 db 10,13,7, 'Iniciando Programa', '$'
    
    ;n1 db 0

.CODE 
.STARTUP ;DIRECTIVA para el inicio de ensamblador

.EXIT ;DIRECTIVA donde terminará el ensablador
END 
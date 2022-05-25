COMMENT ! Programa para imprimir en pantalla
        utilizando el codigo 9H de la interrupción 21H
        Autor: Torres Enriquez Paul Isaac
        Fecha: Hoy en formato 27/04/2022
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
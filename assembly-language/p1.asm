COMMENT ! Programa para imprimir en pantalla
        utilizando el codigo 9H de la interrupción 21H
        Autor: Torres Enriquez Paul Isaac
        Fecha: Hoy en formato 01/04/2022
        !
;DIRECTIVAS de ensmablador por acuerdo se escribiran en MAYUSCULAS y en la primera columna de la izquierda
.MODEL SMALL
.586
.STACK  100h
.DATA
        ;variables se definde dentro del .DATA 
        mensaje0 db 10,13,7,'Programa de operaciones','$'
        mensaje1 db 10,13,7,'Ingrese un numero: ','$'
        mensaje2 db 10,13,7,'La suma es: ','$'
        mensaje3 db 10,13,7,'La resta es: ','$'
        mensaje4 db 10,13,7,'Programa Finalizado','$'
        n1 db 0
        n2 db 0
        r db 0
.CODE 
.STARTUP ;DIRECTIVA para el inicio de ensamblador
        mov ax, seg @data
        mov ds, ax

        mov ah, 09h
        lea dx, mensaje0
        int 21h

        mov ah, 09h
        lea dx, mensaje1
        int 21h

        mov ah, 01h
        int 21h
        sub al, 30h
        mov n1, al

        mov ah, 09h
        lea dx, mensaje1
        int 21h

        mov ah, 01h
        int 21h
        sub al, 30h
        mov n2, al

        ;suma
        mov al, n1
        add al, n2
        mov r, al

        mov ah, 09h
        lea dx, mensaje2
        int 21h

        mov al,r
        AAM
        mov bx, ax
        mov ah, 02h
        mov dl, bh
        add dl, 30h
        int 21h

        mov ah, 02h
        mov dl,bl
        add dl, 30h
        int 21h
        
        ;resta
        mov al, n1
        sub al, n2
        mov r,al

        mov ah, 09h
        lea dx, mensaje3
        int 21h

        mov al,r
        AAM
        mov bx, ax
        mov ah, 02h
        mov dl, bh
        add dl, 30h
        int 21h

        mov ah, 02h
        mov dl,bl
        add dl, 30h
        int 21h

        mov ah, 09h
        lea dx, mensaje4
        int 21h
.EXIT ;DIRECTIVA donde terminará el ensablador
END  
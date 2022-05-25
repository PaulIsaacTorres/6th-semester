COMMENT ! 
        Implementar la instruccion LOOP y CMP para
        sumar 2 numeros introducidos por teclado y
        mostrar su resultado

        utilizando el codigo 9H de la interrupción 21H
        Autor: Torres Enriquez Paul Isaac
        Fecha: Hoy en formato 24/05/2022
        !
;DIRECTIVAS de ensamblador por acuerdo se escribiran en MAYUSCULA y en la primera columna de la izquierda
.MODEL SMALL
.586
.STACK  100h
.DATA
        titulo db 13,10,7,'Programa para sumar 2 numeros por teclado','$'
        message1 db 13,10,7,'Introducir primer numero: $'
        message2 db 13,10,7,'Introducir segundo numero: $'
        messageSum db 13,10,7, 'La suma es: ',' $'
        number1 db '0'
        number2 db '0'
        resultSum db '0'
        counter db 0
.CODE
        .STARTUP
                mov ax, @data
                mov ds, ax

                mov dx, offset titulo
                call printScreen
                ;mov ah, 9h   
                ;int 21h 

                mov cx, 0000h  ; XOR CX, CX
                mov cx, 6
    cicle:
                mov dx, OFFSET message1
                call printString
                ;mov ah, 9h   
                ;int 21h 

                mov AH, 01H
                call readCharacter
                ;int 21H
                ;sub AL, 30H
                ;mov number1,al

                mov dx, OFFSET message2
                mov ah, 9h   
                int 21h 

                mov AH, 01H
                int 21H
                sub AL, 30H
                mov number2 ,al

                add al,number1    ; mov cl, num1   mov ch, num2   add cl,ch
                add al, 30h
                ;OR AL, 30H
                mov resultSum,al

                call printCharacter
                ;mov dx, OFFSET messageSum
                ;mov ah, 9h   
                ;int 21h 

                mov dl,resultSum
                mov AH, 02H
                int 21H

                loop cicle

                ;;;
                mov dx, OFFSET titulo
                mov ah, 9h   
                int 21h 
               
    cicle2:
                mov dx, OFFSET message1
                mov ah, 9h   
                int 21h 

                mov AH, 01H
                int 21H
                sub AL, 30H
                
                mov number1,al

                mov dx, OFFSET message2
                mov ah, 9h   
                int 21h 

                mov AH, 01H
                int 21H
                sub AL, 30H
                mov number2,al

                add al,number1    ; mov cl, num1   mov ch, num2   add cl,ch
                add al, 30h
                ;OR AL, 30H
                mov resultSum,al

                mov dx, OFFSET messageSum
                mov ah, 9h   
                int 21h 

                mov dl,resultSum
                mov AH, 02H
                INT 21H

                inc counter
                cmp counter,6
                jne cicle2
                ;;;
                mov ax, 4c00h
                int 21h
        .EXIT ;DIRECTIVA donde terminará el ensablador
        
        ;print screen procedure 
        printScreen proc
        ;lineas de codigo
            mov ah, 9h   
            int 21h 
            ret
        printScreen endp

        ;read character procedure
        readCharacter proc
            int 21H
            sub AL, 30H
            mov number1,al
            ret
        readCharacter endp

        ;print character procedure
        printCharacter proc
            mov dx, offset messageSum
            mov ah, 9h
            int 21h
            ret
        printCharacter endp

        ;print character string procedure
        printString proc
            mov ah, 9h   
            int 21h 
            ret
        printString endp
END  
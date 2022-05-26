COMMENT ! 
        El programa debe mostrar un mensaje de bienvenida como:
        
        >> Bienvenido a calculo aritmético <<

        El estudiante debe codificar las instrucciones necesarias para que realice las operaciones aritméticas
        básicas de suma, resta, multiplicación y división. Para ello debe emplear datos inmediatos que
        guarde en los registros y una vez realizada la operación mostrar el resultado, por ejemplo, la salida
        de cada operación será:

        >>La suma de # más # es: #
        >>La resta de # menos # es: #
        >>La multiplicación de # por # es de: #
        >>La división de # entre # es de: #
        
        Por último, muestre un mensaje de finalización
        
        >>Programa finalizado <<
        
        Author: Torres Enriquez Paul Isaac
        Fecha: Hoy en formato 25/05/2022
        !

.MODEL SMALL
.586
.STACK  100h
.DATA
    ;message declaration
    welcomeMessage db 10,13,7, '>> Bienvenido a calculo aritmetico <<', '$'
    endMessage db 10,13,7, '>> Programa Finalizado <<', '$'
    
    newline db 10,13, '$'
    inFirstValue db 10,13,7, '>> Ingrese el primer valor: ', '$'
    inSecondValue db 10,13,7, '>> Ingrese el segundo valor: ', '$'
    
    resultSumMsg db 10,13,7, ">> La suma es: ", '$'
    resultRestMsg db 10,13,7, ">> La resta es: ", '$'
    resultMultiplicationMsg db 10,13,7, ">> La multiplicacion es: ", '$'
    resultDivisionMsg db 10,13,7, ">> La division es: ", '$'

    ;variables declaration
    firstDigit db 0
    secondDigit db 0
    result db 0

.CODE 
    .STARTUP
        mov ax, seg @data
        mov ds, ax

        ;print welcome message
        call printWelcome

        ;print new line in terminal
        call printNewLine

        mov ah, 09h
        lea dx, inFirstValue
        int 21h

        mov ah, 01h
        int 21h
        sub al, 30h
        mov firstDigit, al

        mov ah, 09h
        lea dx, inSecondValue
        int 21h

        mov ah, 01h
        int 21h
        sub al, 30h
        mov secondDigit, al

        ;print new line in terminal
        call printNewLine

        ;sum operation        
        call sumOperation   ;call the procedure to sum both digit

        ;rest operation
        call restOperation ;call the procedure to rest both digit

        ;multiplication operation
        call multiplicationOperation ; call the procedur to multiplicate both digit

        ;divition operation
        call divitionOperation ;call the procedure to divide both digit

        ;print new line in terminal
        call printNewLine

        ;print end message
        call printEnd
    .EXIT
    
    ;PROCEDURES
    printWelcome proc
        mov dx, offset welcomeMessage
        mov ah, 09h
        mov ah, 09h
        int 21h
        
        ret
    printWelcome endp

    printEnd proc
        mov dx, offset endMessage
        mov ah, 09h
        mov ah, 09h
        int 21h
        
        ret
    printEnd endp

    printNewLine proc
        mov dx, offset newline
        mov ah, 09h
        mov ah, 09h
        int 21h
        
        ret
    printNewLine endp

    sumOperation proc
        mov al, firstDigit
        add al, secondDigit
        mov result, al

        mov ah, 09h
        lea dx, resultSumMsg
        int 21h

        mov al, result
        aam
        mov bx, ax
        mov ah, 02h
        mov dl, bh
        add dl, 30h
        int 21h

        mov ah, 02h
        mov dl, bl
        add dl, 30h
        int 21h

        ret
    sumOperation endp

    restOperation proc
        mov al, firstDigit
        sub al, secondDigit
        mov result,al

        mov ah, 09h
        lea dx, resultRestMsg
        int 21h

        mov al,result
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

        ret
    restOperation endp

    multiplicationOperation proc
        mov al, firstDigit
        mov bl, secondDigit
        mul bl
        mov result, al
        
        mov ah, 09h
        lea dx, resultMultiplicationMsg
        int 21h

        mov al, result
        aam
        mov bx, ax
        mov ah, 02h
        mov dl, bh
        add dl, 30h
        int 21h

        mov ah, 02h
        mov dl, bl
        add dl, 30h
        int 21h
        
        ret
    multiplicationOperation endp

    divitionOperation proc
        mov ah, 09h
        lea dx, resultDivisionMsg
        int 21h

        xor ax, ax
        mov al, secondDigit
        mov bl, al
        mov al, firstDigit
        div bl

        mov bl, al
        mov dl, bl
        add dl, 30h
        mov ah, 02h
        int 21h

        ret
    divitionOperation endp
END 
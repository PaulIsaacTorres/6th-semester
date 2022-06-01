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
    ;message delcaration part
        ;in and out messages
    welcomeMessage db 10,13,7, 'CALCULADORA ARITMETICA BASICA', '$'
    endMessage db 10,13,7, 'OPERACION FINALIZADA', '$'
    
        ;menu messages
    slSumMenu db 10,13,7, '1) SUMA', '$'
    slResMenu db 10,13,7, '2) RESTA', '$'
    slMulMenu db 10,13,7, '3) MULTIPLICACION', '$'
    slDivMenu db 10,13,7, '4) DIVISION', '$'
    slOutMenu db 10,13,7, '5) SALIR', '$'

        ;in messages
    inSelectorValue db 10,13,7, '>> Seleccione la operacion a realizar: ', '$'
    inFirstValue db 10,13,7, '>> Ingrese el primer valor: ', '$'
    inSecondValue db 10,13,7, '>> Ingrese el segundo valor: ', '$'

        ;result messages
    resultSumMsg db 10,13,7, ">> La suma es: ", '$'
    resultRestMsg db 10,13,7, ">> La resta es: ", '$'
    resultMultiplicationMsg db 10,13,7, ">> La multiplicacion es: ", '$'
    resultDivisionMsg db 10,13,7, ">> La division es: ", '$'

        ;other messages
    breakLine db 10,13,7, "----------------------------------", '$'
    newLine db 10,13, "$"

    ;variables declaration part
        ;in variables
    firstDigit db 0
    secondDigit db 0
    select db 0
    
    result db 0

        ;operation options
    slSum db 1
    slRes db 2
    slMul db 3
    slDiv db 4
    slOut db 5
.CODE 
.STARTUP ;DIRECTIVA para el inicio de ensamblador
    mov ax, @data
    mov ds, ax

    ;print header menu
    call printWelcome
    call printBreakLine
    call printNewLine

    cicle:
    ; print options menu
    call printMenu

    ;print Select Operation Message and ask value
    call printNewLine
    call askValueSelector
    call printBreakLine

    ;evaluate select value
    call evaluateSelect

    ;sum operation segment
    sumOperation:
    call sumProcedure

    ;rest operation segment
    restOperation:
    call restProcedure

    ;multiplication operation segment
    multOperation:
    call multProcedure

    ;division operation segment
    divOperation:
    call divProcedure

    ;out of system operation segment
    outOpertaion:
    call printNewLine
    call printEnd
    call printBreakLine

.EXIT ;DIRECTIVA donde terminará el ensablador
    ;procedures
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

    printBreakLine proc
        mov dx, offset breakLine
        mov ah, 09h
        mov ah, 09h
        int 21h
        
        ret
    printBreakLine endp

    printNewLine proc
        mov dx, offset newline
        mov ah, 09h
        mov ah, 09h
        int 21h
        
        ret
    printNewLine endp

    printMenu proc
        mov dx, offset slSumMenu
        mov ah, 09h
        int 21h
        
        mov dx, offset slResMenu
        mov ah, 09h
        int 21h
        
        mov dx, offset slMulMenu
        mov ah, 09h
        int 21h
        
        mov dx, offset slDivMenu
        mov ah, 09h
        int 21h

        mov dx, offset slOutMenu
        mov ah, 09h
        int 21h

        ret
    printMenu endp

    askValueSelector proc 
        mov ah, 09h
        lea dx, inSelectorValue
        int 21h

        mov ah, 01h
        int 21h
        sub al, 30h
        mov select, al

        ret
    askValueSelector endp

    evaluateSelect proc
        cmp al,slSum
        je sumOperation

        cmp al, slRes
        je restOperation

        cmp al, slMul
        je multOperation

        cmp al, slDiv
        je divOperation

        cmp al, slOut
        je outOpertaion

        ret
    evaluateSelect endp

    sumProcedure proc
        mov ah, 09h
        lea dx, inFirstValue
        int 21h

        mov ah, 01h
        int 21h
        sub al,30h
        mov firstDigit, al

        mov ah, 09h
        lea dx, inSecondValue
        int 21h

        mov ah,01h
        int 21h
        sub al, 30h
        mov secondDigit, al

        mov al, firstDigit
        add al, secondDigit
        mov result, al

        mov ah, 09h
        lea dx, resultSumMsg
        int 21h

        mov al, result
        AAM
        mov bx, ax

        ;show first digit
        mov ah, 02h
        mov dl, bh
        add dl, 30h
        int 21h

        ;show second digit
        mov ah, 02h
        mov dl, bl
        add dl, 30h
        int 21h

        call printBreakLine
        call printNewLine
        call cicle
        .EXIT

        ret
    sumProcedure endp

    restProcedure proc
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
        mov secondDigit,al

        mov al, firstDigit
        sub al, secondDigit
        mov result, al

        mov ah, 09h
        lea dx, resultRestMsg
        int 21h

        mov al, result
        AAM
        mov bx, ax

         ;show first digit
        mov ah, 02h
        mov dl, bh
        add dl, 30h
        int 21h

        ;show second digit
        mov ah, 02h
        mov dl, bl
        add dl, 30h
        int 21h

        call printBreakLine
        call printNewLine
        call cicle
        .EXIT

        ret
    restProcedure endp

    multProcedure proc
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
        mov secondDigit,al

        mov al, firstDigit
        mov bl, secondDigit
        mul bl
        mov result, al

        mov ah, 09h
        lea dx, resultMultiplicationMsg
        int 21h

        mov al, result
        AAM
        mov bx, ax

        ;show first digit
        mov ah, 02h
        mov dl, bh
        add dl, 30h
        int 21h

        ;show second digit
        mov ah, 02h
        mov dl, bl
        add dl, 30h
        int 21h

        call printBreakLine
        call printNewLine
        call cicle
        .EXIT

        ret
    multProcedure endp

    divProcedure proc
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
        mov secondDigit,al
        
        xor ax, ax
        mov al, firstDigit
        mov bl, secondDigit
        div bl
        mov result, al

        mov ah, 09h
        lea dx, resultDivisionMsg
        int 21h

        mov al, result
        AAM
        mov bx, ax

        ;show first digit
        mov ah, 02h
        mov dl, bh
        add dl, 30h
        int 21h

        ;show second digit
        mov ah, 02h
        mov dl, bl
        add dl, 30h
        int 21h

        call printBreakLine
        call printNewLine
        call cicle
        .EXIT

        ret
    divProcedure endp
END 
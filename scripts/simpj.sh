#!/bin/bash

SRC_DIR="./src"


function show_help() {
    clear
    echo ""
echo " _______  ___   __   __  _______      ___ "
echo "|       ||   | |  |_|  ||       |    |   |"
echo "|  _____||   | |       ||    _  |    |   |"
echo "| |_____ |   | |       ||   |_| |    |   |"
echo "|_____  ||   | |       ||    ___| ___|   |"
echo " _____| ||   | | ||_|| ||   |    |       |"
echo "|_______||___| |_|   |_||___|    |_______|"

echo "                       "
    echo " ⚙️  Comandos disponibles del lenguaje SIMPJ:"
    echo "--------------------------------------------------"
    echo "  esth   : mata todos los procesos Java en ejecución"
    echo "  simpj  : compila y ejecuta ->  x simpj carpeta/archivo.simpj"
    echo "  limpia : limpia la terminal (compatible con Windows, Linux y Mac)"
    echo "  help   : muestra esta ayuda y demás instrucciones"
    echo "  exit   : salir de la terminal actual (no se pierde el progreso)"
    echo "--------------------------------------------------"
   
}

case "$1" in
    esth)
    echo "-----------------------------"
    echo "Matando todos los procesos Java..."
    if [[ "$OSTYPE" == "msys" ]] || [[ "$OSTYPE" == "win32" ]]; then
        # Windows
        taskkill //F //IM java.exe //T > /dev/null 2>&1
    else
        # Linux / Mac
        pkill -f java
    fi
    ;;

    simpj)
        if [ -z "$2" ]; then
                echo "-----------------------------"

            echo "Uso: simpj <archivo.simpj>"
            exit 1
        fi

        INPUT="$2"
                echo "-----------------------------"

        echo "Compilando y Generando código..."
        javac "$SRC_DIR/SimpjTranslator.java"
        echo "-----------------------------"

        echo "Ejecutando traductor para $INPUT..."
        java -cp "$SRC_DIR" SimpjTranslator "$INPUT"

        if [ -f Main.java ]; then

            echo "Compilando Main.java..."
            javac Main.java

            echo "Ejecutando Main.java..."
                    echo "-----------------------------"
                    echo "---------RESULTADO----------"

                    echo "                             "

       java Main

        else
                echo "-----------------------------"

            echo "Error: Main.java no se generó."
            exit 1
        fi
        ;;
    limpia)
        clear
        ;;
    help|--help)
        show_help
        ;;
    *)
            echo "-----------------------------"

        echo "Comando no reconocido."
        show_help
        ;;
esac

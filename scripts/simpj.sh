#!/bin/bash

SRC_DIR="./src"

function show_help() {
    echo "Comandos SIMPJ disponibles:"
    echo "  esth   : mata todos los procesos Java"
    echo "  simpj  : compila y ejecuta: x simpj carpeta/archivo.simpj"
    echo "  limpia : limpia la terminal"
    echo "  help   : muestra esta ayuda"
}

case "$1" in
    esth)
        echo "Matando todos los procesos Java..."
        pkill -f java
        ;;
    simpj)
        if [ -z "$2" ]; then
            echo "Uso: simpj <archivo.simpj>"
            exit 1
        fi

        INPUT="$2"
        echo "Compilando traductor..."
        javac "$SRC_DIR/SimpjTranslator.java"

        echo "Ejecutando traductor para $INPUT..."
        java -cp "$SRC_DIR" SimpjTranslator "$INPUT"

        if [ -f Main.java ]; then
            echo "Compilando Main.java..."
            javac Main.java
            echo "Ejecutando Main.java..."
            java Main
        else
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
        echo "Comando no reconocido."
        show_help
        ;;
esac

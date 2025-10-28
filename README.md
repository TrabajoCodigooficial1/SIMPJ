<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>SIMPJ - Lenguaje de programación simplificado</title>
</head>
<body>

<h1>SIMPJ - Lenguaje de programación simplificado</h1>

<p><strong>Autor:</strong> Andrés Blanco Ferro<br>
<strong>Licencia:</strong> Open Source / Uso libre</p>

<hr>

<h2>📖 Introducción</h2>
<p>SIMPJ es un lenguaje de programación <strong>muy simple</strong>, diseñado para programar en Java con una <strong>sintaxis clara y fácil de aprender</strong>.</p>
<p>Permite escribir instrucciones básicas como:</p>
<ul>
    <li><code>imprimir</code></li>
    <li>Asignaciones de variables</li>
    <li>Condicionales (<code>si</code>)</li>
    <li>Bucles (<code>mientras</code>)</li>
</ul>
<p>SIMPJ <strong>traduce automáticamente tus archivos <code>.simpj</code> a Java</strong>, los compila y ejecuta, permitiéndote <strong>aprender y prototipar rápidamente</strong> sin escribir toda la sintaxis de Java.</p>

<hr>

<h2>🛠 Requisitos</h2>
<p>Antes de usar SIMPJ, necesitas:</p>
<ul>
    <li><strong>Java JDK 8 o superior</strong> – <a href="https://www.oracle.com/java/technologies/javase-downloads.html">Descargar</a></li>
    <li><strong>Git Bash</strong> (solo Windows) – <a href="https://gitforwindows.org/">Descargar</a></li>
    <li>Proyecto SIMPJ descomprimido en tu equipo, por ejemplo:</li>
</ul>
<pre><code>C:\Users\TU_USUARIO\Desktop\SIMPJ_v2</code></pre>

<hr>

<h2>⚙️ Configuración inicial (solo una vez)</h2>
<ol>
    <li>Abrir Git Bash.</li>
    <li>Crear o editar tu archivo <code>.bashrc</code> en tu carpeta de usuario:
        <pre><code>nano ~/.bashrc</code></pre>
    </li>
    <li>Copiar y pegar el siguiente contenido:
        <pre><code># -------------------------------
# Configuración de SIMPJ
# -------------------------------

# Carpeta de tu proyecto SIMPJ (ajusta si está en otra ruta)
SIM_PROJECT="$HOME/Desktop/SIMPJ_v2"

# Función para ejecutar SIMPJ
x() {
    if [ "$1" == "simpj" ]; then
        shift
        cd "$SIM_PROJECT" || return
        ./scripts/simpj.sh simpj "$@"
    else
        echo "Uso: x simpj <archivo.simpj>"
        echo "Ejemplo: x simpj ejemplos/holamundo.simpj"
    fi
}

# Alias para otros comandos
alias esth="cd \"$SIM_PROJECT\" && ./scripts/simpj.sh esth"
alias limpia="cd \"$SIM_PROJECT\" && ./scripts/simpj.sh limpia"
alias help="cd \"$SIM_PROJECT\" && ./scripts/simpj.sh help"

# Mensaje de bienvenida al abrir Git Bash
if [ -f "$SIM_PROJECT/scripts/simpj.sh" ]; then
    echo "-------------------------------------"
    echo "Bienvenido a SIMPJ!"
    echo "-------------------------------------"
    cd "$SIM_PROJECT"
    ./scripts/simpj.sh help
fi</code></pre>
    </li>
    <li>Guardar (<code>CTRL+O</code>) y salir (<code>CTRL+X</code>).</li>
    <li>Recargar <code>.bashrc</code>:
        <pre><code>source ~/.bashrc</code></pre>
    </li>
</ol>
<p>Ahora puedes usar SIMPJ desde <strong>cualquier carpeta</strong> en Git Bash.</p>

<hr>

<h2>💻 Uso básico</h2>
<p>Ejecutar un archivo <code>.simpj</code>:</p>
<pre><code>x simpj ejemplos/holamundo.simpj
x simpj ejemplos/sumas.simpj</code></pre>
<p>Esto hará que SIMPJ:</p>
<ol>
    <li>Traduza tu archivo <code>.simpj</code> a <code>Main.java</code></li>
    <li>Compile <code>Main.java</code></li>
    <li>Ejecute el programa y muestre la salida en la terminal</li>
</ol>

<hr>

<h2>📋 Comandos disponibles</h2>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>Comando</th>
        <th>Función</th>
    </tr>
    <tr>
        <td><code>x simpj &lt;archivo.simpj&gt;</code></td>
        <td>Compila y ejecuta un archivo SIMPJ</td>
    </tr>
    <tr>
        <td><code>esth</code></td>
        <td>Mata todos los procesos Java en ejecución</td>
    </tr>
    <tr>
        <td><code>limpia</code></td>
        <td>Limpia la terminal</td>
    </tr>
    <tr>
        <td><code>help</code></td>
        <td>Muestra la ayuda con todos los comandos</td>
    </tr>
</table>

<hr>

<h2>📝 Sintaxis básica de SIMPJ</h2>
<p><strong>Imprimir en pantalla:</strong></p>
<pre><code>imprimir "Hola Mundo"</code></pre>

<p><strong>Asignación de variables:</strong></p>
<pre><code>a = 5
b = "texto"
c = a + 10</code></pre>

<p><strong>Condicionales:</strong></p>
<pre><code>si a > 3:
    imprimir "a es mayor que 3"
fin_si</code></pre>

<p><strong>Bucles mientras:</strong></p>
<pre><code>i = 0
mientras i < 5:
    imprimir i
    i = i + 1
fin_mientras</code></pre>

<p><strong>Comentarios:</strong></p>
<pre><code># Esto es un comentario</code></pre>

<p>SIMPJ traduce automáticamente estas instrucciones a Java y las ejecuta.</p>

<hr>

<h2>📂 Ejemplos incluidos</h2>
<ul>
    <li><code>ejemplos/holamundo.simpj</code> → Muestra un "Hola Mundo"</li>
    <li><code>ejemplos/sumas.simpj</code> → Suma de dos números y muestra el resultado</li>
</ul>

<hr>

<h2>🔧 Flujo recomendado al programar</h2>
<ol>
    <li>Abrir Git Bash (se mostrará el mensaje de bienvenida de SIMPJ).</li>
    <li>Crear tu archivo <code>.simpj</code> en la carpeta <code>ejemplos/</code>.</li>
    <li>Ejecutar:
        <pre><code>x simpj &lt;nombre_archivo&gt;.simpj</code></pre>
    </li>
    <li>Ver la salida en la terminal.</li>
    <li>Usar <code>esth</code> si necesitas matar procesos Java en ejecución.</li>
    <li>Usar <code>limpia</code> para limpiar la terminal si es necesario.</li>
</ol>

<hr>

<h2>📜 Licencia</h2>
<p>SIMPJ es <strong>de uso libre</strong>, puedes usarlo, modificarlo y distribuirlo sin restricciones.</p>
<p><strong>Autor:</strong> Andrés Blanco Ferro<br>
<strong>Licencia:</strong> Open Source / Educativo</p>

<p>¡Listo para programar en SIMPJ! 🚀</p>

</body>
</html>

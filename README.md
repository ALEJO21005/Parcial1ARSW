
### Escuela Colombiana de Ingeniería
### Arquitecturas de Software - ARSW
## Ejercicio Fórmula BBP - Parcial Practico


**Ejercicio Fórmula BBP**

La fórmula [BBP](https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula) (Bailey–Borwein–Plouffe formula) es un algoritmo que permite calcular el enésimo dígito de PI en base 16, con la particularidad de no necesitar calcular nos n-1 dígitos anteriores. Esta característica permite convertir el problema de calcular un número masivo de dígitos de PI (en base 16) a uno [vergonzosamente paralelo](https://en.wikipedia.org/wiki/Embarrassingly_parallel). En este repositorio encontrará la implementación, junto con un conjunto de pruebas. 

Para este ejercicio se quiere calcular, en el menor tiempo posible, y en una sola máquina (aprovechando las características multi-core de la mismas) al menos el primer millón de dígitos de PI (en base 16). Para esto

1. Cree una clase de tipo Thread que represente el ciclo de vida de un hilo que calcule una parte de los dígitos requeridos.

---

Se creó la clase PiDigitsThread que extiende Thread. Esta clase recibe un inicio (start) y una cantidad de dígitos a calcular (count), y en su método run() realiza el cálculo de esos dígitos usando la fórmula BBP. Al terminar, los dígitos quedan guardados internamente y se pueden obtener con el método getDigits(). También tiene un método getProcessedDigits() que permite saber cuántos dígitos ha calculado hasta el momento.

---

2. Haga que la función PiDigits.getDigits() reciba como parámetro adicional un valor N, correspondiente al número de hilos entre los que se va a paralelizar la solución. Haga que dicha función espere hasta que los N hilos terminen de resolver el problema para combinar las respuestas y entonces retornar el resultado. Para esto, puede utilizar el método Join() del API de concurrencia de Java.

---

Se creó una sobrecarga del método getDigits(start, count, N) en la clase PiDigits. Este método divide el total de dígitos entre los N hilos de forma equitativa, repartiendo el residuo entre los primeros hilos. Cada hilo calcula su parte de forma independiente. Al final, se usa join() para esperar a que todos los hilos terminen y luego se combinan los resultados parciales en un solo arreglo usando System.arraycopy().

---

3. Ajuste la implementación para que cada 5 segundos los hilos se detengan e impriman el número de digitos que han procesado y una vez se presione la tecla enter que los hilos continúen su proceso.

---

Se creó la clase PauseControl que maneja la pausa y reanudación de los hilos usando los métodos wait() y notifyAll() de Java. Los hilos de cálculo verifican en cada iteración si deben pausarse llamando a waitIfPaused(). Además, dentro de getDigits(start, count, N) se lanza un hilo de control (daemon) que cada 5 segundos pausa todos los hilos, imprime por consola cuántos dígitos lleva procesados cada uno, y espera a que el usuario presione Enter para reanudar el cálculo.
# Auditoría de Código Java  
## Sistema de Control Básico de Ofertas y Registro de Combos Novaventa

**Lenguaje:** Java  
**Clase auditada:** `Main`  
**Objetivo de la auditoría:** Verificar que el programa cumpla con los requisitos de manejo de un arreglo de ofertas, búsqueda segura, registro de nuevas ofertas y prevención de errores relacionados con valores `null`.

***

## 1. Descripción general del programa

El programa implementa una consola para administrar ofertas de combos. Inicialmente cuenta con tres ofertas registradas y dos espacios disponibles dentro de un arreglo de cinco posiciones.

El usuario puede realizar las siguientes operaciones mediante un menú:

1. Ver las ofertas registradas.
2. Validar si una oferta existe en el catálogo.
3. Registrar una nueva oferta en el primer espacio libre.
4. Finalizar el programa.

El flujo del programa se controla mediante un ciclo `do-while`, una estructura `switch` y ciclos `for` tradicionales. No se utiliza el ciclo `for-each`, de acuerdo con la indicación del ejercicio.

***

## 2. Estructura del arreglo

El arreglo utilizado es el siguiente:

```java
String[] ofertas = {
    "Combo Cuidado Personal",
    "Combo Hogar",
    "Combo Despensa",
    null,
    null
};
```

### Verificación

| Criterio | Resultado | Evidencia |
|---|---|---|
| Se utiliza un único arreglo | Cumple | El arreglo `ofertas` es usado para almacenar, consultar y registrar todas las ofertas. |
| El arreglo es unidimensional | Cumple | Se declara como `String[] ofertas`. |
| El arreglo es de tipo `String` | Cumple | Cada posición puede almacenar texto con el nombre de una oferta. |
| El arreglo tiene cinco posiciones | Cumple | Se inicializa con cinco elementos. |
| Contiene tres ofertas iniciales | Cumple | Tiene los valores `"Combo Cuidado Personal"`, `"Combo Hogar"` y `"Combo Despensa"`. |
| Tiene espacios disponibles representados por `null` | Cumple | Las posiciones cuatro y cinco contienen `null`. |

**Conclusión:** La estructura de datos cumple correctamente con el requerimiento de trabajar con un único arreglo de texto de cinco posiciones.

***

## 3. Auditoría de la opción “Ver ofertas”

El programa utiliza el siguiente código para mostrar las ofertas:

```java
for (int i = 0; i < ofertas.length; i++) {
    if (ofertas[i] != null) {
        System.out.println("- " + ofertas[i]);
    }
}
```

### Verificación

| Criterio | Resultado | Evidencia |
|---|---|---|
| Se utiliza ciclo `for` tradicional | Cumple | Se usa `for (int i = 0; i < ofertas.length; i++)`. |
| No se utiliza `for-each` | Cumple | El recorrido se realiza mediante índice `i`. |
| Se recorren todas las posiciones | Cumple | La condición `i < ofertas.length` permite revisar cada posición del arreglo. |
| No se imprimen valores `null` | Cumple | La condición `if (ofertas[i] != null)` evita mostrar posiciones vacías. |
| Se muestran solo ofertas vigentes | Cumple | Solo se imprimen los elementos que tienen contenido. |

**Resultado esperado:**

```text
Ofertas disponibles:
- Combo Cuidado Personal
- Combo Hogar
- Combo Despensa
```

**Conclusión:** La opción “Ver ofertas” funciona correctamente y evita mostrar las posiciones libres del arreglo.

***

## 4. Auditoría del método `buscarProducto`

El método encargado de validar la existencia de una oferta es:

```java
public static boolean buscarProducto(String[] ofertas, String nombreBuscado) {
    for (int i = 0; i < ofertas.length; i++) {
        if (ofertas[i] != null && ofertas[i].equalsIgnoreCase(nombreBuscado)) {
            return true;
        }
    }
    return false;
}
```

### Propósito del método

El método recibe:

```java
String[] ofertas
```

Este parámetro representa el arreglo que contiene las ofertas registradas.

También recibe:

```java
String nombreBuscado
```

Este parámetro representa el nombre de la oferta que el usuario desea validar.

El método devuelve un valor lógico de tipo `boolean`:

- `true`: la oferta existe en el arreglo.
- `false`: la oferta no existe en el arreglo.

### Verificación

| Criterio | Resultado | Evidencia |
|---|---|---|
| El método está separado del `main` | Cumple | Se declara como método independiente: `public static boolean buscarProducto(...)`. |
| Recibe el arreglo como parámetro | Cumple | Recibe `String[] ofertas`. |
| Recibe el nombre que se desea buscar | Cumple | Recibe `String nombreBuscado`. |
| Retorna un valor booleano | Cumple | La firma del método utiliza `boolean`. |
| Recorre el arreglo con `for` | Cumple | Usa `for (int i = 0; i < ofertas.length; i++)`. |
| Ignora mayúsculas y minúsculas | Cumple | Utiliza `equalsIgnoreCase(nombreBuscado)`. |
| Retorna `true` si encuentra la oferta | Cumple | Cuando hay coincidencia, se ejecuta `return true`. |
| Retorna `false` si no encuentra la oferta | Cumple | Al terminar el recorrido sin coincidencias, se ejecuta `return false`. |
| Maneja posiciones `null` | Cumple | Verifica `ofertas[i] != null` antes de comparar. |

***

## 5. Evidencia de prevención de `NullPointerException`

El punto principal de la auditoría consiste en validar que el método de búsqueda no produzca un error `NullPointerException` cuando el arreglo tenga posiciones vacías.

La condición utilizada es:

```java
if (ofertas[i] != null && ofertas[i].equalsIgnoreCase(nombreBuscado))
```

### Análisis técnico

El arreglo contiene dos posiciones con valor `null`:

```java
null, null
```

Si el programa intentara ejecutar directamente:

```java
ofertas[i].equalsIgnoreCase(nombreBuscado)
```

cuando `ofertas[i]` fuera `null`, se produciría un error llamado `NullPointerException`.

Sin embargo, el código primero valida:

```java
ofertas[i] != null
```

Solo si esa condición es verdadera, Java continúa con la comparación:

```java
ofertas[i].equalsIgnoreCase(nombreBuscado)
```

Esto funciona debido al operador lógico `&&`. En Java, dicho operador usa evaluación de cortocircuito: si la primera condición es falsa, la segunda condición no se ejecuta.

### Ejemplo de ejecución segura

Supóngase que el ciclo llega a la posición cuatro:

```java
ofertas[3] = null;
```

La condición evaluada sería:

```java
ofertas[3] != null && ofertas[3].equalsIgnoreCase(nombreBuscado)
```

Primera evaluación:

```java
ofertas[3] != null
```

Resultado:

```java
false
```

Como el primer resultado es `false`, Java no ejecuta:

```java
ofertas[3].equalsIgnoreCase(nombreBuscado)
```

Por esta razón, el método no intenta aplicar `equalsIgnoreCase()` sobre un valor `null` y no genera `NullPointerException`.

### Resultado de la auditoría sobre valores nulos

| Escenario | Resultado |
|---|---|
| La posición contiene una oferta | La oferta se compara normalmente. |
| La posición contiene `null` | La posición se omite sin generar error. |
| La oferta buscada existe | El método retorna `true`. |
| La oferta buscada no existe | El método retorna `false`. |
| El arreglo contiene posiciones vacías | El programa continúa funcionando correctamente. |

**Conclusión:** El método `buscarProducto` maneja adecuadamente los valores nulos del arreglo y evita correctamente el error `NullPointerException`.

***

## 6. Auditoría del método `agregarCombo`

El método utilizado para registrar una oferta es:

```java
public static boolean agregarCombo(String[] ofertas, String nuevaOferta) {
    for (int i = 0; i < ofertas.length; i++) {
        if (ofertas[i] == null) {
            ofertas[i] = nuevaOferta;
            System.out.println("Oferta registrada en la posición " + (i + 1));
            return true;
        }
    }
    return false;
}
```

### Propósito del método

El método busca la primera posición libre del arreglo, identificada por el valor `null`, y guarda allí el nombre de la nueva oferta.

### Verificación

| Criterio | Resultado | Evidencia |
|---|---|---|
| El método está separado del `main` | Cumple | Se declara como `public static boolean agregarCombo(...)`. |
| Recorre el arreglo con `for` | Cumple | Se utiliza un ciclo con índice `i`. |
| Identifica espacios disponibles | Cumple | Usa la condición `ofertas[i] == null`. |
| Inserta en la primera posición libre | Cumple | El ciclo inicia desde la posición cero y retorna al encontrar el primer `null`. |
| Guarda la nueva oferta | Cumple | Ejecuta `ofertas[i] = nuevaOferta`. |
| Informa la posición registrada | Cumple | Muestra `i + 1` para presentar la posición al usuario desde uno. |
| Retorna `true` si logra registrar | Cumple | Después de guardar la oferta ejecuta `return true`. |
| Retorna `false` si no hay espacio | Cumple | Al finalizar el ciclo sin encontrar `null`, ejecuta `return false`. |

### Ejemplo de registro

Antes de registrar una nueva oferta:

```java
{
    "Combo Cuidado Personal",
    "Combo Hogar",
    "Combo Despensa",
    null,
    null
}
```

Si el usuario registra:

```text
Combo Tecnología
```

Después de ejecutar el método, el arreglo queda así:

```java
{
    "Combo Cuidado Personal",
    "Combo Hogar",
    "Combo Despensa",
    "Combo Tecnología",
    null
}
```

El programa muestra:

```text
Oferta registrada en la posición 4
Oferta agregada correctamente.
```

**Conclusión:** El método agrega correctamente una nueva oferta en la primera posición disponible.

***

## 7. Auditoría de entrada de datos con `Scanner`

El programa utiliza:

```java
opcion = leer.nextInt();
leer.nextLine();
```

La instrucción:

```java
leer.nextLine();
```

después de `nextInt()` es correcta y necesaria. `nextInt()` lee únicamente el número, pero deja pendiente el salto de línea ingresado por el usuario. Si no se consumiera ese salto de línea, la siguiente lectura de texto con `nextLine()` podría quedar vacía.

El código corrige este posible problema mediante:

```java
leer.nextLine();
```

También se utiliza:

```java
leer.nextLine().trim();
```

en las opciones de buscar y registrar una oferta.

El método `trim()` elimina espacios innecesarios al inicio y al final de la entrada del usuario. Además, antes de registrar una nueva oferta se valida:

```java
if (nuevaOferta.isEmpty()) {
    System.out.println("El nombre de la oferta no puede estar vacío.");
}
```

Esta validación evita que el usuario registre una oferta vacía.

**Conclusión:** El manejo de lecturas con `Scanner` es correcto para el flujo normal de uso del programa.

***

## 8. Resultado final de la auditoría

| Criterio de aceptación | Estado |
|---|---|
| Uso de un único arreglo de tipo `String` | Cumple |
| Arreglo de cinco posiciones con tres ofertas iniciales | Cumple |
| Uso de ciclos `for` tradicionales | Cumple |
| No uso de ciclo `for-each` | Cumple |
| Menú controlado con `do-while` | Cumple |
| Opción para visualizar ofertas | Cumple |
| No mostrar posiciones con valor `null` | Cumple |
| Método de búsqueda separado | Cumple |
| Método de búsqueda con retorno `boolean` | Cumple |
| Validación de existencia de una oferta | Cumple |
| Comparación sin distinguir mayúsculas y minúsculas | Cumple |
| Manejo seguro de valores `null` | Cumple |
| Prevención de `NullPointerException` | Cumple |
| Registro en la primera posición libre | Cumple |
| Validación de nombre vacío al registrar | Cumple |
| Finalización del programa | Cumple |

## 9. Conclusión

El programa auditado cumple con los requisitos funcionales principales del ejercicio. Se utiliza un único arreglo de tipo `String` para almacenar las ofertas, se emplean exclusivamente ciclos `for` tradicionales para recorrerlo y se implementan métodos independientes para buscar y registrar ofertas.

La evidencia más importante de la auditoría es el método `buscarProducto`, ya que este verifica que cada posición del arreglo sea diferente de `null` antes de ejecutar `equalsIgnoreCase()`. Esto evita que el programa produzca un `NullPointerException` al recorrer las posiciones libres del arreglo.

Por lo tanto, se concluye que el código presenta un manejo adecuado de los valores nulos, permite validar ofertas de manera segura y registra correctamente nuevas ofertas en la primera posición disponible.

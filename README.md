# 🎟️ Ticketek - Sistema de Gestión de Venta de Entradas

Este proyecto implementa un sistema para la empresa **Ticketek**, organizadora de espectáculos públicos. Su objetivo es gestionar eficientemente **usuarios**, **espectáculos** y **sedes**, cumpliendo con requerimientos funcionales, estructurales y técnicos.

---

## 📌 Objetivo General

Desarrollar un sistema modular, escalable y de fácil mantenimiento, que permita registrar usuarios, administrar espectáculos en múltiples sedes y gestionar la venta y consulta de entradas, utilizando correctamente herramientas y estructuras de Java.

---

## 🧩 Funcionalidades Principales

### 👥 Usuarios

- Registro de usuarios con email (único), nombre, apellido y contraseña.
    
- Compra de entradas para funciones específicas de espectáculos.
    
- Consulta de entradas futuras y pasadas.
    
- Cambios y anulaciones de entradas (con autenticación).
    

### 🎭 Espectáculos

- Identificados por **nombre único**.
    
- Pueden tener múltiples funciones (fecha + sede).
    
- Se permite agregar funciones posteriormente.
    
- Consulta del total recaudado y de entradas vendidas por sede.
    

### 🏟️ Sedes

- **Tipos**: Estadio, Teatro, Miniestadio.
    
- Cada una con nombre único, capacidad, dirección y sectores según el tipo.
    
- **Teatros y Miniestadios**: Sectorizados con distintos precios.
    
- **Miniestadios**: Incluyen puestos de comida y merchandising.
    

### 🎫 Entradas

- Generadas al momento de la compra.
    
- Contienen: código, espectáculo, fecha, sede, ubicación, precio.
    
- Su costo depende del tipo de sede, sector y si hay consumición incluida.
    
- Se pueden anular (O(1)) y cambiar de función (misma obra).
    

---

## ⚙️ Funciones del Sistema

1. Registrar sede.
    
2. Registrar usuario.
    
3. Registrar espectáculo.
    
4. Agregar función a un espectáculo.
    
5. Vender entradas (con autenticación).
    
6. Listar funciones de un espectáculo con formato detallado.
    
7. Listar entradas futuras de un usuario.
    
8. Listar todas las entradas de un usuario.
    
9. Anular una entrada (O(1)).
    
10. Cambiar una entrada de función (misma obra, devuelve nueva entrada).
    
11. Consultar precio de una entrada (O(1)).
    
12. Consultar valor de una entrada para una función específica (O(1)).
    
13. Consultar recaudación de un espectáculo en una sede (O(1)).
    
14. Listar todas las entradas vendidas de un espectáculo.
    

---

## 🛠️ Requerimientos Técnicos

- ✅ Uso obligatorio de:
    
    - `StringBuilder` donde se justifique la modificación eficiente de strings.
        
    - Iteradores y `foreach` para recorrer colecciones.
        
- ✅ Uso correcto de:
    
    - **Herencia, polimorfismo, abstracción.**
        
    - Al menos dos de: _sobrecarga_, _sobrescritura_, _interfaces_.
        
- ✅ Cada TAD debe incluir:
    
    - Su **IREP** documentado.
        
    - Justificación de la estructura de representación.
        
- ✅ Implementación del método `toString()` en el TAD `Ticketek` y relacionados.
    
- ✅ Código debe:
    
    - Funcionar con el **cliente provisto** (no modificable).
        
    - Pasar satisfactoriamente los **tests JUnit oficiales**.
        
    - Aprovechar correctamente las **estructuras de datos** elegidas.
        
- ✅ Se recomienda incluir conjunto propio de tests adicionales.
    

---

## 📚 Documentación Adicional

La documentación adjunta incluye:

- Diagrama de clases actualizado.
    
- Explicación detallada del uso de herencia, polimorfismo, etc.
    
- Análisis de complejidad del punto 8 (Anulación de entrada) usando Álgebra de Órdenes.
    
- IREP de cada TAD implementado.
    

---

## 📦 Estructura del Proyecto

```
src/
├── model/              # TADs principales
├── interfaces/         # Interfaces provistas por la cátedra
├── impl/               # Implementaciones de interfaces
├── util/               # Clases auxiliares
├── main/               # Código cliente (no modificable)
└── test/               # Test JUnit (provistos y propios)
```

---

## 🧪 Testing

- ✅ Pruebas automáticas con **JUnit** provistas por la cátedra.
    
- ✅ Se sugiere ejecutar tests propios para validar comportamiento adicional.
    
- ❗ El proyecto será corregido con **tests extra no provistos**.
    

---

## 🧠 Consideraciones Finales

- El diseño sigue los principios de bajo acoplamiento y alta cohesión.
    
- Se buscó una implementación mantenible y extensible.
    
- La salida del método `toString()` de `Ticketek` presenta:
    
    - Usuarios registrados.
        
    - Sedes disponibles.
        
    - Lista de espectáculos con cantidad de funciones y recaudación por cada uno.
        

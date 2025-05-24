# Proyectos de Software - Gestión de establecimientos (Escuelas de manejo)

Este repositorio contiene cuatro proyectos de software desarrollados para la gestión de escuelas de manejo según el caso solicitado. Cada proyecto corresponde a una capa o tipo de aplicación diferente, utilizando tecnologías y paradigmas específicos.

---

## 1. Aplicación de Consola - Gestión de establecimientos

- Aplicación de consola para la gestión de tablas relacionadas al caso (ENTIDAD, UBIGEO, TIPO ESCUELA, ESTADO ESCUELA y USUARIO).
- Implementa los principales conceptos de Programación Orientada a Objetos (POO):
  - Clases, interfaces, clases utilitarias.
  - Excepciones personalizadas.
  - Encapsulación, polimorfismo, herencia y abstracción.
- Persistencia con JDBC utilizando `PreparedStatement` y `CallableStatement`.
- Operaciones de consulta e inserción mediante procedimientos almacenados.

---

## 2. Aplicación de Escritorio - Java Swing  

- Aplicación gráfica que reutiliza la lógica de la aplicación de consola.
- Utiliza Java Swing para la interfaz de usuario.
- Funcionalidades adicionales con menús, modales, paneles, etc.
- Mejora la experiencia del usuario con componentes gráficos.

---

## 3. API REST Back-End - Spring Boot 

- API REST desarrollada con Spring Boot.
- Estructura modular con:
  - Controller, Entity, DTO, Services, Repository, Util.
- Persistencia con Spring Data JPA.
- Uso de procedimientos almacenados.
- Métodos implementados:
  - Insertar (POST), actualizar (PUT), actualización parcial (PATCH).
  - Eliminar (DELETE), consultas (GET) con URLs amigables y parámetros.
- Implementación de HATEOAS para la gestión de establecimientos (escuelas).
- Paginación y ordenamiento para optimizar consultas.
- Seguridad básica con Basic Auth.

---

## 4. Aplicación Front-End - Angular 19

- Aplicación Angular 19 con control de acceso (login).
- Permite el registro y consulta de departamentos, provincias, distritos y establecimientos.
- Estructura organizada en:
  - Models (interfaces y clases), services, pipes, components, routers, etc.
- UI basada en Bootstrap y ngx-bootstrap.
- Formularios con validaciones estándar y lógica de negocio tanto en Front-End como en Back-End.
- Mensajes personalizados de operaciones con librerías como `sweetalert2`.
- Consultas públicas similares al prototipo.

---

## Tecnologías utilizadas

- Java SE, JDBC, procedimientos almacenados.
- Java Swing.
- Spring Boot, Spring Data, Swagger, HATEOAS.
- Angular 19, Bootstrap, ngx-bootstrap.
- Seguridad: Basic Auth.
- Librerías para UI: sweetalert2.

---

## Cómo ejecutar cada proyecto

- **Aplicación Consola**: Compilar y ejecutar la clase principal.  
- **Aplicación Escritorio**: Ejecutar la clase principal de Swing.  
- **API REST**: Ejecutar el proyecto Spring Boot con Maven.  
- **Angular**: Ejecutar con `ng serve` después de instalar dependencias con `npm install`.

---

Elizabeth Cachicatari Arocutipa - [https://github.com/elizabethcachicatari/](https://github.com/elizabethcachicatari/)

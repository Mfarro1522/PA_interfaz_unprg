# 📚 PA - Sistema de Gestión de Biblioteca - UNPRG

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-GUI-blue?style=for-the-badge)
![NetBeans](https://img.shields.io/badge/NetBeans-IDE-1B6AC6?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white)

Sistema de gestión de biblioteca desarrollado en Java Swing para la Universidad Nacional Pedro Ruiz Gallo (UNPRG). Este proyecto implementa un sistema completo de préstamo y devolución de libros con gestión de usuarios y reportes estadísticos.

## 📑 Tabla de Contenidos

- [Características](#-características)
- [Arquitectura del Proyecto](#-arquitectura-del-proyecto)
- [Patrones de Diseño](#-patrones-de-diseño)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Requisitos del Sistema](#-requisitos-del-sistema)
- [Instalación y Configuración](#-instalación-y-configuración)
- [Cómo Usar](#-cómo-usar)
- [Contribuir al Proyecto](#-contribuir-al-proyecto)
- [Roadmap y Próximos Pasos](#-roadmap-y-próximos-pasos)
- [Créditos](#-créditos)
- [Licencia](#-licencia)

## ✨ Características

### Funcionalidades Principales

- ✅ **Gestión de Usuarios**: Registro y administración de estudiantes, docentes y administrativos
- ✅ **Gestión de Libros**: CRUD completo (Crear, Leer, Actualizar, Eliminar) de libros
- ✅ **Sistema de Préstamos**: Registro de préstamos con fechas de salida y devolución
- ✅ **Gestión de Devoluciones**: Control de retorno de libros prestados
- ✅ **Búsqueda Avanzada**: Filtrado de usuarios y libros por diferentes criterios
- ✅ **Dashboard Estadístico**: Visualización de métricas clave del sistema
- ✅ **Autenticación**: Sistema de login para acceso controlado

### Características de la Interfaz

- 🎨 **UI Moderna**: Diseño con gradientes y componentes personalizados
- 🔘 **Componentes Redondeados**: Botones, campos de texto y paneles con esquinas redondeadas
- 🎯 **Diseño Responsivo**: Adaptable a diferentes resoluciones
- 🌈 **Paleta de Colores Profesional**: Esquema de colores azul/morado (#5449E5, #8359F4)
- 📊 **Tablas Estilizadas**: Visualización de datos con formato profesional

## 🏗️ Arquitectura del Proyecto

El proyecto implementa una **arquitectura de tres capas (Three-Tier Architecture)** que separa la lógica de presentación, negocio y datos:

```
┌─────────────────────────────────────────────┐
│     CAPA DE PRESENTACIÓN (UI/View)          │
│  ┌──────────────────────────────────────┐   │
│  │  • frMenu (Menú Principal)           │   │
│  │  • frmManUsuario (Gestión Usuarios)  │   │
│  │  • frmManLibro (Gestión Libros)      │   │
│  │  • jdPrestamoLibros                  │   │
│  │  • jdDevolucionLibro                 │   │
│  │  • jdLogin (Autenticación)           │   │
│  └──────────────────────────────────────┘   │
└─────────────────────────────────────────────┘
                     ↓ ↑
┌─────────────────────────────────────────────┐
│      CAPA LÓGICA (Business Logic)           │
│  ┌──────────────────────────────────────┐   │
│  │  BibliotecaService                   │   │
│  │  • registrarPrestamo()               │   │
│  │  • devolverPrestamo()                │   │
│  │  • generarIdPrestamo()               │   │
│  └──────────────────────────────────────┘   │
│  ┌──────────────────────────────────────┐   │
│  │  Modelos de Dominio                  │   │
│  │  • Usuario (Abstracto)               │   │
│  │    - Estudiante                      │   │
│  │    - Docente                         │   │
│  │    - Administrativo                  │   │
│  │  • Libro                             │   │
│  │  • Prestamo                          │   │
│  └──────────────────────────────────────┘   │
└─────────────────────────────────────────────┘
                     ↓ ↑
┌─────────────────────────────────────────────┐
│        CAPA DE DATOS (Data Access)          │
│  ┌──────────────────────────────────────┐   │
│  │  IUsuarioDAO (Interface)             │   │
│  │  UsuarioDAO (Implementation)         │   │
│  │  LibroDAO                            │   │
│  │  PrestamoDAO                         │   │
│  └──────────────────────────────────────┘   │
└─────────────────────────────────────────────┘
                     ↓ ↑
┌─────────────────────────────────────────────┐
│          ALMACENAMIENTO IN-MEMORY           │
│      Arrays estáticos con datos iniciales   │
└─────────────────────────────────────────────┘
```

### Descripción de Capas

#### 1. **Capa de Presentación (CapaPresentacion)**
- Responsable de la interfaz gráfica de usuario
- Implementada con Java Swing y componentes personalizados
- Maneja la interacción del usuario y validaciones de entrada
- Se comunica únicamente con la capa lógica

#### 2. **Capa Lógica (CapaLogica)**
- Contiene la lógica de negocio de la aplicación
- Define los modelos de dominio (POJOs)
- Implementa servicios para operaciones complejas
- Aplica reglas de negocio antes de acceder a los datos

#### 3. **Capa de Datos (CapaDatos)**
- Maneja el acceso y persistencia de datos
- Implementa el patrón DAO (Data Access Object)
- Actualmente usa almacenamiento en memoria (arrays estáticos)
- Diseñada para fácil migración a base de datos

## 🎨 Patrones de Diseño

El proyecto implementa varios patrones de diseño reconocidos:

### 1. **MVC (Model-View-Controller)**
Aunque adaptado a una arquitectura de tres capas:
- **Model**: Clases en `CapaLogica.modelos`
- **View**: Formularios en `CapaPresentacion`
- **Controller**: Lógica en DAOs y Services

### 2. **DAO (Data Access Object)**
```java
public interface IUsuarioDAO {
    boolean agregar(Usuario usuario);
    boolean actualizar(Usuario usuario);
    boolean eliminar(String id);
    Usuario buscarPorId(String id);
    Usuario[] listarTodos();
    Usuario[] listarPorTipo(String tipo);
    int getCantidadActual();
}
```
**Ventajas**:
- Abstracción del acceso a datos
- Facilita el cambio de fuente de datos (memoria → BD)
- Separa la lógica de negocio del almacenamiento

### 3. **Singleton Implícito**
Los DAOs usan arrays estáticos y métodos estáticos, comportándose como singletons:
```java
public class LibroDAO {
    private static final int MAX = 30;
    private static Libro[] libros = new Libro[MAX];
    private static int cantidad;
    // ...
}
```

### 4. **Template Method Pattern (Herencia)**
```java
public abstract class Usuario {
    // Atributos y métodos comunes
}

public class Estudiante extends Usuario {
    private String carrera;
}

public class Docente extends Usuario {
    private String facultad;
}

public class Administrativo extends Usuario {
    private String departamento;
}
```
**Ventajas**:
- Reutilización de código
- Polimorfismo
- Extensibilidad

### 5. **Service Layer Pattern**
```java
public class BibliotecaService {
    public static boolean registrarPrestamo(...) {
        // Validaciones de negocio
        // Coordinación entre múltiples DAOs
        // Actualización consistente de datos
    }
}
```
**Responsabilidades**:
- Orquesta operaciones complejas
- Aplica reglas de negocio
- Mantiene la consistencia de datos

### 6. **Factory Method (Implícito)**
Generación automática de IDs únicos:
```java
public static String generarIdPrestamo() {
    // Lógica para generar ID único
    return String.format("P%03d", ultimoId);
}
```

### 7. **Observer Pattern (Implícito en Swing)**
Los componentes Swing usan listeners para eventos:
```java
btnRegistrar.addActionListener(evt -> {
    // Manejo de evento
});
```

## 📂 Estructura del Proyecto

```
PA_1/
├── 📁 src/
│   ├── 📁 CapaDatos/                    # Capa de Acceso a Datos
│   │   ├── IUsuarioDAO.java             # Interface DAO
│   │   ├── UsuarioDAO.java              # Implementación DAO Usuarios
│   │   ├── LibroDAO.java                # DAO de Libros
│   │   └── PrestamoDAO.java             # DAO de Préstamos
│   │
│   ├── 📁 CapaLogica/                   # Capa de Lógica de Negocio
│   │   ├── 📁 modelos/                  # Modelos de Dominio (POJOs)
│   │   │   ├── Usuario.java            # Clase abstracta base
│   │   │   ├── Estudiante.java         # Usuario tipo Estudiante
│   │   │   ├── Docente.java            # Usuario tipo Docente
│   │   │   ├── Administrativo.java     # Usuario tipo Administrativo
│   │   │   ├── Libro.java              # Modelo Libro
│   │   │   └── Prestamo.java           # Modelo Préstamo
│   │   │
│   │   └── 📁 Servicio/                 # Servicios de Negocio
│   │       └── BibliotecaService.java   # Lógica de préstamos
│   │
│   ├── 📁 CapaPresentacion/             # Capa de Presentación (UI)
│   │   ├── Main.java                    # Punto de entrada
│   │   ├── frMenu.java/.form            # Menú principal
│   │   ├── frmManLibro.java/.form       # Gestión de libros
│   │   ├── frmManUsuario.java/.form     # Gestión de usuarios
│   │   ├── jdPrestamoLibros.java/.form  # Diálogo de préstamos
│   │   ├── jdDevolucionLibro.java/.form # Diálogo de devoluciones
│   │   ├── jdLogin.java/.form           # Autenticación
│   │   ├── jdAniadirUsuario.java/.form  # Buscar/seleccionar usuario
│   │   ├── jdAniadirLibro.java/.form    # Buscar/seleccionar libro
│   │   └── jdBuscarPrestamo.java/.form  # Buscar préstamos
│   │
│   └── 📁 Recursos/                     # Recursos del Proyecto
│       ├── 📁 Assets/                   # Imágenes e iconos
│       └── 📁 componentes/              # Componentes UI personalizados
│           ├── RoundedButton.java       # Botón redondeado
│           ├── RoundedTextField.java    # Campo de texto redondeado
│           ├── RoundedPasswordField.java
│           ├── RoundedComboBox.java
│           ├── RoundedScrollPane.java
│           ├── RoundedShadowPanel.java
│           ├── CircularButton.java      # Botón circular
│           ├── GradientPanel.java       # Panel con gradiente
│           ├── ImagePanel.java          # Panel con imagen
│           ├── ShadowBorder.java        # Borde con sombra
│           ├── ToggleSwitch.java        # Interruptor toggle
│           ├── EstiloTablas.java        # Estilos para JTable
│           └── Funciones.java           # Utilidades
│
├── 📁 lib/                              # Dependencias externas
│   └── jcalendar-1.4.jar               # Selector de fechas
│
├── 📁 nbproject/                        # Configuración NetBeans
│   ├── build-impl.xml
│   ├── genfiles.properties
│   ├── project.properties
│   └── project.xml
│
├── 📁 build/                            # Archivos compilados
│   └── classes/
│
├── 📁 test/                             # Tests (vacío actualmente)
│
├── build.xml                            # Script de construcción Ant
├── manifest.mf                          # Manifiesto JAR
└── README.md                            # Este archivo
```

## 💻 Requisitos del Sistema

### Software Necesario

| Software | Versión Mínima | Recomendada | Propósito |
|----------|----------------|-------------|-----------|
| Java JDK | 8 | 11 o superior | Compilación y ejecución |
| NetBeans IDE | 8.2 | 12.0 o superior | Desarrollo (opcional) |
| Apache Ant | 1.9.7 | 1.10+ | Sistema de construcción |

### Dependencias Externas

- **JCalendar 1.4**: Componente para selección de fechas
  - Ubicación: `lib/jcalendar-1.4.jar`
  - Licencia: LGPL
  - [Descarga](https://toedter.com/jcalendar/)

### Requisitos de Hardware

- **RAM**: 512 MB mínimo (2 GB recomendado)
- **Espacio en disco**: 100 MB para el proyecto
- **Resolución de pantalla**: 1024x768 mínimo (1920x1080 recomendado)

## 🚀 Instalación y Configuración

### Opción 1: Clonar el Repositorio

```bash
# Clonar el repositorio
git clone https://github.com/tu-usuario/PA_interfaz_unprg.git

# Navegar al directorio del proyecto
cd PA_interfaz_unprg/PA_1
```

### Opción 2: Fork del Proyecto

1. **Hacer Fork del Repositorio**
   - Ve a la página del repositorio en GitHub
   - Haz clic en el botón **"Fork"** en la esquina superior derecha
   - Selecciona tu cuenta de GitHub como destino

2. **Clonar tu Fork**
   ```bash
   git clone https://github.com/TU-USUARIO/PA_interfaz_unprg.git
   cd PA_interfaz_unprg/PA_1
   ```

3. **Configurar Remote Upstream**
   ```bash
   # Añadir el repositorio original como upstream
   git remote add upstream https://github.com/usuario-original/PA_interfaz_unprg.git
   
   # Verificar remotes
   git remote -v
   ```

4. **Mantener tu Fork Actualizado**
   ```bash
   # Obtener cambios del repositorio original
   git fetch upstream
   
   # Fusionar cambios en tu rama principal
   git checkout main
   git merge upstream/main
   
   # Subir cambios a tu fork
   git push origin main
   ```

### Opción 3: Descargar ZIP

1. Ve a la página del repositorio en GitHub
2. Haz clic en **"Code"** → **"Download ZIP"**
3. Extrae el archivo ZIP en tu directorio de trabajo
4. Navega a la carpeta `PA_1`

### Configuración en NetBeans

1. **Abrir el Proyecto**
   ```
   File → Open Project → Seleccionar PA_1
   ```

2. **Verificar Dependencias**
   - Haz clic derecho en el proyecto
   - Selecciona **"Properties"**
   - Ve a **"Libraries"**
   - Verifica que `jcalendar-1.4.jar` esté listado
   - Si no está, añádelo desde la carpeta `lib/`

3. **Configurar JDK**
   - En Properties → Libraries → Java Platform
   - Selecciona JDK 8 o superior

4. **Compilar el Proyecto**
   ```
   Run → Clean and Build Project (Shift+F11)
   ```

5. **Ejecutar la Aplicación**
   ```
   Run → Run Project (F6)
   ```
   O ejecutar directamente la clase `Main.java`

### Configuración mediante Línea de Comandos

```bash
# Compilar con Ant
ant clean
ant compile

# Ejecutar
ant run

# Generar JAR
ant jar
```

El JAR generado estará en `dist/PA_1.jar` y puede ejecutarse con:
```bash
java -jar dist/PA_1.jar
```

## 📖 Cómo Usar

### Inicio de Sesión

Al iniciar la aplicación, se muestra un diálogo de login:
- El sistema está pre-configurado con usuarios de prueba
- Puedes omitir el login para fines de demostración

### Dashboard Principal

El menú principal (`frMenu`) muestra:
- **Estadísticas**: Total de usuarios, libros, préstamos y devoluciones
- **Fecha actual**: Visualización de la fecha del día
- **Botones de acceso rápido**:
  - 📚 Gestión de Libros
  - 👥 Gestión de Usuarios
  - 📤 Realizar Préstamo
  - 📥 Gestión de Devoluciones

### Gestión de Libros

**Registrar Libro**:
1. Completa todos los campos (Código, Título, Autor, Editorial, Año, Categoría, Cantidad)
2. Marca el estado de disponibilidad con el toggle switch
3. Haz clic en **"Registrar"**

**Buscar Libro**:
1. Ingresa el código del libro
2. Haz clic en **"Buscar"**
3. Los datos se cargarán en el formulario

**Actualizar Libro**:
1. Busca el libro o selecciónalo de la tabla
2. Modifica los campos necesarios
3. Haz clic en **"Actualizar"**

**Eliminar Libro**:
1. Selecciona el libro de la tabla
2. Haz clic en **"Eliminar"**
3. Confirma la acción

### Gestión de Usuarios

Similar a la gestión de libros, permite:
- Registrar nuevos usuarios (Estudiante, Docente, Administrativo)
- Buscar por ID o DNI
- Actualizar información
- Eliminar usuarios
- Filtrar por tipo de usuario

### Realizar Préstamo

1. Haz clic en el icono de búsqueda 🔍 junto a "Usuario"
2. Selecciona un usuario de la tabla emergente
3. Haz clic en el icono de búsqueda 🔍 junto a "Libro"
4. Selecciona un libro disponible
5. Establece la fecha de devolución
6. Haz clic en **"Prestar Libro"**

El sistema validará automáticamente:
- Disponibilidad del libro
- Existencia del usuario
- Cantidad de libros en stock

### Gestión de Devoluciones

1. Abre la ventana de devoluciones
2. Haz clic en el botón de búsqueda 🔍
3. Selecciona el préstamo a devolver de la tabla
4. Haz clic en **"Devolver"**

El sistema:
- Registra la fecha de devolución
- Actualiza el estado del préstamo
- Incrementa la cantidad disponible del libro

## 🤝 Contribuir al Proyecto

¡Las contribuciones son bienvenidas! Sigue estos pasos para contribuir:

### 1. Preparar tu Entorno

```bash
# Fork y clonar como se describió anteriormente
git clone https://github.com/TU-USUARIO/PA_interfaz_unprg.git
cd PA_interfaz_unprg/PA_1

# Crear una rama para tu feature
git checkout -b feature/nueva-funcionalidad
```

### 2. Convenciones de Código

#### Nomenclatura Java

- **Clases**: `PascalCase` (Ej: `LibroDAO`, `BibliotecaService`)
- **Métodos**: `camelCase` (Ej: `registrarPrestamo`, `buscarPorId`)
- **Variables**: `camelCase` (Ej: `idUsuario`, `fechaSalida`)
- **Constantes**: `UPPER_SNAKE_CASE` (Ej: `MAX_LIBROS`)
- **Packages**: `lowercase` (Ej: `capapresentacion`, `capaDatos`)

#### Estilo de Código

```java
// ✅ CORRECTO
public class Usuario {
    private String nombre;
    
    public String getNombre() {
        return nombre;
    }
}

// ❌ EVITAR
public class usuario {
    public String nombre;
}
```

#### Comentarios

```java
/**
 * Registra un nuevo préstamo en el sistema
 * @param idUsuario ID del usuario que solicita el préstamo
 * @param idLibro ID del libro a prestar
 * @param fechaPrestamo Fecha de salida
 * @param fechaDevolucion Fecha estimada de devolución
 * @return true si el préstamo se registró exitosamente
 */
public static boolean registrarPrestamo(...) {
    // Implementación
}
```

### 3. Áreas de Contribución

#### 🐛 Reportar Bugs

Crea un **Issue** con:
- Título descriptivo
- Pasos para reproducir
- Comportamiento esperado vs. actual
- Capturas de pantalla (si aplica)
- Versión de Java y SO

#### ✨ Proponer Features

Antes de implementar un feature grande:
1. Abre un **Issue** describiendo la propuesta
2. Espera feedback de los mantenedores
3. Discute el diseño y enfoque

#### 🎨 Mejorar UI/UX

- Mantén la paleta de colores existente
- Usa los componentes personalizados del paquete `Recursos.componentes`
- Asegura compatibilidad con diferentes resoluciones
- Agrega comentarios sobre el diseño

#### 📝 Mejorar Documentación

- Actualiza el README si cambias funcionalidades
- Documenta nuevas clases y métodos
- Agrega ejemplos de uso
- Traduce documentación (si aplica)

### 4. Proceso de Pull Request

1. **Commit tus cambios**
   ```bash
   git add .
   git commit -m "feat: agregar búsqueda por autor en LibroDAO"
   ```
   
   **Formato de commits** (Conventional Commits):
   - `feat:` Nueva funcionalidad
   - `fix:` Corrección de bug
   - `docs:` Cambios en documentación
   - `style:` Formato, punto y coma faltantes, etc.
   - `refactor:` Refactorización de código
   - `test:` Agregar tests
   - `chore:` Tareas de mantenimiento

2. **Push a tu Fork**
   ```bash
   git push origin feature/nueva-funcionalidad
   ```

3. **Crear Pull Request**
   - Ve a tu fork en GitHub
   - Haz clic en **"Compare & pull request"**
   - Llena la plantilla de PR:
     ```markdown
     ## Descripción
     [Describe qué hace tu PR]
     
     ## Tipo de cambio
     - [ ] Bug fix
     - [ ] Nueva funcionalidad
     - [ ] Mejora de rendimiento
     - [ ] Refactorización
     
     ## Checklist
     - [ ] El código compila sin errores
     - [ ] He probado los cambios localmente
     - [ ] He agregado/actualizado documentación
     - [ ] He seguido las convenciones de código del proyecto
     ```

4. **Code Review**
   - Espera feedback de los revisores
   - Realiza cambios solicitados
   - Push adicionales se agregarán al PR automáticamente

5. **Merge**
   - Después de la aprobación, tu código será mergeado
   - Tu rama será eliminada automáticamente

### 5. Buenas Prácticas

✅ **DO**:
- Haz commits pequeños y frecuentes
- Escribe mensajes de commit descriptivos
- Prueba tu código antes de hacer push
- Mantén tu fork actualizado con upstream
- Respeta el estilo de código existente

❌ **DON'T**:
- No hagas commits de archivos compilados (`build/`, `.class`)
- No incluyas configuraciones locales de IDE
- No cambies múltiples cosas no relacionadas en un PR
- No hagas force push a branches compartidos

## 🗺️ Roadmap y Próximos Pasos

### 📋 Versión 1.0 (Actual)
- [x] Sistema de préstamos y devoluciones
- [x] Gestión de usuarios y libros
- [x] Dashboard con estadísticas
- [x] Interfaz gráfica moderna
- [x] Componentes UI personalizados

### 🚀 Versión 2.0 (Próxima Release)

#### Alta Prioridad
- [ ] **Persistencia en Base de Datos**
  - Migrar de arrays estáticos a MySQL/PostgreSQL
  - Implementar JPA/Hibernate
  - Crear scripts de migración

- [ ] **Sistema de Multas**
  - Calcular multas por retraso en devoluciones
  - Configuración de tarifas
  - Historial de multas por usuario

- [ ] **Reportes y Estadísticas**
  - Exportar a PDF/Excel
  - Gráficos de uso (libros más prestados, usuarios más activos)
  - Reportes por período de tiempo

- [ ] **Notificaciones**
  - Alertas de devolución próxima
  - Recordatorios automáticos
  - Sistema de email (opcional)

#### Media Prioridad
- [ ] **Búsqueda Avanzada**
  - Filtros múltiples combinados
  - Búsqueda por texto completo
  - Sugerencias automáticas

- [ ] **Gestión de Reservas**
  - Reservar libros prestados
  - Cola de espera
  - Notificación cuando esté disponible

- [ ] **Sistema de Roles y Permisos**
  - Roles: Administrador, Bibliotecario, Usuario
  - Permisos granulares
  - Auditoría de acciones

- [ ] **Historial de Préstamos**
  - Visualizar historial completo por usuario
  - Estadísticas personales
  - Libros favoritos

#### Baja Prioridad
- [ ] **Integración con Código de Barras**
  - Escaneo de libros y carnets
  - Generación de códigos de barras

- [ ] **API REST**
  - Exponer funcionalidades vía API
  - Documentación con Swagger
  - Posibilidad de aplicación móvil

- [ ] **Modo Oscuro**
  - Toggle entre tema claro y oscuro
  - Persistencia de preferencia

- [ ] **Internacionalización (i18n)**
  - Soporte para inglés
  - Framework de traducciones

- [ ] **Tests Unitarios**
  - JUnit para DAOs y Services
  - Cobertura de código > 70%
  - CI/CD con GitHub Actions

### 🔧 Mejoras Técnicas

- [ ] **Refactorización**
  - Eliminar métodos estáticos excesivos
  - Implementar inyección de dependencias
  - Mejorar manejo de excepciones

- [ ] **Validaciones**
  - Validación robusta de entrada
  - Mensajes de error claros
  - Prevención de inyección de datos

- [ ] **Logging**
  - Implementar SLF4J + Logback
  - Niveles de log configurables
  - Rotación de archivos de log

- [ ] **Configuración Externa**
  - Archivo properties para configuraciones
  - Separar configuración de código
  - Perfiles (desarrollo, producción)

## 👥 Créditos

### Equipo de Desarrollo

Este proyecto fue desarrollado como parte del curso de Programación Avanzada en la Universidad Nacional Pedro Ruiz Gallo (UNPRG).

**Desarrolladores**:
- **Mauricio** - Lógica de negocio y arquitectura
- **Fernandez** - Lógica de aplicación y servicios
- **Farro** - Diseño UI/UX e interfaces
- **Jack** - Modelos de datos y DAOs

### Recursos y Bibliotecas

- **JCalendar** - Toedter ([Website](https://toedter.com/jcalendar/))
- **Java Swing** - Oracle
- **NetBeans IDE** - Apache Software Foundation
- **Componentes UI personalizados** - Inspirados en [1BestCsharp](https://1bestcsharp.blogspot.com/)

### Inspiración y Referencias

- Arquitectura en capas: Martin Fowler - "Patterns of Enterprise Application Architecture"
- Patrones de diseño: Gang of Four - "Design Patterns"
- Java Swing Best Practices: Oracle Documentation

## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

```
MIT License

Copyright (c) 2025 PA_interfaz_unprg

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## 📞 Contacto y Soporte

### Reportar Problemas

Si encuentras un bug o tienes una sugerencia:
1. Verifica que no exista un issue similar
2. Crea un nuevo **Issue** en GitHub
3. Usa las etiquetas apropiadas: `bug`, `enhancement`, `question`

### Preguntas Frecuentes

**P: ¿Cómo cambio la base de datos de memoria a MySQL?**  
R: Actualmente el proyecto usa arrays estáticos. La migración a BD está planificada para v2.0. Puedes contribuir implementando el patrón Repository con JPA.

**P: ¿Puedo usar este proyecto para mi tesis/proyecto?**  
R: Sí, el proyecto es de código abierto bajo licencia MIT. Por favor, da crédito apropiado.

**P: ¿El sistema soporta múltiples bibliotecas?**  
R: No actualmente. Es un feature potencial para versiones futuras.

**P: ¿Cómo agrego nuevos tipos de usuarios?**  
R: Crea una nueva clase que extienda `Usuario` en `CapaLogica.modelos`, similar a `Estudiante` o `Docente`.

### Comunidad

- 💬 **Discusiones**: Usa la pestaña Discussions en GitHub
- 🐛 **Issues**: Para bugs y features
- 📧 **Email**: [Contacto del equipo - agregar email si aplica]

---

<div align="center">

**⭐ Si este proyecto te fue útil, considera darle una estrella en GitHub ⭐**

Hecho con ❤️ en la Universidad Nacional Pedro Ruiz Gallo

</div>

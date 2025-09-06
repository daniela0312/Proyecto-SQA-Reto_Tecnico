# Proyecto de Automatización SQA Datepicker

Este proyecto automatiza pruebas sobre un **Datepicker Web** usando **Serenity BDD + Cucumber + Selenium WebDriver**, aplicando **Screenplay Pattern**, herencia, polimorfismo y buenas prácticas en Java.

Hace parte del reto técnico de QA Automation Engineer con enfoque en **gobierno de automatizaciones** y buenas prácticas.

---

## Tecnologías utilizadas

- **Lenguaje**: Java 17
- **Build Tool**: Maven
- **Framework BDD**: Serenity BDD (v4.2.14)
- **Framework de pruebas**: JUnit + Cucumber (v7.18.1)
- **Automatización Web**: Selenium WebDriver (v4.27.0)
- **Gestión de Drivers**: WebDriverManager (v5.9.2)
- **Asserts**: AssertJ (v3.26.3)
- **Logger**: SLF4J Simple (v2.0.16)
- **IDE recomendado**: IntelliJ IDEA

---

## Estructura del proyecto
sqa-datepicker/
│── pom.xml                     # Configuración de dependencias y plugins
│── serenity.conf               # Configuración de Serenity
│── README.md                   # Documentación del proyecto
│
├── src
│   ├── main/java/co/daniela/sqa/   # Código base (si aplica)
│   └── test/java/co/daniela/sqa/reto
│       ├── runners/               # Clases Runner (JUnit + Cucumber + Serenity)
│       ├── stepdefinitions/       # Definición de pasos (Glue code)
│       ├── tasks/                 # Interacciones con la UI
│       ├── ui/                    # Page Objects / Elementos mapeados
│       └── questions/             # Validaciones Screenplay
│
└── src/test/resources/features/datepicker
└── seleccion_fecha.feature    # Archivos .feature en Gherkin

---

## Configuración

### 1. Requisitos previos
- [x] **Java 17+** (`java -version`)
- [x] **Maven 3.9+** (`mvn -version`)
- [x] **Google Chrome** actualizado
- [x] **Git** instalado (`git --version`)

### 2. Clonar y compilar el proyecto
```bash
git clone https://github.com/<tu-usuario>/sqa-datepicker.git
cd sqa-datepicker
mvn clean install -U

## Ejecución de pruebas

1. Ejecutar todas las pruebas
mvn clean verify
2. Ejecutar pruebas por etiquetas
mvn clean verify -Dcucumber.filter.tags="@smoke"
mvn clean verify -Dcucumber.filter.tags="@regression"
3. Generar y abrir reportes Serenity
open target/site/serenity/index.html

##Escenarios implementados
Feature: Selección de fechas en el Datepicker
	•	Selección de una fecha en el mes actual.
	•	Selección de una fecha en un mes distinto.
	•	Selección de fechas inválidas (pruebas negativas).
	•	Validación del campo con el valor esperado.

##Reportes de ejecución

Los reportes de Serenity contienen:
-Escenarios ejecutados
- Capturas de pantalla por paso
- Métricas de ejecución
- Historias de usuario asociadas

Se generan en:
target/site/serenity/index.html

## Convenciones de código y commits
	•	Patrón: Screenplay Pattern
	•	Lenguaje: nombres de clases/métodos en inglés
	•	Commits: Conventional Commits
	
## Calidad de código (SonarQube)
	El proyecto incluye verificación de calidad de código mediante SonarQube.
Ejemplo de ejecución local:
mvn sonar:sonar -Dsonar.projectKey=sqa-datepicker -Dsonar.host.url=http://localhost:9000 -Dsonar.login=<token>

##Autor

##Daniela Fabra Valencia
QA Automation Engineer | Especialista en pruebas con Java + Selenium + Cucumber + Serenity
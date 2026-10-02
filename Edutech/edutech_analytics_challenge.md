# EduTech Analytics Core - Java Challenge

Benvingut/da al repositori del projecte **EduTech Analytics Core**. Aquest és un projecte pràctic dissenyat per avaluar i posar a prova les teves capacitats de disseny d'arquitectura en **Java 17+**, aplicant programació orientada a objectes avançada, **Java Streams API**, **Collections Framework**, principis **SOLID** i bones pràctiques de **Clean Code**.

## Context i Objectiu

L'empresa *EduTech Solutions* necessita desenvolupar el nucli d'analítica i gestió de la seva plataforma d'aprenentatge en línia. El sistema ha de gestionar el domini d'**Estudiants**, **Cursos** i **Inscripcions**, oferint un conjunt d'informes i càlculs analítics en memòria.

El teu objectiu és dissenyar i implementar aquest nucli seguint una arquitectura neta, modular, extensible i fàcil de mantenir.

## Model de Domini (Data Model)

S'han de definir les següents entitats i valors de domini (es recomana l'ús de `record` o classes immutables quan sigui escaient):

1. **`Student`**

   * `id` (`String` o `UUID`): Identificador únic.

   * `name` (`String`): Nom complet.

   * `email` (`String`): Correu electrònic.

   * `hoursStudied` (`int`): Hores totals acumulades d'estudi a la plataforma.

2. **`Course`**

   * `id` (`String` o `UUID`): Identificador únic.

   * `title` (`String`): Títol del curs.

   * `instructor` (`String`): Nom de l'instructor/a.

   * `basePrice` (`Money`): Preu base del curs.

   * `category` (`CourseCategory`): Categoria del curs.

3. **`Enrollment`**

   * `id` (`String` o `UUID`): Identificador únic.

   * `student` (`Student`): Estudiant inscrit.

   * `course` (`Course`): Curs en el qual està inscrit.

   * `enrollmentDate` (`LocalDate`): Data de la inscripció.

   * `progressPercentage` (`double`): Progrés del curs (valor entre `0.0` i `100.0`).

   * `grade` (`Optional<Double>`): Nota final de l'estudiant. Present només si el curs està completat.

   * `status` (`EnrollmentStatus`): Estat de la inscripció.

4. **Value Objects i Enums**:

   * `Money`: Value Object immutable que conté un `BigDecimal amount` i un `Currency currency` (o `String`). Ha d'incloure operacions d'aritmètica bàsica (`add`, `multiply`).

   * `CourseCategory` (Enum): `IT`, `DESIGN`, `BUSINESS`, `MARKETING`, `HEALTH`.

   * `EnrollmentStatus` (Enum): `ACTIVE`, `COMPLETED`, `CANCELLED`.

   * `ActivityLevel` (Enum): `HIGH`, `MEDIUM`, `LOW`.

## Requisits Funcionals (`AnalyticsQueryService`)

Cal implementar un servei d'analítica (`AnalyticsQueryServiceImpl`) que extregui dades dels repositoris. **És obligatori utilitzar la Java Streams API i col·lectors avançats per a totes les operacions.** No es permet l'ús de bucles imperatius (`for`, `while`, `do-while`).

### Especificació dels Mètodes:

1. **Mitjana de qualificacions per categoria**:

   * **Signatura**: `Map<CourseCategory, Double> getAverageGradeByCategory()`

   * **Regla de negoci**: Filtra només les inscripcions amb estat `COMPLETED` que tinguin una qualificació present (`grade.isPresent()`). Calcula la mitjana de les notes per cada categoria de curs.

2. **Top N Cursos més populars**:

   * **Signatura**: `List<Course> getTopNCoursesByEnrollment(int limit)`

   * **Regla de negoci**: Compta el nombre d'inscripcions (`ACTIVE` o `COMPLETED`) per cada curs i retorna els $N$ cursos amb més alumnes, ordenats de major a menor popularitat.

3. **Estudiants en risc d'abandonament**:

   * **Signatura**: `Set<Student> getStudentsAtRisk(LocalDate referenceDate)`

   * **Regla de negoci**: Un estudiant es considera en risc si té almenys una inscripció activa (`ACTIVE`) realitzada fa més de 30 dies respecte a `referenceDate` i amb un progrés inferior al `20.0%`. Retorna el conjunt sense duplicats (`Set`).

4. **Agrupació d'estudiants per nivell d'activitat**:

   * **Signatura**: `Map<ActivityLevel, List<Student>> groupStudentsByActivity()`

   * **Regla de negoci**: Classifica els estudiants segons el seu valor `hoursStudied`:

     * `HIGH`: Més de 50 hores ($> 50$).

     * `MEDIUM`: Entre 20 i 50 hores incloses ($20 \le \text{hores} \le 50$).

     * `LOW`: Menys de 20 hores ($< 20$).

5. **Càlcul d'ingressos per instructor**:

   * **Signatura**: `Map<String, BigDecimal> calculateRevenueByInstructor(PricingStrategy strategy)`

   * **Regla de negoci**: Considera les inscripcions no cancel·lades (`ACTIVE` o `COMPLETED`). Aplica l'estratègia de preu (`PricingStrategy`) al preu base del curs i agrupa el total recaptat per cada instructor (`Course.instructor`).

## Principis de Disseny i Arquitectura (SOLID & Clean Code)

El codi serà avaluat minuciosament segons els següents principis:

* **Single Responsibility Principle (SRP)**: Separa clarament el domini, l'emmagatzematge de dades, la lògica de negoci i les regles de càlcul de preus.

* **Open/Closed Principle (OCP)**: El càlcul de preus ha d'utilitzar el **Patró Strategy** (`PricingStrategy`). S'han d'implementar almenys dues estratègies:

  1. `StandardPricingStrategy`: Aplica el preu base directament.

  2. `EarlyBirdDiscountStrategy`: Aplica un descompte percentual (ex. 15%) si la inscripció s'ha fet en una data determinada.
     *El servei ha de poder acceptar noves estratègies sense modificar el seu codi font.*

* **Liskov Substitution Principle (LSP)**: Les implementacions concretes dels repositoris han de ser totalment intercanviables sense alterar el comportament esperat del sistema.

* **Interface Segregation Principle (ISP)**: No creïs interfícies monolítiques. Separa les operacions de consulta analítica (`AnalyticsQueryService`) de les operacions de gestió o modificació (`EnrollmentManagementService`).

* **Dependency Inversion Principle (DIP)**: Els serveis d'alt nivell han de dependre exclusivament d'interfícies (`CourseRepository`, `EnrollmentRepository`), mai de classes concretes. Utilitza injecció de dependències via constructor.

* **Clean Code & Excepcions**:

  * Noms de variables, mètodes i classes expressius en anglès.

  * Lambdas curtes i llegibles; si una lambda supera les 2-3 línies, extreu la lògica a un mètode privat auxiliar.

  * Gestió d'erros mitjançant excepcions personalitzades de domini (ex: `EntityNotFoundException`, `InvalidDomainArgumentException`).

## Estructura de Paquets Requerida

El projecte ha de seguir estrictament la següent estructura modular dins de `src/main/java/`:

```
com.edutech.analytics/
│
├── domain/                         # Model de domini i entitats immutables
│   ├── model/
│   │   ├── Student.java
│   │   ├── Course.java
│   │   ├── Enrollment.java
│   │   ├── CourseCategory.java     # Enum
│   │   ├── EnrollmentStatus.java   # Enum
│   │   └── ActivityLevel.java      # Enum
│   └── valueobject/
│       └── Money.java              # Value object immutable
│
├── repository/                     # Interfícies d'accés a dades
│   ├── CourseRepository.java
│   ├── EnrollmentRepository.java
│   └── memory/                     # Implementacions en memòria utilitzant Collections
│       ├── InMemoryCourseRepository.java
│       └── InMemoryEnrollmentRepository.java
│
├── service/                        # Interfícies i serveis de negoci
│   ├── AnalyticsQueryService.java  # Interfície segons ISP (només consultes)
│   ├── EnrollmentManagementService.java # Interfície segons ISP (gestió)
│   └── impl/
│       ├── AnalyticsQueryServiceImpl.java
│       └── EnrollmentManagementServiceImpl.java
│
├── strategy/                       # Patró Strategy per a preus (OCP)
│   ├── PricingStrategy.java        # Interfície
│   ├── StandardPricingStrategy.java
│   └── EarlyBirdDiscountStrategy.java
│
└── exception/                      # Excepcions de domini
    ├── DomainException.java
    └── EntityNotFoundException.java

```

## Validació i Proves (`Main` o `Tests`)

Cal incloure una classe de prova (`AnalyticsQueryServiceTest` amb **JUnit 5**) o un punt d'entrada `Main` que realitzi les següents accions:

1. Crear i poblar els repositoris en memòria amb:

   * Almenys **5 cursos** de diferents categories i instructors.

   * Almenys **8 estudiants** amb diferents nivells d'hores d'estudi.

   * Almenys **12 inscripcions** combinant diferents estats (`ACTIVE`, `COMPLETED`, `CANCELLED`), diferents percentatges de progrés, dates i notes.

2. Instanciar el servei d'analítica injectant-li els repositoris via constructor.

3. Executar els 5 mètodes analítics i verificar-ne el resultat (mitjançant asseveracions `Assertions` o mostrant per pantalla els resultats de manera ordenada i llegible).

## Llista de Comprovarió (Checklist de Qualitat)

Abans de donar el retall per finalitzat, assegura't que:

* \[ \] No hi ha cap bucle `for`, `foreach` tradicional o `while` dins del servei d'analítica.

* \[ \] S'utilitzen col·lectors avançats de Streams (`Collectors.groupingBy`, `Collectors.averagingDouble`, `Collectors.toMap`, etc.).

* \[ \] Les entitats o records són immutables i incorporen validacions als constructors.

* \[ \] Totes les dependències s'injecten mitjançant interfícies a través dels constructors.

* \[ \] L'estructura de carpetes coincideix exactament amb l'especificada.

* \[ \] Tot el codi compila correctament en Java 17 o superior.
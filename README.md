# Kata 3 - Transformación de datos a JSON

## Objetivo

Practicar la transformación de datos externos JSON en objetos de dominio y la generación de una salida JSON propia y verificable.

La aplicación consulta la API de [Random User](https://randomuser.me/), transforma cada usuario recibido en un objeto `User` y genera el fichero `users.json`. La salida incluye tanto los usuarios válidos como un resumen con el número de registros válidos y descartados.

## Requisitos

- JDK 25.
- Maven para compilar desde terminal, o IntelliJ IDEA con soporte Maven.
- Conexión a Internet para consultar la API Random User.
- Gson 2.14.0, gestionado por Maven.

## Estructura del proyecto

- `User` y `Gender`: modelo de dominio de un usuario.
- `UserExport`: resultado de la transformación; contiene los usuarios válidos y el número de registros descartados.
- `RawDataReader`: puerto de lectura que devuelve un `Reader`.
- `HttpRandomUserReader`: adaptador que obtiene el JSON desde la API mediante `HttpClient`.
- `UserDeserializer`: puerto que transforma la entrada externa en un `UserExport`.
- `JsonRandomUserDeserializer`: adaptador que parsea el JSON, valida los campos necesarios y crea objetos `User`.
- `UserDto`: Data Transfer Object que representa la estructura del JSON externo para Gson.
- `UserJsonSerializer`: puerto de escritura del JSON final.
- `GsonUserJsonSerializer`: adaptador que genera el JSON de salida en un `OutputStream`.
- `Main`: conecta lectura HTTP, deserialización y serialización.

## Compilación y ejecución

### Desde IntelliJ IDEA

1. Abrir `pom.xml` como proyecto Maven.
2. Configurar JDK 25 tanto para el proyecto como para el módulo `kata3`.
3. Recargar el proyecto Maven.
4. Ejecutar `Main.java`.

### Desde terminal

Con Java 25 y Maven disponibles, ejecutar desde la raíz:

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass=software.ulpgc.katas.Main
```

El programa creará o sobrescribirá `users.json` en el directorio de trabajo.

## Entrada y salida esperada

La entrada procede de:

```text
https://randomuser.me/api/?results=10
```

Cada elemento de `results` contiene, entre otros, los campos `gender`, `name` y `dob`. Un usuario con los campos necesarios se transforma a este formato propio:

```json
{
  "name": "Mrs Ada Lovelace",
  "gender": "female",
  "birthday": "1815-12-10T00:00:00Z"
}
```

El fichero generado mantiene una colección de usuarios y un bloque de metadatos:

```json
{
  "users": [
    {
      "name": "Mrs Ada Lovelace",
      "gender": "female",
      "birthday": "1815-12-10T00:00:00Z"
    }
  ],
  "metadata": {
    "validRecords": 1,
    "discardedRecords": 0
  }
}
```

Los registros incompletos, con campos vacíos o con valores no válidos se descartan durante la transformación y se contabilizan en `discardedRecords`.

## Verificación

La verificación manual consiste en:

1. Ejecutar la aplicación con conexión a Internet.
2. Comprobar que se imprimen los usuarios válidos por consola.
3. Abrir `users.json` y verificar que puede leerse como JSON válido.
4. Verificar que `metadata.validRecords` coincide con el número de objetos de `users`.
5. Verificar que un registro con datos obligatorios incorrectos no se convierte en `User` y aumenta `metadata.discardedRecords`.

## Repeticiones y Git

La kata se ha repetido en las ramas `develop`, `develop2` y `develop3`. La versión final está en `develop3`.

## Vídeo explicativo
https://drive.google.com/file/d/1niaISzteGLnsvn8GQYctn9vxrCBWm-OP/view?usp=sharing
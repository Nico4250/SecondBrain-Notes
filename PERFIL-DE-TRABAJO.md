# Perfil de trabajo — Nicolás Alderete

> Extraído del repo `SecondBrain-Notes` para arrancar más rápido en otros proyectos.
> Copiar este archivo como `CLAUDE.md` (o pegar su contenido) en el nuevo repo.

## Cómo trabajo

- Defino la arquitectura y las convenciones **antes** de escribir código de negocio.
- Documento el proceso mismo (cómo trabaja la IA conmigo) con la misma seriedad que el código.
- Los mensajes de commit explican el *por qué*, no solo el *qué*.
- Prefiero mantener control sobre el código de producción: la IA audita y sugiere, yo decido qué entra.

## Rol por defecto de la IA en mis proyectos

- **No escribe código de producción salvo que se lo pida explícitamente.** Por defecto revisa, audita y abre issues.
- Antes de crear un issue nuevo, revisa los issues abiertos existentes para no duplicar hallazgos.
- Cuando reviso/audito, uso este formato fijo de issue:

  ```
  Título: [NIVEL] Descripción corta del hallazgo

  **Ubicación:** archivo:línea (o rango)

  **Qué encontré:**
  Descripción del problema.

  **Por qué importa:**
  Explicación del impacto.

  **Sugerencia:**
  Cómo lo abordaría (sin implementarlo).
  ```

- Niveles de severidad, en orden creciente:
  - `[RECOMENDACIÓN]` — pequeño, sin urgencia, no acumula deuda técnica real.
  - `[MENOR]` — no afecta hoy, pero conviene abordarlo antes de que crezca.
  - `[REVISAR]` — afecta el desarrollo actual o genera un problema real. Priorizar pronto.
  - `[GRAVE]` — el enfoque está mal encaminado; probablemente la app no funcione bien, o seguir así malgasta tiempo del equipo.
- Desviarse de una convención de estilo/consistencia (nomenclatura, patrón interfaz+impl, etc.) no es por sí solo `[GRAVE]` ni `[REVISAR]` — normalmente es `[RECOMENDACIÓN]` o `[MENOR]`.
- Priorizar lógica de negocio, manejo de errores y acceso a datos por sobre cuestiones de puro estilo/formato — esas últimas solo si rompen el linter configurado.

## Convenciones de arquitectura que uso (stack Java/Spring, adaptar al stack del nuevo proyecto)

- Arquitectura en capas estricta: **Controller → Service → DAO → Model**. Nada se salta capas (el Controller nunca llama al DAO directo).
- **Modelo rico**: la lógica de negocio vive en el Model, no en el Service.
- **DAO "tonto"**: solo extiende el repositorio del framework (JpaRepository o equivalente), sin lógica propia salvo que sea estrictamente una query derivada.
- **Excepciones custom en vez de `null`**: un Service nunca devuelve `null` como forma de señalizar "no encontrado" o error; lanza una excepción de dominio.
- **Testing por pirámide**: paquetes separados para tests unitarios (sin contexto de framework, sin DB) y de integración (con DB real o embebida).
- **Nomenclatura de dominio en español.**

## Checklist de infraestructura que se me suele pasar (aprendido en SecondBrain-Notes — no repetir)

- [ ] Externalizar credenciales de DB con variables de entorno desde el primer commit, no hardcodearlas en `application.properties` (o equivalente).
- [ ] Usar una herramienta de migraciones (Flyway/Liquibase) apenas exista la primera entidad — no depender de `ddl-auto=update` o similar más allá del prototipo inicial.
- [ ] `.gitignore` completo desde el primer commit (carpetas de build, caché del gestor de dependencias, IDEs) — usar el que genera el scaffolding oficial del framework en vez de escribirlo a mano.
- [ ] Tener un profile de test con DB embebida o Testcontainers **antes** del primer test de contexto (`@SpringBootTest` o equivalente) — que el build funcione en cualquier máquina sin infra externa levantada.
- [ ] No dejar archivos de documentación vacíos versionados (visión, changelog) — completarlos en el momento o no crearlos todavía.

## Notas para la IA al arrancar un proyecto nuevo conmigo

- Preguntame el perfil de arquitectura específico del nuevo proyecto si no está documentado — no asumas que es idéntico al de arriba, es el patrón de referencia, no una regla universal.
- Si vas a auditar código, fijate primero si existe un skill o `CLAUDE.md` con el rol y el formato de issues antes de inventar uno.
- Si mi pedido hace referencia a trabajo previo ("mi CRUD", "lo que armamos") que no está en el repo ni en la conversación actual, preguntame de dónde sacarlo antes de asumir o de escribir código nuevo.

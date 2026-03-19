## Quick orientation

- Project type: Java Spring Boot (Maven) application. Key artifacts live in two places: the repository root and the `java-maven-app/` subfolder. The codebase uses Java 17 and Spring Boot 3.5.5 (see `pom.xml`).
- Main entry points: `src/main/java/com/example/Application.java` and `java-maven-app/src/main/java/com/example/Application.java` (same structure duplicated). Unit tests live under `java-maven-app/src/test/java/com/example/AppTest.java`.

## What an AI agent should know first

- Build/test/run: use Maven. Typical commands (run from repo root or the module folder):
  - Build & package: `mvn -f java-maven-app/pom.xml clean package` (or `mvn clean package` when working in `java-maven-app/`).
  - Run tests: `mvn -f java-maven-app/pom.xml test`.
  - Run the app locally: `mvn -f java-maven-app/pom.xml spring-boot:run`.
- Java version: 17 (set in `maven-compiler-plugin` in `pom.xml`).

## CI / deployment hooks to watch

- Top-level CI: `Jenkinsfile` (root) loads `script.groovy` and calls helper functions: `buildJar()`, `buildImage()`, `deployApp()`.
  - `script.groovy` implements: `sh 'mvn package'`, `docker build` + `docker push` and expects Jenkins credential id `docker-hub-repo` for DockerHub pushes.
- Module CI: `java-maven-app/Jenkinsfile` is a simpler, parameterized pipeline (VERSION / executeTests). It only echoes stages currently, but it's the CI surface for this module and may be extended.
- Docker: there are `Dockerfile` entries at the repository root and `java-maven-app/Dockerfile`. The groovy helper builds an image tagged `nanatwn/demo-app:jma-2.0` and pushes to Docker Hub; credential id is `docker-hub-repo`.

## Project-specific conventions & patterns

- Duplicate sources: root and `java-maven-app/` both contain `src/main/resources/static/index.html` and `Application.java`. Prefer working against `java-maven-app/` because tests and module-specific Jenkinsfile are colocated there. (If you plan changes, ask which location is canonical.)
- Minimal Spring Boot app: `Application` class contains `main()` and a `getStatus()` helper used by `AppTest` (simple unit test, JUnit4).
- Logging: `pom.xml` includes `net.logstash.logback:logstash-logback-encoder` — logs may assume JSON formatting in production.

## Typical developer flows (explicit)

- Local dev quick-check:
  1. Ensure Java 17 is on PATH: `java -version`.
  2. Build and run tests: `mvn -f java-maven-app/pom.xml clean test`.
  3. Run app locally: `mvn -f java-maven-app/pom.xml spring-boot:run` and open `http://localhost:8080/` if you add controllers/static endpoints.
- Building image locally (mirrors CI): run from repo root (script uses root context):
  - `docker build -t <your-repo>/demo-app:local .`
  - Log in: `docker login` and `docker push <your-repo>/demo-app:local` (CI uses credential id `docker-hub-repo`).

## Files an agent will commonly edit or inspect

- `pom.xml` (root and `java-maven-app/pom.xml`) — dependency/version and compiler configuration (Java 17).
- `java-maven-app/src/main/java/com/example/Application.java` — app entrypoint and simple logic used by tests.
- `java-maven-app/src/test/java/com/example/AppTest.java` — existing unit test to keep green when refactoring.
- `script.groovy` — imperative CI helper used by root `Jenkinsfile` (contains `mvn package` and `docker` steps). Update carefully.
- `Jenkinsfile` (root) and `java-maven-app/Jenkinsfile` — CI pipelines. Root pipeline delegates to `script.groovy`.

## Editing guidance for AI changes

- Keep Java target/source at 17 unless instructed otherwise; update `maven-compiler-plugin` in both poms if changing.
- If modifying Docker or CI behavior, update `script.groovy` and both `Jenkinsfile`s together to keep pipelines consistent.
- When adding tests, place unit tests under `java-maven-app/src/test/java/...` and use JUnit4 style to match existing tests.
- When changing public APIs (package `com.example.Application`), run `mvn -f java-maven-app/pom.xml test` to validate `AppTest` and avoid breaking CI.

## Known gaps / assumptions (ask the human)

- There are duplicate source trees at repo root and `java-maven-app/`. I assume `java-maven-app/` is the canonical module for CI and development because it contains tests and a module-level `Jenkinsfile`. Confirm which path should be the primary target for edits.
- CI expects a Jenkins credential `docker-hub-repo` for pushing images. If adding real publish steps, confirm credential IDs and repository tags.

If you'd like, I can update this file with any additional project conventions you want enforced (coding style, module ownership, or canonical source location). Confirm whether `java-maven-app/` is the canonical module and I'll adapt the doc.

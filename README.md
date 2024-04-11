# Projeto Codegroup

Este é um projeto Java Spring Boot, que utiliza Maven como ferramenta de build e SonarQube para análise de qualidade de código.

## Pré-requisitos

- Java 17
- Maven
- SonarQube
- Docker

## Como executar o Docker Compose

Para iniciar o SonarQube e o banco de dados Postgres usando Docker Compose, você deve ter o Docker e o Docker Compose instalados em sua máquina. Depois disso, você pode usar o seguinte comando no terminal:

```bash
docker-compose up -d
```

Será necessário entrar no portal com o endereço `http://localhost:9000` e fazer login com as credenciais padrão `admin` e `admin`.

Será necessário criar um novo projeto no SonarQube com a chave `codegroup-projects` e gerar um token de acesso para o projeto.


## Como testar

Para executar os testes do projeto, use o seguinte comando no terminal:

```bash
mvn test
```

## Como construir

Para construir o projeto, use o seguinte comando no terminal:

```bash
mvn clean package
```

## Como executar

Para executar o projeto, primeiro construa o projeto com o comando acima, depois execute o seguinte comando:

```bash
java -jar target/projects-0.0.1-SNAPSHOT.jar
```

Verifique o nome do arquivo JAR gerado na pasta `target`.

## Como executar o SonarQube

Para executar a análise do SonarQube, primeiro certifique-se de que o SonarQube está em execução. Em seguida, use o seguinte comando:

```bash
mvn sonar:sonar \
-Dsonar.projectKey=codegroup-projects \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.login=sqp_a3da92900bbc0e0b5e27f877187d7ead6a60e9db
```

Substitua os valores de `sonar.projectKey` e `sonar.login` conforme necessário.

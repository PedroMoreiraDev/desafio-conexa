# Use uma imagem do Maven com o Eclipse Temurin JDK 21
FROM maven:3.9.5-eclipse-temurin-21 AS builder

# Diretório de trabalho no container
WORKDIR /app

# Copie os arquivos do projeto para o container
COPY . .

# Compile o projeto, garantindo compatibilidade de bibliotecas
RUN mvn clean package -DskipTests

# Use uma imagem do Eclipse Temurin JDK para execução (menor e mais leve)
FROM eclipse-temurin:21-jdk-jammy

# Diretório de trabalho no container
WORKDIR /app

# Copie o JAR gerado pela build
COPY --from=builder /app/target/*.jar app.jar

# Exponha a porta configurada
EXPOSE 8090

# Comando para executar a aplicação
CMD ["java", "-jar", "app.jar"]

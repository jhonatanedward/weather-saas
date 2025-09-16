#!/bin/bash

echo "Iniciando o build da aplicação..."
cd ./auth-service
./gradlew bootJar

# Verifica se o build foi bem-sucedido
if [ $? -eq 0 ]; then
    echo "Build concluído com sucesso. Iniciando o Docker Compose..."
    # A flag --build força a reconstrução da imagem
    docker-compose up --build
else
    echo "Falha no build da aplicação. Abortando a execução."
    exit 1
fi
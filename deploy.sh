#!/bin/bash

# Nombre de la imagen y versión
IMAGE_NAME="bank-clients"
IMAGE_VERSION="latest"

echo "Construyendo el servicio"
./gradlew clean build -x test

if [ $? -ne 0 ]; then
    echo "Error"
    exit 1
fi

echo "Construyendo la imagen Docker: $IMAGE_NAME:$IMAGE_VERSION"
docker build -t $IMAGE_NAME:$IMAGE_VERSION .

if [ $? -ne 0 ]; then
    echo "Error en la construcción de la imagen"
    exit 1
fi

echo "Levantando los servicios con Docker Compose..."
#docker-compose up

echo "Despliegue completado. La aplicación está corriendo."

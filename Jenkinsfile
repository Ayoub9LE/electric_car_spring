pipeline {
    agent any

    tools{
        maven 'maven'
        jdk 'java17'
    }

    triggers {
        pollSCM '* * * * *'
    }

    stages{
        stage('Build backend and test it') {
                    steps {
                       bat "mvn clean install -DskipTests"
                       bat "mvn test"
                    }
                }
        stage('Build Docker Image'){
                    steps{
                        bat "docker build -t Ayoub9LE/electric_car_spring:latest"
                    }
        }
        stage('Push Backend Docker Image to the Docker Hub'){
                    steps{
                        withCredentials([usernamePassword(credentialsId: 'DOCKERHUB_CREDENTIALS', passwordVariable: 'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKERHUB_USERNAME')]) {
                            bat "docker login -u $DOCKERHUB_USERNAME -p $DOCKERHUB_PASSWORD"
                            bat "docker pull $DOCKERHUB_USERNAME/electric_car_spring:latest"
                            bat "docker push $DOCKERHUB_USERNAME/electric_car_spring:latest"
                        }

                    }
        }
    }
}
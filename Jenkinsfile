pipeline {
    agent any

    tools{
        maven 'maven'
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
                        bat "docker build -t ayoub9le/electric_car_spring:latest ."
                    }
        }
        stage('Push Backend Docker Image to the Docker Hub'){
                    steps{
                        withCredentials([usernamePassword(credentialsId: 'DOCKERHUB_CREDENTIALS', passwordVariable: 'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKERHUB_USERNAME')]) {
                            bat "docker login -u $DOCKERHUB_USERNAME -p $DOCKERHUB_PASSWORD"
                            bat "docker pull ayoub9le/electric_car_spring:latest"
                            bat "docker push ayoub9le/electric_car_spring:latest"
                        }

                    }
        }
    }
}
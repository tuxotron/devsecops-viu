pipeline {
    agent any
    stages {
        stage('Compiling application') {
            steps {
                sh './mvnw clean compile'
            }
        }

        stage('Running static analysis') {
            steps {
                sh './mvnw spotbugs:check'
            }
        }

        stage('Check dependencies') {
            steps {
                sh './mvnw verify'
            }
        }

        stage('Build docker image') {
            steps {
                sh 'docker build -t viu/devsecops .'
            }
        }
    }
}
pipeline {
    agent any
    stages {
        stage('Compiling application') {
            steps {
                sh './mvnw clean compile'
            }
        }
        stage('Running unit tests') {
            steps {
                sh './mvnw test'
            }
        }

        stage('Running static analysis') {
            steps {
                sh './mvnw spotbugs:check'
            }
        }
    }
}
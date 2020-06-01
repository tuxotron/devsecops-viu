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
    }
}
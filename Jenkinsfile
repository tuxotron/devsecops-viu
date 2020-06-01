pipeline {
    agent any
    stages {
        stage('Compiling application') {
            steps {
                sh './mvnw clean compile'
            }
        }
    }
}
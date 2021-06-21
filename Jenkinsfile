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

        stage('Check dependencies') {
            steps {
                sh './mvnw verify'
            }
        }

        stage('Build docker image') {
            steps {
                sh 'docker build -t tuxotron/devsecops:v1 .'
            }
        }

        stage('Scan docker image') {
            steps {
                sh 'clair-scanner --ip 172.16.249.2 tuxotron/devsecops:v1 || exit 0'
            }
        }
    }
}
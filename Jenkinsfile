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
                sh 'docker build -t tuxotron/devsecops:v1 .'
            }
        }

        stage('Push docker image') {
            steps {
                sh 'kind load docker-image tuxotron/devsecops:v1'
            }
        }

        stage('Deploy to kubernetes') {
            steps {
                sh 'kind export kubeconfig'
                sh 'kubectl apply -f kube/'
            }
        }
    }
}
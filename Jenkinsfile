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
                sh 'clair-scanner --ip 172.16.249.128 tuxotron/devsecops:v1'
            }
        }

//         stage('Push docker image') {
//             steps {
//                 sh 'kind load docker-image tuxotron/devsecops:v1'
//             }
//         }
//
//         stage('Deploy to kubernetes') {
//             steps {
//                 sh 'kind export kubeconfig'
//                 sh 'kubectl apply -f kube/'
//             }
//         }
//
//         stage('sqlmap scanning') {
//             steps {
//                 sh 'PORT=$(kubectl get svc -o json | jq .items[0].spec.ports[0].nodePort); sqlmap --answers="follow=Y" --batch -u http://172.18.0.2:$PORT/user'
//             }
//         }
    }
}
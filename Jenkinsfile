pipeline {
    agent {
        kubernetes {
            cloud 'k8s'  // Cloud agent name
            defaultContainer 'k8s-agent'   // Cloud Agent Template Container name 
            retries 2
        }
    }
    stages {
        stage('Main') {
            steps {
                sh 'hostname'
            }
        }
    }
}


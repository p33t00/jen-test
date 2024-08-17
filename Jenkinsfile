ipeline {
    agent {
        kubernetes {
            cloud 'k8s'  // Cloud agent name
            defaultContainer 'k8s-agent'   // Cloud Agent Template Container name 
            retries 2
        }
    }
    stages {
        stage('Build') {
            steps {
                git([url: 'git://github.com/p33t00/jen-test.git', branch: 'main'])
            }
        }
        stage('Main') {
            steps {
                sh 'hostname'
		sh 'ls -lah'
            }
        }
    }
}


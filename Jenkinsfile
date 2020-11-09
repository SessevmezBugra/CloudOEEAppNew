pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build-War') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Build-Docker') {
            steps {
                sh 'docker-compose build'
            }
        }
    }
}
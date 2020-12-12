pipeline {

    agent any

    stages {

        stage('auth-service') {
            steps {
                build job: 'pipeline-auth-service', propagate: true, wait: true
            }
        }

        stage('config-service') {
            steps {
                build job: 'pipeline-config-service', propagate: true, wait: true
            }
        }

        stage('confirmation-service') {
            steps {
                build job: 'pipeline-confirmation-service', propagate: true, wait: true
            }
        }

        stage('eureka-server') {
            steps {
                build job: 'pipeline-eureka-server', propagate: true, wait: true
            }
        }

        stage('gateway-service') {
            steps {
                build job: 'pipeline-gateway-service', propagate: true, wait: true
            }
        }

        stage('maindata-service') {
            steps {
                build job: 'pipeline-maindata-service', propagate: true, wait: true
            }
        }

        stage('order-service') {
            steps {
                build job: 'pipeline-order-service', propagate: true, wait: true
            }
        }

        stage('stock-service') {
            steps {
                build job: 'pipeline-stock-service', propagate: true, wait: true
            }
        }

        stage('frontend-app') {
            steps {
                build job: 'pipeline-frontend-app', propagate: true, wait: true
            }
        }

        stage('deploy') {
            steps {
                build job: 'pipeline-deploy-app', propagate: true, wait: true
            }
        }

    }
}

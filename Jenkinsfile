pipeline {
    agent any
    tools {
        maven 'maven_3.6.3'
    }
    stages {
        stage ('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
    }
}
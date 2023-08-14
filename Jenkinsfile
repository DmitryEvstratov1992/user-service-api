pipeline {
    agent any
    tools {
        maven 'maven_3.6.3'
        jdk 'JDK_19'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}
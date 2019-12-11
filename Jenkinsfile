#!/bin/env groovy


pipeline {
    agent any

    stages {
        stage('Build and Test') {
            steps {
                sh './gradlew clean test'
            }
        }
    }

}


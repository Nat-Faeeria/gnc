pipeline {
  agent any
  stages {
    stage('Compile') {
      agent {
        docker {
          image 'java:8-jdk-alpine'
        }
        
      }
      steps {
        sh 'javac -d . -sourcepath src  src/gnc/main/GNC.java'
        sh 'ls'
      }
    }
  }
}
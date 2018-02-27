pipeline {
  agent any
  stages {
    stage('Compile') {
      steps {
        sh 'javac -d bin -sourcepath src  src/gnc/main/GNC.java'
      }
    }
  }
}
pipeline {
    agent any
    tools {
        maven 'maven-3.9'
    }

    stages {
        stage('build jar') {
            steps {
                script {
                    echo "building the application ..."
                    def myScript = load 'java-maven-app/script.groovy'
                    
                    myScript.buildJar()
                    echo " testing webhook integration..."
                } 
            } 
        }

        stage('build image') {
            steps {
               /* script {
                    // echo "building the docker image ..."
                    echo "building the docker image to nexus..."
                    def myScript = load 'java-maven-app/script.groovy'
                    
                    dir('java-maven-app') {
                        myScript.buildImage()
                    }*/

                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh 'docker build -t njogud/demo-app:jma2.0 .'
                        sh "echo \$PASS | docker login -u \$USER --password-stdin"
                        sh 'docker push njogud/demo-app:jma2.0'
                    }
                } 
            } 
        }

        stage('deploy') {
            steps {
                script {
                    echo "deploying the application ..."
                }
            } 
        }
    }
}

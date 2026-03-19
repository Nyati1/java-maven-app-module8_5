pipeline {
    agent any
    tools {
        maven 'maven-3.9'
    }

    stages { // <--- Added this missing block
        stage('Debug Workspace') {
            steps {
                script {
                    echo "Checking application structure..."
                    
                    // 1. Force the submodule to actually download its files
                    sh "git submodule update --init --recursive"
                    
                    // 2. List the files to confirm they exist now
                    sh "ls -R" 
                    
                    // 3. Now find the POM and print the script
                    sh "find . -maxdepth 3 -name pom.xml"
                    sh "cat java-maven-app/script.groovy"
                } 
            } 
        }

        stage('build jar') {
            steps {
                script {
                    echo "building the application ..."
                    def myScript = load 'java-maven-app/script.groovy'
                    
                    myScript.buildJar()
                } 
            } 
        }

        stage('build image') {
            steps {
                script {
                    // echo "building the docker image ..."
                    echo "building the docker image to nexus..."
                    def myScript = load 'java-maven-app/script.groovy'
                    
                    dir('java-maven-app') {
                        myScript.buildImage()
                    }

                    /*withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh 'docker build -t njogud/demo-app:jma2.0 .'
                        sh "echo \$PASS | docker login -u \$USER --password-stdin"
                        sh 'docker push njogud/demo-app:jma2.0'
                    }*/
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

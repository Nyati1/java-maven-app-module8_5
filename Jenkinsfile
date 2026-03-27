pipeline {
    agent any
     triggers {
        githubPush()
    }
    tools {
        maven 'maven-3.9'
    }

    stages { 
        stage('build jar') {
            steps {
                script {
                    sh "ls -la"
                    echo "building the application ..."
                
                    def myScript = load 'script.groovy'
                    
                    myScript.buildJar()
                } 
            } 
        }

        stage('build image') {
            steps {
                script {
                    echo "building the docker image ..."
                    echo "Testing the web--hook configurations ...."
                    echo "Testing the web--hook configurations ...."
                    /*script {
                        echo "building the docker image ....."
                        echo "building the docker image to nexus..."
                        def myScript = load 'script.groovy'
                        
                        // dir('java-maven-app') {
                            myScript.buildImage()
                        // }
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

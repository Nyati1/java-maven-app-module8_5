
pipeline {
    agent any
    tools {
        maven 'maven-3.9'
    }

    stages {
        stage('build jar') {
          
            steps {
                dir('java-maven-app') {
                script{
                    echo "building the application ..."
                    sh "mvn package"
                }
                }
            }
        }
        
        stage('build image'){
            steps {
               script{
                       // echo "building the docker image ..."
                   echo "building the docker image via external script..."
            
            // 1. Load the Groovy script into a variable
            def myScript = load 'script.groovy'
            
            // 2. Call the function defined in script.groovy
            myScript.buildImage()
    //withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
      //  sh 'docker build -t njogud/demo-app:jma2.0 .'
       // sh "echo \$PASS | docker login -u \$USER --password-stdin"
       // sh 'docker push njogud/demo-app:jma2.0'
   // } 
                }
            }  
        }
        stage('deploy'){
             steps {
                  script{
                        echo "deploying the application ..."
                }
            }  
        }
    }
}
    

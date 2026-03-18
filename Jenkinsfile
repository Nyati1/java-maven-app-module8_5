
pipeline {
    agent any
    tools {
        maven 'maven-3.9'
    }

    stages {
        stage('build jar') {
          
            steps {
                script{
                    echo "building the application ..."
                    sh "mvn package"
                }
            }
        }
        stage('build image'){
            steps {
                script{
                        echo "building the docker image ..."
                            /*sh 'docker build -t njogud/demo-app:jma2.0 .'
                            sh 'docker login -u njogud --password David@123'
                            sh 'docker push njogud/demo-app:jma2.0'   */
                    // 1. Securely pull the credentials from Jenkins
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        
        // 2. Build the image using the subfolder context
        sh 'docker build -t njogud/demo-app:jma2.0 -f java-maven-app/Dockerfile java-maven-app/'
        
        // 3. Login using double quotes and backslashes to inject the real password
        sh "echo \$PASS | docker login -u \$USER --password-stdin"
        
        // 4. Push the image
        sh 'docker push njogud/demo-app:jma2.0'
    } // This bracket closes the withCredentials block
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
    

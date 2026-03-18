/*def buildApp() {
    echo 'building the application...'
  
}

def testApp() {
    echo 'testing the application...'
   
}

def deployApp() {
    echo 'deploying the application...'
    echo "deploying version ${params.VERSION}"
  
}*/

def buildJar() {
    echo 'building the application...'
    // Build the module explicitly so artifacts land in java-maven-app/target
    sh 'mvn -f java-maven-app/pom.xml clean package'
}

def buildImage() {
    echo "building the docker image..."
  //  withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        
        // 1. Point to the subfolder where the Dockerfile and target/ folder actually live
        // The '.' at the end tells Docker to use 'java-maven-app' as the context
        sh 'docker build -t njogud/demo-app:jma2.0 -f java-maven-app/Dockerfile java-maven-app/'
        
        // 2. Use double quotes and backslashes to pass the credentials to the shell correctly
       // sh "echo \$PASS | docker login -u \$USER --password-stdin"
        sh 'docker login -u njogud --password David@123'
        
        // 3. Push the image
        sh 'docker push njogud/demo-app:jma2.0'
    }

/*def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t njogud/demo-app:jma-2.0 .'
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh 'docker push njogud/demo-app:jma-2.0'
    }*/


def deployApp() {
    echo 'deploying the application...'
}

return this

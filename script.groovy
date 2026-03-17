def buildApp() {
    echo 'building the application...'
  
}

def testApp() {
    echo 'testing the application...'
   
}

def deployApp() {
    echo 'deploying the application...'
    echo "deploying version ${params.VERSION}"
  
}





/*def buildJar() {
    echo 'building the application...'
    // Build the module explicitly so artifacts land in java-maven-app/target
    sh 'mvn -f java-maven-app/pom.xml clean package'
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t nanatwn/demo-app:jma-2.0 .'
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh 'docker push nanatwn/demo-app:jma-2.0'
    }
}

def deployApp() {
    echo 'deploying the application...'
}*/

return this

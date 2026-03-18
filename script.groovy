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
    sh 'mvn -f java-maven-app/pom.xml clean package'
}

def buildImage() {
    echo "building the docker image..."
    
    // 1. Build using the subfolder context
    sh 'docker build -t njogud/demo-app:jma2.0 -f java-maven-app/Dockerfile java-maven-app/'
    
    // 2. Hardcoded login
    sh 'docker login -u njogud -p David@123'
    
    // 3. Push the image
    sh 'docker push njogud/demo-app:jma2.0'
} // <--- THIS is the only bracket you need to close the function

/* The commented out section is fine as it is */

def deployApp() {
    echo 'deploying the application...'
}

return this


/*def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t njogud/demo-app:jma-2.0 .'
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh 'docker push njogud/demo-app:jma-2.0'
    }*/





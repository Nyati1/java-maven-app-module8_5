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
} 

def deployApp() {
    echo 'deploying the application...'
}

return this

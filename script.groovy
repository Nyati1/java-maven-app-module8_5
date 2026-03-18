def buildJar() {
    echo 'building the application...'
    // Build the module using the pom.xml in the current directory
    sh 'mvn clean package'
}

def buildImage() {
    echo "building the docker image..."
    
    // Securely pull credentials from Jenkins Global Credentials
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        
        // 1. Build the image using the current directory (.) as context
        // This assumes your Dockerfile and target/ folder are in the same directory
        sh 'docker build -t njogud/demo-app:jma2.0 .'
        
        // 2. Login using double quotes and escaped dollar signs to inject the real password
        sh "echo \$PASS | docker login -u \$USER --password-stdin"
        
        // 3. Push the image to Docker Hub
        sh 'docker push njogud/demo-app:jma2.0'
    }
}

def deployApp() {
    echo 'deploying the application...'
    // Add your deployment commands here (e.g., docker-compose or kubectl)
}

return this

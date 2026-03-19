def buildJar() {
    echo 'building the application...'
    // Build the module using the pom.xml in the current directory
   // sh 'mvn clean package'
    sh 'mvn -f java-maven-app/pom.xml clean package'
}

/*def buildImage() {
    echo "building the docker image..."
    
    // Securely pull credentials from Jenkins Global Credentials
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t njogud/demo-app:jma2.0 .'
        sh "echo \$PASS | docker login -u \$USER --password-stdin"
        sh 'docker push njogud/demo-app:jma2.0'
    }
}*/
def buildImage() {
    echo "building and pushing to Nexus..."
    
    // 1. Define your Nexus URL
    def nexusUrl = "159.203.37.26:8083" // Or your Nexus domain name
    def imageName = "${nexusUrl}/demo-app:jma2.0"

    withCredentials([usernamePassword(credentialsId: 'nexus-docker-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        
        // 2. Build the image with the Nexus URL in the name
        sh "docker build -t ${imageName} ."
        
        // 3. Login to your PRIVATE Nexus server
        sh "echo \$PASS | docker login -u \$USER --password-stdin ${nexusUrl}"
        
        // 4. Push to Nexus
        sh "docker push ${imageName}"
    }
}

def deployApp() {
    echo 'deploying the application...'
    // Add your deployment commands here (e.g., docker-compose or kubectl)
}

return this

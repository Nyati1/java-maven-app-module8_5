def buildJar() {
    echo 'building the application...'
    // Run maven normally since pom.xml is in the root
    // sh 'mvn -f java-maven-app/pom.xml clean package'
    sh 'mvn clean package'
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t njogud/demo-app:jma2.0 .'
        sh "echo \$PASS | docker login -u \$USER --password-stdin"
        sh 'docker push njogud/demo-app:jma2.0'
    }
}

/*def buildImage() {
    echo "building and pushing to Nexus..."
    
    def nexusUrl = "159.203.37.26:8083" 
    def imageName = "${nexusUrl}/demo-app:jma2.0"

    withCredentials([usernamePassword(credentialsId: 'nexus-docker-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        
        // Build looking at the current root directory (.)
        sh "docker build -t ${imageName} ."
        
        // Login to your PRIVATE Nexus server
        sh "echo \$PASS | docker login -u \$USER --password-stdin ${nexusUrl}"
        
        // Push to Nexus
        sh "docker push ${imageName}"
    }
}*/

def deployApp() {
    echo 'deploying the application...'
}

return this

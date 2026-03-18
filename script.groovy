def buildJar() {
    echo 'building the application...'
    sh 'mvn -f java-maven-app/pom.xml clean package'
}

def buildImage() {
    echo "building the docker image..."
    
    /*// 1. Build using the subfolder context
    sh 'docker build -t njogud/demo-app:jma2.0 -f java-maven-app/Dockerfile java-maven-app/'
    
    // 2. Hardcoded login
    sh 'docker login -u njogud -p David@123'
    
    // 3. Push the image
    sh 'docker push njogud/demo-app:jma2.0'*/
                   // 1. Securely pull the credentials from Jenkins
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        
        // 2. Build the image using the subfolder context
        sh 'docker build -t njogud/demo-app:jma2.0 -f java-maven-app/Dockerfile java-maven-app/'
        
        // 3. Login using double quotes and backslashes to inject the real password
        sh "echo \$PASS | docker login -u \$USER --password-stdin"
        
        // 4. Push the image
        sh 'docker push njogud/demo-app:jma2.0'
} 

def deployApp() {
    echo 'deploying the application...'
}

return this

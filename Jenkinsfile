pipeline {
    agent any
     triggers {
        githubPush()
    }
    tools {
        maven 'maven-3.9'
    }

    stages {
        stage ('increment version'){
            steps{
                script{
                    echo "incerementing app version..."
                    sh 'mvn build-helper:parse-version versions:set \
                    -DnewVersion=\\\${parseVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                    versions:commit'
                   def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                }
            }
        }
        stage('build jar') {
            steps {
                script {
                    
                    echo "building the application ..."
                
                    def myScript = load 'script.groovy'
                    
                    myScript.buildJar()
                } 
            } 
        }

        stage('build image') {
            steps {
                script {
                    echo "building the docker image ..."
                    echo "Testing the web--hook configurations ...."
                    echo "Testing the web--hook configurations ...."
                    /*script {
                        echo "building the docker image ....."
                        echo "building the docker image to nexus..."
                        def myScript = load 'script.groovy'
                        
                        // dir('java-maven-app') {
                            myScript.buildImage()
                        // }
                    }*/

                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                       // sh 'docker build -t njogud/demo-app:jma2.0 .'
                        sh "docker build -t njogud/demo-app:${IMAGE_NAME} ."
                        sh "echo \$PASS | docker login -u \$USER --password-stdin"
                        sh "docker push njogud/demo-app:${IMAGE_NAME}"
                    }
                } 
            } 
        }

        stage('deploy') {
            steps {
                script {
                    echo "deploying the application ..."
                }
            } 
        }
    } 
}

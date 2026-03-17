<<<<<<< HEAD
=======
def gv

>>>>>>> e7e580642daaed25028d8c4d7ef675e0fa63ac8d
pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', choices: ['1.1.0','1.2.0','1.3.0'], description:'')
        booleanParam(name: 'executeTests', defaultValue: true, description:'')
    }

    stages {
<<<<<<< HEAD
        stage('Build') {
          
            steps {
                echo 'This is the building stage'
            }
        }
        stage('Test') {
             when {
                    expression{
                        params.executeTests
                    }
=======
        stage('Init') {
            steps {
                script {
                    // This line is required to link gv to your groovy file
                    gv = load "script.groovy"
                }
            }
        }
        stage('Build') {
            steps {
                script {
                    gv.buildApp()
>>>>>>> e7e580642daaed25028d8c4d7ef675e0fa63ac8d
                }
          
            steps {
                echo 'This is the testing stage'
               
            }  
        }
<<<<<<< HEAD
         stage('Deploy') {
          
            steps {
                echo 'This is the deploying stage'
                echo "deploying version ${params.VERSION}"
            }
          
        }
    }
}
    
=======
        stage('Test') {
             when {
                expression {
                    params.executeTests
                }
            }
            steps {
                script {
                    gv.testApp()
                }
            }  
        }
         stage('Deploy') {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }
}
>>>>>>> e7e580642daaed25028d8c4d7ef675e0fa63ac8d

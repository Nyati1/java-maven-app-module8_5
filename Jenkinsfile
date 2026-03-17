pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', choices: ['1.1.0','1.2.0','1.3.0'], description:'')
        booleanParam(name: 'executeTests', defaultValue: true, description:'')
    }

    stages {
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
                }
          
            steps {
                echo 'This is the testing stage'
               
            }  
        }
         stage('Deploy') {
          
            steps {
                echo 'This is the deploying stage'
                echo "deploying version ${params.VERSION}"
            }
          
        }
    }
}
    

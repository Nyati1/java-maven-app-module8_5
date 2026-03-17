def gv

pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', choices: ['1.1.0','1.2.0','1.3.0'], description:'')
        booleanParam(name: 'executeTests', defaultValue: true, description:'')
    }

    stages {
        stage('Build') {
          
            steps {
                scipt{

                    gv.buildApp()
                }
                
            }
        }
        stage('Test') {
             when {
                    expression{
                        params.executeTests
                    }
                }
          
            steps {
                  scipt{

                    gv.testApp()
                }
            }  
        }
         stage('Deploy') {
          
            steps {
                scipt{

                    gv.deployApp()
                }
            }
          
        }
    }
}
    

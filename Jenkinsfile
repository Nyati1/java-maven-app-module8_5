//def gv

pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', choices: ['1.1.0','1.2.0','1.3.0'], description:'')
        booleanParam(name: 'executeTests', defaultValue: true, description:'')
    }

    stages {
        stage('Build') {
          
            steps {
                script{
                    def gv = load "script.groovy"
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
                  script{
                      def gv = load "script.groovy"
                      gv.testApp()
                }
            }  
        }
         stage('Deploy') {
             input{
                 message "Select environment to deploy to"
                 ok "Done"
                 parameters {
                         choice(name: 'ENV1', choices: ['DEV','STAGING','PROD'], description:'')
                         choice(name: 'ENV2', choices: ['DEV','STAGING','PROD'], description:'')
                 }
             }
            steps {
                script{
                    def gv = load "script.groovy"

                    gv.deployApp()
                    echo "Deploying to ${ENV1}"
                    echo "Deploying to ${ENV2}"
                }
            }
        }
    }
}
    

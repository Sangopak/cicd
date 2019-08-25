node {
    try{
        def mvnHome
        stage('Checkout Stage') {
            echo "Checking out code from SCM for ${env.BRANCH_NAME}"
            checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/Sangopak/cicd.git']]])
            mvnHome = tool 'Maven'
        }
        stage('Build Unit Test and Package') {
            withEnv(["MVN_HOME=$mvnHome"]) {
                echo "Running build unit test and package for ${env.BRANCH_NAME}"
                if (isUnix()) {
                    sh '"$MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean package'
                } else {
                    bat(/"%MVN_HOME%\bin\mvn" clean package/)
                }
            }
        }
        stage('Collect Test Results') {
            echo "Collecting Test results for ${env.BRANCH_NAME}"
            junit '**/target/surefire-reports/*.xml'
        }
        stage('Archive Artifacts') {
            echo "Archiving Artifacts for ${env.BRANCH_NAME}"
            archiveArtifacts 'target/*.jar'
        }
        echo "All stages completed for ${env.BRANCH_NAME}"
        notify(${currentBuild.result})
    }catch (err){
        echo "Caught: ${err}"
        notify(${currentBuild.result})
    }
}

def notify(status){
    try{
        echo "Pipeline Status for ${env.BRANCH_NAME} with ${env.BUILD_NUMBER} and URL as ${env.BUILD_URL} is ${status}"
        mail bcc: '', body: "Pipeline Status for ${env.BRANCH_NAME} with ${env.BUILD_NUMBER} and URL as ${env.BUILD_URL} is ${status}", cc: '', from: '', replyTo: '', subject: "Pipeline Status -> ${status}", to: 'sangojumech07@gmail.com'
    }catch (err){
        echo "Caught: ${err}"
    }

}
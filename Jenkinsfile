node {
    def branch = env.BRANCH_NAME
    def buildNumber = env.BUILD_NUMBER
    def buildUrl = env.BUILD_URL
    try{
        def mvnHome
        stage('Checkout Stage') {
            echo "Checking out code from SCM for env.BRANCH_NAME"
            checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/Sangopak/cicd.git']]])
            mvnHome = tool 'Maven'
        }
        stage('Build Unit Test and Package') {
            withEnv(["MVN_HOME=$mvnHome"]) {
                echo "Running build unit test and package for ${branch}"
                if (isUnix()) {
                    sh '"$MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean package'
                } else {
                    bat(/"%MVN_HOME%\bin\mvn" clean package/)
                }
            }
        }
        stage('Collect Test Results') {
            echo "Collecting Test results for ${branch}"
            junit '**/target/surefire-reports/*.xml'
        }
        stage('Archive Artifacts') {
            echo 'Archiving Artifacts for ${branch}'
            archiveArtifacts 'target/*.jar'
        }
        step([$class: 'JUnitResultArchiver', testResults: 'target\\surefire-reports\\TEST-*.xml'])
        echo 'All stages completed for ${branch}'
        notify("Success")
        currentBuild.result = 'Success'
    }catch (err){
        echo "Caught: ${err}"
        notify("Failure")
        currentBuild.result = 'Failure'
    }
}

def notify(status){
    try{
        echo "Pipeline Status for env.BRANCH_NAME with env.BUILD_NUMBER and URL as env.BUILD_URL is ${status}"
        mail bcc: '', body: "Pipeline Status for env.BRANCH_NAME with env.BUILD_NUMBER and URL as env.BUILD_URL is ${status}", cc: '', from: '', replyTo: '', subject: "Pipeline Status -> ${status}", to: 'sangojumech07@gmail.com'
    }catch (err){
        echo "Caught: ${err}"
    }

}
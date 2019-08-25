node {
    def mvnHome
    stage('Checkout Stage') {
        echo 'Checking out code from SCM'
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/Sangopak/cicd.git']]])
        mvnHome = tool 'Maven'
    }
    stage('Build Unit Test and Package') {
        withEnv(["MVN_HOME=$mvnHome"]) {
            echo 'Running build unit test and package'
            if (isUnix()) {
                sh '"$MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean package'
            } else {
                bat(/"%MVN_HOME%\bin\mvn" clean package/)
            }
        }
    }
    stage('Collect Test Results') {
        echo 'Collecting Test results'
        junit '**/target/surefire-reports/*.xml'
    }
    stage('Archive Artifacts') {
        echo 'Archiving Artifacts'
        archiveArtifacts 'target/*.jar'
    }
    stage('Pipeline Status Email Step'){
        try{
            mail bcc: '', body: 'Pipeline Status -> Not sure if Pipeline ran successfully or now', cc: '', from: '', replyTo: '', subject: 'Pipeline Status', to: 'sangojumech07@gmail.com'
        }catch (err){
            echo "Caught: ${err}"
            currentBuild.result = 'FAILURE'
        }

    }
}
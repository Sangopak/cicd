node {
   def mvnHome
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/Sangopak/cicd.git'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.
      mvnHome = tool 'Maven'
   }
   stage('Build Unit Test and Package') {
      // Run the maven build
      withEnv(["MVN_HOME=$mvnHome"]) {
         if (isUnix()) {
            sh '"$MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean package'
         } else {
            bat(/"%MVN_HOME%\bin\mvn" clean package/)
         }
      }
   }
   stage('Collect Test Results and Archive Artifacts') {
      junit '**/target/surefire-reports/*.xml'
      archiveArtifacts 'target/*.jar'
   }
}
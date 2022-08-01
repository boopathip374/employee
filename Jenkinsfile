pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage("Clone code from GitHub") {
            steps {
                script {
                    git branch: 'develop', credentialsId: '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', url: 'https://github.com/boopathip374/employee';
                }
            }
        }
        stage('Maven Build') {
            steps {
                script {
                     sh "mvn package -DskipTests=true"
                }
            }
        }
        stage("Publish to Nexus Repository Manager") {
            steps {
                script {
                    pom = readMavenPom file: 'pom.xml';
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    echo "artifactExists";
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                            nexusVersion: 'nexus3',
                            protocol: 'http',
                            nexusUrl: '3.87.7.96:8081',
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: 'develop',
                            credentialsId: 'nexus3',
                            artifacts: [
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: "pom.xml",
                                type: "pom"]
                            ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
                }
            }
        }
       stage('Deploy to DEV_EC2'){
            steps {
                dir('deployment'){
                    echo 'Deploying to test'
                     ansiblePlaybook credentialsId: 'private-key', disableHostKeyChecking: true, installation: 'ansible2', inventory: 'servers-ip', playbook: 'site.yml'
                }
            }
        }
    }
}
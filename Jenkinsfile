pipeline {
    environment {
        JAVA_TOOL_OPTIONS = "-Duser.home=/home/jenkins"
    }
    agent {
        dockerfile {
            args "-v /tmp/maven:/home/jenkins/.m2 -e MAVEN_CONFIG=/home/jenkins/.m2"
        }
    }

    stages {
        stage("Build") {
            steps {
                sh "mvn -version"
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'f018a8d1-aa6b-4bd0-877f-05c73eba2d39', url: 'https://github.com/barbosa-thiago/Forwork.git']])
                sh "mvn clean install"
            }
        }

//         stage("Build Docker Image") {
//             steps {
//                 script {
//                     dockerapp = docker.build("barbosathiago/thymeleaf-example", '-f ./Dockerfile ./')
//                 }
//             }
//         }
    }
}
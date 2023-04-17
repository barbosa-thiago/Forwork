pipeline {
    agent any

    environment {
        AWS_REGION = 'us-east-1'
    }

    stages {
        stage("Build") {
            steps {
                sh "mvn -version"
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'f018a8d1-aa6b-4bd0-877f-05c73eba2d39', url: 'https://github.com/barbosa-thiago/Forwork.git']])
                sh "mvn clean install -DskipTests"
            }
        }
        stage('Deploy to EC2') {
            steps {
                withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'jenkins-aws']]) {
                    sh '''
                        export AWS_ACCESS_KEY_ID=$AWS_ACCESS_KEY_ID
                        export AWS_SECRET_ACCESS_KEY=$AWS_SECRET_ACCESS_KEY
                        echo $AWS_ACCESS_KEY_ID
                        aws ec2 describe-instances --region us-east-1
                        aws ec2 cp /target/forwork.jar ec2-user@ec2-3-95-217-58.compute-1.amazonaws.com:~/
                        ssh ec2-user@ec2-3-95-217-58.compute-1.amazonaws.com 'java -jar ~/forwork.jar'
                    '''
                }
            }
        }
    }
}
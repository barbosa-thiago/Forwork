pipeline {
    agent any

    environment {
        AWS_REGION = 'us-east-1'
        FORWORK_USER = "${params.FORWORK_USER}"
        FORWORK_HOST = "${params.FORWORK_HOST}"
        S3_BUCKET = "${params.S3_BUCKET}"
        EC2_ID = "${params.EC2_ID}"
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
                        echo ${FORWORK_HOST}
                        ls target
                        export AWS_ACCESS_KEY_ID=$AWS_ACCESS_KEY_ID
                        export AWS_SECRET_ACCESS_KEY=$AWS_SECRET_ACCESS_KEY
                        aws ssm send-command \
                            --instance-ids ${EC2_ID} \
                            --document-name "AWS-RunShellScript" \
                            --comment "IP config" \
                            --region ${AWS_REGION} \
                            --parameters commands=ls \
                            --output text
                    '''
                }
            }
        }
    }
}
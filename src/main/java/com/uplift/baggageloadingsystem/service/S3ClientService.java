package com.uplift.baggageloadingsystem.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3ClientService {

    private AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket.name}")
    private String bucketName;

    S3ClientService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    void upload(String filename, File file) {
        amazonS3.putObject(new PutObjectRequest(bucketName, filename, file));
    }
}
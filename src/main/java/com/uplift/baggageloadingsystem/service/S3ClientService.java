package com.uplift.baggageloadingsystem.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3ClientService {

    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;



    S3ClientService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void upload(String filename, File file) {
        TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(this.amazonS3).build();
        transferManager.upload(bucketName, filename, file);
    }
}

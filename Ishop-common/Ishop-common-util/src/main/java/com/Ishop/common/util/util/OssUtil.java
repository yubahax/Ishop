package com.Ishop.common.util.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


public class OssUtil {
    private static final String BUCKET_NAME = "yuba-hax";
    private static final String ENDPOINT = "oss-cn-chengdu.aliyuncs.com";
    private static final String KEY_ID = "LTAI5tAFpFknynPJWLLJhMLG";
    private static final String KEY_SECRET = "qxrBqcyRcVRbGlbce5fOzgPLVPscit";


    public static String saveStringByOss(String str){
        OSS ossClient  = new OSSClientBuilder().build(ENDPOINT, KEY_ID, KEY_SECRET);
        InputStream inputStream = new ByteArrayInputStream(str.getBytes());
        //为了使得文件可以重复上传，每次上传的时候需要将文件名进行修改
        String fileName = "blog.txt";
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String newFileName = uuid + fileName;
        //获取当前日期,然后以日期和新的文件名组成全路径，使得oss中的文件按照日期进行分类存储
        String date = new DateTime().toString("yyyy/MM/dd");
        String fullFileName = date + "/" + newFileName;
        //第一个参数Bucket名称 第二个参数 上传到oss文件路径和文件名称
        try {
            ossClient.putObject(BUCKET_NAME, fullFileName, inputStream);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return "https://"+BUCKET_NAME+"."+ ENDPOINT+"/"+fullFileName;
    }

    public static void deleteFile(String filename) {
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, KEY_ID, KEY_SECRET);
        try {
            ossClient.deleteObject(BUCKET_NAME, filename);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    public static void deleteLongFile(String filename) {
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, KEY_ID, KEY_SECRET);
        try {
            String[] strings = filename.split("/");
            String string =  strings[3]+"/"+strings[4]+"/"+strings[5]+"/"+strings[6];
            ossClient.deleteObject(BUCKET_NAME, string);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    public static String saveFile( MultipartFile uploadFile) throws IOException {
        OSS ossClient  = new OSSClientBuilder().build(ENDPOINT, KEY_ID, KEY_SECRET);
        InputStream inputStream = uploadFile.getInputStream();
        //为了使得文件可以重复上传，每次上传的时候需要将文件名进行修改
        String fileName = uploadFile.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String newFileName = uuid + fileName;
        //获取当前日期,然后以日期和新的文件名组成全路径，使得oss中的文件按照日期进行分类存储
        String date = new DateTime().toString("yyyy/MM/dd");
        String fullFileName = date + "/" + newFileName;
        //第一个参数Bucket名称 第二个参数 上传到oss文件路径和文件名称
        ossClient.putObject(BUCKET_NAME, fullFileName, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
        return "https://"+BUCKET_NAME+"."+ ENDPOINT+"/"+fullFileName;
    }
}

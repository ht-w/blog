package io.hongting.service.impl;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import io.hongting.config.OssProperties;
import io.hongting.service.FileService;
import io.hongting.utils.annotation.AuthorizationNotRequired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author hongting
 * @create 2021 11 11 2:00 PM
 */
@Service
public class FileServiceImpl implements FileService {



    @Override
    public String upload(InputStream inputStream, String module, String filename) {

        OSS ossClient = new OSSClientBuilder().build(OssProperties.ENDPOINT, OssProperties.KEY_ID, OssProperties.KEY_SECRET);
        if(!ossClient.doesBucketExist(OssProperties.BUCKET_NAME)){
            ossClient.createBucket(OssProperties.BUCKET_NAME);
            ossClient.setBucketAcl(OssProperties.BUCKET_NAME, CannedAccessControlList.PublicRead);
        }
        String name = UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
        String date = new DateTime().toString("yyyy/MM/dd/");
        String key = module + "/" + date + "/" + name;


        ossClient.putObject(OssProperties.BUCKET_NAME, key, inputStream);
        ossClient.shutdown();

        return "https://" + OssProperties.BUCKET_NAME + "." + OssProperties.ENDPOINT + "/" + key;
    }

    @Override
    public void removeFile(String url) {
        OSS ossClient = new OSSClientBuilder().build(
                OssProperties.ENDPOINT,
                OssProperties.KEY_ID,
                OssProperties.KEY_SECRET);

        String host = "https://" + OssProperties.BUCKET_NAME + "." + OssProperties.ENDPOINT + "/";
        String objectName = url.substring(host.length());


        ossClient.deleteObject(OssProperties.BUCKET_NAME, objectName);

        ossClient.shutdown();
    }
}

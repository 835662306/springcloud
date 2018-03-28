package com.example.utils.qiniu;

import com.example.entry.Rescource;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FetchRet;
import com.qiniu.util.Auth;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/21 14:00
 * @Version 1.0
 */
public class QiniuUtil {
    private static Logger logger = LoggerFactory.getLogger(QiniuUtil.class);

    private static String path = "https://mysiteforme.com/";
    private static String qiniuAccess = "PXj2yRJGbfyhiUid_qeateOg-kiLaF9ZkteYuQQL";
    private static String qiniuKey = "0h9ICCVUgtrLAspsANW-5IW3mG-45SoYDIF1DTDe";
    private static String bucketName = "wanggg";

    /**
     * 普通上传图片
     * @param file
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static String upload(MultipartFile file)throws IOException, NoSuchAlgorithmException {
        Zone zone = Zone.zone0();
        Configuration config = new Configuration(zone);
        String fileName = "", extName = "", filePath = "";
        if(file != null && !file.isEmpty()){
            extName = file.getOriginalFilename().substring(
                    file.getOriginalFilename().lastIndexOf("."));

            fileName = UUID.randomUUID() + extName;
            UploadManager uploadManager = new UploadManager(config);
            Auth auth = Auth.create(qiniuAccess, qiniuKey);
            String token = auth.uploadToken(bucketName);
            byte[] data = file.getBytes();
            QETag qeTag = new QETag();
            String hash = qeTag.calcETag(file);
            Rescource rescource = new Rescource();
            //TODO: 检查是否已经上传
//            EntityWrapper entityWrapper = new EntityWrapper();
//            entityWrapper.eq("hash", hash);

            Response r = uploadManager.put(data, fileName, token);
            if (r.isOK()) {
                filePath = path + fileName;
                rescource = new Rescource();
                rescource.setFileName(fileName);
                rescource.setFileSize(new java.text.DecimalFormat("#.##").format(file.getSize()/1024)+"kb");
                rescource.setHash(hash);
                rescource.setFileType(StringUtils.isBlank(extName)?"unknown":extName);
                rescource.setWebUrl(filePath);
                rescource.setSource("qiniu");
//                rescource.insert();//保存到数据库
            }
        }
        return filePath;
    }

    /***
     * 删除已经上传的图片
     * @param imgPath
     */
    public static void deleteQiniuP(String imgPath) {
        Zone z = Zone.zone0();
        Configuration config = new Configuration(z);
        Auth auth = Auth.create(qiniuAccess, qiniuKey);
        BucketManager bucketManager = new BucketManager(auth,config);
        imgPath = imgPath.replace(path, "");
        try {
            bucketManager.delete(bucketName, imgPath);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }

    /***
     * 上传网络图片
     * @param src
     * @return
     */
    public static String uploadImageSrc(String src){
        Zone z = Zone.zone0();
        Configuration config = new Configuration(z);
        Auth auth = Auth.create(qiniuAccess, qiniuKey);
        BucketManager bucketManager = new BucketManager(auth, config);
        String fileName = UUID.randomUUID().toString(),filePath="";
        try {
            FetchRet fetchRet = bucketManager.fetch(src, bucketName);
            filePath = path + fetchRet.key;
            Rescource rescource = new Rescource();
            rescource.setFileName(fetchRet.key);
            rescource.setFileSize(new java.text.DecimalFormat("#.##").format(fetchRet.fsize/1024)+"kb");
            rescource.setHash(fetchRet.hash);
            rescource.setFileType(fetchRet.mimeType);
            rescource.setWebUrl(filePath);
            rescource.setSource("qiniu");
//            rescource.insert();
        } catch (QiniuException e) {
            filePath = src;
            e.printStackTrace();
        }
        return filePath;
    }

    public static void main(String [] args){
       // https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E7%BE%8E%E5%A5%B3&step_word=&hs=0&pn=0&spn=0&di=138602125640&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2544269114%2C2104066965&os=2739078909%2C177206311&simid=3414337523%2C315566574&adpicid=0&lpn=0&ln=3938&fr=&fmq=1521615060879_R&fm=result&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=girl&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg5.xiazaizhijia.com%2Fwalls%2F20160708%2F1440x900_2f172c09d079701.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bxtwzwtzit3tw_z%26e3Bv54AzdH3FktzitAzdH3Fmla00_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0
        uploadImageSrc("https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E7%BE%8E%E5%A5%B3&step_word=&hs=0&pn=0&spn=0&di=138602125640&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2544269114%2C2104066965&os=2739078909%2C177206311&simid=3414337523%2C315566574&adpicid=0&lpn=0&ln=3938&fr=&fmq=1521615060879_R&fm=result&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=girl&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg5.xiazaizhijia.com%2Fwalls%2F20160708%2F1440x900_2f172c09d079701.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bxtwzwtzit3tw_z%26e3Bv54AzdH3FktzitAzdH3Fmla00_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0");
    }

    /***
     * 上传本地图片
     * @param src
     * @return
     */
    public static String uploadLocalImg(String src) throws IOException, NoSuchAlgorithmException{
        Zone z = Zone.zone0();
        Configuration config = new Configuration(z);
        UploadManager uploadManager = new UploadManager(config);
        Auth auth = Auth.create(qiniuAccess, qiniuKey);
        String token = auth.uploadToken(bucketName);
        File file = new File(src);
        if(!file.exists()){
            throw new IOException("本地文件不存在");
        }
        QETag tag = new QETag();
        String hash = tag.calcETag(file);
        Rescource rescource = new Rescource();
        //TODO: 验证此文件是否上传
//        EntityWrapper<RestResponse> wrapper = new EntityWrapper<>();
//        wrapper.eq("hash",hash);
//        rescource = rescource.selectOne(wrapper);
//        if( rescource!= null){
//            return rescource.getWebUrl();
//        }
        String filePath="",
                extName = "",
                name = UUID.randomUUID().toString();
        extName = file.getName().substring(
                file.getName().lastIndexOf("."));
        Response response = uploadManager.put(file,name,token);
        if(response.isOK()){
            filePath = path + name;
            rescource = new Rescource();
            rescource.setFileName(name);
            rescource.setFileSize(new java.text.DecimalFormat("#.##").format(file.length()/1024)+"kb");
            rescource.setHash(hash);
            rescource.setFileType(StringUtils.isBlank(extName)?"unknown":extName);
            rescource.setWebUrl(filePath);
            rescource.setSource("qiniu");
            //rescource.insert();
        }
        return filePath;
    }

    /**
     * 上传base64位的图片
     * @param base64
     * @return
     */
    public static String uploadBase64(String base64,String name) {
        Zone z = Zone.zone0();
        Configuration config = new Configuration(z);
        UploadManager uploadManager = new UploadManager(config);
        Auth auth = Auth.create(qiniuAccess, qiniuKey);
        String token = auth.uploadToken(bucketName),filePath;

        byte[] data = Base64.decodeBase64(base64);
        try {
            uploadManager.put(data,name,token);
        } catch (IOException e) {
            e.printStackTrace();
        }
        filePath = path+name;
        return filePath;
    }
}
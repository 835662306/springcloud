package com.example.utils.cameraUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/16 13:42
 * @Version 1.0
 */
public class CameraUtil {
    private static final String filePath = "F:\\";
    private static final String imageFormat = "jpg";
    private static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * 保存屏幕图片
     * @param engine 搜索引擎
     * @param key 关键字
     * @param page 页码
     */
    public static void snapShot(String engine,String key,String page) throws AWTException, IOException{
        int width = (int)dimension.getWidth();
        int heigth = (int)dimension.getHeight();
        //拷贝屏幕到一个bufferedImage对象中
        BufferedImage scanShot = (new Robot()).createScreenCapture(
                new Rectangle(0, 60, width, heigth)
        );
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
        String datestr=format.format(new Date());
        //根据文件前缀,格式自动生成文件名
        String name=filePath+datestr+"-"+engine+"-"+key+"-"+page+"."+imageFormat;
        File f=new File(name);
        ImageIO.write(scanShot,imageFormat,f);
    }

    public static void main(String [] args) throws IOException, AWTException {
        snapShot("苗苗", "google", "1");
    }
}

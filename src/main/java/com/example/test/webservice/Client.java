package com.example.test.webservice;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * java安装目录/bin下执行wsimport命令，将服务的的包名配置
 * wsimport -s F:\\  -keep webservice地址+?wsdl 会在包下生成服务端的接口
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/15 16:53
 * @Version 1.0
 */
public class Client {
    public static void main(String [] args) throws Exception{
        URL url = new URL("http://127.0.0.1:12345/weather");
        QName serviceName = new QName("http://webservice_jaxws/","WebServiceImpl");//wsdl命名空间、服务视图名称
        Service service = Service.create(url,serviceName);//使用service创建服务视图
        WebServiceImpl impl = service.getPort(WebServiceImpl.class);//从服务视图中得到portType对象
        System.out.println(impl.query("武汉"));
    }
}

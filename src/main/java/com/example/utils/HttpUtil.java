package com.example.utils;

import java.io.*;
import java.net.*;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/14 10:40
 * @Version 1.0
 */
public class HttpUtil {

    private String charset = "UTF-8";
    private String proxyHost = null;
    private Integer proxyPort = null;
    private Integer connectTimeout = null;
    private Integer socketTimeout = null;

    public String doGet(String url) throws Exception {
        URL realUrl = null;
        ByteArrayOutputStream outputStream = null;
        GZIPInputStream gip = null;

        try {
            realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            // 设置连接属性
            connection.setRequestProperty("accept","application/xhtml+xml,application/json,application/xml;charset=utf-8, text/javascript, **/*//*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("contentType", "utf-8");
            connection.setRequestMethod("GET");
            connection.setRequestProperty("user-agent","Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");

            gip = new GZIPInputStream(connection.getInputStream());
            outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];

            int len;
            while ((len = gip.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if(gip!=null){
                    gip.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String str = new String(outputStream.toByteArray(),charset);
        return str;
    }

    /**
     * Do POST request
     *
     * @param url
     * @param parameterMap
     * @return
     * @throws Exception
     */
    public String doPost(String url, Map<String, ?> parameterMap)
            throws Exception {

		/* Translate parameter map to parameter date string */
        StringBuffer parameterBuffer = new StringBuffer();
        if (parameterMap != null) {
            Iterator<?> iterator = parameterMap.keySet().iterator();
            String key = null;
            String value = null;
            while (iterator.hasNext()) {
                key = (String) iterator.next();
                if (parameterMap.get(key) != null) {
                    value = (String) parameterMap.get(key);
                } else {
                    value = "";
                }

                parameterBuffer.append(key).append("=").append(value);
                if (iterator.hasNext()) {
                    parameterBuffer.append("&");
                }
            }
        }

        System.out.println("POST parameter : " + parameterBuffer.toString());

        URL localURL = new URL(url);

        URLConnection connection = openConnection(localURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("contentType", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(parameterBuffer.toString().length()));

        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;

        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream,"utf-8");

            outputStreamWriter.write(parameterBuffer.toString());
            outputStreamWriter.flush();

            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception(
                        "HTTP Request is not success, Response code is "
                                + httpURLConnection.getResponseCode());
            }

            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);

            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }

        } finally {

            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

        }

        return resultBuffer.toString();
    }

    private URLConnection openConnection(URL localURL) throws IOException {
        URLConnection connection;
        if (proxyHost != null && proxyPort != null) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
                    proxyHost, proxyPort));
            connection = localURL.openConnection(proxy);
        } else {
            connection = localURL.openConnection();
        }
        return connection;
    }

    public String doGet2(String url) throws Exception {


        URL localURL = new URL(url);

        URLConnection connection = openConnection(localURL);
        connection.setReadTimeout(9000);
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        httpURLConnection.setRequestProperty("Accept-Charset", charset);
        httpURLConnection.setRequestProperty("contentType", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");


        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;

        if (httpURLConnection.getResponseCode() >= 300) {
            throw new Exception(
                    "HTTP Request is not success, Response code is "
                            + httpURLConnection.getResponseCode());
        }
        try {
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            reader = new BufferedReader(inputStreamReader);

            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }

        } finally {

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

        }

        return resultBuffer.toString();
    }


    /**
     * Render request according setting
     *
     * @param
     */
    @SuppressWarnings("unused")
    private void renderRequest(URLConnection connection) {

        if (connectTimeout != null) {
            connection.setConnectTimeout(connectTimeout);
        }

        if (socketTimeout != null) {
            connection.setReadTimeout(socketTimeout);
        }

    }
    /*
     * Getter & Setter
     */
    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
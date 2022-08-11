package com.example.ippool.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.ippool.service.PoolService;
import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Service
public class PoolServiceImpl implements PoolService {

    @Override
    public Object getIp() {
        return null;
    }

    @Override
    public Object putIp() throws IOException {
        List<String> ips = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            String url = "https://free.kuaidaili.com/free/inha/" + i;
            Document document = Jsoup.connect(url).get();
            Elements trs = document.select("div#list>table>tbody>tr");
            for (Element tr : trs) {
                String ip = tr.select("td[data-title=IP]").text();
                String port = tr.select("td[data-title=PORT]").text();
                System.out.println("==> " + ip + ":" + port);
                try {
                    String reqUrl = "https://www.baidu.com";
                    Connection.Response execute = Jsoup.connect(reqUrl)
                            .timeout(3000)
                            .proxy(ip, Integer.parseInt(port)).execute();
                    int statusCode = execute.statusCode();
                    System.out.println("status: " + statusCode);
                    ips.add(ip + ":" + port);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return ips;
    }

    public static void main(String[] args) throws IOException {
        List<String> ips = new ArrayList<>();
        ips.add("120.194.55.139:6969");
        ips.add("61.216.185.88:60808");
        //String filePath = "ips.json";//json文件地址
        ClassPathResource resource = new ClassPathResource("ips.json", PoolServiceImpl.class.getClassLoader());
        File file = resource.getFile();
        System.out.println(file.getAbsolutePath());
        FileUtils.writeStringToFile(file, JSON.toJSONString(ips), Charset.defaultCharset());
        String read = FileUtils.readFileToString(file, Charset.defaultCharset());
        System.out.println(read);
    }
}

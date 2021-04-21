package com.github.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author gujunxiang@51haohuo.com
 * @date 2021/4/21
 */
public class AppleRefurbished {
    public static void main(String[] args) {
        AppleRefurbished t = new AppleRefurbished();
        Document doc = t.getDocument("https://www.apple.com.cn/shop/refurbished/mac");
        String str = doc.toString();
        System.out.println(str);
        boolean flag = str.contains("Apple M1 芯片");
        if (flag) {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://pushplus.hxtrip.com/send?token=e69a12073e8a464ea483a9d44f27e0a3&title=apple翻新商城&content=扫描到上新啦";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            System.out.println("发送成功");
        }
    }

    /**
     * @param url 访问路径
     * @return
     */
    public Document getDocument(String url) {
        try {
            //5000是设置连接超时时间，单位ms
            return Jsoup.connect(url).timeout(5000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

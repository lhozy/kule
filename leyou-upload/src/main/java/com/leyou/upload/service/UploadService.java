package com.leyou.upload.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {
    //定义文件类型白名单
    private static final List<String> CONTENT_TYPES = Arrays.asList("image/gif","image/jpeg","image/png");
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);
    private static final String WEBURL = "http://image.leyou.com/";
    private static final String IMAGEPATH = "C:\\leyou\\image\\";
    public String uploadImage(MultipartFile file) {
        //1、校验文件Mime-Type类型是否在白名单
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        if(!CONTENT_TYPES.contains(contentType)){
            LOGGER.info("文件类型不合法:{}",originalFilename);
            return null;
        }
        //2、校验文件内容
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if(null == bufferedImage){
                LOGGER.info("文件内容不合法：{}",originalFilename);
                return null;
            }
            //3、保存文件到服务器
            file.transferTo(new File(IMAGEPATH + originalFilename));
            //4、返回URL回显
            return WEBURL+originalFilename;
        } catch (IOException e) {
            LOGGER.info("服务器内部错误:{}",originalFilename);
            e.printStackTrace();
        }
        return null;

    }
}

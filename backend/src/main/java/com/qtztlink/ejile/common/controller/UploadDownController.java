package com.qtztlink.ejile.common.controller;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qtztlink.ejile.common.bean.ResponseBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadDownController {
    private static final String ACCESSKEY = "WFu4RDKywdcDlQCYJhcBaQh0uZlHik79PFo3WCRh";
    private static final String SECRET_KEY = "Zx3CSdhhJZMIetxUiRp2rBqCtgSp7Qf6Sb8EiseP";
    private static final String BUCKET = "shop-cloud";
    private static final String DOMAIN = "http://cloud.yhhu.xyz/";

    /**
     * 文件上传
     *
     * @param picture
     * @param request
     * @return
     */
    @RequestMapping("/img/upload")
    public ResponseBean upload(
            @RequestParam("picture") MultipartFile picture,
            HttpServletRequest request
    ) throws IOException {
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(ACCESSKEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET);
        try {
            Response response = uploadManager.put((FileInputStream) picture.getInputStream(),
                    UUID.randomUUID().toString().replace("-", ""),
                    upToken, null, null);
            // 解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            // 这个returnPath是获得到的外链地址,通过这个地址可以直接打开图片
            return new ResponseBean().code(200)
                    .message("SUCCESS")
                    .data(DOMAIN +  putRet.key);
        } catch (QiniuException ex) {
            return new ResponseBean().code(500)
                    .message("上传失败")
                    .data(null);
        }
    }
}

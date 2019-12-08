package com.mxp.car.controller;

import com.mxp.car.config.Config;
import com.mxp.car.util.ResultRtn;
import com.mxp.car.util.StatusCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/12/7
 * TIME: 12:04
 */
@Log4j2
@RestController
public class FileUpController {
    //多个文件的上传
    @PostMapping("/uploads")
    public ResultRtn uploads(MultipartFile[] uploadFiles, HttpServletRequest request) {
        //1，对文件数组做判空操作
        if (uploadFiles == null || uploadFiles.length < 1) {
            log.warn("-==上传文件为空==-");
            return ResultRtn.of(StatusCode.UPLOAD_NO_FILE);
        }
        //2，定义文件的存储路径,
        String realPath = Config.UPLOAD_PATH;
        File dir = new File(realPath);
        if (!dir.isDirectory()){
            log.warn("-==上传路径不存在{}==-",dir);
            return ResultRtn.of(StatusCode.UPLOAD_NO_PATH);
        }
        try {
            StringBuilder filePathS = new StringBuilder();
            //3，遍历文件数组，一个个上传
            for (MultipartFile uploadFile : uploadFiles) {
                String filename = uploadFile.getOriginalFilename();
                if (filename == null){
                    return ResultRtn.of(StatusCode.UPLOAD_NONAME);
                }
                //服务端保存的文件对象
                File fileServer = new File(dir, filename);
                log.info("file文件真实路径:" + fileServer.getAbsolutePath());
                //2，实现上传
                uploadFile.transferTo(fileServer);
                String filePath = request.getScheme() + "://" +
                        request.getServerName() + ":"
                        + request.getServerPort()
                        + "/uploadFile/" + filename;
                filePathS.append("\n").append(filePath);
            }
            //4，返回可供访问的网络路径
            return ResultRtn.of(StatusCode.UPLOAD_SUCCESS,filePathS);
        } catch (IOException e) {
            log.error("-==上传异常==-",e);
            return ResultRtn.of(StatusCode.UPLOAD_ERROR);
        }
    }
}

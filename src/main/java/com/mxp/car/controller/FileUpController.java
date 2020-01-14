package com.mxp.car.controller;

import com.mxp.car.config.Config;
import com.mxp.car.model.Photo;
import com.mxp.car.service.PhotoService;
import com.mxp.car.util.ResultRtn;
import com.mxp.car.util.StatusCode;
import com.mxp.car.util.Utils;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/12/7
 * TIME: 12:04
 */
@Log4j2
@RestController
public class FileUpController {

    @Autowired
    private PhotoService photoService;

    /**
     * 替换 和 新增
     * @param uploadFiles 传入文件
     * @param request photoId ：替换的id
     *                carId ： 替换的carId
     *                orderId ：替换的位置 / 0 为列表里新增
     */
    //多个文件的上传
    @PostMapping("/uploads")
    public ResultRtn uploads(MultipartFile[] uploadFiles, HttpServletRequest request) {
        log.info(uploadFiles);
        String photoId = request.getParameter("photoId");
        String carId = request.getParameter("carId");
        String orderId = request.getParameter("orderId");
        if (photoId != null){
            // 删除对应的图片信息
            this.photoService.findById(Long.parseLong(photoId)).map(photo -> {
                this.photoService.remove(photo);
                return "Success";
            }).orElseGet(() -> "Success");
        }
        //1，对文件数组做判空操作
        if (uploadFiles == null || uploadFiles.length < 1) {
            log.warn("-==上传文件为空==-");
            return ResultRtn.of(StatusCode.UPLOAD_NO_FILE);
        }
        String timeName = Utils.CarUtil.strToDate(LocalDate.now().toString()).toString();
        //2，定义文件的存储路径,当天时间
        var realPath = Config.UPLOAD_PATH +"/" + timeName + "/";
        var dir = new File(realPath);
        if (!dir.isDirectory()){
            if (!dir.mkdirs()){
                log.warn("-==上传路径不存在{}==-",dir);
                return ResultRtn.of(StatusCode.UPLOAD_NO_PATH);
            }
        }
        try {
            var i = 1;
            var list = new ArrayList<Photo>();
            //3，遍历文件数组，一个个上传
            for (MultipartFile uploadFile : uploadFiles) {
                Photo photo = new Photo();
                String filename = uploadFile.getOriginalFilename();
                if (filename == null){
                    return ResultRtn.of(StatusCode.UPLOAD_NONAME);
                }
                //服务端保存的文件对象
                File fileServer = new File(dir, filename);
                log.info("file文件真实路径:" + fileServer.getAbsolutePath());
                //2，实现上传
                uploadFile.transferTo(fileServer);
                Thumbnails.of(fileServer.getAbsolutePath()).size(640,480).toFile(fileServer.getAbsolutePath());
                // http 获取协议
                String scheme = request.getScheme();
                // localhost 获取ip
                String serverName = request.getServerName();
                // 9091 获取端口
                int serverPort = request.getServerPort();
                String ipPath = scheme+"://"+serverName+":"+serverPort;
                String filePath = ipPath + "/photo/" + timeName + "/" + filename;
                photo.setUrl(filePath);
                if (orderId != null && !orderId.equals("")){
                    photo.setOrderId(Integer.parseInt(orderId));
                }else {
                    photo.setOrderId(i++);
                }
                if (carId != null && !carId.equals("")) {
                    photo.setCarId(Long.valueOf(carId));
                }
                photo.setPhotoId(Utils.CarUtil.getId());
                list.add(photo);
            }
            var status = this.photoService.saveList(list);
            if (status > 0){
                //4，返回可供访问的网络路径
                return ResultRtn.of(StatusCode.UPLOAD_SUCCESS,list);
            }else {
                return ResultRtn.of(StatusCode.ERROR);
            }
        } catch (IOException e) {
            log.error("-==上传异常==-",e);
            return ResultRtn.of(StatusCode.UPLOAD_ERROR);
        }
    }

}

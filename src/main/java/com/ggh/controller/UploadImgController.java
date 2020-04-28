package com.ggh.controller;

import com.ggh.common.json.Body;
import com.ggh.utils.UploadImgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chaihu
 * @function
 * @date 2020-04-23 20:09
 */
@CrossOrigin
@Controller
@ResponseBody
@RequestMapping("upload")
public class UploadImgController {

    @Autowired
    private UploadImgUtils uploadImgUtils;


    /**
     *  上传图片接口
     * @param file
     * @return
     */
    @RequestMapping("uploadImg")
    public Body uploadImg(
            MultipartFile[] file
    ){
        String path = null;
        try{
            path = uploadImgUtils.uploadFiles(file);
            System.out.println(path);
        }catch (IOException e){
            return Body.newInstance(500,"上传失败，流异常");
        }catch (Exception e){
            return Body.newInstance(500,"上传失败，不知道啥异常");
        }
        return Body.newInstance(path);
    }

    /**
     *  后台上传图片接口
     * @param file
     * @return
     */
    @RequestMapping("bguploadImg")
    public Map bguploadImg(
            MultipartFile[] file
    ){
        String[] path = new String[1];
        Map<String,Object> map =  new HashMap<>();
        try{
            path[0] = uploadImgUtils.uploadFiles(file);
            map.put("data",path);
        }catch (IOException e){
            return map;
        }catch (Exception e){
            return map;
        }
        map.put("errno",0);
        return map;
    }

}

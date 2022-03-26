package com.baidu.ai.aip;

import com.baidu.ai.aip.utils.HttpUtil;
import com.baidu.ai.aip.auth.AuthService;
import com.baidu.ai.aip.utils.GsonUtils;
import java.util.*;
/**人脸添加 返回结果集*/
public class FaceAdd {
    public static String add(String img,String name,String username) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image",img);	//图片base64数据
            map.put("group_id","user");	//指定用户组group 
            map.put("user_id",username);	//添加的用户id	
            map.put("user_info",name);		//用户信息
            map.put("liveness_control", "NORMAL");	//活体检测控制
            map.put("quality_control", "LOW");	//图片质量控制
            map.put("image_type", "BASE64");	//图片类型，转化过的base64
            map.put("face_type", "LIVE");
            String param = GsonUtils.toJson(map);
            // 动态获取access_token
            String accessToken = AuthService.getAuth();
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
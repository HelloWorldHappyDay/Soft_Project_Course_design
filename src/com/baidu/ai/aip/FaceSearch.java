package com.baidu.ai.aip;

/**
 * @author Administrator
 *
 */

import java.util.*;


import com.baidu.ai.aip.auth.AuthService;
import com.baidu.ai.aip.utils.GsonUtils;
import com.baidu.ai.aip.utils.HttpUtil;

/**人脸搜索
*在云端人脸库中查找对应的人脸
* 若成功则返回分数，失败则返回false*/
public class FaceSearch {
    public static String search(String img ,String username) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image",img); //图片base64数据
            map.put("liveness_control","NORMAL");	//活体检测控制
            map.put("group_id_list","user");	//指定用户组group 
            map.put("image_type","BASE64");	//图片类型，转化过的base64
            map.put("quality_control","LOW");	//图片质量控制
            map.put("user_id",username);	//查找的用户id	
            String param = GsonUtils.toJson(map);
            String accessToken = AuthService.getAuth();
            // 动态获取access_token
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            String state = result.split(",")[0].split(":")[1];
            System.out.println(result);
            System.out.println(state);
            if(state.equals("0")) {
            	String score = result.split("score")[1].split(":")[1].split("}")[0];
            	System.out.println(score);
            	return score;
            }
            else {
            	return "false";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
}

package com.baidu.ai.aip;

import com.baidu.ai.aip.utils.HttpUtil;
import com.baidu.ai.aip.auth.AuthService;
import com.baidu.ai.aip.utils.GsonUtils;

import java.util.*;

/**人脸删除
 * 删除人脸库中指定用户的人脸图片*/
public class FaceDelete {
    public static String delete(String username) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/delete";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("group_id", "user");	//指定用户组group
            map.put("user_id", username);	//删除的用户id
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
package servlet;

import com.baidu.aip.speech.AipSpeech;
import org.json.JSONObject;

public class SpeechToText {
    public static final String APP_ID = "16230143";
    public static final String API_KEY = "9zOeTif0KAGSroyjmR9pqceg";
    public static final String SECRET_KEY = "XwgfD6Hr0Uq90Wtm5G2qkGRKvVECEn9d";

    public static String SpeechToVoice(String path) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        //client.setConnectionTimeoutInMillis(2000);
        //client.setSocketTimeoutInMillis(60000);

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        JSONObject res = client.asr(path, "pcm", 16000, null);
        //System.out.println(res.toString(2));
        return  res.toString(2);
    }
}

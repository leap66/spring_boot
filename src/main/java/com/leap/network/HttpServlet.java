package com.leap.network;

import com.leap.config.MarsConfig;
import com.leap.handle.exception.base.BaseException;
import com.leap.model.VoiceParam;
import com.leap.model.baidu.BVoice;
import com.leap.model.baidu.SToken;
import com.leap.model.enums.TtsCode;
import com.leap.model.tuling.BChat;
import com.leap.util.GsonUtil;
import com.leap.util.IsEmpty;
import io.jsonwebtoken.impl.Base64Codec;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/19
 * @description :
 */
@Service
public class HttpServlet {

  public String getToken() throws IOException {
    String url = MarsConfig.BAI_DU_TOKEN_URL //
        + "?grant_type=client_credentials" //
        + "&client_id=" + MarsConfig.BAI_DU_API_KEY //
        + "&client_secret=" + MarsConfig.BAI_DU_SECRET_KEY;
    // 创建HttpClient对象
    HttpClient client = HttpClients.createDefault();
    // 创建GET请求（在构造器中传入URL字符串即可）
    HttpGet get = new HttpGet(url);
    // 调用HttpClient对象的execute方法获得响应
    HttpResponse response = client.execute(get);
    // 调用HttpResponse对象的getEntity方法得到响应实体
    HttpEntity httpEntity = response.getEntity();
    // 使用EntityUtils工具类得到响应的字符串表示
    String result = EntityUtils.toString(httpEntity, "utf-8");
    SToken token = GsonUtil.parse(result, SToken.class);
    MarsConfig.BAI_DU_TOKEN = token.getAccess_token();
    return MarsConfig.BAI_DU_TOKEN;
  }

  /**
   * 图灵网络请求
   *
   * @param chat
   *          入参
   * @return 返回字符串
   * @throws IOException
   *           IO异常
   */
  public String tuLingQuest(BChat chat) throws IOException {
    // 创建HttpClient对象
    HttpClient client = HttpClients.createDefault();
    // 创建POST请求
    HttpPost post = new HttpPost(MarsConfig.TU_LING_URL);
    // 创建一个List容器，用于存放基本键值对（基本键值对即：参数名-参数值）
    List<BasicNameValuePair> parameters = new ArrayList<>();
    parameters.add(new BasicNameValuePair("key", MarsConfig.TU_LING_KEY));
    parameters.add(new BasicNameValuePair("userid",
        chat.getUserid().substring(0, chat.getUserid().length() - 6)));
    parameters.add(new BasicNameValuePair("info", chat.getInfo()));
    if (!IsEmpty.string(chat.getLoc()))
      parameters.add(new BasicNameValuePair("loc", chat.getLoc()));
    // 向POST请求中添加消息实体
    post.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));
    // 得到响应并转化成字符串
    HttpResponse response = client.execute(post);
    HttpEntity httpEntity = response.getEntity();
    return EntityUtils.toString(httpEntity, "utf-8");
  }

  /**
   * 百度语音转文字
   *
   * @param voice
   *          入参
   * @return 返回字符串
   * @throws IOException
   *           IO异常
   */
  public String vop(VoiceParam param, String token, BVoice voice) throws IOException {
    // 创建HttpClient对象
    HttpClient client = HttpClients.createDefault();
    // 创建POST请求
    HttpPost post = new HttpPost(MarsConfig.BAI_DU_VOP_URL);
    // 创建一个List容器，用于存放基本键值对（基本键值对即：参数名-参数值）
    List<BasicNameValuePair> parameters = new ArrayList<>();
    // 语音文件的格式，pcm 或者 wav 或者 amr。不区分大小写
    parameters.add(new BasicNameValuePair("format", voice.getFormat()));
    // 采样率，支持 8000 或者 16000
    parameters.add(new BasicNameValuePair("rate", param.getRate()));
    // 声道数，仅支持单声道，请填写固定值 1
    parameters.add(new BasicNameValuePair("channel", param.getChannel()));
    // 用户唯一标识，用来区分用户，计算UV值。建议填写能区分用户的机器 MAC 地址或 IMEI 码，长度为60字符以内。
    parameters.add(new BasicNameValuePair("cuid", param.getUserId()));
    // 开放平台获取到的开发者access_token
    parameters.add(new BasicNameValuePair("token", token));
    // 语种选择，默认中文（zh）。 中文=zh、粤语=ct、英文=en，不区分大小写。
    parameters.add(new BasicNameValuePair("lan", param.getLan()));
    // 语音下载地址，与callback连一起使用，确保百度服务器可以访问
    // parameters.add(new BasicNameValuePair("url", ""));
    // 识别结果回调地址，确保百度服务器可以访问
    // parameters.add(new BasicNameValuePair("callback", ""));
    // 真实的语音数据 ，需要进行base64 编码。与len参数连一起使用。
    String temp = Base64Codec.BASE64.encode(voice.getCode());
    parameters.add(new BasicNameValuePair("speech", temp));
    // 原始语音长度，单位字节
    parameters.add(new BasicNameValuePair("len", String.valueOf(voice.getLen())));
    // 向POST请求中添加消息实体
    post.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));
    // 得到响应并转化成字符串
    HttpResponse response = client.execute(post);
    HttpEntity httpEntity = response.getEntity();
    if (response.getStatusLine().getStatusCode() != 200)
      throw new BaseException(response.getStatusLine().getStatusCode(),
          TtsCode.getEnum(response.getStatusLine().getStatusCode()).getMessage());
    return EntityUtils.toString(httpEntity, "utf-8");
  }

  /**
   * 百度文字转语音
   *
   * @param value
   *          入参
   * @return 返回字符串
   * @throws IOException
   *           IO异常
   */
  public byte[] tts(VoiceParam param, String token, String value)
      throws IOException, BaseException {
    // 创建HttpClient对象
    HttpClient client = HttpClients.createDefault();
    // 创建POST请求
    HttpPost post = new HttpPost(MarsConfig.BAI_DU_TTS_URL);
    // 创建一个List容器，用于存放基本键值对（基本键值对即：参数名-参数值）
    List<BasicNameValuePair> parameters = new ArrayList<>();
    // 合成的文本，使用UTF-8编码，请注意文本长度必须小于1024字节
    parameters.add(new BasicNameValuePair("tex", value));
    // 语言选择,目前只有中英文混合模式，填写固定值zh
    parameters.add(new BasicNameValuePair("lan", param.getLan()));
    // 开放平台获取到的开发者access_token
    parameters.add(new BasicNameValuePair("tok", token));
    // 客户端类型选择，web端填写固定值1
    parameters.add(new BasicNameValuePair("ctp", param.getCtp()));
    // 用户唯一标识，用来区分用户，计算UV值。建议填写能区分用户的机器 MAC 地址或 IMEI 码，长度为60字符以内。
    parameters.add(new BasicNameValuePair("cuid", param.getUserId()));
    // 语速，取值0-9，默认为5中语速
    parameters.add(new BasicNameValuePair("spd", param.getSpd()));
    // 音调，取值0-9，默认为5中语调
    parameters.add(new BasicNameValuePair("pit", param.getPit()));
    // 音量，取值0-15，默认为5中音量
    parameters.add(new BasicNameValuePair("vol", param.getVol()));
    // 发音人选择, 0为普通女声，1为普通男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女声
    parameters.add(new BasicNameValuePair("per", param.getPer()));
    // 向POST请求中添加消息实体
    post.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));
    // 得到响应并转化成字符串
    HttpResponse response = client.execute(post);
    HttpEntity httpEntity = response.getEntity();
    if (response.getStatusLine().getStatusCode() != 200)
      throw new BaseException(response.getStatusLine().getStatusCode(),
          TtsCode.getEnum(response.getStatusLine().getStatusCode()).getMessage());
    // InputStream转换为byte[]
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    byte[] buff = new byte[1000];// buff用于存放循环读取的临时数据
    int rc;
    while ((rc = httpEntity.getContent().read(buff, 0, 1000)) > 0)
      stream.write(buff, 0, rc);
    return stream.toByteArray();// ByteArrayOutputStream转换为byte[]
  }

}

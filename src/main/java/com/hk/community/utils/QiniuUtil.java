package com.hk.community.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * @author 31618
 * @description : 七牛云文件上传工具
 * @date 2021-04-18 14:46
 */
@Component
@Data
@ToString
public class QiniuUtil {

	@Value("${qiniu.accessKey}")
	private String accessKey ;
	@Value("${qiniu.secretKey}")
	private String secretKey ;
	@Value("${qiniu.bucket}")
	private String bucketName ;
	@Value("${qiniu.fileDomain}")
	private String fileDomain;

	private UploadManager uploadManager;
	private BucketManager bucketManager;
	private Configuration cfg;      //此处是七牛云 Configuration 类不是 SpringContext 的配置类
	private Client client;


	// 密钥配置
	private Auth auth;
	//配置用户凭证
	public Auth getAuth() {
		if (auth == null) {
			auth = Auth.create(getAccessKey(), getSecretKey());
		}
		return auth;
	}

	public Configuration getConfiguration() {
		if (cfg == null) {
			//配置七牛云空间位置: 码客社区使用的是 华南 Region.region2(), Region.huanan()
			//Zone z = Zone.autoZone();  自动获取空间位置,若不指定 Region 或 Region.autoRegion() ，则会使用 自动判断 区域，使用相应域名处理。
			cfg = new Configuration(Region.huanan());
			/** 自定义上传域名
			 * Zone zone = new Zone.Builder(Zone.zone0())
			 * 				.upHttp("http://upload.qiniup.com")
			 * 				.upHttps("http://upload.qiniup.com")
			 * 				.upBackupHttp("http://upload.qiniup.com")
			 * 				.upBackupHttps("http://upload.qiniup.com")
			 * 				.rsHttp("http://rs.qiniu.com")
			 * 				.rsfHttp("http://rsf.qiniu.com")
			 * 				.apiHttp("http://api.qiniu.com")
			 * 				.iovipHttp("http://iovip.qbox.me").build();
			 * Configuration cfg = new Configuration(zone);
			 */

		}
		return cfg;
	}


	public Client getClient(){
		if (client==null) {
			client=new Client(getConfiguration());
		}
		return client;
	}

	public BucketManager getBucketManager() {
		if (bucketManager == null) {
			bucketManager = new BucketManager(getAuth(), getConfiguration());
		}
		return bucketManager;
	}

	public UploadManager getUploadManager() {
		if (uploadManager == null) {
			uploadManager = new UploadManager(getConfiguration());
		}
		return uploadManager;
	}


	//简单上传模式的凭证
	public String getUpToken() {
		return getAuth().uploadToken(getBucketName());
	}

	//覆盖上传模式的凭证 : filekey 为同名的2个文件的名字
	public String getUpToken(String fileKey) {
		return getAuth().uploadToken(getBucketName(), fileKey);
	}

	/**
	 * 将本地文件上传
	 * @param localFilePath 本地文件路径
	 * @param fileKey 上传到七牛后保存的文件路径名称: 码客社区使用" image/**
	 * @return : //默认不指定key的情况下，以文件内容的hash值作为文件名
	 * @throws IOException
	 */
	public DefaultPutRet upload(String localFilePath, String fileKey) throws IOException {

		DefaultPutRet putRet = null;
		try {
			Response res  = getUploadManager().put(localFilePath, fileKey, getUpToken(fileKey));
			// 解析上传成功的结果
			putRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);
			return putRet;
		} catch (QiniuException ex) {
			Response r = ex.response;
			System.err.println(r.toString());
			try {
				System.err.println(r.bodyString());
			} catch (QiniuException ex2) {
				//ignore
			}
		}
		return putRet ;
	}

	/**
	 * 上传二进制数据
	 * @param data
	 * @param fileKey
	 * @return
	 * @throws IOException
	 */
	public DefaultPutRet upload(byte[] data, String fileKey) throws IOException {
		Response res = getUploadManager().put(data, fileKey, getUpToken(fileKey));
		// 解析上传成功的结果
		DefaultPutRet putRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);
		return  putRet;
	}

	/**
	 * 上传输入流
	 * @param inputStream
	 * @param fileKey
	 * @return
	 * @throws IOException
	 */
	public DefaultPutRet upload(InputStream inputStream, String fileKey) throws IOException {
		Response res = getUploadManager().put(inputStream, fileKey, getUpToken(fileKey),null,null);
		// 解析上传成功的结果
		DefaultPutRet putRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);
		return putRet ;

	}

	/**
	 * @Title: 断点续传
	 * @description:
	 * @author: 31618
	 * @date: 2021/4/18
	 * @param :
	 * @return:
	 */
	public DefaultPutRet upload(String localFilePath , String localTempDir, String filekey) throws IOException {

		//String localTempDir = Paths.get(System.getenv("java.io.tmpdir"), bucket).toString();
		//设置断点续传文件进度保存目录
		FileRecorder fileRecorder = new FileRecorder(localTempDir);
		UploadManager uploadManager = new UploadManager(getConfiguration(), fileRecorder);
		Response response = uploadManager.put(localFilePath, filekey, getUpToken(filekey));
		//解析上传成功的结果
		DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		return putRet ;

	}

	public FileInfo getFileInfo(String filekey) throws QiniuException {
		FileInfo fileInfo = getBucketManager().stat(getBucketName(), filekey);
		return fileInfo ;
	}

	/**
	 * @description 删除文件
	 * @param fileKey
	 * @return
	 * @throws QiniuException
	 */
	public boolean delete(String fileKey) throws QiniuException {
		Response response = bucketManager.delete(this.getBucketName(), fileKey);
		return response.statusCode == 200 ? true:false;
	}


	/**
	 * @description 获取公共空间文件, 返回文件的外链
	 * @param fileKey
	 * @return :
	 */
	public String getFileUrl(String fileKey) throws Exception{
		String encodedFileName = URLEncoder.encode(fileKey, "utf-8").replace("+", "%20");
		String url = String.format("%s/%s", fileDomain, encodedFileName);
		return url;
	}

	/**
	 * @description 获取私有空间文件, 返回文件外连
	 * @param fileKey
	 * @return
	 */
	public String getPrivateFileUrl(String fileKey) throws Exception{
		String encodedFileName = URLEncoder.encode(fileKey, "utf-8").replace("+", "%20");
		String publicUrl = String.format("%s/%s", fileDomain, encodedFileName);
		Auth auth = Auth.create(accessKey, secretKey);
		long expireInSeconds = 3600 * 24;//1小时，可以自定义链接过期时间
		String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
		return finalUrl;
	}

}

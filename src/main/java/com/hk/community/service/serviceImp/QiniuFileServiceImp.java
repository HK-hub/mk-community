package com.hk.community.service.serviceImp;

import com.alibaba.fastjson.JSON;
import com.hk.community.utils.DateUtil;
import com.hk.community.utils.QiniuUtil;
import com.qiniu.storage.model.DefaultPutRet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

/**
 * @author 31618
 * @description: 文件上传服务
 * @date 2021-04-18 19:06
 */
@Slf4j
@Service
public class QiniuFileServiceImp {

	@Value("${qiniu.fileDomain}")
	String prefixPath ;
	@Autowired
	QiniuUtil qiniuUtil ;

	public Map fileUplod(MultipartFile[] multipartFiles, Model model){
		if (multipartFiles.length <= 0 || multipartFiles[0].isEmpty()){
			model.addAttribute("msg", "上传文件不能为空") ;
		}
		Long totalSize = 0L;
		List<String> urls = new ArrayList<String>();
		Map<String, Object> map = new HashMap<>();
		try {
			for (MultipartFile file : multipartFiles) {
				InputStream inputStream = file.getInputStream();
				final String originalFilename = file.getOriginalFilename();
				String fileExtend = originalFilename.substring(originalFilename.lastIndexOf("."));
				String yyyyMMddHHmmss = DateUtil.format(new Date(), "yyyy-MM-dd");
				String fileKey = UUID.randomUUID().toString().replace("-", "") + "-" + yyyyMMddHHmmss + fileExtend;
				DefaultPutRet uploadInfo = qiniuUtil.upload(inputStream, fileKey);
				totalSize += file.getSize();
				urls.add(prefixPath+uploadInfo.key);
			}
			map.put("urls", urls) ;
			/*InputStream fileInputStream = multipartFiles[0].getInputStream();
			//原始文件名字
			String originalFilename = multipartFiles[0].getOriginalFilename();
			//文件拓展名,后缀名
			String fileExtend = originalFilename.substring(originalFilename.lastIndexOf("."));
			String yyyyMMddHHmmss = DateUtil.format(new Date(), "yyyy-MM-dd");
			//final String yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

			//默认不指定key的情况下，以文件内容的hash值作为文件名
			//重新分配文件名称
			String fileKey = UUID.randomUUID().toString().replace("-", "") + "-" + yyyyMMddHHmmss + fileExtend;

			//文件流方式上传文件
			DefaultPutRet uploadInfo = qiniuUtil.upload(fileInputStream, fileKey);
			map.put("fileName", uploadInfo.key);
			map.put("originName", originalFilename);
			map.put("size", multipartFiles[0].getSize());
			//七牛云文件私有下载地址（看自己七牛云公开还是私有配置）: 图片外链地址
			map.put("url",  prefixPath+uploadInfo.key);
			//map.put("url", uploadInfo.key);//七牛云公开下载地址*/

			log.info("文件：" + JSON.toJSONString(map));
			//添加属性到model
			model.addAttribute("fileUploadMsg", "upload file success");
			model.addAttribute("file", map) ;

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("fileUploadMsg", "upload file failed");
		}
		return map ;
	}

}

package com.ancao.treasures.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ancao.treasures.bean.TreasuresHotlink;
import com.ancao.treasures.bean.TreasuresHotlinkExample;
import com.ancao.treasures.common.BaseController;
import com.ancao.treasures.constant.APIConstant;
import com.ancao.treasures.util.StringUtil;

@RestController
@RequestMapping("hotLink")
public class HotLinkAction extends BaseController {

	public File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * 热门链接上传
	 * 
	 * @author sh
	 * @version V1.0
	 * @throws Exception
	 */
	@RequestMapping("upload")
	public void upload() throws Exception {
		String prmTreasuresHotlink = getParameter("hotLink");
		TreasuresHotlink hotLink = JSON.parseObject(prmTreasuresHotlink, TreasuresHotlink.class);
		TreasuresHotlink newTreasuresHotlink = new TreasuresHotlink();
		newTreasuresHotlink.setUrl(hotLink.getUrl());
		newTreasuresHotlink.setDescription(hotLink.getDescription());
		// 查询是否已存在
		TreasuresHotlinkExample hotLinkExample = new TreasuresHotlinkExample();
		hotLinkExample.createCriteria().andUrlEqualTo(newTreasuresHotlink.getUrl());
		List<TreasuresHotlink> hotlinks = treasuresHotlinkMapper.selectByExample(hotLinkExample);
		Date date = new Date();
		if (hotlinks.size() > 0) {
			// 修改
			newTreasuresHotlink.setId(hotlinks.get(0).getId());
			newTreasuresHotlink.setUpdtime(date);
			treasuresHotlinkMapper.updateByPrimaryKeySelective(newTreasuresHotlink);
		} else {
			// 新增
			Random random = new Random();
			newTreasuresHotlink.setHotnumber(20000 + random.nextInt(1000));
			newTreasuresHotlink.setCreatetime(date);
			newTreasuresHotlink.setUpdtime(date);
			treasuresHotlinkMapper.insertSelective(newTreasuresHotlink);
		}
		writeOkToResponse();
	}

	/**
	 * 查询接口
	 * 
	 * @author sh
	 * @version V1.0
	 * @throws Exception
	 */
	@RequestMapping("findAll")
	public void findAll() throws Exception {
		String projectType = getParameter("projectType");
		if (StringUtil.isEmpty(projectType)) {
			projectType = APIConstant.STRING[1];
		}

		TreasuresHotlinkExample hotLinkExample = new TreasuresHotlinkExample();
		hotLinkExample.createCriteria().andIdIsNotNull().andProjecttypeEqualTo(projectType);
		hotLinkExample.setOrderByClause(" updTime DESC " + makeLimitSQL(getPageNum(), getPageSize()));
		List<TreasuresHotlink> hotLinks = treasuresHotlinkMapper.selectByExample(hotLinkExample);
		JSONObject jsonObject = getResultOKJSONObject();
		jsonObject.put("hotLinks", hotLinks);
		writeOkToResponse(jsonObject);
	}

}

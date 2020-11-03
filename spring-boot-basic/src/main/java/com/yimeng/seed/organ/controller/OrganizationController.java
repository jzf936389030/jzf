package com.yimeng.seed.organ.controller;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yimeng.seed.organ.exception.NotFoundException;
import com.yimeng.seed.organ.mapper.OrganizationMapper;
import com.yimeng.seed.organ.model.Organization;
import com.yimeng.seed.organ.model.ResponseListWraper;

@RestController
@RequestMapping("organization")
@Api(value = "组织机构控制器")
public class OrganizationController {
	@Autowired
	private OrganizationMapper organizationService;

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	@ApiOperation(value = "分页列表查询")
	public ResponseListWraper<Organization> list(
			@ApiParam(value = "当前页码") @RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@ApiParam(value = "每页条数") @RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Organization> list = organizationService.selectByExample(null);
		PageInfo<Organization> page = new PageInfo<Organization>(list);
		//
		ResponseListWraper<Organization> response = new ResponseListWraper<Organization>();
		response.setTotal(page.getTotal());
		response.setList(list);
		return response;
	}

	@RequestMapping(value = "/{orgid}", method = RequestMethod.GET)
	@ApiOperation(value = "根据主键查询")
	public Organization getById(
			@ApiParam(value = "主键") @PathVariable long orgid) {
		Organization entity = organizationService.selectByPrimaryKey(orgid);
		if (entity == null) {
			throw new NotFoundException("not found");
		}
		return entity;
	}

	@RequestMapping(value = "/{orgid}", method = { RequestMethod.PUT,
			RequestMethod.POST })
	@ApiOperation(value = "根据主键修改")
	public Organization updateById(
			@ApiParam(value = "主键") @PathVariable long orgid,
			@ApiParam(value = "实体类") @RequestBody @Valid Organization entity) {
		entity.setOrgid(orgid);
		organizationService.updateByPrimaryKeySelective(entity);
		return entity;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ApiOperation(value = "添加")
	public Organization add(
			@ApiParam(value = "实体类") @RequestBody @Valid Organization organization) {
		organization.setOrgid(1L);
		organizationService.insertSelective(organization);
		return organization;
	}

	@RequestMapping(value = "/{orgid}", method = RequestMethod.DELETE)
	@ApiOperation(value = "通过主键删除")
	public Organization deleteById(
			@ApiParam(value = "主键") @PathVariable Long orgid) {
		Organization entity = organizationService.selectByPrimaryKey(orgid);
		if (entity == null) {
			throw new NotFoundException("not found");
		}
		organizationService.deleteByPrimaryKey(orgid);
		return entity;
	}
	@RequestMapping(value = "/abc", method = RequestMethod.GET)
	@ApiOperation(value = "/abc")
	public String abc(){
		return "abc";
	}

}

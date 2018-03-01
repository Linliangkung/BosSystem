package com.jsako.bos.domain;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

/**
 * 权限实体
 */

public class Function implements java.io.Serializable,Comparable<Function> {

	// Fields

	private String id;
	private Function parentFunction;//当前权限的上级权限
	private String name;
	private String code;
	private String description;
	private String pageResource;
	private String generatemenu;//是否生成菜单，1：是 0：否
	private Integer zindex;
	private Set roles = new HashSet(0);//当前权限对应的多个角色
	private Set children = new HashSet(0);//当前权限的下级权限
	public String getText(){
		return name;
	}
	
	public String getPage(){
		return pageResource;
	}
	
	public String getpId(){
		if(parentFunction!=null&&StringUtils.isNotBlank(parentFunction.getId())){
			return parentFunction.getId();
		}
		return "0";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Function getParentFunction() {
		return parentFunction;
	}
	public void setParentFunction(Function parentFunction) {
		this.parentFunction = parentFunction;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPageResource() {
		return pageResource;
	}
	public void setPageResource(String page) {
		this.pageResource = page;
	}
	public String getGeneratemenu() {
		return generatemenu;
	}
	public void setGeneratemenu(String generatemenu) {
		this.generatemenu = generatemenu;
	}
	public Integer getZindex() {
		return zindex;
	}
	public void setZindex(Integer zindex) {
		this.zindex = zindex;
	}
	public Set getRoles() {
		return roles;
	}
	public void setRoles(Set roles) {
		this.roles = roles;
	}
	public Set getChildren() {
		return new TreeSet<Function>(children);
	}
	public void setChildren(Set children) {
		this.children = children;
	}

	@Override
	public int compareTo(Function o) {
		return this.zindex.compareTo(o.zindex);
	}
	
}
package com.safesmart.safesmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safesmart.safesmart.dto.RoleDto;
import com.safesmart.safesmart.service.RoleService;

@RestController
@RequestMapping(value = "/role")
@CrossOrigin
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void add(@RequestBody RoleDto roleDto) {
		roleService.add(roleDto);
	}
	
	@RequestMapping(value = "/{roleId}", method = RequestMethod.PUT)
	public void update(@PathVariable("roleId") Long id,@RequestBody RoleDto roleDto) {
		roleDto.setId(id);
		roleService.upDate(roleDto);
	}
	
	@RequestMapping(value="/{roleId}",method = RequestMethod.DELETE)
	public void delete(@PathVariable("roleId") Long id) {
		roleService.toDelete(id);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<RoleDto> findAll() {
		return roleService.findAll();
	}
	
}

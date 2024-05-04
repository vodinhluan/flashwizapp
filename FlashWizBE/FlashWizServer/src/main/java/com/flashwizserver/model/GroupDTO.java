package com.flashwizserver.model;

public class GroupDTO {
	private Integer id;
	private String groupName;
	private String groupCode;

	public GroupDTO(Integer id, String groupName, String groupCode) {
		this.id = id;
		this.groupName = groupName;
		this.groupCode = groupCode;
	}

	public GroupDTO() {

	}

	public static GroupDTO fromGroup(Group group) {
		GroupDTO dto = new GroupDTO();
		dto.setId(group.getId());
		dto.setGroupName(group.getGroupName());
		dto.setGroupCode(group.getGroupCode());
		return dto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

}

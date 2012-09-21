package com.cisco.pmonitor.service;

import java.util.List;
import java.util.Map;

import com.cisco.pmonitor.dao.dataobject.GroupDo;
import com.cisco.pmonitor.dao.query.GroupQuery;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

public interface IGroupService {

	public Result<Integer> addGroup(GroupDo groupDo) throws ServiceException;
	
	public Result<Integer> updateGroup(GroupDo groupDo) throws ServiceException;
	
	public Result<Integer> deleteGroup(int id) throws ServiceException;
	
	public Result<GroupDo> findGroupById(int id) throws ServiceException;
	
	public Result<GroupDo> findGroupByName(String name) throws ServiceException;
	
	public Result<List<GroupDo>> loadAllGroups() throws ServiceException;
	
	public Result<List<GroupDo>> loadGroupsByRoomId(int roomId) throws ServiceException;
	
	public Result<Map<String, Object>> loadGroupsByQuery(GroupQuery query)
										throws ServiceException;
}

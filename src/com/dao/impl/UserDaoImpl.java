package com.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.UserDao;


public class UserDaoImpl implements UserDao {

	@Override
	public List<Map> findUsers() {
		// 业务逻辑层 (model)
		List<Map> datas = new ArrayList<Map>();
		Map map1 = new HashMap();
		map1.put("name", "zs");
		map1.put("id", "1");

		Map map2 = new HashMap();
		map2.put("name", "lisi");
		map2.put("id", "2");

		datas.add(map1);
		datas.add(map2);

		return datas;
	}

}

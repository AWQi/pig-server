package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.BreederMapper;
import com.mapper.PigstyMapper;
import com.pojo.Breeder;
import com.pojo.BreederExample;
import com.pojo.BreederExample.Criteria;
import com.pojo.Pigsty;
import com.pojo.PigstyExample;

@Service
public class BreederService {

	@Autowired	
	private BreederMapper breederMapper ;
		/**
		 *    查询所有
		 * @param earlabel
		 * @return
		 */
	public List<Breeder> queryAll() {
			BreederExample example = new BreederExample();
			Criteria criteria = example.createCriteria();
			criteria.andIdIsNotNull();
			List<Breeder> list = breederMapper.selectByExample(example);
			return list;
		}

	

}

package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.PigstyMapper;
import com.pojo.BreedingPig;
import com.pojo.BreedingPigExample;
import com.pojo.Pigsty;
import com.pojo.PigstyExample;
import com.pojo.PigstyExample.Criteria;

@Service
public class PigstyService {
@Autowired	
private PigstyMapper pigstyMapper ;
	/**
	 *    查询所有
	 * @param earlabel
	 * @return
	 */
	public List<Pigsty> queryAll() {
		PigstyExample example = new PigstyExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIsNotNull();
		List<Pigsty> list = pigstyMapper.selectByExample(example);
		return list;
	}

}

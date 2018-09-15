package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.BreedingPigMapper;
import com.mapper.ChangePigstyBreedMapper;
import com.mapper.SwineBreedingMapper;
import com.pojo.BreedingPig;
import com.pojo.BreedingPigExample;
import com.pojo.BreedingPigExample.Criteria;
import com.pojo.ChangePigstyBreed;
import com.pojo.SwineBreeding;

/**
 * 
 *   种猪
 * @author Dell
 *
 */
@Service
public class BreedingPigService {
@Autowired
private BreedingPigMapper breedingPigMapper ;
@Autowired
private ChangePigstyBreedMapper changePigstyBreedMapper;
@Autowired
private SwineBreedingMapper swineBreedingMapper;




	/**
	 *    查询所有
	 * @param earlabel
	 * @return
	 */
	public List<BreedingPig> queryAll() {
		BreedingPigExample example = new BreedingPigExample();
		Criteria criteria = example.createCriteria();
		criteria.andEarlabelIsNotNull();
		List<BreedingPig> list = breedingPigMapper.selectByExample(example);
		return list;
	}

/**
 *   根据耳标查询
 * @param earlabel
 * @return
 */
	public BreedingPig queryByEarlabel(Integer earlabel) {
		BreedingPigExample example = new BreedingPigExample();
		Criteria criteria = example.createCriteria();
		criteria.andEarlabelEqualTo(earlabel);
		List<BreedingPig> list = breedingPigMapper.selectByExample(example);
		return list.get(0);	
	}
	/**
	 * 
	 *   录入 种猪 
	 * @param breedingPig
	 */
	public void addBreedingPig(BreedingPig breedingPig) {
		
		breedingPigMapper.insert(breedingPig);
		
	}
	/**
	 * 
	 *  耳标查询
	 * @param earlabel
	 */
	public void  deleteBreedingPigByEarlabel(int earlabel) {
		breedingPigMapper.deleteByPrimaryKey(earlabel);
		
	}
	/**
	 *  换舍
	 * @param changePigstyBreed
	 */
	public void exchangePigsty(ChangePigstyBreed changePigstyBreed) {
		
		
		BreedingPig breedingPig = breedingPigMapper.selectByPrimaryKey(changePigstyBreed.getEarlabel());
		breedingPig.setPigstyMessage(changePigstyBreed.getInPigsty());
		breedingPigMapper.updateByPrimaryKey(breedingPig);
		
		changePigstyBreedMapper.insert(changePigstyBreed);
		
		
	}
	/**
	 * 
	 *  配种1
	 * @param swineBreeding
	 */
	
	public void swineBreeding(SwineBreeding swineBreeding) {
		swineBreedingMapper.insert(swineBreeding);
	}
	
	
	
}

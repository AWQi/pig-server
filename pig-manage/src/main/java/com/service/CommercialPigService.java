package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.BreedingPigMapper;
import com.mapper.ChangePigstyBreedMapper;
import com.mapper.ChangePigstyCommercialMapper;
import com.mapper.CommercialPigMapper;
import com.mapper.SaleMapper;
import com.mapper.SwineBreedingMapper;
import com.pojo.BreedingPig;
import com.pojo.BreedingPigExample;
import com.pojo.ChangePigstyBreed;
import com.pojo.ChangePigstyCommercial;
import com.pojo.CommercialPig;
import com.pojo.CommercialPigExample;
import com.pojo.CommercialPigExample.Criteria;
import com.pojo.Sale;
import com.pojo.SwineBreeding;

/**
 * 
 * 
 *   商品猪
 * @author Dell
 *
 */
@Service
public class CommercialPigService {
	@Autowired
	private CommercialPigMapper commercialPigMapper ;
	@Autowired
	private ChangePigstyCommercialMapper changePigstyCommercialMapper;
	@Autowired
	private SaleMapper saleMapper;




		/**
		 *    查询所有
		 * 
		 * @return
		 */
		public List<CommercialPig> queryAll() {
			CommercialPigExample example = new CommercialPigExample();
			Criteria criteria = example.createCriteria();
//			criteria.andEarlabelIsNotNull();
			List<CommercialPig> list = commercialPigMapper.selectByExample(example);
			return list;
		}

	/**
	 *   根据耳标查询
	 * @param earlabel  batch_number
	 * @return
	 */
		public CommercialPig queryByEarlabel(Integer earlabel) {
		
			CommercialPigExample example = new CommercialPigExample();
			Criteria criteria = example.createCriteria();
			criteria.andEarlabelEqualTo(earlabel);
			List<CommercialPig> list = commercialPigMapper.selectByExample(example);
			return list.get(0);	
		}
		/**
		 * 
		 *   录入 种商品猪
		 * @param breedingPig
		 */
		public void addCommercialPig(CommercialPig commercialPig) {
			
			commercialPigMapper.insert(commercialPig);
			
		}
		/**
		 * 
		 *  批次号删除
		 * @param earlabel
		 */
		public void  deleteCommercialPigByBatchNumber(int batchNumber) {
			commercialPigMapper.deleteByPrimaryKey(batchNumber);
			
		}
		/**
		 *  换舍
		 * @param changePigstyBreed
		 */
		public void exchangePigsty(ChangePigstyCommercial changePigstyCommercial) {
			
			
			CommercialPig commercialPig = commercialPigMapper.selectByPrimaryKey(changePigstyCommercial.getBatchNumber());
			commercialPig.setPigstyMessage(changePigstyCommercial.getInPigsty());
			commercialPigMapper.updateByPrimaryKey(commercialPig);		                                                                                                                                                                                                                                                                                                                                     
			changePigstyCommercialMapper.insert(changePigstyCommercial);
			
			
		}
		/**
		 * 
		 *  销售
		 * @param 
		 */
		
		public void saleCommercialPig(Sale sale) {
			saleMapper.insert(sale);
		}
		
		
			
	
	

}

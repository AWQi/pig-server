package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.JsonUtils;
import com.common.PigResult;
import com.mapper.ChangePigstyBreedMapper;
import com.mapper.CommercialPigMapper;
import com.mapper.SaleMapper;
import com.pojo.ChangePigstyBreed;
import com.pojo.ChangePigstyCommercial;
import com.pojo.CommercialPig;
import com.pojo.CommercialPigExample;
import com.pojo.Sale;
import com.service.CommercialPigService;
import com.pojo.CommercialPigExample.Criteria;

@RequestMapping("CommecialPig")
@Controller
public class CommecialPigController {

	@Autowired
	private CommercialPigService commercialPigService ;
//	@Autowired
//	private ChangePigstyBreedMapper changePigstyBreedMapper;
//	@Autowired
//	private SaleMapper saleMapper;
//



		/**
		 *    查询所有
		 * 
		 * @return
		 */
	@RequestMapping("queryAll")
	@ResponseBody
		public PigResult queryAll() {
			List<CommercialPig> commercialPigs = commercialPigService.queryAll();	
			return new PigResult(commercialPigs);
		}

	/**
	 *   根据耳标查询
	 * @param earlabel  batch_number
	 * @return
	 */
	@RequestMapping("queryByEarlabel")
	@ResponseBody
		public PigResult queryByEarlabel(@RequestParam("earlabel")Integer earlabel) {
			CommercialPig commercialPig = commercialPigService.queryByEarlabel(earlabel);
			
			return new PigResult(commercialPig);	
		}
		/**
		 * 
		 *   录入 种商品猪
		 * @param breedingPig
		 */
	@RequestMapping("add")
	@ResponseBody
		public PigResult addCommercialPig(@RequestParam("commercialPigStr")String commercialPigStr) {
			CommercialPig commercialPig = JsonUtils.jsonToPojo(commercialPigStr, CommercialPig.class);
			commercialPigService.addCommercialPig(commercialPig);
			return new PigResult();
			
		}
		/**
		 * 
		 *  批次号删除
		 * @param earlabel
		 */
	@RequestMapping("deleteByBatchNumber")
	@ResponseBody
		public PigResult  deleteBreedingPigByBatchNumber(@RequestParam("batchNumber")int batchNumber) {
			commercialPigService.deleteCommercialPigByBatchNumber(batchNumber);
			return new PigResult();
		}
		/**
		 *  换舍
		 * @param changePigstyCommercial
		 */
	@RequestMapping("exchangePigsty")
	@ResponseBody
		public PigResult exchangePigsty(@RequestParam("changePigstyCommercialStr")String changePigstyCommercialStr) {
			
			ChangePigstyCommercial changePigstyCommercial  = JsonUtils.jsonToPojo(changePigstyCommercialStr, ChangePigstyCommercial.class);
			commercialPigService.exchangePigsty(changePigstyCommercial);                                                                                                                                                                                                                                                                                                                                     
			
			return new PigResult();
		}
		/**
		 * 
		 *  销售
		 * @param 
		 */
		@RequestMapping("saleCommercialPig")
		@ResponseBody
		public PigResult saleCommercialPig(@RequestParam("saleStr")String saleStr) {
			Sale sale = JsonUtils.jsonToPojo(saleStr, Sale.class);
			commercialPigService.saleCommercialPig(sale);
			return new PigResult();
			
		}
		
		
			
	
}

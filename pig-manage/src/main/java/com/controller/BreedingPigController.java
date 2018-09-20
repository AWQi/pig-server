package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.JsonUtils;
import com.common.PigResult;
import com.pojo.BreedingPig;
import com.pojo.BreedingPigExample;
import com.pojo.ChangePigstyBreed;
import com.pojo.SwineBreeding;
import com.pojo.BreedingPigExample.Criteria;
import com.service.BreedingPigService;

@RequestMapping("BreedingPig")
@Controller
public class BreedingPigController {
@Autowired
private BreedingPigService breedingPigService;
/**
 *    查询所有
 * @param earlabel
 * @return
 */
@RequestMapping("queryAll")
@ResponseBody
public PigResult queryAll() {
List<com.bean.BreedingPig>list =breedingPigService.queryAll();

	return new PigResult(list);
}

@RequestMapping("queryByEarlabel")
@ResponseBody
public PigResult queryByEarlabel(@RequestParam("earlabel")String earlabel) {
		breedingPigService.queryByEarlabel(earlabel);
		return new PigResult("ok");
}
/**
 * 
 *   录入 种猪 
 * @param breedingPig
 */
@RequestMapping("add")
@ResponseBody
public PigResult addBreedingPig(@RequestParam("breedingPigStr")String breedingPigStr) {
	BreedingPig breedingPig = JsonUtils.jsonToPojo(breedingPigStr, BreedingPig.class);
	breedingPigService.addBreedingPig(breedingPig);
	return new PigResult();
	
}
/**
 * 
 *  耳标查询
 * @param earlabel
 */
@RequestMapping("deleteByEarlabel")
@ResponseBody
public PigResult  deleteBreedingPigByEarlabel(@RequestParam("earlabel")int earlabel) {
	breedingPigService.deleteBreedingPigByEarlabel(earlabel);
	return new PigResult();
}
/**
 *  换舍
 * @param changePigstyBreed
 */
@RequestMapping("exchangePigsty")
@ResponseBody
public PigResult exchangePigsty(@RequestParam("changePigstyBreedStr")String changePigstyBreedStr) {
	ChangePigstyBreed changePigstyBreed = JsonUtils.jsonToPojo(changePigstyBreedStr,ChangePigstyBreed.class);
	breedingPigService.exchangePigsty(changePigstyBreed);
	return new PigResult();	
}
/**
 * 
 *  配种
 * @param swineBreeding
 */
@RequestMapping("swineBreeding")
@ResponseBody
public PigResult swineBreeding(@RequestParam("swineBreedingStr")String swineBreedingStr) {
	SwineBreeding swineBreeding = JsonUtils.jsonToPojo(swineBreedingStr, SwineBreeding.class);
	breedingPigService.swineBreeding(swineBreeding);
	return new PigResult();
}

}

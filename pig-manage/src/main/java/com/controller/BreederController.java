package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.PigResult;
import com.pojo.Breeder;
import com.pojo.Pigsty;
import com.service.BreederService;
import com.service.PigstyService;

@RequestMapping("Breeder")
@Controller
public class BreederController {

	@Autowired
	private BreederService breederService ;

	@ResponseBody
	@RequestMapping("queryAll")
	public PigResult queryAll() {
	List<Breeder>breeders = breederService.queryAll();
	return new PigResult(breeders);
	}
	
}

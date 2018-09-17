package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.PigResult;
import com.pojo.Pigsty;
import com.service.PigstyService;

@RequestMapping("Pigsty")
@Controller
public class PigstyController {
@Autowired
private PigstyService pigstyService ;

@ResponseBody
@RequestMapping("queryAll")
public PigResult queryAll() {
List<Pigsty>pigsties = 	pigstyService.queryAll();
return new PigResult(pigsties);
}

}

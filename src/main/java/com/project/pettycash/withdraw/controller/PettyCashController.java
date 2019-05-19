package com.project.pettycash.withdraw.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.common.bean.ResponseData;
import com.project.common.constants.CommonConstant;
import com.project.common.constants.CommonConstant.RESPONSE_MESSAGE;
import com.project.common.constants.CommonConstant.RESPONSE_STATUS;
import com.project.pettycash.withdraw.persistence.entities.PettyCash;
import com.project.pettycash.withdraw.service.PettyCashService;
import com.project.pettycash.withdraw.vo.PettyCashVo;
import com.project.pettycash.withdraw.vo.PettyResVo;

@Controller
@RequestMapping("/api/petty-cash")
public class PettyCashController {

	private static final Logger log = LoggerFactory.getLogger(PettyCashController.class);

	@Autowired
	private PettyCashService pettyCashService;

	@GetMapping("/")
	@ResponseBody
	public ResponseData<PettyResVo> list() {
		log.info("PettyCashController method list...");
		ResponseData<PettyResVo> response = new ResponseData<>();
		try {
			response.setData(pettyCashService.list());
			response.setMessage(CommonConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(CommonConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setMessage(CommonConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(CommonConstant.RESPONSE_STATUS.FAILED);// TODO: handle exception
		}
		return response;
	}

	@PostMapping("/")
	@ResponseBody
	public ResponseData<?> save(@RequestBody PettyCashVo formVo) {

		log.info("PettyCashController method save {}", formVo.toString());
		ResponseData<?> response = new ResponseData<>();
		try {
			pettyCashService.save(formVo);
			response.setMessage(CommonConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(CommonConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setMessage(CommonConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(CommonConstant.RESPONSE_STATUS.FAILED);// TODO: handle exception
		}
		return response;
	}

	@PutMapping("/")
	@ResponseBody
	public ResponseData<?> update(@RequestBody PettyCashVo formVo) {
		log.info("PettyCashController method update {}", formVo.toString());
		ResponseData<?> response = new ResponseData<>();
		try {
			pettyCashService.save(formVo);
			response.setMessage(CommonConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(CommonConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setMessage(CommonConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(CommonConstant.RESPONSE_STATUS.FAILED);// TODO: handle exception
		}
		return response;
	}

	@PostMapping("/{id}/{status}")
	@ResponseBody
	public ResponseData<?> changeStatus(@PathVariable Integer id, @PathVariable String status) {
		log.info("PettyCashController method changeStatus id :{}, status {} ", id, status);
		ResponseData<?> response = new ResponseData<>();
		try {
			pettyCashService.changeStatus(id, status);
			response.setMessage(CommonConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(CommonConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setMessage(CommonConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(CommonConstant.RESPONSE_STATUS.FAILED);// TODO: handle exception
		}
		return response;
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseData<PettyCash> getForm(@PathVariable Integer id) {
		log.info("PettyCashController method getForm id :{} ", id);
		ResponseData<PettyCash> response = new ResponseData<>();
		try {
			response.setData(pettyCashService.getForm(id));
			response.setMessage(CommonConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(CommonConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setMessage(CommonConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(CommonConstant.RESPONSE_STATUS.FAILED);// TODO: handle exception
		}
		return response;
	}

	@PostMapping("/withdraw")
	@ResponseBody
	public ResponseData<?> withdraw(@RequestBody PettyCashVo formVo) {
		log.info("PettyCashController method withdraw ids :{} ", formVo.getIds().toString());
		ResponseData<?> response = new ResponseData<>();
		try {
			List<Integer> ids = formVo.getIds();
			pettyCashService.withdraw(ids);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
		}
		return response;
	}
}

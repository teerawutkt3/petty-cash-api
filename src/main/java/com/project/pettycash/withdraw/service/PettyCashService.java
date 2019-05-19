package com.project.pettycash.withdraw.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.common.constants.CommonConstant;
import com.project.common.service.EmployeeService;
import com.project.pettycash.withdraw.persistence.entities.PettyCash;
import com.project.pettycash.withdraw.persistence.repository.PettyCashReposirory;
import com.project.pettycash.withdraw.persistence.repository.jdbc.PettyCashJdbcRepository;
import com.project.pettycash.withdraw.vo.PettyCashVo;
import com.project.pettycash.withdraw.vo.PettyResVo;

@Service
public class PettyCashService {

	@Autowired
	private PettyCashReposirory pettyCashReposirory;

	@Autowired
	private PettyCashJdbcRepository pettyCashJdbcRepository;

	@Autowired
	private EmployeeService employeeService;

	public PettyResVo list(PettyCashVo formVo) {
		String empId = employeeService.getEmployeeId();
		PettyResVo vo = pettyCashJdbcRepository.count(empId);
		List<PettyCashVo> list = pettyCashJdbcRepository.findPettyCashAll(empId, formVo.getStatus());

		vo.setDatas(list);
		return vo;
	}

	@Transactional
	public void save(PettyCashVo formVo) {

		PettyCash entity = null;

		if (formVo.getId() != null) {
			Optional<PettyCash> pettyCash = pettyCashReposirory.findById(formVo.getId());
			if (pettyCash.isPresent()) {
				entity = pettyCash.get();
				entity.setUpdateDatetime(new Date());
			}
		} else {
			entity = new PettyCash();
			entity.setCraeteDatetime(new Date());
		}

		entity.setCode(formVo.getCode());
		entity.setAmount(new BigDecimal(formVo.getAmount()));
		entity.setDescription(formVo.getDescription());
		entity.setStatus(CommonConstant.PETTY_CASH_STATUS.WAIT);

		entity.setEmp_id(employeeService.getEmployeeId());
		pettyCashReposirory.save(entity);
	}

	@Transactional
	public void changeStatus(Integer id, String status) {
		Optional<PettyCash> pettyCash = pettyCashReposirory.findById(id);

		PettyCash entity = null;
		if (pettyCash.isPresent()) {
			entity = pettyCash.get();
			if (!entity.getStatus().equals(CommonConstant.PETTY_CASH_STATUS.CANCEL)
					&& !entity.getStatus().equals(CommonConstant.PETTY_CASH_STATUS.APPROVE)
					&& !entity.getStatus().equals(CommonConstant.PETTY_CASH_STATUS.SUCCESS)) {
				entity.setStatus(status);
				entity.setUpdateDatetime(new Date());
				pettyCashReposirory.save(entity);
			}

		}
	}

	public PettyCash getForm(Integer id) {
		Optional<PettyCash> pettyCash = pettyCashReposirory.findById(id);

		if (pettyCash.isPresent()) {
			return pettyCash.get();
		}

		return null;
	}

	public void withdraw(List<Integer> ids) {
		PettyCash entity = null;
		List<PettyCash> entities = new ArrayList<PettyCash>();
		for (Integer id : ids) {
			Optional<PettyCash> pettyCash = pettyCashReposirory.findById(id);
			if (pettyCash.isPresent()) {

				entity = pettyCash.get();
				entity.setStatus(CommonConstant.PETTY_CASH_STATUS.SUCCESS);
				entity.setUpdateDatetime(new Date());
				entities.add(entity);
			}
		}
		pettyCashReposirory.saveAll(entities);
	}
}

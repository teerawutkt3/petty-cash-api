package com.project.pettycash.withdraw.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.common.constants.CommonConstant;
import com.project.common.entities.Employee;
import com.project.common.entities.ParamGroup;
import com.project.common.repository.EmployeeRepository;
import com.project.common.repository.ParamGroupRepository;
import com.project.common.service.EmployeeService;
import com.project.common.utils.ConvertDateUtils;
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
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ParamGroupRepository paramGroupRepository;
	
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
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> role = auth.getAuthorities();
		
		Optional<PettyCash> pettyCash = pettyCashReposirory.findById(id);

		PettyCash entity = null;
		if (pettyCash.isPresent()) {
			entity = pettyCash.get();
			String empId = entity.getEmp_id();
			Optional<Employee> empEntity = employeeRepository.findById(Integer.valueOf(empId));
			if(!empEntity.get().getStatus().equals(CommonConstant.EMPLOYEE_STATUS.RESIGN)) {
				if (role.contains(new SimpleGrantedAuthority(CommonConstant.ROLE.MANAGER))) {
					if (!entity.getStatus().equals(CommonConstant.PETTY_CASH_STATUS.CANCEL) && !entity.getStatus().equals(CommonConstant.PETTY_CASH_STATUS.SUCCESS)) {
						entity.setStatus(status);
						entity.setUpdateDatetime(new Date());
						pettyCashReposirory.save(entity);
					}
				}else {
					if (!entity.getStatus().equals(CommonConstant.PETTY_CASH_STATUS.CANCEL) && !entity.getStatus().equals(CommonConstant.PETTY_CASH_STATUS.APPROVE) && !entity.getStatus().equals(CommonConstant.PETTY_CASH_STATUS.SUCCESS)) {
						entity.setStatus(status);
						entity.setUpdateDatetime(new Date());
						pettyCashReposirory.save(entity);
					}
				}
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

	public PettyCashVo getDetail(Integer id) {

		Optional<PettyCash> pettyCash = pettyCashReposirory.findById(id);
		PettyCashVo vo = new PettyCashVo();
		if (pettyCash.isPresent()) {
			PettyCash entiryPetty = pettyCash.get();
			vo.setId(id);
			vo.setAmount(entiryPetty.getAmount().toString());
			vo.setCode(entiryPetty.getCode());

			ParamGroup paramPetty = paramGroupRepository.findByTypeAndValue(CommonConstant.PARAM_TYPE.PETTY_CASH_STATUS, entiryPetty.getStatus());
			
			
			vo.setDescription(entiryPetty.getDescription());
			
			vo.setStatus(entiryPetty.getStatus());
			vo.setStatusDesc(paramPetty.getDescription());
			vo.setEmp_id(entiryPetty.getEmp_id());

			Optional<Employee> emp = employeeRepository.findById(Integer.valueOf(entiryPetty.getEmp_id()));
			if (emp.isPresent()) {
				vo.setName(emp.get().getFirshname() + " " + emp.get().getSurname());
				
				
				//ParamGroup paramEmp = paramGroupRepository.findByTypeAndValue(CommonConstant.PARAM_TYPE.EMPLOYEE_STATUS, emp.get().getStatus());
				vo.setEmployeeStatus(emp.get().getStatus());
			}

			String createdDate = ConvertDateUtils.formatDateToString(entiryPetty.getCraeteDatetime(),
					ConvertDateUtils.DD_MM_YYYY_HHMMSS, ConvertDateUtils.LOCAL_EN);
			String updatedDate = ConvertDateUtils.formatDateToString(entiryPetty.getUpdateDatetime(),
					ConvertDateUtils.DD_MM_YYYY_HHMMSS, ConvertDateUtils.LOCAL_EN);

			vo.setCreateDatetime(createdDate);
			vo.setUpdateDatetime(updatedDate);
			return vo;
		}

		return null;
	}

	public void withdraw(List<Integer> ids) {
		PettyCash entity = null;
		List<PettyCash> entities = new ArrayList<PettyCash>();
		for (Integer id : ids) {
			Optional<PettyCash> pettyCash = pettyCashReposirory.findById(id);
			if (pettyCash.isPresent()) {
				if (pettyCash.get().getStatus().equals(CommonConstant.PETTY_CASH_STATUS.APPROVE)) {
					entity = pettyCash.get();
					entity.setStatus(CommonConstant.PETTY_CASH_STATUS.SUCCESS);
					entity.setUpdateDatetime(new Date());
					entities.add(entity);
				}
				
			}
		}
		pettyCashReposirory.saveAll(entities);
	}
}

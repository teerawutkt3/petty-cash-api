package com.project.pettycash.withdraw.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.project.common.constants.CommonConstant;
import com.project.common.utils.ConvertDateUtils;
import com.project.pettycash.withdraw.vo.PettyCashVo;
import com.project.pettycash.withdraw.vo.PettyResVo;

@Repository
public class PettyCashJdbcRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<PettyCashVo> findPettyCashAll(String empId, String status) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(
				" SELECT PC.*, PG.DESCRIPTION AS STATUS_DESC, EM.MANAGER_ID, EM.FIRSHNAME, EM.SURNAME  FROM PETTY_CASH PC ");
		sql.append(" INNER JOIN PARAM_GROUP PG ON PC.STATUS=PG.VALUE ");
		sql.append(" INNER JOIN EMPLOYEE EM ON EM.ID=PC.EMP_ID WHERE PG.TYPE='PETTY_CASH_STATUS'");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> roles = auth.getAuthorities();

		if (roles.contains(new SimpleGrantedAuthority(CommonConstant.ROLE.MANAMENT))) {
			sql.append(" AND  EM.MANAGER_ID=?");
			params.add(empId);
		}
		if (roles.contains(new SimpleGrantedAuthority(CommonConstant.ROLE.PETTY_CASH))) {
			sql.append(" AND  PC.STATUS=?");
			params.add(CommonConstant.PETTY_CASH_STATUS.APPROVE);
		}
		if (roles.contains(new SimpleGrantedAuthority(CommonConstant.ROLE.NORMAL))) {
			sql.append(" AND  PC.EMP_ID=?");
			params.add(empId);
		}
		if (StringUtils.isNotBlank(status)) {
			sql.append(" AND  PC.STATUS=?");
			params.add(status);
		}
		sql.append(" ORDER BY CRAETE_DATETIME DESC");
		return jdbcTemplate.query(sql.toString(), params.toArray(), pettyCashRowMapper);
	}

	protected RowMapper<PettyCashVo> pettyCashRowMapper = new RowMapper<PettyCashVo>() {
		@Override
		public PettyCashVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			PettyCashVo vo = new PettyCashVo();
			vo.setId(rs.getInt("ID"));
			vo.setCode(rs.getString("CODE"));
			vo.setEmp_id(rs.getString("EMP_ID"));
			vo.setDescription(rs.getString("DESCRIPTION"));
			vo.setAmount(rs.getString("amount"));
			vo.setStatus(rs.getString("STATUS"));
			vo.setStatusDesc(rs.getString("STATUS_DESC"));

			String createdDate = ConvertDateUtils.formatDateToString(rs.getTimestamp("CRAETE_DATETIME"),
					ConvertDateUtils.DD_MM_YYYY_HHMMSS, ConvertDateUtils.LOCAL_EN);
			vo.setCreateDatetime(createdDate);

			vo.setName(rs.getString("FIRSHNAME") + " " + rs.getString("SURNAME"));

			return vo;
		}
	};

	public PettyResVo count(String empId) {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> roles = auth.getAuthorities();

		sql.append(" SELECT  ");
		sql.append("    COUNT(PC.STATUS) AS COUNTALL,");
		sql.append(" 	(SELECT COUNT(PC.STATUS) FROM PETTY_CASH PC INNER JOIN EMPLOYEE EM ON EM.ID=PC.EMP_ID WHERE PC.STATUS = '1'");
		if (!roles.contains(new SimpleGrantedAuthority(CommonConstant.ROLE.MANAMENT))) {
			sql.append(" AND  PC.EMP_ID=? ");
			params.add(empId);
		}else {
			sql.append(" AND EM.MANAGER_ID=? ");
			params.add(empId);
		}
		sql.append(" ) AS COUNTWAIT, ");
		sql.append(" 	(SELECT COUNT(PC.STATUS) FROM PETTY_CASH PC INNER JOIN EMPLOYEE EM ON EM.ID=PC.EMP_ID WHERE PC.STATUS = '2'");
		if (!roles.contains(new SimpleGrantedAuthority(CommonConstant.ROLE.MANAMENT))) {
			sql.append(" AND PC. EMP_ID=? ");
			params.add(empId);
		}else {
			sql.append(" AND EM.MANAGER_ID=? ");
			params.add(empId);
		}
		sql.append(" ) AS COUNTAPROVE, ");
		sql.append(" 	(SELECT COUNT(PC.STATUS) FROM PETTY_CASH  PC INNER JOIN EMPLOYEE EM ON EM.ID=PC.EMP_ID WHERE PC.STATUS = '3'");
		if (!roles.contains(new SimpleGrantedAuthority(CommonConstant.ROLE.MANAMENT))) {
			sql.append(" AND  PC.EMP_ID=? ");
			params.add(empId);
		}else {
			sql.append(" AND EM.MANAGER_ID=? ");
			params.add(empId);
		}
		sql.append(" ) AS COUNTNOTAPPROVE, ");
		sql.append(" 	(SELECT COUNT(PC.STATUS) FROM PETTY_CASH PC INNER JOIN EMPLOYEE EM ON EM.ID=PC.EMP_ID WHERE PC.STATUS = '4'");
		if (!roles.contains(new SimpleGrantedAuthority(CommonConstant.ROLE.MANAMENT))) {
			sql.append(" AND  PC.EMP_ID=? ");
			params.add(empId);
		}else {
			sql.append(" AND EM.MANAGER_ID=? ");
			params.add(empId);
		}
		sql.append(" ) AS COUNTCANCEL, ");
		sql.append(" 	(SELECT COUNT(PC.STATUS) FROM PETTY_CASH  PC INNER JOIN EMPLOYEE EM ON EM.ID=PC.EMP_ID WHERE PC.STATUS = '5'");
		if (!roles.contains(new SimpleGrantedAuthority(CommonConstant.ROLE.MANAMENT))) {
			sql.append(" AND  PC.EMP_ID=? ");
			params.add(empId);
		}else {
			sql.append(" AND EM.MANAGER_ID=? ");
			params.add(empId);
		}
		sql.append(" ) AS COUNTSUCCESS ");
		sql.append(" FROM PETTY_CASH PC INNER JOIN EMPLOYEE EM ON EM.ID=PC.EMP_ID  WHERE 1=1 ");

		if (roles.contains(new SimpleGrantedAuthority(CommonConstant.ROLE.MANAMENT))) {
			sql.append(" AND  EM.MANAGER_ID=?");
			params.add(empId);
		}
		if (roles.contains(new SimpleGrantedAuthority(CommonConstant.ROLE.PETTY_CASH))) {
			sql.append(" AND  PC.STATUS=?");
			params.add(CommonConstant.PETTY_CASH_STATUS.APPROVE);
		}
		if (roles.contains(new SimpleGrantedAuthority(CommonConstant.ROLE.NORMAL))) {
			sql.append(" AND  PC.EMP_ID=?");
			params.add(empId);
		}

		sql.append(" AND  PC.STATUS LIMIT 1 ");
		List<PettyResVo> result = jdbcTemplate.query(sql.toString(), params.toArray(), countRowMapper);
		if (result.isEmpty()) {
			return new PettyResVo();
		}
		return result.get(0);
	}

	protected RowMapper<PettyResVo> countRowMapper = new RowMapper<PettyResVo>() {
		@Override
		public PettyResVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			PettyResVo vo = new PettyResVo();
			vo.setCountAll(rs.getInt("COUNTALL"));
			vo.setCountWait(rs.getInt("COUNTWAIT"));
			vo.setCountApprove(rs.getInt("COUNTAPROVE"));
			vo.setCountNotApprove(rs.getInt("COUNTNOTAPPROVE"));
			vo.setCountCancel(rs.getInt("COUNTCANCEL"));
			vo.setCountSuccess(rs.getInt("COUNTSUCCESS"));

			return vo;
		}
	};

}

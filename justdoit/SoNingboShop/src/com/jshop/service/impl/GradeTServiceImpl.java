package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.GradeTDaoImpl;
import com.jshop.entity.GradeT;
import com.jshop.service.GradeTService;
@Service("gradeTServiceImpl")
@Scope("prototype")
public class GradeTServiceImpl implements GradeTService {
	@Resource(name="gradeTDaoImpl")
	private GradeTDaoImpl gradeTDaoImpl;


	public GradeTDaoImpl getGradeTDaoImpl() {
		return gradeTDaoImpl;
	}

	public void setGradeTDaoImpl(GradeTDaoImpl gradeTDaoImpl) {
		this.gradeTDaoImpl = gradeTDaoImpl;
	}

	public int delGradet(String[] list) {
		return this.getGradeTDaoImpl().delGradet(list);
	}

	public int addGradet(GradeT gt) {
		return this.getGradeTDaoImpl().addGradet(gt);
	}

	public int countfindAllGrade() {
		return this.getGradeTDaoImpl().countfindAllGrade();
	}

	public List<GradeT> findAllGrade(int currentPage, int lineSize) {
		return this.getGradeTDaoImpl().findAllGrade(currentPage, lineSize);
	}

	public List<GradeT> findAllGradeByValue(String gradevalue) {
		return this.getGradeTDaoImpl().findAllGradeByValue(gradevalue);
	}

	public GradeT findGradeById(String gradeid) {
		return this.getGradeTDaoImpl().findGradeById(gradeid);
	}

	public int updateGradeById(GradeT gt) {
		return this.getGradeTDaoImpl().updateGradeById(gt);
	}
}

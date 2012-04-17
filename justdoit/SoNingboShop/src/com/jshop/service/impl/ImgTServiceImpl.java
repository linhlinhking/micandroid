package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.ImgTDaoImpl;
import com.jshop.entity.ImgT;
import com.jshop.service.ImgTService;

@Service("imgTServiceImpl")
@Scope("prototype")
public class ImgTServiceImpl implements ImgTService {
	@Resource(name="imgTDaoImpl")
	private ImgTDaoImpl imgTDaoImpl;

	public ImgTDaoImpl getImgTDaoImpl() {
		return imgTDaoImpl;
	}

	public void setImgTDaoImpl(ImgTDaoImpl imgTDaoImpl) {
		this.imgTDaoImpl = imgTDaoImpl;
	}

	public int addImgT(ImgT i) {
		return this.getImgTDaoImpl().addImgT(i);
	}

	public int countfindAllImgT() {
		return this.getImgTDaoImpl().countfindAllImgT();
	}

	public int delImgT(String[] list) {
		return this.getImgTDaoImpl().delImgT(list);
	}

	public List<ImgT> findAllImgT(int currentPage, int lineSize) {
		return this.getImgTDaoImpl().findAllImgT(currentPage, lineSize);
	}

	public int updateImgT(ImgT i) {
		return this.getImgTDaoImpl().updateImgT(i);
	}

	public ImgT findImgTByImgName(String imgName) {
		return this.getImgTDaoImpl().findImgTByImgName(imgName);
	}

	public List<ImgT> findImgTByusedGoodsidandusedPositionId(String imgId) {
		return this.getImgTDaoImpl().findImgTByusedGoodsidandusedPositionId(imgId);
	}

	public List<ImgT> findImgTByusedGoodsid(String usedGoodsid) {
		return this.getImgTDaoImpl().findImgTByusedGoodsid(usedGoodsid);
	}

	public List<ImgT> findImgTByusedGoodsidandPositionIdisNull(String usedGoodsid) {
		return this.getImgTDaoImpl().findImgTByusedGoodsidandPositionIdisNull(usedGoodsid);
	}

	public int updateImgState(String imgId, String state) {
		return this.getImgTDaoImpl().updateImgState(imgId, state);
	}

	public int countfindAllImgTByImgName(String imgName) {
		return this.getImgTDaoImpl().countfindAllImgTByImgName(imgName);
	}

	public List<ImgT> findAllImgTByImgName(int currentPage, int lineSize, String imgName) {
		return this.getImgTDaoImpl().findAllImgTByImgName(currentPage, lineSize, imgName);
	}

	public List<ImgT> sortAllImgT(int currentPage, int lineSize, String queryString) {

		return this.getImgTDaoImpl().sortAllImgT(currentPage, lineSize, queryString);
	}
}

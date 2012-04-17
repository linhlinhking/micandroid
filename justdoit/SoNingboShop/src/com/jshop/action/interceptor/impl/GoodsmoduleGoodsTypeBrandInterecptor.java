package com.jshop.action.interceptor.impl;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Controller;
import com.jshop.action.interceptor.GoodsmoduleInterecptor;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
@ParentPackage("jshop")

@Controller("goodsmoduleGoodsTypeBrandInterecptor")
public class GoodsmoduleGoodsTypeBrandInterecptor extends MethodFilterInterceptor {
	
	@Resource(name="goodsmoduleInterecptor")
	private GoodsmoduleInterecptor goodsmoduleInterecptor;
	
	public GoodsmoduleInterecptor getGoodsmoduleInterecptor() {
		return goodsmoduleInterecptor;
	}

	public void setGoodsmoduleInterecptor(GoodsmoduleInterecptor goodsmoduleInterecptor) {
		this.goodsmoduleInterecptor = goodsmoduleInterecptor;
	}

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext=invocation.getInvocationContext();
		Map<String,Object> params=invocation.getInvocationContext().getParameters();
		if(actionContext.getName().equals("UpdateGoodsTypeTN")){
			
			if(params.get("name")!=null&&params.get("goodsTypeId")!=null){
				String []namestrs=(String[]) params.get("name");
				String []goodsTypeIdstrs=(String[])params.get("goodsTypeId");
				String goodsTypeName=namestrs[0];
				String goodsTypeId=goodsTypeIdstrs[0];
				this.getGoodsmoduleInterecptor().updateGoodsTypeBrandTname(goodsTypeName,goodsTypeId);
				this.getGoodsmoduleInterecptor().updateGoodsAttributeTgoodsTypeName(goodsTypeName,goodsTypeId);	
				this.getGoodsmoduleInterecptor().updateGoodsTypeNameBygoodsTypeId(goodsTypeName, goodsTypeId);
			}
			return invocation.invoke();
		}
	
		
		return invocation.invoke();
	}

}

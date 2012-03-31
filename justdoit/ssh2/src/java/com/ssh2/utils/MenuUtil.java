package com.ssh2.utils;

import java.util.LinkedHashSet;
import java.util.Set;

import com.ssh2.vo.menu.MenuLinked;
import com.ssh2.vo.menu.MenuModel;

public class MenuUtil {

	/**
	 * 
	 * @param models
	 * @return
	 * @author JeccyZhao
	 */
	public static Set<MenuLinked> menu2linked(Set<MenuLinked> linked, Set<MenuModel> models){
		if(models != null && models.size() > 0){
			if (linked == null) {
				linked = new LinkedHashSet<MenuLinked>();
			}
			for(MenuModel model : models){
				MenuLinked lm = new MenuLinked(model);
				if (model.getMenuParent() == null){//一级目录
					if(!linked.contains(lm)){
						linked.add(lm);
					}
					continue;
				}
				if(model.getMenuChildren().size() > 0){//二级目录
					MenuLinked root = null;
					for(MenuLinked parent : linked){
						if(parent.getMenuId().equals(model.getMenuParent().getId())){
							root = parent;
							break;
						}
					}
					root.getMenuChildren().add(new MenuLinked(model));
					linked.add(root);
				}else{//三级目录
					MenuLinked root = null;
					for(MenuLinked parent : linked){
						if(model.getMenuParent().getMenuParent() != null){
							if(parent.getMenuId().equals(model.getMenuParent().getMenuParent().getId())){
								root = parent;
								break;
							}
						}
					}
					if(root != null && root.getMenuChildren() != null){
						for(MenuLinked parent : root.getMenuChildren()){
							if(parent.getMenuId().equals(model.getMenuParent().getId())){
								parent.getMenuChildren().add(new MenuLinked(model));
								root.getMenuChildren().add(parent);
								break;
							}
						}
						linked.add(root);
					}
				}
			}
			return linked;
		}
		return null;
	}
}

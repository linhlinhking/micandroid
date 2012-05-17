package ningbo.media.core.dao;

import java.io.Serializable;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionFactoryBean;

public abstract class AbstractMyBatisBaseDao<E,PK extends Serializable> {
	
	@Resource
	protected SqlSessionFactoryBean sqlSessionFactory ;

	public SqlSessionFactoryBean getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactoryBean sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
}

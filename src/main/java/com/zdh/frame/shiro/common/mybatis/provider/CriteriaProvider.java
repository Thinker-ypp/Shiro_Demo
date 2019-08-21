package com.zdh.frame.shiro.common.mybatis.provider;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;
import tk.mybatis.mapper.provider.ExampleProvider;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.21 16:05
 */
public class CriteriaProvider extends MapperTemplate {

    private ExampleProvider exampleProvider;

    public CriteriaProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
        exampleProvider = new ExampleProvider(mapperClass, mapperHelper);
    }

    public String selectByCriteria(MappedStatement ms) {
        return exampleProvider.selectByExample(ms);
    }

    public String selectSliceByCriteria(MappedStatement ms){
        Class<?> entityClass = getEntityClass(ms);
        //将返回值修改为实体类型
        setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append("<if test=\"distinct\">distinct</if>");
        //支持查询指定列
        sql.append(SqlHelper.exampleSelectColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.exampleWhereClause());
        sql.append(SqlHelper.exampleOrderBy(entityClass));
        sql.append(limit());
        return sql.toString();
    }

    private String limit(){
        StringBuilder sql = new StringBuilder();
        sql.append("<if test=\"limit != null\">");
        sql.append("limit ${offset},${limit}");
        sql.append("</if>");
        return sql.toString();
    }
}

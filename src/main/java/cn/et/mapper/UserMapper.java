package cn.et.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
@Mapper
public interface UserMapper {
	/**
	 * 提供动态sql的内部类
	 * @author Administrator
	 *
	 */
  static class SqlProvider{
	  public String getSelectAllUserSql(Map map) {
		  Map m=(Map)map.get("user");
		  Object userName=m.get("userName");
		  Object gender=m.get("gender");
		  Integer startIndex=(Integer)map.get("startIndex");
		  Integer limit=(Integer)map.get("limit");
		  SQL sql=new SQL();
		  sql=sql.SELECT("*").FROM("t_user");
		  if(userName!=null && !"".equals(userName)) {
			  sql.WHERE("n_name like '%"+userName+"%'");
		  }
		  if(gender!=null && !"".equals(gender)) {
			  sql.AND();
			  sql.WHERE("gender = #{user.gender}");
		  }
		  String sqlStr=sql.toString()+" limit "+startIndex+","+limit;
		  System.out.println(sqlStr);
		  return sqlStr;
	  }
	  
	  public String getSelectAllUserCoutSql(Map map) {
		  Map m=(Map)map.get("user");
		  Object userName=m.get("userName");
		  Object gender=m.get("gender");
		  SQL sql=new SQL();
		  sql=sql.SELECT("count(*)").FROM("t_user");
		  if(userName!=null && !"".equals(userName)) {
			  sql.WHERE("n_name like '%"+userName+"%'");
		  }
		  if(gender!=null && !"".equals(gender)) {
			  sql.AND();
			  sql.WHERE("gender = #{user.gender}");
		  }
		  System.out.println(sql.toString());
		  return sql.toString();
	  }
	  
	  
  }
  
  @SelectProvider(type=SqlProvider.class,method="getSelectAllUserSql")
  public List<Map> selectAllUser(@Param("startIndex")Integer startIndex,
		  @Param("limit")Integer limit,@Param("user")Map map);
		  
  @SelectProvider(type=SqlProvider.class,method="getSelectAllUserCoutSql")
  public int selectAllUserCount(@Param("user")Map map);
  
  @Select("select * from t_user where id=#{id}")
  public Map selectUserById(@Param("id") String id);
  
  @Delete("delete from t_user where id=#{id}")
  public void deleteUser(@Param("id")String id);
  
  @Insert("insert into t_user values(UUID(),#{userName},#{age},#{gender})")
  public void saveUser(@Param("userName")String userName, @Param("age")String age, @Param("gender")String gender);
}

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class Generator {
	/**
	 * 测试 run 执行 注意：不生成service接口 注意：不生成service接口 注意：不生成service接口
	 * <p>
	 * 配置方法查看 {@link Generator}
	 * </p>
	 */
	public static void main(String[] args) {
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir("E:\\tmp");
		gc.setFileOverride(true);// 是否覆盖文件
		gc.setActiveRecord(true);// 开启 activeRecord(活动记录)模式
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(true);// XML columList
		gc.setOpen(false);//生成后打开文件夹
		gc.setAuthor("zhouran");
		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		// gc.setMapperName("%sDao");
		// gc.setXmlName("%sDao");
		gc.setServiceName("%sService");
		gc.setServiceImplName("%sServiceImpl");
		gc.setMapperName("%sMapper");
		// gc.setControllerName("%sAction");
		mpg.setGlobalConfig(gc);
		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("mysql");
		dsc.setUrl("jdbc:mysql://localhost:3306/easy_admin?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=PRC&useSSL=false");
		mpg.setDataSource(dsc);
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// strategy.setTablePrefix("sys_");// 此处可以修改为您的表前缀
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        //strategy.setCapitalMode(true);// 全局大写命名
        //strategy.setDbColumnUnderline(true);//全局下划线命名
//		strategy.setInclude(new String[] { "ea_admin_view","ea_admin_menu",
//				"ea_admin_column","ea_admin_component" });
		strategy.setInclude(new String[] { "ea_table_info","ea_column_info"});
		// strategy.setExclude(new String[]{"test"}); // 排除生成的表
		// 自定义实体父类
		strategy.setSuperEntityClass("com.haivin.database.activerecord.EaModel");
		// 自定义实体，公共字段
//		strategy.setSuperEntityColumns(new String[] { "id_", "enable_", "remark_", "create_by", "create_time", "update_by", "update_time" });
		// 自定义 mapper 父类
//		strategy.setSuperMapperClass("org.ibase4j.core.base.BaseMapper");
		// 自定义 service 父类
//		strategy.setSuperServiceClass("org.ibase4j.core.base.BaseService");
		// 自定义 service 实现类父类
//		strategy.setSuperServiceImplClass("org.ibase4j.core.base.BaseServiceImpl");
		// 自定义 controller 父类
//		strategy.setSuperControllerClass("org.ibase4j.core.base.BaseController");
		strategy.setSuperControllerClass("com.haivin.database.controller.BaseController");
		// 【实体】是否生成字段常量（默认 false）
		// public static final String ID = "test_id";
//		 strategy.setEntityColumnConstant(true);
		// 【实体】是否为构建者模型（默认 false）
		// public User setName(String name) {this.name = name; return this;}
		strategy.setEntityBuilderModel(true);
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		mpg.setStrategy(strategy);
		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.haivin.admin.table");
//		pc.setParent("com.haivin.admin.view");
		pc.setEntity("entity");
		pc.setMapper("mapper");
		pc.setXml("mapper.xml");
		pc.setServiceImpl("service.impl");
		pc.setService("service");
		pc.setController("controller");
		mpg.setPackageInfo(pc);
		// 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
//		InjectionConfig cfg = new InjectionConfig() {
//			public void initMap() {
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("providerClass", "ISysProvider");
//				map.put("providerClassPackage", "org.ibase4j.provider.ISysProvider");
//				this.setMap(map);
//			}
//		};
//		mpg.setCfg(cfg);
		// 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/template 下面内容修改，
		// 放置自己项目的 src/main/resources/template 目录下, 默认名称一下可以不配置，也可以自定义模板名称
		TemplateConfig tc = new TemplateConfig();
		tc.setEntity("tpl/entity.java.vm");
		tc.setMapper("tpl/mapper.java.vm");
		tc.setXml("tpl/mapper.xml.vm");
		tc.setService("tpl/service.java.vm");
		tc.setServiceImpl("tpl/serviceImpl.java.vm");
		tc.setController("tpl/controller.java.vm");
		mpg.setTemplate(tc);
		// 执行生成
		mpg.execute();

//		System.err.println(mpg.getCfg().getMap().get("abc"));
	}
}

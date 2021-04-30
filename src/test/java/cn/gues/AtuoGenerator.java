package cn.gues;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AtuoGenerator {
    @Test
    void test(){
        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();
        String projectPath = System.getProperty("user.dir");
        // 配置 GlobalConfig 全局配置
        GlobalConfig gc = new GlobalConfig();
        // gc.setOutputDir("E:\\workspace\\IDEA\\schooltelecom\\src\\main\\java"); // 绝对路径写法
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("作者");
        gc.setOpen(false); // 生成后是否打开资源管理器
        gc.setFileOverride(true); // 重新生成文件时是否覆盖
        gc.setServiceName("%sService"); // 去掉Service接口的首字母I
        gc.setIdType(IdType.ASSIGN_ID); // 主键策略
        gc.setDateType(DateType.ONLY_DATE); // 定义生成的实体类中日期类型
        gc.setSwagger2(true); // 开启Swagger2模式
        gc.setBaseResultMap(true);//生成xml公共返回结果

        mpg.setGlobalConfig(gc);

        // 配置 DataSourceConfig
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/telecom?useSSL=false&serverTimezone=Asia/Shanghai");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("cn.gues"); // 包名
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);
        // 自定义配置
        InjectionConfig cfg =  new InjectionConfig(){
            @Override
            public void initMap() {

            }
        };
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return  projectPath+"/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);


        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("feedback_view");//生成表名

        // 表填充字段配置
        ArrayList<TableFill> tableFills = new ArrayList<>();
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        tableFills.add(gmtModified);
        tableFills.add(gmtCreate);
        strategy.setTableFillList(tableFills);
        // 逻辑删除属性名称
        strategy.setLogicDeleteFieldName("is_deleted");

        strategy.setNaming(NamingStrategy.underline_to_camel); // 数据库表映射到实体的命名策略
        strategy.setTablePrefix("tb_"); // 生成实体时去掉表前缀
        strategy.setColumnNaming(NamingStrategy.underline_to_camel); // 数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain=true) setter 链式操作
        strategy.setEntityTableFieldAnnotationEnable(true);//是否生成实体时，生成字段注解
        strategy.setRestControllerStyle(true); // restful api 风格
        strategy.setControllerMappingHyphenStyle(true); // url中驼峰转连字符
        mpg.setStrategy(strategy);

        // 6、执行
        mpg.execute();
    }
}

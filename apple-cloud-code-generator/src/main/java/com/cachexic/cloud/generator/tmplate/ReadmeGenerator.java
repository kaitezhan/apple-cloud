package com.cachexic.cloud.generator.tmplate;

/**
 * 测试类 的xml模板
 *
 * @author tangmin
 * @date 2016年2月26日
 */
public class ReadmeGenerator extends TemplateCodeGenerator {

  @Override
  public String getTemplateFile() {
    /**
     * 模板文件
     */
    return "/template/readme.ftl";
  }

}

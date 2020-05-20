控制层vo ==> 控制层与service层通信dto ==> service封装的do ==> 数据库表po

vo-->dto-->do-->po

简化如下::
  控制层vo（简单接口使用基本类型的封装类）==> 控制层与service层通信dto（简单接口使用基本类型的封装类）==>数据库表po
  优化后去除了dto
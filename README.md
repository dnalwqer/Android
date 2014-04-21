天气查询APP
=======  
该APP Demo展示的是一个能够实时查询北京天气的小APP应用，数据接口来源为[数据来源](http://flash.weather.com.cn/wmaps/xml/beijing.xml)。通过HttpClient对数据请求后得到XML文件利用DOM进行解析，将结果存进ArrayList中。在安卓GridView组件中自定义Adapter，天气信息会实时刷新显示在GridView中  


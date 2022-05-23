package com.hastemd.studySpringboot.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试
 * @see @InitBinder
 *
 * @author lijie
 * @date 2022/5/23 11:40
 */
@RestController
@RequestMapping("/test/binder")
public class TestInitBinderController {

    //https://blog.csdn.net/weixin_44296929/article/details/116211715
    @InitBinder
    public void InitBinder(WebDataBinder binder) {
        /**
         * 1、@InitBinder注解简介：
         *
         * @InitBinder作用于@Controller中的方法，表示为当前控制器注册一个属性编辑器，对WebDataBinder进行初始化，且只对当前的Controller有效，一般用做BaseController对数据进行预处理操作。
         *
         * 2、@InitBinder执行时机：
         *
         * @InitBinder注解被解析的时机，是其所标注的方法，在该方法被请求执行之前。同时@InitBinder标注的方法是可以多次执行的，也就是说来一次请求就执行一次@InitBinder解析。
         *
         * 3、@InitBinder执行原理：
         *
         * 当某个Controller上的第一次请求，由于SptingMVC前端控制器匹配到该Controller之后，根据Controller的class类型来查找所有标注了@InitBinder注解的方法，
         * 并且存入RequestMappingHandlerAdapter里的initBinderCache缓存中。等下一次请求执行对应业务方法之前，会先走initBinderCache缓存，而不再去解析@InitBinder。
         *
         * 4、@InitBinder的使用：
         *
         * @InitBinder注解的方法可以对WebDataBinder初始化；WebDataBinder是用于表单到方法的数据绑定的，WebDataBinder中有很多方法可以对数据绑定进行具体的设置：
         *
         * 1）比如我们设置name属性为非绑定属性（也可以设置绑定值setAllowedFields）：
         */
        //前端传入的时间格式必须是"yyyy-MM-dd"效果!
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor dateEditor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, dateEditor);

        binder.setAllowedFields("oldEmailAddress", "newEmailAddress");

    }



    @RequestMapping("/input/date")
    public String inputDate(Date date){
        return String.valueOf(date);
    }
}

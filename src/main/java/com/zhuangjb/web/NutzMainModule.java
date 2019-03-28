package com.zhuangjb.web;

import com.zhuangjb.web.action.LoginAction;
import com.zhuangjb.web.helloworld.HelloWorld;
import com.zhuangjb.web.pet.PetModule;
import org.nutz.mvc.annotation.*;

import com.zhuangjb.web.mvc.MyViewMaker;
import org.nutz.mvc.ioc.provider.JsonIocProvider;

/**
 * nutz配置
 */
@Modules
@IocBy(type = JsonIocProvider.class, args = {"ioc"})
@SetupBy(HelloMvcSetup.class)
@Localization("msg")
@Fail("json")
public class NutzMainModule {

}

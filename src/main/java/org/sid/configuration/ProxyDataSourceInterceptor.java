package org.sid.configuration;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import javax.sql.DataSource;
import java.lang.reflect.Method;

public class ProxyDataSourceInterceptor implements MethodInterceptor {

    //    private final DataSource dataSource;
    public ProxyDataSourceInterceptor(final DataSource dataSource) {
//        this.dataSource = ProxyDataSourceBuilder.create(dataSource)
//                .name("Batch-Insert-Logger")
//                .asJson().countQuery().logQueryToSysOut().build();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return null;
    }
}

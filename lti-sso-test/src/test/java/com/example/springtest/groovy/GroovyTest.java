package com.example.springtest.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/12/28 16:24
 */

public class GroovyTest {
    private static final Logger log = LoggerFactory.getLogger(GroovyTest.class);
    @Test
    public void test1() {
        GroovyShell groovyShell = new GroovyShell();
        final String s = "x  > 2  &&  y  > 1";
        Script script = groovyShell.parse(s);

        Binding binding = new Binding();
        binding.setProperty("x",5);
        binding.setProperty("y",3);
        script.setBinding(binding);

        Object result = script.run();
        Assert.assertEquals(true, result);

        log.debug("evaluating [{}] with (x,y)=({},{}) => {}\n", s,
                script.getBinding().getProperty("x"), script.getBinding().getProperty("y"), result);

        binding.setProperty("y",0);
        result = script.run();
        Assert.assertEquals(false, result);
    }
}

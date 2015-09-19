package pt.common.libs.servlet.controllers;


public class MyController {

    public String index(Integer var1, Integer var2, String x) {
        return "ola:"+var1+":"+var2+x;
    }

    public String teste() {
        return "teste";
    }
}

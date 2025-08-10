package com.abdullah.drivers;

import com.abdullah.dataReader.PropertyReader;
import org.openqa.selenium.WebDriver;

public abstract class AbstractDriver {
    protected static final String remoteHost = PropertyReader.getProperty("remoteHost");
    protected static final String remotePort = PropertyReader.getProperty("remotePort");
    protected static final String executionType = PropertyReader.getProperty("executionType");

    public abstract WebDriver createDriver();


}


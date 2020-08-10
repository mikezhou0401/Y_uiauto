package com.yunsom.base;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.html5.RemoteWebStorage;

import java.net.URL;

public class RemoteStorageWebDriver extends RemoteWebDriver implements WebStorage {
    private RemoteWebStorage webStorage;

    public RemoteStorageWebDriver(URL remoteAddress, Capabilities capabilities) {
        super(remoteAddress, capabilities);
        webStorage = new RemoteWebStorage(getExecuteMethod());
    }

    @Override
    public LocalStorage getLocalStorage() {
        return webStorage.getLocalStorage();
    }

    @Override
    public SessionStorage getSessionStorage() {
        return webStorage.getSessionStorage();
    }
}

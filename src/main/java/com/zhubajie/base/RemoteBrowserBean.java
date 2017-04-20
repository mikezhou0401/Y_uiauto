package com.zhubajie.base;

/**
 * 分布式执行－jenkins
 */
public class RemoteBrowserBean {
	private String browserName;
	private String version;
	private String[] platform;
	private String hubURL;
	private String IP = "192.168.142.7";
	public RemoteBrowserBean(){
		this.browserName="firefox";
		this.version="38";
		this.platform=new String[]{"VISTA", "windows 7"};
		this.hubURL="http://"+IP+":4444/wd/hub";
	}
	
	public RemoteBrowserBean(String browser){
		this.browserName=browser;
		this.version="42";
		this.platform=new String[]{"VISTA", "windows 7"};
		this.hubURL="http://"+IP+":4444/wd/hub";
	}
	
	public String getBrowserName() {
		return browserName;
	}
	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String[] getPlatform() {
		return platform;
	}
	public void setPlatform(String[] platform) {
		this.platform = platform;
	}
	public String getHubURL() {
		return hubURL;
	}
	public void setHubURL(String hubURL) {
		this.hubURL = hubURL;
	}
	 
}

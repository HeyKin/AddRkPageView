package xyz.heykin.ProxyCrawler.bean;

public class IpBean {

	private String ip;
	
	private int port;
	
	private String type;

	public IpBean(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}

	public IpBean(String ip, int port, String type) {
		super();
		this.ip = ip;
		this.port = port;
		this.type = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}

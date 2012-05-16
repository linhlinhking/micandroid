package ningbo.media.core.security.ip;

import java.util.ArrayList;
import java.util.Collection;

import ningbo.media.core.security.resource.SecurityUtils;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import sun.net.util.IPAddressUtil;

/**
 * Description:Control the Access of Ip Address.
 * @author Devon.Ning
 * @2012-4-18上午11:17:01
 * @version 1.0
 * <p>Copyright (c) 2012 宁波商外文化传媒有限公司,Inc. All Rights Reserved.</p>
 */
public class IPAddressVoter implements AccessDecisionVoter<Object> {

	private static Collection<String> ipAddressList = new ArrayList<String>();

	public static void put(String ip) {
		ipAddressList.add(ip);
	}

	public static void remove(String ip) {
		ipAddressList.remove(ip);
	}

	public static void removeAll() {
		ipAddressList.clear();
	}

	private boolean enabled() {
		return !ipAddressList.isEmpty();
	}

	public Collection<String> getIpAddressList() {
		return ipAddressList;
	}

	public void setIpAddressList(Collection<String> ipAddressList) {
		IPAddressVoter.ipAddressList = ipAddressList;
	}

	public boolean supports(ConfigAttribute config) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public int vote(Authentication auth, Object object,
			Collection<ConfigAttribute> config) {
		if (enabled()) {
			String remoteIPAddress = SecurityUtils.getCurrentUserIPAddress();
			if (remoteIPAddress != null && !remoteIPAddress.isEmpty()) {
				return vote(remoteIPAddress);
			}
		}
		return ACCESS_DENIED;// 弃权
	}

	private static int vote(String currentIp) {
		if (IPAddressUtil.isIPv6LiteralAddress(currentIp)) {
			return ACCESS_DENIED;// 如果是IPV6则否定
		}
		for (String ip : ipAddressList) {
			if (currentIp.equals(ip)) {
				return ACCESS_GRANTED;
			} else if (ip.indexOf("-") > -1) {
				try {
					String[] ipBlocks = ip.split("\\.");
					String[] currentIpBlocks = currentIp.split("\\.");
					int isMeet = 4;// 是否符合IP段匹配
					for (int i = 0; i < ipBlocks.length; i++) {
						if (ipBlocks[i].indexOf("-") > -1) {
							String[] ipNumbers = ipBlocks[i].split("\\-");
							int start = Integer.parseInt(ipNumbers[0]);
							int end = Integer.parseInt(ipNumbers[1]);
							int currentNumber = Integer
									.parseInt(currentIpBlocks[i]);
							if (currentNumber <= end || currentNumber >= start) {
								isMeet = isMeet - 1;
							}
						} else {
							if (currentIpBlocks[i].equals(ipBlocks[i])) {
								isMeet = isMeet - 1;
							}
						}
					}
					if (isMeet == 0) {
						return ACCESS_GRANTED;
					}
				} catch (Exception e) {
					return ACCESS_DENIED;// 登录ip验证发生意外错误则否定
				}
			}
		}
		return ACCESS_DENIED;// 登录ip验证发生意外错误则否定
	}

}

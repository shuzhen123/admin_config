package com.mzfk.utils;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class CommonUtils {
	
	/**
	 * 获取当前时间
	 * @return
	 * @createtime 2018年2月14日下午10:34:06
	 */
	@Deprecated
	public static String getCurentTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date(System.currentTimeMillis()));
	}

    /**
     * 获取当前时间
     * @return
     * @createtime 2018年2月14日下午10:34:06
     */
    public static String getCurentTimeWithZone(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        return df.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 获取当前时间的指定格式
     */
    public static String getFormatNow(String pattern) {
        return  formatTime(LocalDateTime.now(), pattern);
    }

    /**
     * 获取指定时间的指定格式
     */
    public static String formatTime(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * md5加密
     */
    public static String md5Password(String password) {

        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 获得客户端的主机 IP 地址
     * @param request
     * @return
     * @author
     * @date
     */
    public static String getRemoteHostAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    /**
     * 获得客户端的主机 域名
     * @param request
     * @return
     * @author
     * @date
     */
    public static String getRemoteHostName(HttpServletRequest request){
        String hostName = request.getServerName();
        return hostName;
    }

    /**
     * 获得东八区时间
     *
     * @return
     */
    public static String getChinaTime() {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8:00");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获得UTC 标准时间
     *
     * @return
     */
    public static String getUTCTime() {
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(new Date());
    }


}

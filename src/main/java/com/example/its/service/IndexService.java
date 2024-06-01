package com.example.its.service;

import com.example.its.repository.AccessLogRepository;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class IndexService {
    private final AccessLogRepository accessLogRepository;

    public IndexService(AccessLogRepository accessLogRepository) {
        this.accessLogRepository = accessLogRepository;
    }

    @Transactional
    public void InsertLog(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        String userAgentString = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
        Browser browser = userAgent.getBrowser();
        OperatingSystem os = userAgent.getOperatingSystem();
        DeviceType deviceType = os.getDeviceType();

        // LocalDateTime currentDateTime = LocalDateTime.now();
        ZonedDateTime jstDateTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        LocalDateTime currentDateTime = jstDateTime.toLocalDateTime();

        // タイムゾーンの取得
        ZoneId zoneId = ZoneId.systemDefault();
        String timezone = zoneId.toString();

        // リファラーの取得
        String referrer = request.getHeader("Referer");

        // System.out.println("日付="+currentDateTime+";ip="+ipAddress+";" + "ブラウザ=" + browser + ";" + "OS=" + os + ";" + "デバイスタイプ=" + deviceType);
        // リクエストURLの取得
        String requestUrl = request.getRequestURI();
        if (request.getQueryString() != null) {
            requestUrl += "?" + request.getQueryString();
        }

        accessLogRepository.insertLog(currentDateTime, ipAddress, requestUrl, userAgentString, browser.toString(), os.toString(), deviceType.toString(), timezone, referrer);
    }
}

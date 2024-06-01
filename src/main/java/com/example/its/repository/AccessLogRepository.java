package com.example.its.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface AccessLogRepository {
    @Insert("insert into issues(summary, description) values(#{summary}, #{description})")
    void insert(String summary, String description);

//    @Insert("insert into t_access_log" +
//            " values(#{currentDateTime}, #{ipAddress}, #{url}, #{browser}, #{os}, #{deviceType})")
//    void insertLog(LocalDateTime currentDateTime, String ipAddress, String url , String browser, String os, String deviceType);
    @Insert("insert into t_access_log values(#{currentDateTime}, #{ipAddress}, #{url}, #{browser}, #{os}, #{deviceType}, #{zoneId}, #{userAgent}, #{referrer})")
    void insertLog(
        @Param("currentDateTime") LocalDateTime currentDateTime,
        @Param("ipAddress") String ipAddress,
        @Param("url") String url,
        @Param("userAgent") String userAgent,
        @Param("browser") String browser,
        @Param("os") String os,
        @Param("deviceType") String deviceType,
        @Param("zoneId") String zoneId,
        @Param("referrer") String referrer
);

}

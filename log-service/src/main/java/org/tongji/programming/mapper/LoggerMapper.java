package org.tongji.programming.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author cinea
 */
public interface LoggerMapper {
    void logLogin(@Param("stuNo") String stuNo, @Param("ipAddr") String ipAddr, @Param("userAgent") String userAgent, @Param("comment") String comment);

    void logPost(@Param("postNo") Integer postNo, @Param("stuNo") String stuNo, @Param("ipAddr") String ipAddr, @Param("comment") String comment);
}

package org.tongji.programming.service;

import org.tongji.programming.pojo.Homework;
import org.tongji.programming.pojo.HomeworkUploaded;

import java.io.File;
import java.util.List;

/**
 * @author cinea
 */
public interface HomeworkService {
    Homework getHomework(String term, Integer hwId);

    List<HomeworkUploaded> getHomeworkUploaded(String boardId, boolean withHidden);

    /**
     * 编辑作业（暂时废除）
     */
    boolean postHomework(HomeworkUploaded homeworkUploaded, File file, String contentType, String filename, Long fileSize);

    String postHomework(HomeworkUploaded homeworkUploaded);
}

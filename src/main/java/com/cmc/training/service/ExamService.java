package com.cmc.training.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cmc.training.entity.Duration;
import com.cmc.training.entity.Exam;
import com.cmc.training.entity.StatusExam;
import com.cmc.training.entity.view.ViewListExam;
import com.cmc.training.util.Paging;
import com.cmc.training.util.Sorting;
import com.cmc.training.util.filter.FilterExam;

public interface ExamService {

  /**
   * @description: return list exam is content of a page object .
   * @author NVDong.
   * @param paging
   *          include page number and number per page.
   * @param sorting
   *          include sort field and sort type.
   * @return Page<ViewListExam>
   */
  Page<ViewListExam> getAllExam(Paging paging, Sorting sorting, String keywordSearch,
      FilterExam filterExam);

  /**
   * get all status
   * 
   * @return Page<StatusExam>
   * @author: NVDong
   */
  List<StatusExam> getAllStatus();

  /**
   * 
   * This method is create exam.
   * 
   * @param exam
   *          void
   * @author: NTVAnh1
   */
  Exam createExam(Exam ex);

  /**
   * This method is check List CategoryQuestion exist by id.
   * 
   * @param id
   *          - list id of CategoryQuestion need check exist.
   * @return boolean
   * @author: Vdhao
   */
  boolean isExamExists(List<Integer> ids);

  /**
   * This method is check List CategoryQuestion exist by id.
   * 
   * @param id
   *          - list id of CategoryQuestion need check exist.
   * @return boolean
   * @author: Vdhao
   */
  boolean isStatusDraftOfExam(List<Integer> ids);

  /**
   * This method is get Exam by id.
   * 
   * @param id
   *          - id of Exam need get.
   * @return exam
   * @author: VDHAO
   */
  Exam getExamById(int id);

  /**
   * This method is delete Exam
   * 
   * @param id
   *          - id of Exam need delete.
   * @author: VDhao
   */
  void deleteExam(int id);

  /**
   * 
   * This method to delete exam by id
   * 
   * @param ids
   *          void
   * @author: VDHao
   */
  void deleteExams(List<Integer> ids);

  /**
   * get all Duration
   * 
   * @return List<Duration>
   * @author: NTVAnh1
   */
  List<Duration> getAllDurations();
  

  /**
   * check Duration Existed
   * 
   * @return boolean
   * @author: NTVAnh1
   */
  boolean isDurationExist(int id);
}

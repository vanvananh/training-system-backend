package com.cmc.training.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cmc.training.entity.Duration;
import com.cmc.training.entity.Exam;
import com.cmc.training.entity.StatusExam;
import com.cmc.training.entity.view.ViewListExam;
import com.cmc.training.repository.CategoryQuestionRepository;
import com.cmc.training.repository.DurationRepository;
import com.cmc.training.repository.ExamRepository;
import com.cmc.training.repository.StatusExamRepository;
import com.cmc.training.repository.ViewListExamRepository;
import com.cmc.training.util.Constants;
import com.cmc.training.util.Constants.Operation;
import com.cmc.training.util.MethodUtil;
import com.cmc.training.util.Paging;
import com.cmc.training.util.Sorting;
import com.cmc.training.util.filter.FilterExam;
import com.cmc.training.util.specification.CriteriaCustom;
import com.cmc.training.util.specification.SpecificationBuilder;

/**
 * 
 * @author: NVDong
 * @Date: Mar 14, 2018
 */
@Service
public class ExamServiceImpl implements ExamService {

	// declare exam Repository
	@Autowired
	private ViewListExamRepository viewExamRepository;

	// declare exam Repository
	@Autowired
	private ExamRepository examRepository;

	// declare status exam Repository
	@Autowired
	private StatusExamRepository statusExamRepository;

	// declare category question Repository
	@Autowired
	private CategoryQuestionRepository categoryQuestionRepository;

	// declare duration Repository
	@Autowired
	private DurationRepository durationRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cmc.training.service.ExamService#getAllExam(com.cmc.training.util.Paging,
	 * com.cmc.training.util.Sorting, java.lang.String)
	 */
	@Override
	public Page<ViewListExam> getAllExam(Paging paging, Sorting sorting, String keywordSearch, FilterExam filterExam) {

		SpecificationBuilder<ViewListExam> specification = new SpecificationBuilder<ViewListExam>();

		// get all record don't deleted
		specification.addCriteriaCustom(
				new CriteriaCustom(Constants.NameColume.IS_DELETED, Constants.Operation.EQUAL, false));

		// search
		if (!MethodUtil.isNull(keywordSearch)) {
			specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.EXAM_TITLE_SEARCH,
					Constants.Operation.LIKE, keywordSearch));
		}

		// filter
		if (!MethodUtil.isNull(filterExam)) {
			// categoryId
			if (!MethodUtil.isNull(filterExam.getCategoryName())) {
				specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.EXAM_TYPE_FILTER,
						Operation.EQUAL, filterExam.getCategoryName()));
			}
			// time from
			if (!MethodUtil.isNull(filterExam.getDurationValueFrom())) {
				specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.EXAM_TIMES_FILTER,
						Operation.GREATER_THAN_OR_EQUAL_INT, filterExam.getDurationValueFrom()));
			}
			// time to
			if (!MethodUtil.isNull(filterExam.getDurationValueTo())) {
				specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.EXAM_TIMES_FILTER,
						Operation.LESS_THAN_OR_EQUAL_INT, filterExam.getDurationValueTo()));
			}
			// questions from
			if (!MethodUtil.isNull(filterExam.getNumberOfQuestionFrom())) {
				specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.EXAM_QUESTIONS_FILTER,
						Operation.GREATER_THAN_OR_EQUAL_INT, filterExam.getNumberOfQuestionFrom()));
			}
			// questions to
			if (!MethodUtil.isNull(filterExam.getNumberOfQuestionTo())) {
				specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.EXAM_QUESTIONS_FILTER,
						Operation.LESS_THAN_OR_EQUAL_INT, filterExam.getNumberOfQuestionTo()));
			}
			// create date from
			if (!MethodUtil.isNull(filterExam.getCreateDateFrom())) {
				specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.EXAM_CREATE_DATE_FILTER,
						Operation.GREATER_THAN_OR_EQUAL_DATE, filterExam.getCreateDateFrom()));
			}
			// create date to
			if (!MethodUtil.isNull(filterExam.getCreateDateTo())) {
				specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.EXAM_CREATE_DATE_FILTER,
						Operation.LESS_THAN_OR_EQUAL_DATE, filterExam.getCreateDateTo()));
			}

			// user
			if (!MethodUtil.isNull(filterExam.getFullname())) {
				specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.EXAM_CREATE_USER_FILTER,
						Operation.EQUAL, filterExam.getFullname()));
			}

			// status
			if (!MethodUtil.isNull(filterExam.getStatusName())) {
				specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.EXAM_STATUS_FILTER,
						Operation.EQUAL, filterExam.getStatusName()));
			}
		}

		// convert sorting field search
		sorting.convertSort(Constants.NameColume.EXAM_TITLE, Constants.NameColume.EXAM_TITLE_SEARCH);
		sorting.convertSort(Constants.NameColume.CATEGORY_NAME, Constants.NameColume.CATEGORY_NAME_SEARCH);
		sorting.convertSort(Constants.NameColume.FULL_NAME, Constants.NameColume.FULL_NAME_SEARCH);
		// query extra sort field examId
		sorting.and(Direction.ASC, Constants.NameColume.EXAM_ID);

		// query
		return viewExamRepository.findAll(specification.build(), MethodUtil.Pagination(paging, sorting));
	}

	@Override
	public List<StatusExam> getAllStatus() {
		return statusExamRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cmc.training.service.ExamService#createExam(com.cmc.training.entity.Exam)
	 */
	@Override
	public Exam createExam(Exam ex) {
		Exam exam = examRepository.save(ex);
		exam.setCategoryId(categoryQuestionRepository.findOne(exam.getCategoryId().getCategoryId()));
		String code = exam.getCategoryId().getCategoryCode().concat("_" + exam.getExamId().toString());
		exam.setCode(code);
		return examRepository.save(exam);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmc.training.service.ExamService#isExamExists(java.util.List)
	 */
	@Override
	public boolean isExamExists(List<Integer> ids) {
		SpecificationBuilder<Exam> specification = new SpecificationBuilder<Exam>();
		specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.EXAM_ID, Constants.Operation.IN, ids));
		return examRepository.count(specification.build()) == (long) ids.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmc.training.service.ExamService#isStatusDraftOfExam(java.util.List)
	 */
	@Override
	public boolean isStatusDraftOfExam(List<Integer> ids) {
		SpecificationBuilder<Exam> specification = new SpecificationBuilder<Exam>();

		// exam id in list ids
		specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.EXAM_ID, Constants.Operation.IN, ids));

		// add condition statusExam = draft
		specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.EXAM_STATUS_FILTER, Operation.EQUAL,
				Constants.Common.DRAFT_STATUS_IN_EXAM));
		return examRepository.count(specification.build()) == (long) ids.size();
	}

	@Override
	public Exam getExamById(int id) {
		return examRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmc.training.service.ExamService#deleteExams(java.util.List)
	 */
	@Override
	public void deleteExams(List<Integer> ids) {
		for (Integer id : ids) {
			deleteExam(id);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmc.training.service.ExamService#deleteExam(int)
	 */
	@Override
	public void deleteExam(int id) {
		Exam examCurrent = examRepository.findOne(id);
		examRepository.delete(examCurrent);
	}

	public List<Integer> getAllDuration() {
		List<Integer> duration = new ArrayList<>();
		duration.add(15);
		duration.add(30);
		return duration;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmc.training.service.ExamService#getAllDurations()
	 */
	@Override
	public List<Duration> getAllDurations() {
		return durationRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cmc.training.service.ExamService#isDurationExist(int)
	 */
	@Override
	public boolean isDurationExist(int id) {
		// TODO Auto-generated method stub
		return durationRepository.exists(id);
	}

}

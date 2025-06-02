package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.dto.QuestionerEditDTO;
import com.svyter.spring.swimingbysvyter.dto.base.ResponseDTO;
import com.svyter.spring.swimingbysvyter.entity.Complexity;
import com.svyter.spring.swimingbysvyter.exception.NotFoundDataException;
import com.svyter.spring.swimingbysvyter.repo.ComplexityRepo;
import com.svyter.spring.swimingbysvyter.repo.CustomersRepo;
import com.svyter.spring.swimingbysvyter.repo.QuestionerRepo;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import com.svyter.spring.swimingbysvyter.entity.Questioner;
import com.svyter.spring.swimingbysvyter.dto.QuestionerDTO;
import com.svyter.spring.swimingbysvyter.security.JwtUtils;
import com.svyter.spring.swimingbysvyter.service.QuestionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class QuestionerServiceImpl implements QuestionerService {
    private final QuestionerRepo questionerRepo;
    private final CustomersRepo customersRepo;
    private final ComplexityRepo complexityRepo;
    private final MessageSource messageSource;
    private final JwtUtils jwtUtils;

    @Autowired
    public QuestionerServiceImpl(QuestionerRepo questionerRepo, CustomersRepo customersRepo, ComplexityRepo complexityRepo, MessageSource messageSource, JwtUtils jwtUtils) {
        this.questionerRepo = questionerRepo;
        this.customersRepo = customersRepo;
        this.complexityRepo = complexityRepo;
        this.messageSource = messageSource;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void createQuestioner(QuestionerEditDTO questionerEditDTO, String token) {
        Long id = jwtUtils.extractUserId(token);
        Customers customers = customersRepo.findById(id).orElseThrow(() -> new NotFoundDataException(
                String.format(messageSource.getMessage("error.customer.notfound", null, Locale.getDefault()), "id " + id)
        ));
        Questioner questioner = new Questioner(questionerEditDTO.getLengthPool(), questionerEditDTO.getGender(), questionerEditDTO.getAge(), questionerEditDTO.getTimeTrain(), questionerEditDTO.getCountWeek(),
                questionerEditDTO.getCountTrainOneWeek(),
                customers);

        Complexity complexity = complexityRepo.findById(questionerEditDTO.getComplexityId()).orElseThrow(() -> new NotFoundDataException(
                messageSource.getMessage("error.complexity.notfound", new Object[]{"id " + id}, Locale.getDefault())
        ));
        questioner.setComplexity(complexity);
        customers.setQuestioner(questioner);
        questionerRepo.save(questioner);
        customersRepo.save(customers);
    }

    @Override
    public ResponseDTO<QuestionerDTO> editQuestioner(QuestionerEditDTO questionerEditDTO, String token) {
        Long id = jwtUtils.extractUserId(token);
        Questioner questioner = questionerRepo.findByCustomersId(id).orElseThrow(() -> new NotFoundDataException(
                messageSource.getMessage("error.questioner.notfound", new Object[]{"id " + id},
                        Locale.getDefault())));

        Complexity complexity = complexityRepo.findById(questionerEditDTO.getComplexityId()).orElseThrow(() -> new NotFoundDataException(
               messageSource.getMessage("error.complexity.notfound", new Object[]{"id " + id},
                        Locale.getDefault())));

        questioner.setLengthPool(questionerEditDTO.getLengthPool());
        questioner.setGender(questionerEditDTO.getGender());
        questioner.setAge(questionerEditDTO.getAge());
        questioner.setComplexity(complexity);
        questioner.setTimeTrain(questionerEditDTO.getTimeTrain());
        questioner.setCountWeek(questionerEditDTO.getCountWeek());
        questioner.setCountTrainOneWeek(questionerEditDTO.getCountTrainOneWeek());
        questionerRepo.save(questioner);
        return new ResponseDTO<>(QuestionerDTO.questionerConvertor(questioner));
    }

    @Override
    public ResponseDTO<QuestionerDTO> getQuestioner(String token) {
        Long id = jwtUtils.extractUserId(token);
        return new ResponseDTO<>(QuestionerDTO.questionerConvertor(questionerRepo.findByCustomersId(id).orElseThrow(() -> new NotFoundDataException(
                String.format(messageSource.getMessage("error.questioner.notfound", null, Locale.getDefault()), "idCustomers " + id)
        ))));
    }

    @Override
    public void delQuestioner(Long idQuest) {
        questionerRepo.deleteById(idQuest);
    }
}

package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.dto.base.ResponseDTO;
import com.svyter.spring.swimingbysvyter.entity.Complexity;
import com.svyter.spring.swimingbysvyter.exception.NotFoundDataException;
import com.svyter.spring.swimingbysvyter.repo.ComplexityRepo;
import com.svyter.spring.swimingbysvyter.repo.CustomersRepo;
import com.svyter.spring.swimingbysvyter.repo.QuestionerRepo;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import com.svyter.spring.swimingbysvyter.entity.Questioner;
import com.svyter.spring.swimingbysvyter.dto.QuestionerDTO;
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

    @Autowired
    public QuestionerServiceImpl(QuestionerRepo questionerRepo, CustomersRepo customersRepo, ComplexityRepo complexityRepo, MessageSource messageSource) {
        this.questionerRepo = questionerRepo;
        this.customersRepo = customersRepo;
        this.complexityRepo = complexityRepo;
        this.messageSource = messageSource;
    }

    @Override
    public void createQuestioner(QuestionerDTO questionerDTO, Long customerId) {
        try {
            Customers customers = customersRepo.findById(customerId).orElseThrow(() -> new NotFoundDataException(
                    String.format(messageSource.getMessage("error.customer.notfound",null, Locale.getDefault()),"id " + customerId)
            ));
            Questioner questioner = new Questioner(questionerDTO.getLengthPool(), questionerDTO.getGender(), questionerDTO.getAge(), questionerDTO.getTimeTrain(), questionerDTO.getCountWeek(),
                    questionerDTO.getCountTrainOneWeek(),
                    customers);
            customers.setQuestioner(questioner);
            questionerRepo.save(questioner);
            customersRepo.save(customers);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResponseDTO<QuestionerDTO> editQuestioner(QuestionerDTO questionerDTO, Long customerId ) {
        try {
            Questioner questioner = questionerRepo.findByCustomersId(customerId).orElseThrow(() -> new NotFoundDataException(
                    String.format(messageSource.getMessage("error.questioner.notfound",null,
                            Locale.getDefault()),"idCustomers " +customerId)));

            Complexity complexity = complexityRepo.findById(questionerDTO.getComplexity().getId()).orElseThrow(() -> new NotFoundDataException(
                    String.format(messageSource.getMessage("error.questioner.notfound",null,
                            Locale.getDefault()),"customerId " + customerId)));

            questioner.setLengthPool(questionerDTO.getLengthPool());
            questioner.setGender(questionerDTO.getGender());
            questioner.setAge(questionerDTO.getAge());
            questioner.setComplexity(complexity);
            questioner.setTimeTrain(questionerDTO.getTimeTrain());
            questioner.setCountWeek(questionerDTO.getCountWeek());
            questioner.setCountTrainOneWeek(questionerDTO.getCountTrainOneWeek());
            questionerRepo.save(questioner);
            return new ResponseDTO<>(QuestionerDTO.questionerConvertor(questioner));
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e.getMessage());
        }

    }

    @Override
    public ResponseDTO<QuestionerDTO> getQuestioner(Long customerId) {
        try {
           return new ResponseDTO<>(QuestionerDTO.questionerConvertor(questionerRepo.findByCustomersId(customerId).orElseThrow(() -> new NotFoundDataException(
                   String.format(messageSource.getMessage("error.questioner.notfound",null, Locale.getDefault()),"idCustomers " +customerId)
           ))));
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void delQuestioner(Long idQuest) {
        try {
           questionerRepo.deleteById(idQuest);
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e.getMessage());
        }

    }
}

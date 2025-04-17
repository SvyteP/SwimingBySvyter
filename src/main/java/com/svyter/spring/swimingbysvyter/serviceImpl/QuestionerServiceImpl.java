package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.exception.NotFoundDataException;
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
    private final MessageSource messageSource;

    @Autowired
    public QuestionerServiceImpl(QuestionerRepo questionerRepo, CustomersRepo customersRepo, MessageSource messageSource) {
        this.questionerRepo = questionerRepo;
        this.customersRepo = customersRepo;
        this.messageSource = messageSource;
    }

    @Override
    public void createQuestioner(QuestionerDTO questionerDTO) {
        try {
            Customers customers = customersRepo.findById(questionerDTO.getIdCustomer()).orElseThrow(() -> new NotFoundDataException(
                    String.format(messageSource.getMessage("error.customer.notfound",null, Locale.getDefault()),"id " + questionerDTO.getIdCustomer())
            ));
            Questioner questioner = new Questioner(questionerDTO.getLangthPool(), questionerDTO.getGender(), questionerDTO.getAge(),
                    questionerDTO.getComplexityTrain(), questionerDTO.getTimeTrain(), questionerDTO.getCountWeek(),
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
    public void editQuestioner(QuestionerDTO questionerDTO, Long id) {
        try {
           Questioner questioner = questionerRepo.findById(id).orElseThrow(() -> new NotFoundDataException(
                   String.format(messageSource.getMessage("error.questioner.notfound",null, Locale.getDefault()),"id " +id)
           ));
            questioner.setLengthPool(questionerDTO.getLangthPool());
            questioner.setGender(questionerDTO.getGender());
            questioner.setAge(questionerDTO.getAge());
            questioner.setLevelTrain(questionerDTO.getComplexityTrain());
            questioner.setTimeTrain(questionerDTO.getTimeTrain());
            questioner.setCountWeek(questionerDTO.getCountWeek());
            questioner.setCountTrainOneWeek(questionerDTO.getCountTrainOneWeek());
            questionerRepo.save(questioner);
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e.getMessage());
        }

    }

    @Override
    public QuestionerDTO getQuestioner(Long idCustomers) {
        try {
           return QuestionerDTO.questionerConvertor(questionerRepo.findByCustomersId(idCustomers).orElseThrow(() -> new NotFoundDataException(
                   String.format(messageSource.getMessage("error.questioner.notfound",null, Locale.getDefault()),"idCustomers " +idCustomers)
           )));
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

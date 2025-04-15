package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.dto.CustomersRepo;
import com.svyter.spring.swimingbysvyter.dto.QuestionerRepo;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import com.svyter.spring.swimingbysvyter.entity.Questioner;
import com.svyter.spring.swimingbysvyter.model.QuestionerModel;
import com.svyter.spring.swimingbysvyter.service.QuestionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionerServiceImpl implements QuestionerService {
    private final QuestionerRepo questionerRepo;
    private  final CustomersRepo customersRepo;
    @Autowired
    public QuestionerServiceImpl(QuestionerRepo questionerRepo, CustomersRepo customersRepo) {
        this.questionerRepo = questionerRepo;
        this.customersRepo = customersRepo;
    }

    @Override
    public void createQuestioner(QuestionerModel questionerModel) {
        try {
            Customers customers = customersRepo.findById(questionerModel.getIdCustomer()).orElseThrow();
            Questioner questioner = new Questioner(questionerModel.getLangthPool(), questionerModel.getGender(), questionerModel.getAge(),
                    questionerModel.getComplexityTrain(), questionerModel.getTimeTrain(), questionerModel.getCountWeek(),
                    questionerModel.getCountTrainOneWeek(),
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
    public void editQuestioner(QuestionerModel questionerModel, Long id) {
        try {
           Questioner questioner = questionerRepo.findById(id).orElseThrow();
            questioner.setLengthPool(questionerModel.getLangthPool());
            questioner.setGender(questionerModel.getGender());
            questioner.setAge(questionerModel.getAge());
            questioner.setLevelTrain(questionerModel.getComplexityTrain());
            questioner.setTimeTrain(questionerModel.getTimeTrain());
            questioner.setCountWeek(questionerModel.getCountWeek());
            questioner.setCountTrainOneWeek(questionerModel.getCountTrainOneWeek());
            questionerRepo.save(questioner);
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e.getMessage());
        }

    }

    @Override
    public QuestionerModel getQuestioner(Long idCustomers) {
        try {
           return QuestionerModel.questionerConvertor(questionerRepo.findById(idCustomers).orElseThrow());
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

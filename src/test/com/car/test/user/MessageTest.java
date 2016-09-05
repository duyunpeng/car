package com.car.test.user;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import pengyi.domain.model.message.IMessageRepository;
import pengyi.domain.model.message.Message;
import pengyi.domain.service.user.IBaseUserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liubowen on 2016/3/18.
 */
public class MessageTest {
    /*  public Pagination<Message> pagination(ListMessageCommand command) {

        List<Criterion> criterionList = new ArrayList<Criterion>();

//        criterionList.add(Restrictions.eq("receiveBaseUser", user));

        criterionList.add(Restrictions.eq("showType", command.getShowType()));

        List<Order> orderList = new ArrayList<Order>();

        orderList.add(Order.desc("sendDate"));

        return messageRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);

    }
    * */
    @Autowired
    private IMessageRepository<Message, String> messageRepository;

    @Autowired
    private IBaseUserService baseUserService;

    public void messageTest(){
        List<Criteria> criterias=new ArrayList<Criteria>();


    }



}

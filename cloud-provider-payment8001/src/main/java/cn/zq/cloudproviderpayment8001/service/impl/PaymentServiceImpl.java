package cn.zq.cloudproviderpayment8001.service.impl;

import cn.zq.cloudproviderpayment8001.entity.Payment;
import cn.zq.cloudproviderpayment8001.mapper.PaymentMapper;
import cn.zq.cloudproviderpayment8001.service.PaymentService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public int insert(Payment paymentEo) {
        return paymentMapper.insert(paymentEo);
    }

    @Override
    public List<Payment> queryPaymentEoById(Long id) {
        List<Payment> payments2 = paymentMapper.queryPaymentEoById(id);
        System.out.println("第一次查询数据库");
        List<Payment> payments = paymentMapper.queryPaymentEoById(id);
        System.out.println("第二次查询数据"+payments2.equals(payments));
        List<Payment> payments1 = paymentMapper.queryPaymentEoById(id);
        System.out.println("第三次查询数据"+payments.equals(payments1));
        return paymentMapper.queryPaymentEoById(id);
    }
}

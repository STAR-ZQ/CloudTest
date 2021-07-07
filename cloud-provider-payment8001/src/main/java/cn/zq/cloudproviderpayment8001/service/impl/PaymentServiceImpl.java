package cn.zq.cloudproviderpayment8001.service.impl;

import cn.zq.cloudproviderpayment8001.entity.Payment;
import cn.zq.cloudproviderpayment8001.mapper.PaymentMapper;
import cn.zq.cloudproviderpayment8001.service.PaymentService;
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
        return paymentMapper.queryPaymentEoById(id);
    }
}

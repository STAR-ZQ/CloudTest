package cn.zq.cloudproviderpayment8001.service;

import cn.zq.cloudproviderpayment8001.entity.Payment;

import java.util.List;

public interface PaymentService {
    int insert(Payment paymentEo);

    List<Payment> queryPaymentEoById(Long id);
}

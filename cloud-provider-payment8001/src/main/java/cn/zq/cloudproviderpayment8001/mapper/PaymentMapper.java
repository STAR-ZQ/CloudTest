package cn.zq.cloudproviderpayment8001.mapper;

import cn.zq.cloudproviderpayment8001.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentMapper {
    int insert(@Param("payment") Payment paymentEo);

    List<Payment> queryPaymentEoById(@Param("id") Long id);
}

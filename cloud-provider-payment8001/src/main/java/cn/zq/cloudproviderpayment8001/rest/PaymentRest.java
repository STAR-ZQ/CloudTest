package cn.zq.cloudproviderpayment8001.rest;

import cn.zq.cloudproviderpayment8001.common.FileUtil;
import cn.zq.cloudproviderpayment8001.common.RestResponse;
import cn.zq.cloudproviderpayment8001.dao.ExportTestDto;
import cn.zq.cloudproviderpayment8001.entity.Payment;
import cn.zq.cloudproviderpayment8001.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class PaymentRest {
    private static Logger logger = LoggerFactory.getLogger(PaymentRest.class);
    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/insert", produces = "application/json")
    public int inert(Payment paymentEo) {
        return paymentService.insert(paymentEo);
    }

    @GetMapping(value = "/{id}/queryById")
    public RestResponse<List<Payment>> queryPaymentInfoById(@Validated @PathVariable("id") Long id) {
        logger.info("哈哈哈哈哈范德萨哈哈哈哈");
        return new RestResponse<>(paymentService.queryPaymentEoById(id));
    }


    @RequestMapping("export")
    public void export(HttpServletResponse response){

        //模拟从数据库获取需要导出的数据
        List<ExportTestDto> personList = new ArrayList<>();
        ExportTestDto person1 = new ExportTestDto(1,"路飞");
        ExportTestDto person2 = new ExportTestDto(2,"娜美");
        ExportTestDto person3 = new ExportTestDto(3,"索隆");
        ExportTestDto person4 = new ExportTestDto(4,"小狸猫");
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);

        //导出操作
        FileUtil.exportExcel(personList,"花名册","草帽一伙",ExportTestDto.class,"海贼王.xls",response);
    }


}

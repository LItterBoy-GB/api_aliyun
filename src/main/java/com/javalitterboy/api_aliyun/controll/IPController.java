package com.javalitterboy.api_aliyun.controll;

import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.javalitterboy.api_aliyun.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: api_aliyun
 * @description:
 * @author: JavaLitterBoy
 * @create: 2018-11-02 17:54
 **/
@RestController
public class IPController {

    @Value("${domain}")
    private String domain;

    @Resource
    private RecordService recordService;

    private static Logger logger = LoggerFactory.getLogger(IPController.class);

    @GetMapping("/update_ip")
    public String update_ip(@RequestParam("ip") String ip,@RequestParam("sub") String subdomain){
        try {
            logger.info("更换<"+subdomain+"."+domain+">ip:"+ip);
            List<DescribeDomainRecordsResponse.Record> records = recordService.recordList(domain);
            for(DescribeDomainRecordsResponse.Record r:records){
                if(subdomain.equals(r.getRR())){
                    r.setValue(ip);
                    recordService.updateRecord(r);
                }
            }
            return "success";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "fail";
        }
    }
}

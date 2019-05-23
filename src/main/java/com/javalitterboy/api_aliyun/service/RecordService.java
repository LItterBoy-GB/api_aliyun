package com.javalitterboy.api_aliyun.service;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordRequest;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordResponse;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @program: api_tencent
 * @description: 解析服务
 * @author: JavaLitterBoy
 * @create: 2018-11-02 13:59
 **/
@Service
public class RecordService {

    @Resource
    private IAcsClient iAcsClient;

    /**
     * 获取指定域名解析记录
     */
    public List<DescribeDomainRecordsResponse.Record> recordList(String domainName) throws ClientException {
        DescribeDomainRecordsRequest request = new DescribeDomainRecordsRequest();
        request.setDomainName(domainName);
        DescribeDomainRecordsResponse response = iAcsClient.getAcsResponse(request);
        return response.getDomainRecords();
    }

    /**
     * 根据id更新域名解析记录
     */
    public void updateRecord(DescribeDomainRecordsResponse.Record record) throws ClientException {
        UpdateDomainRecordRequest request = new UpdateDomainRecordRequest();
        request.setRecordId(record.getRecordId());
        request.setRR(record.getRR());
        request.setType(record.getType());
        request.setValue(record.getValue());
        iAcsClient.getAcsResponse(request);
    }
}

package com.Ishop.store.service.serviceImpl;

import com.Ishop.common.vo.CountOrderItem;
import com.Ishop.store.service.OrderService;
import com.alibaba.excel.EasyExcel;
import junit.framework.TestCase;

import javax.annotation.Resource;
import java.util.List;

public class CouponServiceImplTest extends TestCase {
    @Resource
    OrderService orderService;
    public void testGetCouponByIdList() {
        List<CountOrderItem> list =  orderService.getMonthOrderItem();
        String fileName = "E:\\java.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        //write方法两个参数 ，第一个参数文件路径名称，第二个参数实体类class
        EasyExcel.write(fileName, CountOrderItem.class).sheet("第一个sheet").doWrite(list);

    }
}
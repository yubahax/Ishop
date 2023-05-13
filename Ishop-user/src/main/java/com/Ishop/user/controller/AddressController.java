package com.Ishop.user.controller;

import com.Ishop.common.entity.TbAddress;
import com.Ishop.common.util.util.ParamVail;
import com.Ishop.common.util.util.RestBean;
import com.Ishop.common.util.util.RestGenerator;
import com.Ishop.user.service.AddressService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Resource
    AddressService addressService;
    @GetMapping("/getAllAddress")
    public RestBean getAllAddress(){
        return RestGenerator.successResult(addressService.getAllAddress());
    }

    @GetMapping("/getDefaultAddress")
    public RestBean getDefaultAddress() {return RestGenerator.successResult(addressService.getDefaultADDress());}

    @GetMapping("/delAddress")
    public RestBean delAddress(@RequestParam("addressId") int id){
        if (!ParamVail.vailNumber(id)) {
            return RestGenerator.errorResult("非法参数");
        }
        return addressService.delAddress(id)?RestGenerator.successResult("删除成功"):RestGenerator.errorResult("删除失败");
    }

    @GetMapping("/setAddressStatus")
    public RestBean setAddressStatus(@RequestParam("addressId") int id,
                                     @RequestParam("isDefault") int status) {
        if (ParamVail.vailNumber(id,status) && status < 2 && status >= 0) {
            return addressService.setAddressStatus(id, status)?RestGenerator.successResult("修改成功"):RestGenerator.errorResult("修改失败");
        } else {
            return RestGenerator.errorResult("非法参数");
        }
    }

    @PostMapping("/addAddress")
    public RestBean addAddress(@RequestBody TbAddress tbAddress){
        if (ParamVail.isNull(tbAddress)) {
            return RestGenerator.errorResult("非法参数");
        }
        return addressService.addAddress(tbAddress)?RestGenerator.successResult("添加成功"):RestGenerator.errorResult("添加失败");
    }

    @PostMapping("/updateAddress")
    public RestBean updateAddress(@RequestBody TbAddress tbAddress){
        if (ParamVail.isNull(tbAddress)) {
            return RestGenerator.errorResult("非法参数");
        }
        return addressService.updateAddress(tbAddress)?RestGenerator.successResult("修改成功"):RestGenerator.errorResult("修改失败");
    }














}


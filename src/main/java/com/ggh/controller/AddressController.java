package com.ggh.controller;

import com.ggh.common.json.Body;
import com.ggh.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chaihu
 * @function 上传图片公用controller
 * @date 2020-04-23 19:29
 */
@CrossOrigin
@Controller
@ResponseBody
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 查询我的收货地址列表
     * @return
     */
    @RequestMapping("myAddress")
    public Body myAddress(){
        return addressService.queryMyAddress();
    }

    /**
     * 添加收货地址
     * @param consigneeName 收货人姓名
     * @param consigneeAdd 收货地址
     * @param consigneePhone 收货电话
     * @param isDefault 是否是默认地址(0普通地址,1默认地址
     * @return
     */
    @RequestMapping("addAddress")
    public Body addAddress(
            String consigneeName,
            String consigneeAdd,
            String consigneePhone,
            Integer isDefault
    ){
        return addressService.addAddressInfo(consigneeName,consigneeAdd,consigneePhone,isDefault);
    }

    /**
     * 修改地址信息
     * @param addressId 地址id
     * @param consigneeName 收货姓名
     * @param consigneeAdd 收货地址
     * @param consigneePhone 收货电话
     * @param isDefault 是否默认(0普通地址,1默认地址
     * @return
     */
    @RequestMapping("updateAddress")
    public Body updateAddress(
            Integer addressId,
            String consigneeName,
            String consigneeAdd,
            String consigneePhone,
            Integer isDefault
    ){
        return addressService.updateAddressInfo(addressId,consigneeName,consigneeAdd,consigneePhone,isDefault);
    }

    /**
     * 删除地址信息
     * @param addressId
     * @return
     */
    @RequestMapping("delAddress")
    public Body delAddress(Integer addressId){
        return addressService.delAddressInfo(addressId);
    }
}

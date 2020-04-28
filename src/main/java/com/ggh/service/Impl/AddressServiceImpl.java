package com.ggh.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ggh.common.json.Body;
import com.ggh.entity.Address;
import com.ggh.mapper.AddressMapper;
import com.ggh.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author chaihu
 * @function 地址service
 * @date 2020-04-23 19:30
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 查询我的收货地址列表
     * @return
     */
    @Override
    public Body queryMyAddress() {
        Integer userId = StpUtil.getLoginId(-1);
        if(userId == -1){
            return Body.newInstance(500,"未登录");
        }
        List<Address> addresses = addressMapper.selectList(new LambdaQueryWrapper<Address>()
                .eq(Address::getUserId,userId)
                .orderByDesc(Address::getCreateDate)
        );
        return Body.newInstance(addresses);
    }

    /**
     * 添加收货地址
     * @param consigneeName
     * @param consigneeAdd
     * @param consigneePhone
     * @param isDefault
     * @return
     */
    @Override
    public Body addAddressInfo(String consigneeName, String consigneeAdd, String consigneePhone, Integer isDefault) {
        Integer userId = StpUtil.getLoginId(-1);
        if(userId == -1){
            return Body.newInstance(500,"未登录");
        }
        // 判断添加的是不是默认地址
        if(isDefault == 1){
            // 添加的是默认地址
            Address address = addressMapper.selectOne(new LambdaQueryWrapper<Address>()
                    .eq(Address::getUserId,userId)
                    .eq(Address::getIsDefault,1)
            );
            // 如果该用户无默认地址.直接添加默认地址
            if(address == null){
                Address newAddress = new Address();
                newAddress.setConsigneeName(consigneeName);
                newAddress.setConsigneeAdd(consigneeAdd);
                newAddress.setConsigneePhone(consigneePhone);
                newAddress.setIsDefault(1);
                newAddress.setCreateDate(new Date());
                if(newAddress.insert()){
                    return Body.newInstance();
                }
            }else {
                // 如果该用户有默认地址,修改该用户的默认地址未不默认
                address.setIsDefault(0);
                if(address.updateById()){
                    // 修改完不默认,创建新的默认地址
                    Address newAddress = new Address();
                    newAddress.setConsigneeName(consigneeName);
                    newAddress.setConsigneeAdd(consigneeAdd);
                    newAddress.setConsigneePhone(consigneePhone);
                    newAddress.setIsDefault(1);
                    newAddress.setCreateDate(new Date());
                    if(newAddress.insert()){
                        // 新的默认地址创建成功
                        return Body.newInstance();
                    }
                }
            }
        }else {
            // 如果添加的地址不是默认地址
            Address newAddress = new Address();
            newAddress.setConsigneeName(consigneeName);
            newAddress.setConsigneeAdd(consigneeAdd);
            newAddress.setConsigneePhone(consigneePhone);
            newAddress.setIsDefault(0);
            newAddress.setCreateDate(new Date());
            if(newAddress.insert()){
                // 新的地址创建成功
                return Body.newInstance();
            }
        }
        return Body.newInstance(500,"添加新的地址失败");
    }

    /**
     * 修改地址信息
     * @param addressId
     * @param consigneeName
     * @param consigneeAdd
     * @param consigneePhone
     * @param isDefault
     * @return
     */
    @Override
    public Body updateAddressInfo(Integer addressId, String consigneeName, String consigneeAdd, String consigneePhone, Integer isDefault) {
        Integer userId = StpUtil.getLoginId(-1);
        if(userId == -1){
            return Body.newInstance(500,"未登录");
        }

        // 判断是否修改成默认地址
        if(isDefault == 1){
            // 修改成默认地址
            Address address = addressMapper.selectOne(new LambdaQueryWrapper<Address>()
                    .eq(Address::getUserId,userId)
                    .eq(Address::getIsDefault,1)
            );
            // 判断用户是否有默认地址
            if(address == null){
                // 用户无默认地址,直接修改地址即可
                address.setId(addressId);
                address.setConsigneeName(consigneeName);
                address.setConsigneeAdd(consigneeAdd);
                address.setConsigneePhone(consigneePhone);
                address.setCreateDate(new Date());
                address.setIsDefault(1);
                if(address.updateById()){
                    // 修改成功
                    return Body.newInstance();
                }
            }
        }else {
            // 不是修改成默认地址,或者把地址修改成不默认的,直接修改信息即可
            Address address = new Address();

            address.setId(addressId);
            address.setConsigneeName(consigneeName);
            address.setConsigneeAdd(consigneeAdd);
            address.setConsigneePhone(consigneePhone);
            address.setCreateDate(new Date());
            address.setIsDefault(0);
            if(address.updateById()){
                // 修改成功
                return Body.newInstance();
            }
        }

        return Body.newInstance(500,"修改失败,请重试");
    }

    /***
     * 删除地址信息
     * @param addressId
     * @return
     */
    @Override
    public Body delAddressInfo(Integer addressId) {
        Address address = new Address();
        address.setId(addressId);
        if(address.deleteById()){
            return Body.newInstance();
        }
        return Body.newInstance(500,"删除失败,请重试");
    }
}

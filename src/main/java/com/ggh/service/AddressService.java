package com.ggh.service;

import com.ggh.common.json.Body;

/**
 * @author chaihu
 * @function
 * @date 2020-04-23 19:30
 */
public interface AddressService {
    Body queryMyAddress();

    Body addAddressInfo(String consigneeName, String consigneeAdd, String consigneePhone, Integer isDefault);

    Body updateAddressInfo(Integer addressId, String consigneeName, String consigneeAdd, String consigneePhone, Integer isDefault);

    Body delAddressInfo(Integer addressId);
}

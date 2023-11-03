package com.reviewlah.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.UpdateAddressRequest;
import com.reviewlah.db.pojo.Address;
import com.reviewlah.db.pojo.Merchant;
import com.reviewlah.db.pojo.User;
import com.reviewlah.service.AddressService;
import com.reviewlah.service.MerchantService;
import com.reviewlah.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping({"/merchant/address"})
@Tag(name = "商家地址模块")
public class AddressController {
    @Autowired
    private UserService userService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private AddressService addressService;

    @Operation(summary = "商家地址更新")
    @PostMapping({"/update"})
    public RCode updateAddress(@RequestBody UpdateAddressRequest request) {
        BigInteger user_id = request.getUser_id();
        String address_code = request.getAddress_code();
        String address_detail = request.getAddress_detail();
        String unitnum = request.getUnitnum();
        User user = this.userService.selectUserById(user_id);
        if(user != null && user.getType() == 2) {
            BigInteger merchant_id = this.merchantService.selectMerchantIdByUserId(user_id);
            if(address_code == null || address_code.isEmpty()) {
                address_code = this.addressService.selectAddressByMerchantId(merchant_id).getAddress_code();
            }
            if(merchant_id != null) {
                Address address = new Address(address_code, merchant_id, address_detail, unitnum);
                this.addressService.updateAddress(address);
                System.out.println("successful");
            }
            else {
                System.out.println("Merchant Does Not Exist");
                return RCode.error("Merchant Does Not Exist");
            }
        }
        else {
            System.out.println("Merchant Does Not Exist");
            return RCode.error("Merchant Does Not Exist");
        }
        return RCode.ok("successful");
    }
}

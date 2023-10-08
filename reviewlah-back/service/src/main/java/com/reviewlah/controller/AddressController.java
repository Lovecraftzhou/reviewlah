package com.reviewlah.controller;

import com.reviewlah.controller.form.UpdateAddressRequest;
import com.reviewlah.db.pojo.Address;
import com.reviewlah.db.pojo.Merchant;
import com.reviewlah.db.pojo.User;
import com.reviewlah.service.AddressService;
import com.reviewlah.service.MerchantService;
import com.reviewlah.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping({"/merchant/address"})
public class AddressController {
    @Autowired
    private UserService userService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private AddressService addressService;
    @PostMapping({"/update"})
    public String updateAddress(@RequestBody UpdateAddressRequest request) {
        BigInteger user_id = request.getUser_id();
        String address_code = request.getAddress_code();
        String address_detail = request.getAddress_detail();
        String unitnum = request.getUnitnum();
        User user = this.userService.selectUserById(user_id);
        if(user != null && user.getType() == 2) {
            BigInteger merchant_id = this.merchantService.selectMerchantIdByUserId(user_id);
            Address address = new Address(address_code, merchant_id, address_detail, unitnum);
            this.addressService.updateAddress(address);
            System.out.println("successful");
        }
        else {
            System.out.println("Merchant Does Not Exist");
        }
        return "merchant/address";
    }
}

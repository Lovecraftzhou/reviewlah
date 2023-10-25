package com.reviewlah.controller;

import java.math.BigInteger;
import java.util.ArrayList;

import com.reviewlah.controller.form.DeleteAddressRequest;
import com.reviewlah.controller.form.InsertAddressRequest;
import com.reviewlah.db.pojo.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.UpdateAddressRequest;
import com.reviewlah.db.pojo.Address;
import com.reviewlah.service.AddressService;
import com.reviewlah.service.MerchantService;

@RestController
@RequestMapping({"/merchant/address"})
public class AddressController {
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private AddressService addressService;
    @PostMapping({"/update"})
    public RCode updateAddress(@RequestBody UpdateAddressRequest request) {
        BigInteger user_id = request.getUser_id();
        String address_code = request.getAddress_code();
        String address_detail = request.getAddress_detail();
        String unitnum = request.getUnitnum();
        BigInteger merchant_id = this.merchantService.selectMerchantIdByUserId(user_id);
        if(merchant_id != null) {
            if(address_code == null || address_code.isEmpty()) {
                address_code = this.addressService.selectAddressByMerchantId(merchant_id).getAddress_code();
            }
            Address address = new Address(address_code, merchant_id, address_detail, unitnum);
            this.addressService.updateAddress(address);
            System.out.println("successful");
        }
        else {
            System.out.println("Merchant Does Not Exist");
            return RCode.error("Merchant Does Not Exist");
        }
        return RCode.ok("successful");
    }
    @PostMapping({"/insert"})
    public RCode insertAddress(@RequestBody InsertAddressRequest request) {
        BigInteger user_id = request.getUser_id();
        String address_code = request.getAddress_code();
        String address_detail = request.getAddress_detail();
        String unitnum = request.getUnitnum();
        Merchant merchant = this.merchantService.selectMerchantById(user_id);
        if(merchant != null) {
            Address tmp = this.addressService.selectAddressByMerchantId(merchant.getMerchant_id());
            if(tmp == null) {
                if(address_code == null || address_code.isEmpty()) {
                    System.out.println("AddressCode Cannot Be Empty");
                    return RCode.error("AddressCode Cannot Be Empty");
                }
                Address address = new Address(address_code, merchant.getMerchant_id(), address_detail, unitnum);
                this.addressService.insertAddress(address);
                System.out.println("successful");
            }
            else {
                System.out.println("Merchant Address Does Already Exist");
                return RCode.error("Merchant Address Does Already Exist");
            }

        }
        else {
            System.out.println("Merchant Does Not Exist");
            return RCode.error("Merchant Does Not Exist");
        }
        return RCode.ok("successful");
    }
    @PostMapping({"/{merchant_id}"})
    public RCode selectAddressByMerchantId(@PathVariable BigInteger merchant_id) {
        Merchant merchant = this.merchantService.selectMerchantById(merchant_id);
        if(merchant != null) {
            Address address = this.addressService.selectAddressByMerchantId(merchant_id);
            return RCode.ok().put("list", address);
        }
        else {
            System.out.println("Merchant Does Not Exist");
            return RCode.error("Merchant Does Not Exist");
        }
    }
    @GetMapping({"/showAll"})
    public RCode selectAllAddress() {
        ArrayList<Address> list = this.addressService.selectAllAddress();
        return RCode.ok().put("list", list);
    }

    @PostMapping({"/delete"})
    public RCode deleteAddress(@RequestBody DeleteAddressRequest request) {
        BigInteger address_id = request.getAddress_id();
        Address address = this.addressService.selectAddressById(address_id);
        if(address != null) {
            this.addressService.deleteAddress(address_id);
            return RCode.ok("successful");
        }
        else {
            System.out.println("Address Does Not Exist");
            return RCode.error("Address Does Not Exist");
        }
    }
}

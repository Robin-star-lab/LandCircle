package com.example.landcircle.models;

public class PlannerRequest {

    private String first_name;
    private String second_name;
    private String phone_number;
    private String national_id;
    private String physical_address_number;
    private String postal_address;
    private String pprb_reggistration;
    private String business_name;

    public PlannerRequest(String first_name,
                          String second_name,
                          String phone_number,
                          String national_id,
                          String physical_address_number,
                          String postal_address,
                          String pprb_reggistration,
                          String business_name) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.phone_number = phone_number;
        this.national_id = national_id;
        this.physical_address_number = physical_address_number;
        this.postal_address = postal_address;
        this.pprb_reggistration = pprb_reggistration;
        this.business_name = business_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getNational_id() {
        return national_id;
    }

    public String getPhysical_address_number() {
        return physical_address_number;
    }

    public String getPostal_address_number() {
        return postal_address;
    }

    public String getPprb_reg_number() {
        return pprb_reggistration;
    }

    public String getBusiness_name() {
        return business_name;
    }
}

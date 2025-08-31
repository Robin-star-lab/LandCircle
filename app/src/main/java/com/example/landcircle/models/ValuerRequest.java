package com.example.landcircle.models;

public class ValuerRequest {
    private String first_name;
    private  String second_name;
    private String phone_number;
    private String national_id;
    private String vrb_reg_number;
    private String isk_membership_number;
    private String physical_address;
    private String postal_address;

    public ValuerRequest(String first_name,
                         String second_name,
                         String phone_number,
                         String national_id,
                         String vrb_reg_number,
                         String isk_membership_number,
                         String physical_address,
                         String postal_address) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.phone_number = phone_number;
        this.national_id = national_id;
        this.vrb_reg_number = vrb_reg_number;
        this.isk_membership_number = isk_membership_number;
        this.physical_address = physical_address;
        this.postal_address = postal_address;
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

    public String getVrb_reg_number() {
        return vrb_reg_number;
    }

    public String getIsk_membership_number() {
        return isk_membership_number;
    }

    public String getPhysical_address() {
        return physical_address;
    }

    public String getPostal_address() {
        return postal_address;
    }
}

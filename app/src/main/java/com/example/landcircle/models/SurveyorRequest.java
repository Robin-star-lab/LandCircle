package com.example.landcircle.models;

public class SurveyorRequest {

    private String first_name;
    private String second_name;
    private String phone_number;
    private String national_id;
    private String postal_address;
    private String physical_address;
    private String lsb_reg_no;
    private String isk_membership;
    private String profile_photo;

    public SurveyorRequest(String first_name,
                           String second_name,
                           String phone_number,
                           String national_id,
                           String postal_address,
                           String physical_address,
                           String lsb_reg_no,
                           String isk_membership,
                           String profile_photo) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.phone_number = phone_number;
        this.national_id = national_id;
        this.postal_address = postal_address;
        this.physical_address = physical_address;
        this.lsb_reg_no = lsb_reg_no;
        this.isk_membership = isk_membership;
        this.profile_photo = profile_photo;
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

    public String getPostal_address_number() {
        return postal_address;
    }

    public String getPhysical_address_number() {
        return physical_address;
    }

    public String getLsb_reg_number() {
        return lsb_reg_no;
    }

    public String getIsk_membership_number() {
        return isk_membership;
    }

    public String getProfile_photo() {
        return profile_photo;
    }
}

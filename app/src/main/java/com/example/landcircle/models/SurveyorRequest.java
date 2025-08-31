package com.example.landcircle.models;

public class SurveyorRequest {

    private String first_name;
    private String second_name;
    private String phone_number;
    private String national_id;
    private String postal_address_number;
    private String physical_address_number;
    private String lsb_reg_number;
    private String isk_membership_number;
    private String profile_photo;

    public SurveyorRequest(String first_name,
                           String second_name,
                           String phone_number,
                           String national_id,
                           String postal_address_number,
                           String physical_address_number,
                           String lsb_reg_number,
                           String isk_membership_number,
                           String profile_photo) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.phone_number = phone_number;
        this.national_id = national_id;
        this.postal_address_number = postal_address_number;
        this.physical_address_number = physical_address_number;
        this.lsb_reg_number = lsb_reg_number;
        this.isk_membership_number = isk_membership_number;
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
        return postal_address_number;
    }

    public String getPhysical_address_number() {
        return physical_address_number;
    }

    public String getLsb_reg_number() {
        return lsb_reg_number;
    }

    public String getIsk_membership_number() {
        return isk_membership_number;
    }

    public String getProfile_photo() {
        return profile_photo;
    }
}

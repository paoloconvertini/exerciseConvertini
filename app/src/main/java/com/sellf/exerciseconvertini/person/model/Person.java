package com.sellf.exerciseconvertini.person.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by pconvertini on 04/09/2017.
 */

public class Person {

    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("company_id")
    @Expose
    private String companyId;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("prospect_status")
    @Expose
    private Integer prospectStatus;
    @SerializedName("vat")
    @Expose
    private String vat;
    @SerializedName("birthday")
    @Expose
    private LocalDate birthday;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("skype")
    @Expose
    private String skype;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("email_secondary")
    @Expose
    private String emailSecondary;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        String firstName = this.getFirstName() != null ? this.getFirstName() : "";
        String lastName = this.getLastName() != null ? this.getLastName() : "";

        return firstName + " " + lastName;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getSkype() {
        return skype;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setAddress(String address) {
        this.address = address;

    }

    public Person(String companyId, String firstName, String userId,
                  String title, String phone, String lastName, String address, String email) {
        this.companyId = companyId;
        this.firstName = firstName;
        this.userId = userId;
        this.title = title;
        this.phone = phone;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
    }
}


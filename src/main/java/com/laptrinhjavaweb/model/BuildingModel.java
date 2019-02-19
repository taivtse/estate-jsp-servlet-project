package com.laptrinhjavaweb.model;

import com.laptrinhjavaweb.orm.anotation.Column;
import com.laptrinhjavaweb.orm.anotation.Entity;
import com.laptrinhjavaweb.orm.anotation.Id;

import java.io.InputStream;

@Entity(tableName = "building")
public class BuildingModel {
    @Id(autoIncrement = true)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "district")
    private String district;

    @Column(name = "ward")
    private String ward;

    @Column(name = "street")
    private String street;

    @Column(name = "structure")
    private String structure;

    @Column(name = "number_of_basement")
    private Integer numberOfBasement;

    @Column(name = "building_area")
    private Integer buildingArea;

    @Column(name = "direction")
    private String direction;

    @Column(name = "level")
    private String level;

    @Column(name = "rental_area")
    private String rentalArea;

    @Column(name = "area_description")
    private String areaDescription;

    @Column(name = "rental_cost")
    private Integer rentalCost;

    @Column(name = "cost_description")
    private String costDescription;

    @Column(name = "service_cost")
    private Integer serviceCost;

    @Column(name = "car_cost")
    private Integer carCost;

    @Column(name = "motorbike_cost")
    private Integer motorbikeCost;

    @Column(name = "overtime_cost")
    private Integer overtimeCost;

    @Column(name = "electricity_cost")
    private Integer electricityCost;

    @Column(name = "deposit_cost")
    private Integer depositCost;

    @Column(name = "payment_cost")
    private Integer paymentCost;

    @Column(name = "time_contract")
    private String timeContract;

    @Column(name = "time_decorator")
    private String timeDecorator;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "manager_phone")
    private String managerPhone;

    @Column(name = "commission_cost")
    private Integer commissionCost;

    @Column(name = "note")
    private String note;

    @Column(name = "link")
    private String link;

    @Column(name = "location")
    private String location;

    @Column(name = "type_arrays")
    private String typeArrays;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "thumbnail_base64")
    private InputStream thumbnailBase64;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public Integer getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(Integer buildingArea) {
        this.buildingArea = buildingArea;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRentalArea() {
        return rentalArea;
    }

    public void setRentalArea(String rentalArea) {
        this.rentalArea = rentalArea;
    }

    public String getAreaDescription() {
        return areaDescription;
    }

    public void setAreaDescription(String areaDescription) {
        this.areaDescription = areaDescription;
    }

    public Integer getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(Integer rentalCost) {
        this.rentalCost = rentalCost;
    }

    public String getCostDescription() {
        return costDescription;
    }

    public void setCostDescription(String costDescription) {
        this.costDescription = costDescription;
    }

    public Integer getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(Integer serviceCost) {
        this.serviceCost = serviceCost;
    }

    public Integer getCarCost() {
        return carCost;
    }

    public void setCarCost(Integer carCost) {
        this.carCost = carCost;
    }

    public Integer getMotorbikeCost() {
        return motorbikeCost;
    }

    public void setMotorbikeCost(Integer motorbikeCost) {
        this.motorbikeCost = motorbikeCost;
    }

    public Integer getOvertimeCost() {
        return overtimeCost;
    }

    public void setOvertimeCost(Integer overtimeCost) {
        this.overtimeCost = overtimeCost;
    }

    public Integer getElectricityCost() {
        return electricityCost;
    }

    public void setElectricityCost(Integer electricityCost) {
        this.electricityCost = electricityCost;
    }

    public Integer getDepositCost() {
        return depositCost;
    }

    public void setDepositCost(Integer depositCost) {
        this.depositCost = depositCost;
    }

    public Integer getPaymentCost() {
        return paymentCost;
    }

    public void setPaymentCost(Integer paymentCost) {
        this.paymentCost = paymentCost;
    }

    public String getTimeContract() {
        return timeContract;
    }

    public void setTimeContract(String timeContract) {
        this.timeContract = timeContract;
    }

    public String getTimeDecorator() {
        return timeDecorator;
    }

    public void setTimeDecorator(String timeDecorator) {
        this.timeDecorator = timeDecorator;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public Integer getCommissionCost() {
        return commissionCost;
    }

    public void setCommissionCost(Integer commissionCost) {
        this.commissionCost = commissionCost;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTypeArrays() {
        return typeArrays;
    }

    public void setTypeArrays(String typeArrays) {
        this.typeArrays = typeArrays;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public InputStream getThumbnailBase64() {
        return thumbnailBase64;
    }

    public void setThumbnailBase64(InputStream thumbnailBase64) {
        this.thumbnailBase64 = thumbnailBase64;
    }
}

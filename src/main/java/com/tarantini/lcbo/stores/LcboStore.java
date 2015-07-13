package com.tarantini.lcbo.stores;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class LcboStore {
    private int id;
    @JsonProperty("is_dead")
    private boolean isDead;
    private String name;
    private String tags;
    @JsonProperty("address_line_1")
    private String addressLineOne;
    @JsonProperty("address_line_2")
    private String addressLineTwo;
    private String city;
    @JsonProperty("postal_code")
    private String postalCode;
    private String telephone;
    private String fax;
    private float latitude;
    private float longitude;
    @JsonProperty("products_count")
    private int productsCount;
    @JsonProperty("inventory_count")
    private int inventoryCount;
    @JsonProperty("inventory_price_in_cents")
    private int inventoryPriceInCents;
    @JsonProperty("inventory_volume_in_milliliters")
    private int inventoryVolumeInMilliliters;
    @JsonProperty("has_wheelchair_accessability")
    private boolean hasWheelchairAccessability;
    @JsonProperty("has_bilingual_services")
    private boolean hasBilingualServices;
    @JsonProperty("has_product_consultant")
    private boolean hasProductConsultant;
    @JsonProperty("has_tasting_bar")
    private boolean hasTastingBar;
    @JsonProperty("has_beer_cold_room")
    private boolean hasBeerColdRoom;
    @JsonProperty("has_special_occasion_permits")
    private boolean hasSpecialOccasionPermits;
    @JsonProperty("has_vintages_corner")
    private boolean hasVintagesCorner;
    @JsonProperty("has_parking")
    private boolean hasParking;
    @JsonProperty("has_transit_access")
    private boolean hasTransitAccess;
    @JsonProperty("sunday_open")
    private int sundayOpen;
    @JsonProperty("sunday_close")
    private int sundayClose;
    @JsonProperty("monday_open")
    private int mondayOpen;
    @JsonProperty("monday_close")
    private int mondayClose;
    @JsonProperty("tuesday_open")
    private int tuesdayOpen;
    @JsonProperty("tuesday_close")
    private int tuesdayClose;
    @JsonProperty("wednesday_open")
    private int wednesdayOpen;
    @JsonProperty("wednesday_close")
    private int wednesdayClose;
    @JsonProperty("thursday_open")
    private int thursdayOpen;
    @JsonProperty("thursday_close")
    private int thursdayClose;
    @JsonProperty("friday_open")
    private int fridayOpen;
    @JsonProperty("friday_close")
    private int fridayClose;
    @JsonProperty("saturday_open")
    private int saturdayOpen;
    @JsonProperty("saturday_close")
    private int saturdayClose;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("store_no")
    private int storeNo;
}
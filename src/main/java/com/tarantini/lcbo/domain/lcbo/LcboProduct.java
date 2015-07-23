package com.tarantini.lcbo.domain.lcbo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LcboProduct {
    private Integer id;
    @JsonProperty("is_dead")
    private Boolean isDead;
    private String name;
    private String tags;
    @JsonProperty("is_discontinued")
    private Boolean isDiscontinued;
    @JsonProperty("price_in_cents")
    private Integer priceInCents;
    @JsonProperty("regular_price_in_cents")
    private Integer regularPriceInCents;
    @JsonProperty("limited_time_offer_savings_in_cents")
    private Integer limitedTimeOfferSavingsInCents;
    @JsonProperty("limited_time_offer_ends_on")
    private String limitedTimeOfferEndsOn;
    @JsonProperty("bonus_reward_miles")
    private Integer bonusRewardMiles;
    @JsonProperty("bonus_reward_miles_ends_on")
    private String bonusRewardMilesEndsOn;
    @JsonProperty("stock_type")
    private String stockType;
    @JsonProperty("primary_category")
    private String primaryCategory;
    @JsonProperty("secondary_category")
    private String secondaryCategory;
    private String origin;
    @JsonProperty("package")
    private String packageDescription;
    @JsonProperty("package_unit_type")
    private String packageUnitType;
    @JsonProperty("package_unit_volume_in_milliliters")
    private Integer packageUnitVolumeInMilliliters;
    @JsonProperty("total_package_units")
    private Integer totalPackageUnits;
    @JsonProperty("volume_in_milliliters")
    private Integer volumeInMilliliters;
    @JsonProperty("alcohol_content")
    private Integer alcoholContent;
    @JsonProperty("price_per_liter_of_alcohol_in_cents")
    private Integer pricePerLiterOfAlcoholInCents;
    @JsonProperty("price_per_liter_in_cents")
    private Integer pricePerLiterInCents;
    @JsonProperty("inventory_count")
    private Integer inventory_count;
    @JsonProperty("inventory_volume_in_milliliters")
    private Integer inventoryVolumeInMilliliters;
    @JsonProperty("inventory_price_in_cents")
    private Integer inventoryPriceInCents;
    @JsonProperty("sugar_content")
    private String sugarContent;
    @JsonProperty("producer_name")
    private String producerName;
    @JsonProperty("released_on")
    private String releasedOn;
    @JsonProperty("has_value_added_promotion")
    private Boolean hasValueAddedPromotion;
    @JsonProperty("has_limited_time_offer")
    private Boolean hasLimitedTimeOffer;
    @JsonProperty("has_bonus_reward_miles")
    private Boolean hasBonusRewardMiles;
    @JsonProperty("is_seasonal")
    private Boolean isSeasonal;
    @JsonProperty("is_vqa")
    private Boolean isVqa;
    @JsonProperty("is_ocb")
    private Boolean isOcb;
    @JsonProperty("is_kosher")
    private Boolean isKosher;
    @JsonProperty("value_added_promotion_description")
    private String valueAddedPromotionDescription;
    private String description;
    @JsonProperty("serving_suggestion")
    private String servingSuggestion;
    @JsonProperty("tasting_note")
    private String tastingNote;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("image_thumb_url")
    private String imageThumbUrl;
    @JsonProperty("image_url")
    private String imageUrl;
    private String varietal;
    private String style;
    @JsonProperty("tertiary_category")
    private String tertiaryCategory;
    @JsonProperty("sugar_in_grams_per_liter")
    private Integer sugarInGramsPerLiter;
    @JsonProperty("clearance_sale_savings_in_cents")
    private Integer clearanceSaleSavingsInCents;
    @JsonProperty("has_clearance_sale")
    private Boolean hasClearanceSale;
    @JsonProperty("product_no")
    private Integer productNo;
}

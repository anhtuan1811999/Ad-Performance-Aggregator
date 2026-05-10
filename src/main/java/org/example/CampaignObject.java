package org.example;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CampaignObject {
    private String campaign_id;
    private int total_impressions;
    private int total_clicks;
    private float total_spend;
    private int total_conversions;
    private float CTR;
    private float CPA;

    public CampaignObject(String campaign_id, int total_impressions, int total_clicks, float total_spend, int total_conversions) {
        this.campaign_id = campaign_id;
        this.total_impressions = total_impressions;
        this.total_clicks = total_clicks;
        this.total_spend = total_spend;
        this.total_conversions = total_conversions;
        this.CTR = this.total_impressions > 0 ? (float) (this.total_clicks / (this.total_impressions * 1.0)) : 0;
        this.CPA = this.total_conversions > 0 ? this.total_spend / this.total_conversions : 0;
    }

    public void accumulate(int impressions,
                           int clicks,
                           float spend,
                           int conversions) {
        this.total_impressions += impressions;
        this.total_clicks += clicks;
        this.total_spend += spend;
        this.total_conversions += conversions;
        this.CTR = this.total_impressions > 0 ? (float) (this.total_clicks / (this.total_impressions * 1.0)) : 0;
        this.CPA = this.total_conversions > 0 ? this.total_spend / this.total_conversions : 0;
    }

}

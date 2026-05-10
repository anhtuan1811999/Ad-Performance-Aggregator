package org.example;

import lombok.Data;

import java.util.Date;

@Data
public class LineObject {
    private String campaign_id;
    private Date date;
    private int impressions;
    private int clicks;
    private float spend;
    private int conversions;
}

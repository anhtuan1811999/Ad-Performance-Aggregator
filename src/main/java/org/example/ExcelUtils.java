package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    private static final Map<String, CampaignObject> campaignMap = new HashMap<>();


    public static void processCsv(String filePath, String linkSave) {

        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {

            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 6) {
                    continue;
                }
                String campaignId = parts[0];
                int impressions = Integer.parseInt(parts[2]);
                int clicks = Integer.parseInt(parts[3]);
                float spend = Float.parseFloat(parts[4]);
                int conversions = Integer.parseInt(parts[5]);

                updateCampaignData(campaignId, impressions, clicks, spend, conversions);
            }

            List<CampaignObject> topTenHighestCTR = filterCampaignsByHighestCTR(10);
            List<CampaignObject> topTenLowestCPA = filterCampaignsByLowestCPA(10);

            exportToCSVFile(topTenHighestCTR, linkSave, "top_ten_highest_ctr.csv");
            exportToCSVFile(topTenLowestCPA, linkSave,  "top_ten_lowest_cpa.csv");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateCampaignData(String campaignId, int impressions, int clicks, float spend, int conversions) {
        if (campaignMap.containsKey(campaignId)) {
            CampaignObject campaignObject = campaignMap.get(campaignId);
            campaignObject.accumulate(impressions, clicks, spend, conversions);
        } else {
            campaignMap.put(campaignId, new CampaignObject(campaignId, impressions, clicks, spend, conversions));
        }
    }

    private static List<CampaignObject> filterCampaignsByHighestCTR(int amount) {
        return campaignMap.values().stream().sorted(Comparator.comparingDouble(CampaignObject::getCTR).reversed()).limit(amount).toList();
    }

    private static List<CampaignObject> filterCampaignsByLowestCPA(int amount) {
        return campaignMap.values().stream().sorted(Comparator.comparingDouble(CampaignObject::getCPA)).limit(amount).toList();
    }

    private static void exportToCSVFile(List<CampaignObject> campaignObjects, String filePath, String fileName) {
        try {
            Files.createDirectories(Path.of(filePath));
            Path outputPath = Path.of(filePath, fileName);
            try (BufferedWriter writer =
                         Files.newBufferedWriter(outputPath)) {
                writer.write(
                        "campaign_id,total_impressions,total_clicks,total_spend,total_conversions,CTR,CPA"
                );
                writer.newLine();

                for (CampaignObject campaign : campaignObjects) {

                    writer.write(String.format(
                            "%s,%d,%d,%.2f,%d,%.4f,%s",
                            campaign.getCampaign_id(),
                            campaign.getTotal_impressions(),
                            campaign.getTotal_clicks(),
                            campaign.getTotal_spend(),
                            campaign.getTotal_conversions(),
                            campaign.getCTR(),
                            campaign.getCPA() == 0
                                    ? "null"
                                    : String.format("%.2f", campaign.getCPA())
                    ));

                    writer.newLine();
                }
            }

            System.out.println(
                    "CSV exported successfully: " + outputPath
            );

        } catch (IOException e) {

            System.err.println(
                    "Failed to export CSV file: " + e.getMessage()
            );
        }
    }
}
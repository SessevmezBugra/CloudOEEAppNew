package com.oee.enums;

public enum QualityTypeEnum {
    FIRST_QUALITY("FIRST_QUALITY", "First Quality"),
    SECOND_QUALITY("SECOND_QUALITY", "Second Quality"),
    SCRAP("SCRAP", "Scrap");

    private final String qualityType;
    private final String qualityDesc;

    QualityTypeEnum(String qualityType, String qualityDesc) {
        this.qualityType = qualityType;
        this.qualityDesc = qualityDesc;
    }


    public String getQualityType() {
        return qualityType;
    }

    public String getQualityDesc() {
        return qualityDesc;
    }
}

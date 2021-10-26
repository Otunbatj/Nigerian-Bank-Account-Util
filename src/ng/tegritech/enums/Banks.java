package ng.tegritech.enums;

public enum Banks {
    ACCESS_BANK("ACCESS BANK", "044"),
    CITI_BANK("CITIBANK", "023"),
    ACCESS_DIAMOND("ACCESS (DIAMOND) BANK", "063"),
    ECOBANK("ECOBANK NIGERIA", "050"),
    FIDELITY_BANK("FIDELITY BANK", "070"),
    FIRST_BANK("FIRST BANK OF NIGERIA", "011"),
    FCMB("FIRST CITY MONUMENT BANK", "214"),
    GTBANK("GUARANTY TRUST BANK", "058"),
    HERITAGE_BANK("HERITAGE BANK", "030"),
    JAIZ_BANK("JAIZ BANK", "301"),
    KEYSTONE_BANK("KEYSTONE BANK", "082"),
    PROVIDUS_BANK("PROVIDUS BANK", "101"),
    POLARIS_BANK("POLARIS(SKYE) BANK", "076"),
    STANBIC_BANK("STANBIC IBTC BANK", "221"),
    STANDARD_CHARTERED("STANDARD CHARTERED BANK", "068"),
    STERLING("STERLING BANK", "232"),
    SUNTRUST("SUNTRUST", "100"),
    UNION_BANK("UNION BANK OF NIGERIA", "032"),
    UBA("UNITED BANK FOR AFRICA", "033"),
    UNITY_BANK("UNITY BANK", "215"),
    WEMA_BANK("WEMA BANK", "035"),
    ZENITH_BANK("ZENITH BANK", "057");

    private String bankName;
    private String bankCode;

    Banks(String bankName, String bankCode) {
        this.bankName = bankName;
        this.bankCode = bankCode;
    }

    public Banks getBankNameFromCode(String bankCode) {
        for (Banks bank : values()) {
            if (this.bankCode.equals(bank.bankCode)) {
                return this;
            }
        }
        throw new IllegalArgumentException(String.format("No Bank with Bankcode %s", bankCode));
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    @Override
    public String toString() {
        return "Banks{" +
                "bankName='" + bankName + '\'' +
                ", bankCode='" + bankCode + '\'' +
                '}';
    }
}

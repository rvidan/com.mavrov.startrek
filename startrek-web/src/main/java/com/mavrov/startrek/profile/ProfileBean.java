package com.mavrov.startrek.profile;

/**
 * @author serg.mavrov@gmail.com
 */
public class ProfileBean {

    private String result;
    private String conversionSummary;
    private String buttonLabel;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        double conversionFactor = 39.37;
        try {
            double metersToConvert = Double.parseDouble(result);
            double inches = conversionFactor*metersToConvert;
            String inchesString = Double.toString(inches);
            this.result = result + " m = " + inchesString + " inches";
            setConversionSummary("CONVERSION RESULT");
            setButtonLabel("Convert another number");
        } catch (NumberFormatException e) {
            this.result = "\"" + result + "\" is not a number.";
            if (result.isEmpty()) this.result = "You forgot to specify the number of meters.";
            setConversionSummary("CONVERSION FAILED");
            setButtonLabel("Try once more");
        }
    }

    public String getConversionSummary() {
        return conversionSummary;
    }

    public void setConversionSummary(String conversionSummary) {
        this.conversionSummary = conversionSummary;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public void setButtonLabel(String buttonLabel) {
        this.buttonLabel = buttonLabel;
    }

}
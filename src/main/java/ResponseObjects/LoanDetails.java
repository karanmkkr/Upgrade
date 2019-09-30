package ResponseObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LoanDetails {
    @SerializedName("id")
    @Expose
    @Getter
    @Setter
    private Integer id;

    @SerializedName("uuid")
    @Expose
    @Getter
    @Setter
    private String uuid;

    @SerializedName("status")
    @Expose
    @Getter
    @Setter
    private String status;

    @SerializedName("productType")
    @Expose
    @Getter
    @Setter

    private String productType;
    @SerializedName("sourceSystem")
    @Expose
    @Getter
    @Setter
    private String sourceSystem;

    @SerializedName("hasOpenBackendCounter")
    @Expose
    @Getter
    @Setter
    private Boolean hasOpenBackendCounter;

    @SerializedName("purpose")
    @Expose
    @Getter
    @Setter
    private String purpose;

    @SerializedName("createDate")
    @Expose
    @Getter
    @Setter
    private String createDate;

    @SerializedName("postIssuanceStatus")
    @Expose
    @Getter
    @Setter
    private Object postIssuanceStatus;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("uuid", uuid).append("status", status).append("productType", productType).append("sourceSystem", sourceSystem).append("hasOpenBackendCounter", hasOpenBackendCounter).append("purpose", purpose).append("createDate", createDate).append("postIssuanceStatus", postIssuanceStatus).toString();
    }
}

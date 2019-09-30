package ResponseObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class UserDetails {
    @SerializedName("firstName")
    @Expose
    @Getter
    @Setter
    private String firstName;

    @SerializedName("userId")
    @Expose
    @Getter
    @Setter
    private Integer userId;

    @SerializedName("userUuid")
    @Expose
    @Getter
    @Setter
    private String userUuid;

    @SerializedName("loanApplications")
    @Expose
    @Getter
    @Setter
    private List<Object> loanApplications = null;

    @SerializedName("loansInReview")
    @Expose
    @Getter
    @Setter
    private List<LoanDetails> loansInReview = null;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("firstName", firstName).append("userId", userId).append("userUuid", userUuid).append("loanApplications", loanApplications).append("loansInReview", loansInReview).toString();
    }

}

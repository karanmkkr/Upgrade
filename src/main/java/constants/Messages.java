package constants;

public class Messages {
    public final static String UNSUPPORTED_CONTENT_TYPE = "Unexpected server exception of type HttpMediaTypeNotSupportedException";
    public final static String MISSING_SOURCE_ID = "operation=SECURITY_ALERT,type=missing csrf,valueType=X-CF-SOURCE-ID,method=POST,path=/api/brportorch/v2/login";
    public final static String MISSING_CORR_ID = "operation=SECURITY_ALERT,type=missing csrf,valueType=X-CF-CORR-ID,method=POST,path=/api/brportorch/v2/login";
}
